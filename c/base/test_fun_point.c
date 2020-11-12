#include <stdio.h>
#include <stdlib.h>

#define ARRAY_SIZE 10

int get_random()
{
    return rand();
}

void set_array(int *array,int array_size,int (*get_ele)(void))
{
    for(int i=0;i<array_size;i++){
        array[i]=get_ele();
    }
}

int equal(int a,int b)
{
    return a==b;
}

/*
函数指针是指向函数的指针变量。

通常我们说的指针变量是指向一个整型、字符型或数组等变量，而函数指针是指向函数。

函数指针可以像一般函数一样，用于调用函数、传递参数。

函数指针变量的声明：

typedef int (*fun_ptr)(int,int); // 声明一个指向同样参数、返回值的函数指针类型
*/

int d;
int main()
{
    //////////// 函数指针 ////////////
    int (*fun_equal)(int,int)=&equal;// &可省略
    int r=fun_equal(fun_equal(1,1),2);
    printf("%d\n",r);

    int array[ARRAY_SIZE];
    printf("before array[0]:%d\n",array[0]);
    set_array(array,ARRAY_SIZE,get_random);
    printf("after array[0]:%d\n",array[0]);

    ////////////默认值////////////
    // 局部变量，随机值，数组也是 //
    // 静态变量和全局变量默认值为0//
    int b;
    static int c;
    int e[ARRAY_SIZE];
    printf("b:%d\n",b);
    printf("static c:%d\n",c);
    printf("e[0]:%d\n",e[0]);

    return 0;
}