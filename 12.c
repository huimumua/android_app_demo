#include <stdio.h>

void main(int argc, char ** argv){
    printf("argc= %d\n",argc);
    printf("argc[0]= %s\n",argv[0]);
    printf("argc[1]= %s\n",argv[1]);
    printf("argc[2]= %s\n",argv[2]);
    //int  a = 0x7FFF;
    int b = 2;
    int c = b/4;

    int a[100] = {1,2,3};

    printf("%d %d %d, C:%d\n",a[1],a[2],a[3],c);


}


