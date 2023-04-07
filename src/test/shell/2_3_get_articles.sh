#!/bin/sh

: ${TOKEN:=Token abcde}

: ${APIURL:=http://localhost:8080/api}

if [ ! -z "$AUTHOR" ]; then AUTHOR='author='${AUTHOR}; fi
if [ ! -z "${TAG}" ]; then TAG='tag='${TAG}; fi
if [ ! -z "${LIKER}" ]; then LIKER='favorited='${LIKER}; fi
if [ ! -z "${LIMIT}" ]; then LIMIT='limit='${LIMIT}; fi
if [ ! -z "${OFFSET}" ]; then OFFSET='offset='${OFFSET}; fi

QUERY=$(echo "${AUTHOR} ${TAG} ${LIKER} ${LIMIT} ${OFFSET}" \
  | sed -e 's/^ *//g' -e 's/ *$//g' -Ee 's/ +/\&/g')

echo ${QUERY}

curl -i -X GET \
  -H "Authorization: ${TOKEN}" \
  ${APIURL}/articles?"${QUERY}"
