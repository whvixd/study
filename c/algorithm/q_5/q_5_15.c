#include <stdio.h>
#include <math.h>

// Q:用二分法求下面方程在(-10,10)之间的根：
// 2x^3-4x^2+3x-6=0
float get(float x);
int main()
{
    float m,l,r;
    l=-10;
    r=10;

    float f,f1,f2;
    f1=get(l);// -2436
    f2=get(r);// 1624
    printf("f1:%5.2f\n",f1);
    printf("f2:%5.2f\n",f2);
    do{
        m=(l+r)/2;
        f=get(m);//-6
        if(f*f1<0){
            r=m;
            f2=f;
        }else{
            l=m; // 右移
            f1=f;
        }

    }while(fabs(f)>=1e-5);
    printf("m:%5.2f\n",m);
    return 0;
}

float get(float x)
{
    return ((2*x-4)*x+3)*x-6;
}