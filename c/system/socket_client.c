#include <sys/stat.h>
#include <fcntl.h>
#include <errno.h>
#include <netdb.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>

#define SERVER_IP "127.0.0.1"//服务器IP
#define SERVER_PORT 6666//服务器端口号
#define TRUE 1
#define BUFF_SIZE 1024

/*
连接到服务器后，会不停循环，等待输入，
输入quit后，断开与服务器的连接
*/
int main() {

    //客户端只需要一个套接字文件描述符，用于和服务器通信
    int sockfd;

    //描述服务器的socket
    struct sockaddr_in server_addr;
    char sendbuf[BUFF_SIZE];
    char recvbuf[BUFF_SIZE];//储存接收数据
    int iDataNum;

    if ((sockfd = socket(AF_INET, SOCK_STREAM, 0)) < 0) {/*建立socket*/
        perror("socket");
        return 1;
    }

    server_addr.sin_family = AF_INET;
    server_addr.sin_port = htons(SERVER_PORT);

    //指定服务器端的ip，本地测试：127.0.0.1
    //inet_addr()函数，将点分十进制IP转换成网络字节序IP
    server_addr.sin_addr.s_addr = inet_addr(SERVER_IP);
    if (connect(sockfd, (struct sockaddr *) &server_addr, sizeof(server_addr)) < 0) {//连接方法，传入句柄，目标地址和大小
        perror("connect");
        return 1;

    }

    printf("连接到主机...\n");
    while (TRUE) {
        printf("发送消息:");
        scanf("%s", sendbuf);
        printf("\n");
        send(sockfd, sendbuf, strlen(sendbuf), 0);
        if (strcmp(sendbuf, "quit") == 0)
            break;

///////////读取消息////////////
        printf("读取消息:");
        recvbuf[0] = '\0';
        iDataNum = recv(sockfd, recvbuf, BUFF_SIZE, 0);//将接收数据打入buf，参数分别是句柄，储存处，最大长度，其他信息（设为0即可）。 
        recvbuf[iDataNum] = '\0';
        printf("%s\n", recvbuf);

    }

    close(sockfd);
    return 0;

}
