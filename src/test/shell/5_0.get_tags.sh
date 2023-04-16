#!/bin/sh

: "${APIURL:=http://localhost:8080/api}"


curl -i -X GET \
  ${APIURL}/tags
