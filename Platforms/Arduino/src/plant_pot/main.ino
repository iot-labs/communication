#include <Debug.h>
#include <Arduino.h>
#include <SoftwareSerial.h>
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

}
