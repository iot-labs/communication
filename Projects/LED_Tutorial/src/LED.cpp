#include <stdio.h>
#include <wiringPi.h>

// set macro values for LED
#define LED 1

int main()
{
	// if wiringPi initialization fails, return with -1
	if(wiringPiSetup() == -1)
		return -1;

	// set pin mode for LED pin to OUTPUT
	pinMode(LED, OUTPUT);

	while(1){
		// write HIGH to LED pin
		digitalWrite(LED, 1);

		// wait for 1000 milliseconds
		delay(1000);

		// write LOW to LED pin
		digitalWrite(LED, 0);

		// wait for 1000 milliseconds
		delay(1000);
	}
}
