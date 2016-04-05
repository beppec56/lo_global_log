#!/bin/bash
#this grab the repository revision, for user display in about dialog
GIT_REV=$(git branch |grep "*" | sed 's/* //g')$(echo :$(git log -n1 --format=%h))

echo $GIT_REV


