#!/bin/sh

: ${EMAIL:=abcde@abcde.com}
: ${PASSWORD:=password}

: ${APIURL:=http://localhost:8080/api}

UJSON='{"user":{"email":"'${EMAIL}'","password":"'${PASSWORD}'"}}'

echo "UJSON: ${UJSON}"

curl -i -X POST \
  -H "Content-Type: application/json" \
  -d "${UJSON}" \
  ${APIURL}/users/login
