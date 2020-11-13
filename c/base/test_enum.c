#include <stdio.h>

enum Day
{
    D1='c',
    D2=2,
    D3=1000l
} day;

int main(void)
{
    printf("D1:%c\n",D1);
    printf("D2:%d\n",D2);
    printf("D3:%dl\n",D3);
}