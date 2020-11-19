#include <stdio.h>
#include <math.h>

// Q:用牛顿迭代法求下面方程在1.5附近的根
// 2x^3-4x^2+3x-6=0

/*
tip:牛顿迭代法：设r为f(x)=0的根，选取x0为r的初始近似值，过点(x0,f(x0))做曲线y=f(x)切线L，
 L=f(x0)+f'(x0)(x-x0)，则与x轴交点的横坐标x1=x0-f(x0)/f'(x0)，称x1为r的一次近似值。
 过点(x1,f(x1))做曲线y=f(x)切线，与上述步骤一致，交x轴x2=x1-f(x1)/f'(x1)，称x2为r的二次近似值
 类推x(n+1)=xn-f(xn)/f'(xn)，x(n+1)称r的n+1次近似值
 */
void get(void);
int main()
{
    double f,f1,x0,x1;
    x1=1.5;
    do{
        x0=x1;
        f=((2*x0-4)*x0+3)*x0-6;
        f1=(6*x0-4)*x0+3;
        x1=x0-f/f1;
        printf("x0:%f,x1:%f\n",x0,x1);
    }while(fabs(x1-x0)>=1e-5);
    printf("Q:f(x)=2x^3-4x^2+3x-6,求f(x)=0 的根？\nA:x0=%5.2f\n",x1);
    return 0;
}

void get(void){
    double x1,x0,f,f1;
    x1=0.5;
    do{
        x0=x1;
        f=2*x0*x0*(2*x0+1)-6;
        f1=4*x0*(3*x0+1);
        x1=x0-f/f1;
    }while(fabs(x1-x0)>=1e-5);
    printf("The root of equation is %5.2f\n",x1);
}