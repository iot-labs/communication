#include <stdio.h>
#include <wiringPi.h>

#define LEDR 1
#define LEDG 2
#define LEDB 3
int main()
{
	if(wiringPiSetup() == -1)
		return -1;

	pinMode(LEDR, OUTPUT);
	pinMode(LEDG, OUTPUT);
	pinMode(LEDB, OUTPUT);

	while(1){
		digitalWrite(LEDR, 1);
		digitalWrite(LEDB, 0);
		
		delay(1000);

		digitalWrite(LEDG, 1);
		digitalWrite(LEDR, 0);

		delay(1000);

		digitalWrite(LEDB, 1);
		digitalWrite(LEDG, 0);

		delay(1000);
	}
}
