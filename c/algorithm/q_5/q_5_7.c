#include <stdio.h>

int sigma_fun1()
{
    int sum=0;
    for(int i=0;i<=100;i++){
        sum+=i;
    }
    return sum;
}

int sigma_fun2()
{
    int sum;
    for(int i=1;i<=50;i++){
        sum+=(i*i);
    }
    return sum;
}

float sigma_fun3()
{
    float sum=0;
    for(float i=1;i<=10;i++){
        sum+=(1/i);
    }
    return sum;
}

// Q:sigma(k=1,100)k+sigma(k=1,50)k^2+sigma(k=1,10)1/k
int main()
{
    float sigma1,sigma2,sigma3;
    sigma1=sigma_fun1();
    sigma2=sigma_fun2();
    sigma3=sigma_fun3();
    printf("sigma1:%f\n",sigma1);
    printf("sigma2:%f\n",sigma2);
    printf("sigma3:%f\n",sigma3);
    printf("sum:%f\n",sigma1+sigma2+sigma3);

    return 0;
}