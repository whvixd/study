#include <stdio.h>

// ./test_main_arg 1 2 3
// 执行，argv[0] 为文件名称
// argc:参数数量，argv具体的传入的值
int main(int argc,char *argv[])
{
    int i;
    for(i=0;i<argc;i++){
        printf("arg[%d]:%s\n",i,argv[i]);
    }
}