#include <stdio.h>

// a:2,n:4
// 2222
int gen_number(int a,int n)
{
    int sum=0;
    for(int i=0;i<n;i++){
        sum+=a;
        a*=10;
    }
    return sum;
}

int for_add(int a,int n)
{
    int sum;
    for(int i=1;i<=n;i++){
        sum+=gen_number(a,i);
    }
    return sum;
}

// 求 Sn=a+aa+aaa+...+aa..aa之值，输入n
int main()
{
    int sum=for_add(2,4);
    printf("sum:%d\n",sum);
    return 0;
}