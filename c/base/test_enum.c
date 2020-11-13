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

    day=D2;
    switch(day)
    {
        case D1:
            printf("select D1\n");
            break;
        case D2:
            printf("select D2\n");
            break;
        case D3:
            printf("select D3\n");
            break;
        default:
            printf("default");
    }
}