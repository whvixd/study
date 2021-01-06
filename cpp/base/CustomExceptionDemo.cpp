#include <iostream>
#include <exception>

using namespace std;

struct CustomException:public exception{
    const char* what() const throw(){
        return "cpp custom exception";
    }
};

int main(){

    try{
        throw CustomException();
    }catch(CustomException& e){
        std::cout<<e.what()<<std::endl;
    }catch(...){
        std::cout<<"other e"<<std::endl;
    }
    return 0;
}