# include <stdio.h>

// 先声明
int getResult(int x);
int main()
{
    int x;
    scanf("%d",&x);
    int b = getResult(x);
    printf("result:%d \n",b);
    return 0;
}

int getResult(int x)
{
    if(x<1)
    {
        return x;
    }else if(1<=x&&x<10)
    {
        return 2*x-1;
    }else if(x>=10)
    {
        return 3*x-11;
    }
    return 0;
}