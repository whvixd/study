#include <stdio.h>


int single_add(int n)
{
    int sum=1;
    for(int i=1;i<=n;i++){
        sum*=i;
    }
    return sum;
}

// Q:1!+2!+...+20!
int main()
{
    int sum=0;
    for(int i=1;i<=3;i++){
        sum+=single_add(i);
    }
    printf("sum:%d\n",sum);
}