#include <stdio.h>
#include <stdlib.h>

/*
/////////// void 作用 ///////////
1. 对函数返回的限定，如 void fun();
2. 对函数的入参限定，如 int fun(void);

/////////// void * 作用 ///////////
1. void * 可以指向任意类型的数据，可以对void * 赋值;相反，将void * 给其他类型赋值，需要强转

*/
int main(void)
{
    int *a;
    void *p;
    p=a;
    a=(int *)p;

    long int_size=sizeof(int);
    printf("int_size:%ld\n",int_size);
    void *p_m=malloc(int_size);// 分配所需的内存空间，并返回一个指向它的指针。
    a=(int *)p_m;

    if(NULL!=a){
        printf("a:%d\n",*a);
    }

    int b=10;
    a=&b;
    printf("a:%d\n",*a);
    free(p_m);
}