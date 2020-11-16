#include <stdio.h>

int main()
{
    int a=3,b=4,c=5;
    printf("a+b>c&&b==c:%d\n",a+b>c&&b==c);
    printf("a||b+c&&b-c:%d\n",a||(b+c&&b-c));//3||9&&-1 = 1

}