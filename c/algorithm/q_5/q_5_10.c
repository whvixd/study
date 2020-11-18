#include <stdio.h>

// 2,1 -> 3/2
float single(int a,int b){
    float denominator=a+b;
    float divisor=a;
    return denominator/divisor;
}


// Q:求出分数序列:2/1,3/2,5/3,8/5,13/8,21/13..
// 求出前20项之和
int main()
{
    int denominator,divisor,temp;
    float sum,next;
    sum=0.0,next=0.0,denominator=2,divisor=1;
    for(int i=0;i<20;i++){
        next=single(denominator,divisor);
        printf("next:%f\n",next);
        sum+=next;
        temp=denominator;
        denominator+=divisor;
        divisor=temp;
        printf("denominator:%d,divisor:%d\n",denominator,divisor);
    }
    printf("sum:%f\n",sum);
    return 0;
}