#include <stdio.h>

// Q:用迭代法求x=根号a,求平方根的迭代公式为 x2=1/2 (x1+a/x1)
// 要求前后两次求出的x的差绝对值小于10^-5
int main()
{
    float a,x1,x2;
    printf("输出一个数字:");
    scanf("%f",&a);

    x0=a/2;
    x1=(x0+a/x0)/2;
    do{
        x0=x1;
        x1=(x0+a/x0)/2;
    }while(fabs(x0-x1)>=le-5);
    return 0;
}