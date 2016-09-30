#include <stdio.h>
#include <stdint.h>
#include <string.h>
#include <mosquitto.h>
#include <wiringPi.h>
#include <string.h>
#include <cstring>

#define LEDR 1
#define LEDG 2
#define LEDB 3

#define HOST "192.168.0.159"
#define TOPIC "/IoTLabs/LED"
#define PORT 1883

struct mosquitto *mosq;
int rc=0;

void message_callback(struct mosquitto *mosq, void *obj, const struct mosquitto_message *message){
    printf("%s\n", message->payload);
    if(strcasecmp((char*)(message->payload), "R")==0){
        digitalWrite(LEDR, HIGH);
        digitalWrite(LEDG, LOW);
        digitalWrite(LEDB, LOW);
    }
    if(strcasecmp((char*)(message->payload), "G")==0){
        digitalWrite(LEDR, LOW);
        digitalWrite(LEDG, HIGH);
        digitalWrite(LEDB, LOW);
    }
    if(strcasecmp((char*)(message->payload), "B")==0){
        digitalWrite(LEDR, LOW);
        digitalWrite(LEDG, LOW);
        digitalWrite(LEDB, HIGH);
    }
}

int init(){
    int keepalive = 60;
    bool clean_session = true;

    if(wiringPiSetup() == -1) return -1;
    pinMode(LEDR, OUTPUT);
    pinMode(LEDG, OUTPUT);
    pinMode(LEDB, OUTPUT);
    digitalWrite(LEDR, LOW);
    digitalWrite(LEDG, LOW);
    digitalWrite(LEDB, LOW);

    mosquitto_lib_init();
    mosq = mosquitto_new("LED_Tutorial", clean_session, NULL);
    if(mosq){
        mosquitto_message_callback_set(mosq, message_callback);
        rc = mosquitto_connect(mosq, HOST, PORT, keepalive);

        mosquitto_subscribe(mosq, NULL, TOPIC, 0);
    }
    return 0;
}

void loop(){
    while(1){
        rc = mosquitto_loop(mosq, -1, 1);
        if(rc){
            printf("Reconnect\n");
            mosquitto_reconnect(mosq);
        }
    }
}

void destroy(){
    mosquitto_destroy(mosq);
    mosquitto_lib_cleanup();
}

int main(){

    if(init()==-1){
        printf("Setup failed\n");
        return -1;
    }

    loop();

    destroy();

    return 0;
}
