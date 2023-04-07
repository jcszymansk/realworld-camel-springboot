#!/bin/sh

: ${USERNAME:=abcde}

: ${TOKEN:=Token abcde}

: ${APIURL:=http://localhost:8080/api}


curl -i -X GET \
  -H "Authorization: ${TOKEN}" \
  ${APIURL}/profiles/${USERNAME}
