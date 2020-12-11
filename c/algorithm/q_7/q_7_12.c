#include <stdio.h>
#include <math.h>

// Q:用牛顿迭代法求根，方程ax^3+bx^2+cx+d=0,系数a,b,c,d的值依次为1，2，3，4，由主函数输入，求x在1附近的一个实根，求出根后由主函数输出。
int main(){
    float get_root(int a,int b,int c,int d);
    int a,b,c,d;
    printf("input a,b,c,d:");
    scanf("%d,%d,%d,%d",&a,&b,&c,&d);
    float root=get_root(a,b,c,d);
    printf("root:%10.7f\n",root);
    return 1;
}

float get_root(int a,int b,int c,int d){
    float x1=1,x,f,f1;
    do{
        x=x1;
        f=a*x*x*x+b*x*x+c*x+d;// 原函数,f(x)=ax^3+bx^2+cx+d
        f1=3*a*x*x+2*b*x+c;   // 导数,  f'(x)=3ax^2+2bx+c
        x1=x-f/f1;            // 差
    }while(fabs(x1-x)>=1e-3); // |x-x'|>=0.001 继续求值
    return x;
}