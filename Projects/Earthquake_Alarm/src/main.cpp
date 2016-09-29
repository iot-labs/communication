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

    softToneCreate(BUZZER);

    return 0;
}

void alarm(){
    digitalWrite(LEDG, LOW);
    for(int i=0; i<20; i++){
        digitalWrite(LEDR, HIGH);
        softToneWrite(BUZZER, 987.77);
        delay(200);
        digitalWrite(LEDR, LOW);
        softToneWrite(BUZZER, 783.99);
        delay(200);
    }
    digitalWrite(LEDB, HIGH);
    softToneWrite(BUZZER, 0);
}

void loop(){
    while(1){
        digitalWrite(LEDR, LOW);
        digitalWrite(LEDG, HIGH);
        digitalWrite(LEDB, LOW);
        delay(5000);
        alarm();
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
