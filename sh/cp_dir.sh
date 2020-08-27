#!/bin/bash

# -d 文件是否存在
if [[ ! -d /user/xx ]]
then
    mkdir -p /user/xx
    cp -R ~/Documents/xxx/xx /user/xx
    echo 'cp success'
fi
