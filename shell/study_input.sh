#!/usr/bin/env bash

commit_log=$1

# 输入是否为空判断
if [ ! -n "$commit_log" ]; then
    commit_log="default"
    echo "NULL"
else
    echo "NOT NULL"
fi

echo ${commit_log}