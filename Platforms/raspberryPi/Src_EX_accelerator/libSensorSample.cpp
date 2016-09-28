#include <iostream>
#include <wiringPi.h>
#include <stdio.h>
#include <cmath>
#include "libSensor.h"

using namespace std;

int main()
{
    if (wiringPiSetup() == -1)
        return -1;

    Sensor gyro;
    perror("Init gyro");

    int x,y,z;

    while(1){
        x=gyro.getAccelX();
        y=gyro.getAccelY();
        z=gyro.getAccelZ();

        printf("x=%d    y=%d    z=%d      ax=%f\n", x, y, z, gyro.getAngleX());
    }
}

