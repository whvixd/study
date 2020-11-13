#include <stdio.h>
#include <string.h>

/*
共用体，是一种特殊的数据类型，允许在相同的内存位置存储不桶的数据类型
*/
typedef union Data
{
    int i;
    float f;
    char str[10];
}D;

int main(void)
{
    D d;
    d.i=10;
    printf("d.i:%d\n",d.i);

    d.f=1.1f;
    printf("d.f:%f\n",d.f);

    strcpy(d.str,"abc");
    printf("d.str:%s\n",d.str);

    // i输出错乱，因为d.str占用了i的内存位置
    d.i=100;
    strcpy(d.str,"123");
    printf("d.i:%d,d.str:%s\n",d.i,d.str);
}