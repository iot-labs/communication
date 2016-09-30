#include <stdio.h>
#include <wiringPi.h>
#include <softTone.h>
#include <mosquitto.h>
#include <string.h>
#include "libSensor.h"

using namespace std;
#define LEDR 1
#define LEDG 2
#define LEDB 3
#define BUZZER 4

#define HOST "localhost"
#define TOPIC "IoTLabs/Earthquake"
#define MESSAGE "Alarm"
#define PORT 1883

Sensor gyro;
struct mosquitto *mosq;

int init(){
    int keepalive = 60;
    bool clean_session = true;

    if(wiringPiSetup()==-1) return -1;
    
    pinMode(LEDR, OUTPUT);
    pinMode(LEDG, OUTPUT);
    pinMode(LEDB, OUTPUT);

    softToneCreate(BUZZER);

    mosquitto_lib_init();
    mosq = mosquitto_new(NULL, clean_session, NULL);
    if(mosq){
        if(mosquitto_connect(mosq, HOST, PORT, keepalive)){
            return -1;
        }
    }
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
        //mosquitto_publish(mosq, NULL, TOPIC, strlen(MESSAGE), MESSAGE, 0, false);
    }
    digitalWrite(LEDB, HIGH);
    softToneWrite(BUZZER, 0);
}

void loop(){
    int gx, gy, gz, ax, ay, az;
    int count=0;
    while(1){
        digitalWrite(LEDR, LOW);
        digitalWrite(LEDG, HIGH);
        digitalWrite(LEDB, LOW);
        ax=gyro.getAccelX();
        ay=gyro.getAccelY();
        az=gyro.getAccelZ();
        gx=gyro.getAngleX();
        gy=gyro.getAngleY();
        gz=gyro.getAngleZ();
        printf("Angle : %d, %d, %d\n", gx, gy, gz);
        printf("Accel : %d, %d, %d\n", ax, ay, az);
        if(gx>20 || gy > 20 || gz > 20){
            count++;   
        }
        else{
            count = 0;
        }
        if(count>10){
            mosquitto_publish(mosq, NULL, TOPIC, strlen(MESSAGE), MESSAGE, 0, false);
            alarm();
            printf("************ALART************\n");
            count=0;
        }
    }
}

void destroy(){
    mosquitto_destroy(mosq);
    mosquitto_lib_cleanup();
}

int main(){
    
    if(init()==-1){
        printf("wiringPi setup failed");
        return -1;
    }

    loop();
    
    destroy();

    return 0;
}
