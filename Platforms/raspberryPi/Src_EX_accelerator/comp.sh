#!/bin/bash
g++ libSensorSample.cpp -o test -lwiringPi libSensor.cpp
./test
