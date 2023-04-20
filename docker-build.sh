#!/bin/sh

: "${PROJECT_NAME:=jacekszymanski/realworld-camel-springboot}"
: "${PROJECT_VERSION:=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)}"
: "${LATEST:=true}"
: "${IMAGE_TAGS:=${PROJECT_VERSION} $(if ${LATEST}; then echo latest; fi)}"
TAG_OPTS="$(for tag in ${IMAGE_TAGS}; do echo " -t ${PROJECT_NAME}:${tag}"; done)"

echo "Building image ${PROJECT_NAME} with tags ${IMAGE_TAGS}"
# shellcheck disable=SC2086
docker build ${TAG_OPTS} .
