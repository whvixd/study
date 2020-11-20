#include <stdio.h>
#include <stdlib.h>
// 内存模型 https://blog.csdn.net/second60/article/details/79946310
/*
BSS段：存放未初始化的全局变量
数据段：存放已初始化的全局变量
代码段：存放执行代码的内存区域
堆：存放程序运行中动态分配的内存段，malloc/free
栈：存放局部变量，函数的参数值，返回值等。 `ulimit  -s` 查看栈大小，默认2M
*/
typedef struct NodeStruct{
    int value;
    struct NodeStruct *next;
}Node;

void test_point1(Node *node){
    Node p={4,NULL};
    *node=p;
}

// 相当于自己说明了个变量 node，不影响原实参地址
void test_point2(Node node){
    Node p={4,NULL};
    node=p;
}

// 传入指针，实现的地址
void test_point3(Node *node){
    Node *p=(Node *)malloc(sizeof(Node));
    p->value=4;
    *node=*p; // 堆中p对象赋值给堆中node对象
}

/*
type *var-name;// type数据类型

所有实际数据类型，不管是整型、浮点型、字符型，还是其他的数据类型，对应指针的值的类型都是一样的，都是一个代表内存地址的长的十六进制数。
不同数据类型的指针之间唯一的不同是，指针所指向的变量或常量的数据类型不同。

*/
void test_int(int *a);
int main()
{
    int a;
    printf("a init value:%d\n",a);
    int *ap;
    ap=&a;
    // %p 打印ap指向的内存地址
    printf("$a:%p \n",ap);

    a=10;
    printf("$a:%p \n",&a);

    printf("a:%d \n",*ap);

    printf("/////////空指针////////\n");
    // NULL：空指针 =.=! //
    // 0x0 内存0地址，系统保留地址，程序不可访问
    int *null_point=NULL;
    printf("NULL:%p\n",null_point);
    if(!null_point) printf("空\n");
    if(null_point) printf("非空\n\n");

    printf("/////////指针、地址、内容/////////\n");
    int x=3;
    int *px=&x;
    int **ppx=&px;
    printf("x:%d\n",x);//x内容 => 3
    printf("&x:%p\n\n",&x);//x地址

    printf("px:%p\n",px);//px内容 => x地址
    printf("&px:%p\n",&px);//px地址
    printf("*px:%d\n\n",*px);//px指向地址的内容 => x内容

    printf("ppx:%p\n",ppx);//ppx内容 => px地址
    printf("&ppx:%p\n",&ppx);//ppx地址
    printf("*ppx:%p\n",*ppx);//ppx指向地址的内容 => px内容 => x地址
    printf("**ppx:%d\n",**ppx);//ppx指向地址的内容指向的地址内容 =>x内容

    int e=1;
    test_int(&e);
    printf("e:%d\n",e);

    printf("/////////指针、地址、内容通过函数改变/////////\n");
    Node p={1,NULL};
    test_point2(p);
    printf("p.value:%d\n",p.value);

    test_point3(&p);
    printf("p.value:%d\n",p.value);

    Node *p2=(Node *)malloc(sizeof(Node));
    test_point1(p2);
    printf("p2->value:%d\n",p2->value);

    printf("/////////野指针/////////\n");
    /*
        refer:https://blog.csdn.net/liuchunjie11/article/details/80969689
        野指针由来：
          1、局部指针变量没有初始化

          2、指针所指向的变量在指针之前被销毁

          3、使用已经释放过的指针

          4、进行了错误指针运算

          5、进行了错误的强制类型转换
    */
}

// void test_int(int &a) 这种写法c++支持引用类型，c不支持，c可修改地址的方式实现
void test_int(int *a){
    int b=2;
    *a=b;
}