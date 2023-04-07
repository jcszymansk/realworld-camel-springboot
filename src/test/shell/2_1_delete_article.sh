#!/bin/sh

: ${SLUG:=article-2137}

: ${TOKEN:=Token abcde}

: ${APIURL:=http://localhost:8080/api}


curl -i -X DELETE \
  -H "Authorization: ${TOKEN}" \
  ${APIURL}/articles/${SLUG}
