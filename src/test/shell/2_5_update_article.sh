#!/bin/sh

: ${ARTTITLE:=How to train your dragon 2}
: ${ARTBODY:=Very carefully.}
: ${SLUG:=how-to-train-your-dragon-14}
ARTJSON='{"article":{"title":"'${ARTTITLE}'","description":"'${ARTDESC}'","body":"'${ARTBODY}'"}}'

: ${TOKEN:=Token eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhYmNkZSJ9.iFBORfKYZ5YTir5cMVYMjEZ8sveV-Vg-lW6SShWkCLI}

: ${APIURL:=http://localhost:8080/api}


curl -i -X PUT \
  -H "Content-Type: application/json" \
  -H "Authorization: ${TOKEN}" \
  -d "${ARTJSON}" \
  ${APIURL}/articles/${SLUG}
