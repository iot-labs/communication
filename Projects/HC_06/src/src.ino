#include <SoftwareSerial.h>

/**
 *  RX_PIN은 HC 모듈의 TX 핀과 연결될 핀
 *  TX_PIN은 HC 모듈의 RX 핀과 연결될 핀
 */
#define RX_PIN 3
#define TX_PIN 2
#define SERIAL_BAUD 9600
#define HC_BAUD 9600

SoftwareSerial HCSerial(RX_PIN, TX_PIN);

void setup() {
  Serial.begin(SERIAL_BAUD);
  HCSerial.begin(HC_BAUD);
}

void loop() {
  if(HCSerial.available()) {
    // HC 모듈로부터 데이터가 들어온 경우 수행 루틴
    HCFunction();
  }
  if(Serial.available()) {
    // 시리얼 모니터로부터 데이터가 들어온 경우 수행 루틴
    SerialFunction();
  }
}

void HCFunction() {
  /** 
   * Init routine
   * HC 모듈로부터 들어온 데이터를 시리얼 모니터로 출력 
   * 커스터마이징할 함수 영역
   */
  Serial.write(HCSerial.read());
}

void SerialFunction() {
  /**
   * Init routine
   * 시리얼 모니터로부터 들어온 데이터를 HC 모듈로 출력
   * 커스터마이징할 함수 영역
   */
  HCSerial.write(Serial.read());
}

