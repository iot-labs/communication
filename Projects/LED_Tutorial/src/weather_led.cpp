#include <stdio.h>
#include <wiringPi.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <stdlib.h>

// set macro values for 3 LEDs
#define LEDR 1
#define LEDG 2
#define LEDB 3
int main()
{
	int fp;
	int i;
	
	if((fp = open("weather_ipc",O_RDWR))<0) {
		if(mkfifo("weather_ipc",0666) != 0) {
			perror("ipc creation failure");
			return 1;
		}
		fp=open("weather_ipc",O_RDWR);
	}
		
	// if wiringPi initialization fails, return with -1
	if(wiringPiSetup() == -1)
		return -1;

	// set pin mode for LED pins to OUTPUT
	pinMode(LEDR, OUTPUT);
	pinMode(LEDG, OUTPUT);
	pinMode(LEDB, OUTPUT);

	// loop forever
	while(1){
		read(fp,(void*)&i,sizeof(int));
	
		// turn off all LED
		digitalWrite(LEDR ,0);
		digitalWrite(LEDB ,0);
		digitalWrite(LEDG ,0);

		// wait for 1000 milliseconds
		delay(1000);

		// turn on GREEN, turn off RED
		if(i == 0)
			digitalWrite(LEDG,1);
		else if(i == 1)
			digitalWrite(LEDR,1);
		else if(i == 2)
			digitalWrite(LEDB,1);
	}
}
