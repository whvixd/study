#include <stdio.h>


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
int main()
{
    // 函数指针
    int (*fun_equal)(int,int)=&equal;// &可省略
    int r=fun_equal(fun_equal(1,1),2);
    printf("%d\n",r);

}