#include <Debug.h>
#include <JSN270.h>
#include <Arduino.h>
#include <SoftwareSerial.h>


#include <Wire.h>

#define MPU6050_address 0x68
#define MPU6050_PWR1 0x6B
#define MPU6050_data_address 0x3b

#include "DHT11.h"

DHT11 dht11(6);

//#define SSID      "JSN270_2G"		// your wifi network SSID
//#define KEY       "12345678"		// your wifi network password
//#define SSID      "iPhonekkye"    // your wifi network SSID
//#define KEY       "9698pqe75615"    // your wifi network password

#define SSID      "G5_2935"    // your wifi network SSID
#define KEY       "qwer1234"    // your wifi network password


//#define SSID      "G Pro2_9436"    // your wifi network SSID
//#define KEY       "82000687"    // your wifi network password


#define AUTH       "WPA2" 		// your wifi network security (NONE, WEP, WPA, WPA2)
//http://223.62.163.86/

#define USE_DHCP_IP 1

#if !USE_DHCP_IP
#define MY_IP          "192.168.1.133"
#define SUBNET         "255.255.255.0"
#define GATEWAY        "192.168.1.254"
#endif

#define SERVER_PORT    80
#define PROTOCOL       "TCP"

int led_status = 0;
    
SoftwareSerial mySerial(3, 2); // RX, TX
 
JSN270 JSN270(&mySerial);

void setup() {
	char c;

	mySerial.begin(9600);
	Serial.begin(9600);

  Wire.begin();
  Wire.beginTransmission(MPU6050_address);//mpu address set
  Wire.write(MPU6050_PWR1);
  Wire.write(0);//bit 6 sleep mode set
  Wire.endTransmission();

	Serial.println("--------- JSN270 Simple HTTP server Test --------");

	// wait for initilization of JSN270
	delay(5000);
	//JSN270.reset();
	delay(1000);

	//JSN270.prompt();
	JSN270.sendCommand("at+ver\r");
	delay(5);
	while(JSN270.receive((uint8_t *)&c, 1, 1000) > 0) {
		Serial.print((char)c);
	}
	delay(1000);

#if USE_DHCP_IP
	JSN270.dynamicIP();
#else
	JSN270.staticIP(MY_IP, SUBNET, GATEWAY);
#endif    
    
	if (JSN270.join(SSID, KEY, AUTH)) {
		Serial.println("WiFi connect to " SSID);
	}
	else {
		Serial.println("Failed WiFi connect to " SSID);
		Serial.println("Restart System");

		return;
	}    
	delay(1000);

	JSN270.sendCommand("at+wstat\r");
	delay(5);
	while(JSN270.receive((uint8_t *)&c, 1, 1000) > 0) {
		Serial.print((char)c);
	}
	delay(1000);        

	JSN270.sendCommand("at+nstat\r");
	delay(5);
	while(JSN270.receive((uint8_t *)&c, 1, 1000) > 0) {
		Serial.print((char)c);
	}
	delay(1000);
}

void loop() {
	if (!JSN270.server(SERVER_PORT, PROTOCOL)) {
		Serial.println("Failed connect ");
		Serial.println("Restart System");
	} else {
		Serial.println("Waiting for connection...");
	}

 int accel_x, accel_y, accel_z;
 int gyro_x, gyro_y, gyro_z;
  int temp;

  Wire.beginTransmission(MPU6050_address);
  Wire.write(MPU6050_data_address);
  Wire.endTransmission(false);//계속 통신하계사든 으미 false는

  Wire.requestFrom(MPU6050_address,14);//data address의 2바이트 읽기
  if(Wire.available() == 14){
    accel_x = Wire.read() << 8 | Wire.read();
    accel_x = Wire.read() << 8 | Wire.read();
    accel_x = Wire.read() << 8 | Wire.read();
    temp = Wire.read() << 8 | Wire.read();
    gyro_x = Wire.read() << 8 | Wire.read();
    gyro_y = Wire.read() << 8 | Wire.read();
    gyro_z = Wire.read() << 8 | Wire.read();
  }

  Serial.print("ac_x: "); Serial.print(accel_x);
  Serial.print(" ac_y: ");  Serial.print(accel_y);
  Serial.print(" ac_z: ");  Serial.print(accel_z);
  Serial.print(" temp: ");  Serial.print(temp / 340 + 36.53);
  Serial.print(" gy_x: ");  Serial.print(gyro_x);
  Serial.print(" gy_y: ");  Serial.print(gyro_y);
  Serial.print(" gy_z: ");  Serial.println(gyro_z);
      
	String currentLine = "";                // make a String to hold incoming data from the client
	int get_http_request = 0;

	while (1) {
		if (mySerial.overflow()) {
			Serial.println("SoftwareSerial overflow!");
		}
   
		if (JSN270.available() > 0) {
			char c = JSN270.read();
			Serial.print(c);

			if (c == '\n') {                    // if the byte is a newline character
				if (currentLine.length() == 0) {
					if (get_http_request) {
						Serial.println("new client");
						//Serial.println("HTTP RESPONSE");
						// Enter data mode
						JSN270.sendCommand("at+exit\r");
						delay(100);
						
						// HTTP headers always start with a response code (e.g. HTTP/1.1 200 OK)
						// and a content-type so the client knows what's coming, then a blank line:
						JSN270.println("HTTP/1.1 200 OK");
						JSN270.println("Content-type:text/html");
						JSN270.println();

						// the content of the HTTP response follows the header:
						//JSN270.print("Click <a href=\"/H\">here</a> turn the LED on pin 9 on<br>");
						//JSN270.print("Click <a href=\"/L\">here</a> turn the LED on pin 9 off<br>");

           JSN270.println("<!DOCTYPE HTML>");
            JSN270.println("<html>");
            JSN270.println("<H1>Humidity, temperature and Accelater data</H1>");
            int error;
            float temp, humidity;
            if(error = dht11.read(humidity, temp) == 0) {
              JSN270.print("<H4>temperature : ");
              JSN270.print(temp);
              JSN270.print("&nbsp;humidity : ");
              JSN270.println(humidity);

              Serial.print("temperature : "); Serial.print(temp);
              Serial.print("humidity: ");  Serial.print(humidity);

              
              JSN270.print("&nbsp;Accelater X : ");
              JSN270.print(accel_x);
              JSN270.print("&nbsp;Accelater Y : ");
              JSN270.print(accel_y);
              JSN270.print("&nbsp;Accelater Z : ");
              JSN270.print(accel_z);

              JSN270.print("&nbsp;gyro X : ");
              JSN270.print(gyro_x);
              JSN270.print("&nbsp;gyro Y : ");
              JSN270.print(gyro_y);
              JSN270.print("&nbsp;gyro Z : ");
              JSN270.print(gyro_z);
              
              JSN270.println("</H4>");
            }
            JSN270.println("</html>");
            

						// The HTTP response ends with another blank line:
						JSN270.println();

						// wait until all http response data sent:
						delay(1000);

						// Enter command mode:
						JSN270.print("+++");
						delay(100);
						
						// break out of the while loop:
						break;
					}
				}
				// if you got a newline, then clear currentLine:
				else {                
					// Check the client request:
					if (currentLine.startsWith("GET / HTTP")) {
						Serial.println("HTTP REQUEST");
						get_http_request = 1;
					}
					else if (currentLine.startsWith("GET /H")) {
						//Serial.println("GET HIGH");
						get_http_request = 1;
						led_status = 1;

					}
					else if (currentLine.startsWith("GET /L")) {
						//Serial.println("GET LOW");
						get_http_request = 1;
						led_status = 0;
					}
					currentLine = "";
				}
			}
			else if (c != '\r') {    // if you got anything else but a carriage return character,
				currentLine += c;      // add it to the end of the currentLine
			}
		}
	}

	if (led_status) {
		JSN270.sendCommand("at+gpioset=15,1\r");               // GET /H turns the LED on
	}
	else {
		JSN270.sendCommand("at+gpioset=15,0\r");               // GET /H turns the LED off
	}
	
	// close the connection
	JSN270.sendCommand("at+nclose\r");
	Serial.println("client disonnected");
}
