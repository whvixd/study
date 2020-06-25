#!/usr/bin/env bash

commit_log=$1

# 输入是否为空判断
if [ ! -n "$commit_log" ]; then
    echo "NULL"
else
    echo "NOT NULL"
fi