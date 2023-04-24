#!/bin/sh

# NOTE this script can only run on an empty database, it's slow as hell, and it's better to populate it
# from etc/conduit.sql.gz; also, although it runs on api.realworld.io, it's not guaranteed to work
# elsewhere, as it depends on the host allowing any limit on the number of articles returned at once.
# It wouldn't work on this project, as any limit greater than 100 would be ignored.


: "${DESTURL:=http://localhost:8080/api}"
: "${APIURL:=http://api.realworld.io/api}"

die() {
  exit 1
}

uecho() {
  printf '%s' "$1"
}

TMPDIR=$(mktemp -d)
echo "Using temporary directory ${TMPDIR}"

cd "${TMPDIR}" || die

# Articles
if [ ! -s "articles.json" ]; then
  echo "Fetching articles from ${APIURL}/articles"
  curl -s "${APIURL}/articles?limit=10000" > "articles.json" || die
fi

# Slugs
jq -r '.articles[].slug' "articles.json" | sort -u | while read -r slug; do
  echo "${slug}"
done > "slugs.txt"

# Users
jq -r '.articles[].author.username' "articles.json" | sort -u | while read -r user; do
  if [ -s "profile-${user}.json" ]; then
    continue
  fi
  echo "Fetching user ${user} from ${APIURL}/profiles/$(echo ${user} | sed -Ee 's/\\s/%20/g')"
  curl -s "${APIURL}/profiles/$(echo ${user} | sed -e 's/ /%20/g')" > "profile-${user}.json" || die
done

# Comments
while read -r article ; do
  articlefname=$(echo "${article}" | sha1sum | cut -d' ' -f1)
  if [ -s "comments-${articlefname}.json" ]; then
    continue
  fi
  echo "Fetching comments for article ${article} from ${APIURL}/articles/${article}/comments"
  curl -s "${APIURL}/articles/${article}/comments" > "comments-${articlefname}.json" || die
done < "slugs.txt"

# Comment authors
jq -r '.comments[].author.username' comments-*.json | sort -u | while read -r user ; do
  if [ -s "profile-${user}.json" ]; then
    continue
  fi
  echo "Fetching user ${user} from ${APIURL}/profiles/$(echo ${user} | sed -Ee 's/\\s/%20/g')"
  curl -s "${APIURL}/profiles/$(echo ${user} | sed -e 's/ /%20/g')" > "profile-${user}.json" || die
done


# Create users, save tokens to files
for profile in profile-*.json; do
  user=$(jq -r '.profile.username' "${profile}")
  if [ -s "user-${user}.json" ]; then
    continue
  fi
  email="$(echo ${user} | sha1sum | cut -d' ' -f1)@example.com"
  password="$(echo ${user}password | sha1sum | cut -d' ' -f1)"
  json=$(jq -r '{"user": {"email": "'"${email}"'", "password": "'"${password}"'", "username": .profile.username}}' "${profile}")

  echo "Creating user ${user} with ${json}"
  curl -s -X POST -H "Content-Type: application/json" -d "${json}" "${DESTURL}/users" > "user-${user}.json" || die
done

# Split articles to files
jq -c -r '.articles[]' "articles.json" | while read -r article; do
  slug=$(uecho "${article}" | jq -r '.slug')
  if [ -z "${slug}" ]; then
    echo ERROR "${article}"
  fi
  articlefname=$(echo "${slug}" | sha1sum | cut -d' ' -f1)

  if [ -s "article-${articlefname}.json" ]; then
    continue
  fi

  echo "Saving article ${slug} to article-${articlefname}.json"

  uecho "${article}" > "article-${articlefname}.json"
done

# Create articles
for art in article-*.json; do
  if [ -s "created-${art}" ]; then
    continue
  fi
  json=$(jq -r '{"article": {"title": .title, "description": .description, "body": .body, "tagList": .tagList}}' "${art}")
  username=$(jq -r '.author.username' "${art}")
  token=$(jq -r '.user.token' "user-${username}.json")

  echo "Creating article ${art} with ${json}"
  curl -s -H "Authorization: Token ${token}" -X POST -H "Content-Type: application/json" -d "${json}" "${DESTURL}/articles" > "created-${art}" || die
done

# Split comments to files
echo "Splitting comments to files"
for com in comments-*.json; do
  echo Processing "${com}"
  jq -c -r '.comments[]' "${com}" | while read -r comment; do
    articlefname=$(echo "${com}" | sed -e 's/^comments-//' -e 's/\.json$//')
    cffilename="comment-${articlefname}-$(echo "${comment}" | jq -r '.id').json"
    if [ -s "${cffilename}" ]; then
      continue
    fi
    uecho "${comment}" > "${cffilename}"
  done
done

# Create comments
for com in comment-*-*.json; do
  cmname=$(echo "${com}" | sed -e 's/^comment-//' -e 's/-[0-9]*\.json$//')
  token=$(jq -r '.user.token' "user-$(jq -r '.author.username' "${com}").json")
  json=$(jq -r '{"comment": {"body": .body}}' "${com}")
  slug=$(jq -r '.article.slug' "created-article-${cmname}.json")


  echo "Creating comment ${com}"
  curl -s -H "Authorization: Token ${token}" -X POST -H "Content-Type: application/json" \
      -d "${json}" \
      "${DESTURL}/articles/${slug}/comments" > "created-com-${com}" || die
done
