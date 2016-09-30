#include <stdint.h>
#include <wiringPi.h>
#include <wiringPiI2C.h>
#include "libSensor.h"
#include <stdio.h>
#include <cmath>

#define MPU6050_GYRO_XOUT_H        0x43   // R
#define MPU6050_GYRO_YOUT_H        0x45   // R
#define MPU6050_GYRO_ZOUT_H        0x47   // R

#define MPU6050_ACCEL_XOUT_H       0x3B   // R
#define MPU6050_ACCEL_YOUT_H       0x3D   // R
#define MPU6050_ACCEL_ZOUT_H       0x3F   // R

#define MPU6050_PWR_MGMT_1         0x6B   // R/W
#define MPU6050_I2C_ADDRESS        0x68   // I2C

Sensor::Sensor()
{
    fd = wiringPiI2CSetup(MPU6050_I2C_ADDRESS);
    if (fd == -1)
        return;

    wiringPiI2CReadReg8 (fd, MPU6050_PWR_MGMT_1);
    wiringPiI2CWriteReg16(fd, MPU6050_PWR_MGMT_1, 0);
}

int8_t Sensor::getGyroX()
{
    return (int8_t) wiringPiI2CReadReg8(fd, MPU6050_GYRO_XOUT_H);
}

int8_t Sensor::getGyroY()
{
    return (int8_t) wiringPiI2CReadReg8(fd, MPU6050_GYRO_YOUT_H);
}

int8_t Sensor::getGyroZ()
{
    return (int8_t) wiringPiI2CReadReg8(fd, MPU6050_GYRO_ZOUT_H);
}

int8_t Sensor::getAccelX()
{
    return (int8_t) wiringPiI2CReadReg8(fd, MPU6050_ACCEL_XOUT_H);
}

int8_t Sensor::getAccelY()
{
    return (int8_t) wiringPiI2CReadReg8(fd, MPU6050_ACCEL_YOUT_H);
}

int8_t Sensor::getAccelZ()
{
    return (int8_t) wiringPiI2CReadReg8(fd, MPU6050_ACCEL_ZOUT_H);
}

float Sensor::getAngleX()
{
    int x = getAccelX();
    int y = getAccelY();
    int z = getAccelZ();
    float ax = atan(x/(sqrt(y*y+z*z)))* 180/M_PI;
    return ax;
}

float Sensor::getAngleY()
{
    int x = getAccelX();
    int y = getAccelY();
    int z = getAccelZ();
    float ay = atan(y/(sqrt(x*x+z*z)))* 180/M_PI;
    return ay;
}

float Sensor::getAngleZ()
{
    int x = getAccelX();
    int y = getAccelY();
    int z = getAccelZ();
    float az = atan((sqrt(y*y+x*x))/z)* 180/M_PI;
    return az;
}

