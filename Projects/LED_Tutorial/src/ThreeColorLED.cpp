#include <stdio.h>
#include <wiringPi.h>

// set macro values for 3 LEDs
#define LEDR 1
#define LEDG 2
#define LEDB 3
int main()
{
	// if wiringPi initialization fails, return with -1
	if(wiringPiSetup() == -1)
		return -1;

	// set pin mode for LED pins to OUTPUT
	pinMode(LEDR, OUTPUT);
	pinMode(LEDG, OUTPUT);
	pinMode(LEDB, OUTPUT);

	// loop forever
	while(1){
		// turn on RED, turn off BLUE
		digitalWrite(LEDR, 1);
		digitalWrite(LEDB, 0);

		// wait for 1000 milliseconds
		delay(1000);

		// turn on GREEN, turn off RED
		digitalWrite(LEDG, 1);
		digitalWrite(LEDR, 0);

		// wait for 1000 milliseconds
		delay(1000);

		// turn on BLUE, turn off GREEN
		digitalWrite(LEDB, 1);
		digitalWrite(LEDG, 0);

		// wait for 1000 milliseconds
		delay(1000);
	}
}
