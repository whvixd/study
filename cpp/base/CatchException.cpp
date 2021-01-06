#include <iostream>

using namespace std;

int main(){

    try{
        throw "exception!!!";
    }catch(const char* msg){// ... 捕获任何异常
        cerr<<msg<<endl;
    }

    return 0;
}