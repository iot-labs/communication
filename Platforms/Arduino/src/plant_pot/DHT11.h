/*
 * DH11.h
 *
 *  Created on: 2012. 12. 12.
 *      Author: dalxx
 *      Version : 0.8
 */

#ifndef DHT11_H_
#define DHT11_H_
#include <Arduino.h>
#define DHT11_RETRY_DELAY 1000  // 1000ms

class DHT11 {
	int pin;
	unsigned long last_read_time;
protected:

	byte readByte();
	unsigned long waitFor(uint8_t target, unsigned long time_out_us);
	void waitFor(uint8_t target);
public:
	DHT11(int pin_number);
	~DHT11();
	int read( float& humidity, float& temperature);



};


#endif /* DHT11_H_ */
