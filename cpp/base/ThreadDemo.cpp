#include <iostream>
#include <mutex>// 互斥
#include <thread>
#include <time.h>
//#include <windows.h>
#include <cstdio>

std::mutex mu;

int count=0;

void thread01(){
    for(int i=0;i<50;i++){
        mu.lock();// 加锁
        count+=i;
        std::cout<<"thread01:"<<count<<std::endl;
        mu.unlock();// 解锁
    }
}

void thread02(){
    for(int i=50;i<100;i++){
        mu.lock();
        count+=i;
        std::cout<<"thread02:"<<count<<std::endl;
        mu.unlock();
    }
}

int main(){
    clock_t startTime,endTime;
    startTime=clock();//开始时间
    std::thread task01(thread01);
    std::thread task02(thread02);
    task01.join();// 线程1加入，并堵塞主线程
    task02.join();// 线程2加入，并堵塞主线程
    std::cout<<"main:"<<count<<std::endl;

    endTime=clock();// 结束时间
    std::cout<<"period:"<<endTime-startTime<<std::endl;

    // todo 终止所有线程
//    system("pause");
    return 0;
}
//  g++ -std=c++11 ThreadDemo.cpp -lpthread -o ./debug/ThreadDemo  //用C++11编译