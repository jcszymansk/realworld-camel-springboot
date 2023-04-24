#!/bin/sh

: "${PROJECT_NAME:=jszymanski/realworld-camel-springboot}"
: "${PROJECT_VERSION:=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)}"
: "${LATEST:=true}"
: "${IMAGE_TAGS:=${PROJECT_VERSION} $(if ${LATEST}; then echo latest; fi)}"
TAG_OPTS="$(for tag in ${IMAGE_TAGS}; do echo " -t ${PROJECT_NAME}:${tag}"; done)"
PACKAGE_FILE=target/$(mvn help:evaluate -Dexpression=project.build.finalName -q -DforceStdout).jar

if [ ! -s "${PACKAGE_FILE}" ]; then
  echo "Package file ${PACKAGE_FILE} not found. Run 'mvn package' first."
  exit 1
fi

echo "Building image ${PROJECT_NAME} with tags ${IMAGE_TAGS}"
# shellcheck disable=SC2086
docker build ${TAG_OPTS} --build-arg JAR_FILE=${PACKAGE_FILE} .
