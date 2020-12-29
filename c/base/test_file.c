#include <stdio.h>
#include <string.h>
#define BUFF_SIZE 1024
/*
访问模式
r:打开一个已有的文本文件，【允许读文件】
w:打开一个文本文件，若没有，则新建；从头写入【允许写文件+新建】
a:r+w基本上，不会重头写，而是追加到现在内容后面【允许写文件+新建】
r+:打开文本文件，允许读写【允许读写文件】
w+:打开文本文件，允许读写。文件不存在，则新建，重头写【允许读写文件+新建】
a+:打开文本文件，允许读写。文件不存在，则新建，追加写【允许读写文件+新建】
*/
int main(void)
{
    FILE *fp=NULL;
    char file_name[100];
    int r=1;

    strcpy(file_name,"/Users/xx/Documents/workspace/idea/study/c/debug/tmp.txt");
    // 打开文件
    fp=fopen(file_name,"w+");
    // 写入字符串
    r&=fputs("write file \n",fp);
    // 写入字符
    r&=fputc('c',fp);
    r&=fputc('\n',fp);

    if(EOF==r){
        printf("操作文件异常,文件名：%s\n",file_name);
    }
    // 关闭文件
    fclose(fp);

    // 打开文件
    fp=fopen(file_name,"r+");

    char buff[BUFF_SIZE];
    int len;
    // 将字符读取入到buff中，如果满了或遇到换行，输出buff，直到文件读完
    while((fgets(buff,BUFF_SIZE,(FILE *)fp))!=NULL){
        len=strlen(buff);
        printf("%s",buff);
    }

    // 关闭文件
    fclose(fp);
}