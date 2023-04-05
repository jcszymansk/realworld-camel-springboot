#!/bin/sh

: ${ARTTITLE:=How to train your dragon}
: ${ARTDESC:=Ever wonder how?}
: ${ARTBODY:=Very carefully.}
: ${ARTTAGS:=\"training\",\"dragons\"}
ARTJSON='{"article":{"title":"'${ARTTITLE}'","description":"'${ARTDESC}'","body":"'${ARTBODY}'","tagList":['${ARTTAGS}']}}'

: ${TOKEN:=Token abcde}


curl -i -X POST \
  -H "Content-Type: application/json" \
  -H "Authorization: ${TOKEN}" \
  -d "${ARTJSON}" \
  http://localhost:8080/api/articles
