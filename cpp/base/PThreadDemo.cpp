#include <iostream>
#include <pthread.h>

using namespace std;

#define THREAD_NUM 5

void* exec(void* args){
    cout<<"exec"<<endl;
    return 0;
}

int main(){
    // 定义多线程
    pthread_t tids[THREAD_NUM];
    for(int i=0;i<THREAD_NUM;i++){
        // 创建线程id，线程参数，调用函数，传入函数的参数
        int r=pthread_create(&tids[i],NULL,exec,NULL);
        if(r!=0){
            cout<<"pthread_create error:error_code="<<r<<endl;
        }
    }
    // 等多线程退出后，进程才结束，若进程结束了，线程可能还没反应过来
    pthread_exit(NULL);
    return 0;
}
// g++ PThreadDemo.cpp -lpthread -o ./debug/PThreadDemo 使用 -lpthread库编译