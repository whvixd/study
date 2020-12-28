#include <iostream>
#include <ctime>

using namespace std;

int main(){
    // 当前时间
    time_t now=time(0);

    char* dt=ctime(&now);

    cout<<"now:"<<dt<<endl;

    tm *gmtm=gmtime(&now);

    dt=asctime(gmtm);

    cout<<"UTC datetime"<<dt<<endl;
}