#!/bin/sh

: ${USERNAME:=abcde}
: ${EMAIL:=abcde@abcde.com}
: ${PASSWORD:=password}
: ${IMAGE:=https://static.productionready.io/images/smiley-cyrus.jpg}
: ${BIO:=I work at statefarm}

UJSON='{"user":{"username":"'${USERNAME}'","email":"'${EMAIL}'","password":"'${PASSWORD}'"}}'

echo "UJSON: ${UJSON}"

curl -i -X POST \
  -H "Content-Type: application/json" \
  -d "${UJSON}" \
  http://localhost:8080/api/users
