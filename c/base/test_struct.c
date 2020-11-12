# include <stdio.h>
# include <string.h>
# include <stdlib.h>

typedef struct Student
{
    long id;
    char name[10];
    int age;
}Stu;

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
    Stu *stu3=(Stu *)malloc(sizeof(Stu));
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


}