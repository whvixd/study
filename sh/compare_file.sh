#!/usr/bin/env bash

# 比较两个文件是否有不同行
# compare_file.sh base.txt compare.txt
# base.txt 为基础文件，compare.txt是比较的文件，与base文件对比，看compare是否有不同的行

path=`pwd`

file1_name=$1

file2_name=$2

file1_path=${path}/${file1_name}
file2_path=${path}/${file2_name}

if [[ ! -f ${file1_path} ]]
then
    echo "${file1_path} does not exist"
    exit
fi

if [[ ! -f ${file2_path} ]]
then
    echo "${file2_path} does not exist"
    exit
fi

for line in `cat ${file2_path}`
do
  filter=`grep $line $file1_path`
  if  [[ -z $filter ]]
  then
       echo "${line}"
  fi
done