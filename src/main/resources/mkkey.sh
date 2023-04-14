#!/bin/sh

head -c 64 /dev/urandom | base64 > jwt.key.txt
