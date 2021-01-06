#include <iostream>
#include <csignal>
#include <unistd.h>

using namespace std;

// 信号处理器
void signalHandler(int signum){
    cout<<"terminate signal("<<signum<<") received"<<endl;
    // 退出程序
    exit(signum);
}

int main(){
    // 注册信号SIGINT和信号处理程序
    signal(SIGINT,signalHandler);

    for(int i=0;1;i++){
        cout<<"sleep... "<<i<<endl;
        sleep(1);
        if(i==5){
            // 生成信号
            raise(SIGINT);
        }
    }
    return 0;
}