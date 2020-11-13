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

#define SERVER_PORT 6666
#define BUFF_SIZE 1024
#define TRUE 1

/*
监听后，一直处于accept阻塞状态，
直到有客户端连接，
当客户端如数quit后，断开与客户端的连接

//////////////////////
套接字的数据结构：C语言进行套接字编程时，常会使用到sockaddr数据类型和sockaddr_in数据类型，用于保存套接字信息。
struct sockaddr {
    //地址族，2字节
    unsigned short sa_family;

    //存放地址和端口，14字节
    char sa_data[14];
}


struct sockaddr_in {
    //地址族
    short int sin_family;

    //端口号(使用网络字节序)
    unsigned short int sin_port;

    //地址
    struct in_addr sin_addr;

    //8字节数组，全为0，该字节数组的作用只是为了让两种数据结构大小相同而保留的空字节
    unsigned char sin_zero[8]
}
*/
int main() {

    //调用socket函数返回的文件描述符,/*socket句柄和建立连接后的句柄*/
    int sockfd,new_fd;
    //声明两个套接字sockaddr_in结构体变量，分别表示客户端和服务器
    struct sockaddr_in server_addr; /*本方地址信息结构体，下面有具体的属性赋值*/
    struct sockaddr_in client_addr;/*对方地址信息*/
    int addr_len = sizeof(client_addr);
    char buffer[BUFF_SIZE];
    int iDataNum;

    //socket函数，失败返回-1
    //int socket(int domain, int type, int protocol);
    //第一个参数表示使用的地址类型，一般都是ipv4，AF_INET
    //第二个参数表示套接字类型：tcp：面向连接的稳定数据传输SOCK_STREAM
    //第三个参数设置为0
    if ((sockfd = socket(AF_INET, SOCK_STREAM, 0)) < 0) { //建立socket
        perror("socket");
        return 1;
    }

    bzero(&server_addr, sizeof(server_addr));

    //初始化服务器端的套接字，并用htons和htonl将端口和地址转成网络字节序
    server_addr.sin_family = AF_INET;/*该属性表示接收本机或其他机器传输*/
    server_addr.sin_port = htons(SERVER_PORT);/*端口号*/
    //ip可是是本服务器的ip，也可以用宏INADDR_ANY代替，代表0.0.0.0，表明所有地址
    server_addr.sin_addr.s_addr = htonl(INADDR_ANY);/*IP，括号内容表示本机IP*/

    //对于bind，accept之类的函数，里面套接字参数都是需要强制转换成(struct sockaddr *)
    //bind三个参数：服务器端的套接字的文件描述符，
    if (bind(sockfd, (struct sockaddr *) &server_addr, sizeof(server_addr)) < 0) {
        perror("connect");
        return 1;
    }

    if (listen(sockfd, 5) < 0) {//开启监听 ，第二个参数是最大监听数
        perror("listen");
        return 1;
    }

    while (TRUE) {

        printf("监听端口: %d\n", SERVER_PORT);

        //调用accept函数后，会进入阻塞状态
        //accept返回一个套接字的文件描述符，这样服务器端便有两个套接字的文件描述符，
        //sockfd和new_fd。
        //sockfd仍然继续在监听状态，new_fd则负责接收和发送数据
        //client_addr是一个传出参数，accept返回时，传出客户端的地址和端口号
        //addr_len是一个传入-传出参数，传入的是调用者提供的缓冲区的client_addr的长度，以避免缓冲区溢出。
        //传出的是客户端地址结构体的实际长度。
        //出错返回-1
        new_fd = accept(sockfd, (struct sockaddr *) &client_addr, (socklen_t *) &addr_len);//在这里阻塞知道接收到消息，参数分别是socket句柄，接收到的地址信息以及大小
        if (new_fd < 0) {
            perror("accept");
            continue;

        }

        printf("等待消息...\n");
        //inet_ntoa ip地址转换函数，将网络字节序IP转换为点分十进制IP
        //表达式：char *inet_ntoa (struct in_addr);
        printf("IP is %s\n", inet_ntoa(client_addr.sin_addr));
        printf("Port is %d\n", htons(client_addr.sin_port));

        while (TRUE) {
            printf("读取消息:");
            buffer[0] = '\0';
            iDataNum = recv(new_fd, buffer, 1024, 0);
            if (iDataNum < 0) {
                perror("recv null");
                continue;
            }

            buffer[iDataNum] = '\0';
            if (strcmp(buffer, "quit") == 0)
                break;
            printf("%s\n", buffer);
            printf("发送消息:");

            scanf("%s", buffer);
            printf("\n");
            send(new_fd, buffer, strlen(buffer), 0);

            if (strcmp(buffer, "quit") == 0)
                break;
        }

    }

    close(sockfd);
    return 0;
}