#!/bin/sh

: ${USERNAME:=abcde}

: ${TOKEN:=Token eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhYmNkZSJ9.iFBORfKYZ5YTir5cMVYMjEZ8sveV-Vg-lW6SShWkCLI}

: ${APIURL:=http://localhost:8080/api}


curl -i -X GET \
  -H "Authorization: ${TOKEN}" \
  ${APIURL}/profiles/${USERNAME}