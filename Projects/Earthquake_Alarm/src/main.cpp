#include  <stdio.h>
#include <wiringPi.h>
#include <softTone.h>

#define LEDR 1
#define LEDG 2
#define LEDB 3
#define BUZZER 4

int init(){
    if(wiringPiSetup()==-1) return -1;
    
    pinMode(LEDR, OUTPUT);
    pinMode(LEDG, OUTPUT);
    pinMode(LEDB, OUTPUT);

    return 0;
}

int loop(){
    while(1){
        digitalWrite(LEDR, LOW);
        digitalWrite(LEDG, HIGH);
        digitalWrite(LEDB, LOW);
    }
}

int main(){
    
    if(init()==-1){
        printf("wiringPi setup failed");
        return -1;
    }

    loop();

    return 0;
}
