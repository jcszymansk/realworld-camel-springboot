#!/bin/sh

: ${TOKEN:=Token abcde}

: ${APIURL:=http://localhost:8080/api}

if [ ! -z "${LIMIT}" ]; then LIMIT='limit='${LIMIT}; fi
if [ ! -z "${OFFSET}" ]; then OFFSET='offset='${OFFSET}; fi

QUERY=$(echo "${AUTHOR} ${TAG} ${LIKER} ${LIMIT} ${OFFSET}" \
  | sed -e 's/^ *//g' -e 's/ *$//g' -Ee 's/ +/\&/g')

echo ${QUERY}

curl -i -X GET \
  -H "Authorization: ${TOKEN}" \
  ${APIURL}/articles/feed?"${QUERY}"
