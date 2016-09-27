#include <Debug.h>

#include <Arduino.h>
#include <SoftwareSerial.h>
#include "DHT11.h"
DHT11 dht11(6);
SoftwareSerial mySerial(3, 2); // RX, TX
void setup() {
  // put your setup code here, to run once:
	char c;

	mySerial.begin(9600);
	Serial.begin(9600);
    Serial.println("Serial communication test");

}

void loop() {
  // put your main code here, to run repeatedly:

Serial.print("temperature : "); Serial.print(temp);
int error;
float temp, humidity;
            if(error = dht11.read(humidity, temp) == 0) {
              Serial.print("humidity: ");  Serial.print(humidity);

}
}
