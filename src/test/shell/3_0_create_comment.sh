#!/bin/sh

: "${SLUG:=6}"
: "${CMTBODY:=Comment very carefully.}"

CMTJSON='{"comment":{"body":"'"${CMTBODY}"'"}}'

: "${TOKEN:=Token eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhYmNkZSJ9.iFBORfKYZ5YTir5cMVYMjEZ8sveV-Vg-lW6SShWkCLI}"

: "${APIURL:=http://localhost:8080/api}"


curl -i -X POST \
  -H "Content-Type: application/json" \
  -H "Authorization: ${TOKEN}" \
  -d "${CMTJSON}" \
  ${APIURL}/articles/${SLUG}/comments
