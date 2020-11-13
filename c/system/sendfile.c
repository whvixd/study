#include <stdio.h>
#include <sys/socket.h>

int main()
{
    // ssize_t sendfile(int out_fd, int in_fd, off_t *offset, size_t count);
    FILE *p_in=fopen("/Users/didi/Documents/workspace/idea/study/c/debug/in.txt","r+");
    FILE *p_out=fopen("/Users/didi/Documents/workspace/idea/study/c/debug/out.txt","w+");

    int size=sendfile(p_out,p_in,1024,1024);
    printf("size:%d",size);
    return 0;
}