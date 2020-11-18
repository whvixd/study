#include <stdio.h>

// Q:一个球从100m高度自由落下，每次落地后又跳回原高度的一半，
// 再落下，再反弹，球它在第10次落地时，共经过多少米，第10次反弹有多高
int main()
{
    double sum_distance,next;
    sum_distance=0,next=100;
    for(int i=0;i<20;i++){
        sum_distance+=next;
        next/=2;
    }
    printf("sum_distance:%f,high:%f\n",sum_distance,next);
    return 0;
}