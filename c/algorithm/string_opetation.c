# include <stdio.h>

void putstr(char *str){
    if(str==NULL||*str=='\0'){
        return;
    }

    do{
        printf("%c",*str);
    }while(*str++!='\0');

}

char *strcat(char *dst,const char *src)
{
    // 第一个地址
    char *ret=dst;
    // dst 移动到最后一个地址
    while(*dst++ != '\0');
    --dst;
    while((*dst++ = *src++)!='\0');
    return ret;

}



int main()
{
    char dst[]="abc";
    char src[]={"efg"};
    char *a="123";
    strcat(dst,a);
    putstr(dst);
    printf("\n%s\n",a);
    printf("\n%c\n",*a);

    // %o 数组的起始地址
    printf("\n%o\n",dst);
    return 1;
}