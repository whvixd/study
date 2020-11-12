# include <stdio.h>
# include <string.h>
# include <stdlib.h>

// typedef 给变量添加别名,可以添加多个别名
// typedef type type_alias1,*type_alias2
typedef struct Student
{
    long id;
    char name[10];
    int age;
}Stu;

typedef int Integer,Number;

// 全局的结构体变量
struct Teacher
{
    long id;
    char name[10];
//    char subject[10];
//    long stu_ids[50];
} tea3={3l,"tea Z"};

/////////////位域///////////////
struct Data{
    unsigned a:4;//a占四位 2^3+2^2+2^1+2^0 [0,15]
    unsigned  :4;//空域，该四位不存值
    unsigned b:1;//b一位
    unsigned c:7;//c七位
} data,*p_data;

int main()
{
    Stu stu1;
    strcpy(stu1.name,"tom");
    stu1.id=123;
    stu1.age=20;

    printf("%ld %s %d\n",stu1.id,stu1.name,stu1.age);

    Stu stu2={2,"Jam",21};
    printf("%ld %s %d\n",stu2.id,stu2.name,stu2.age);

    // stu2 是对象，访问成员变量用 .
    // *stu3 是指针，访问成员变量用 ->
    Stu *stu3=(Stu *)malloc(sizeof(Stu)); //malloc 函数返回的是 void * ，需要强转我们需要的类型
    (*stu3).id=3;
    strcpy(stu3->name,"Jack");
    stu3->age=24;
    printf("%ld %s %d\n",stu3->id,(*stu3).name,stu3->age);

    Stu stu;
    Stu *stu5=&stu;
    (*stu5).id=4;
    strcpy(stu5->name,"BigJ");
    stu5->age=25;
    printf("%ld %s %d\n",(*stu5).id,(*stu5).name,(*stu5).age);

    struct Teacher tea1={1l,"tea wang"};
    printf("tea1.id:%ld,tea1.name:%s\n",tea1.id,tea1.name);
    struct Teacher *tea2=NULL;
    printf("tea2:%p\n",tea2);
    printf("tea3.id:%ld,tea3.name:%s\n",tea3.id,tea3.name);

    printf("/////////局部结构体/////////\n");
    struct Book
    {
        long id;
        char name[10];
    } bo={1l,"Java"};
    printf("bo.id:%ld,bo.name:%s\n",bo.id,bo.name);
    printf("//////////////////\n");

    Integer int1=3;
    printf("Integer int1:%d\n",int1);
    Number num=4;
    printf("Number num:%d\n",num);

    data.a=15;
    data.b=1;
    data.c='c';
    printf("data.a:%d,data.b:%d,data.c:%c\n",data.a,data.b,data.c);

    p_data=&data;
    p_data->a=2;
    printf("p_data->a:%d\n",p_data->a);


}