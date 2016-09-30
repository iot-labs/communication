#READ ME

@(GY-521 6축 가속센서 소개)

![enter image description here](https://raw.githubusercontent.com/maestroxhyun/IoTLabs/master/assets/img/introduce_mpu-6050.png)

GY-521는 자이로 센서와 가속도계가 합쳐진 모듈로써, 
X,Y,Z축의 기울기측정과 X,Y,Z축의 가속도 측정을 해주는 i2c 통신을 하는 센서

i2c 통신?

![enter image description here](https://raw.githubusercontent.com/maestroxhyun/IoTLabs/master/assets/img/introduce_i2c.png)

I2C는 풀업 저항이 연결된 직렬 데이터(SDA)와 직렬 클럭(SCL)이라는 두 개의 양 방향 오픈 컬렉터 라인을 사용하며 최대 전압은 +5V 이며, 일반적으로 +3.3V 를 사용합니다.

통신규격
1. SCL이 high이면, SDA의 신호는 일정해야 합니다. (Data)
2. SCL이 low이면, SDA의 신호는 변경가능합니다.
3. SDA의 신호가 failing edge면 START
4. SDA의 신호가 rising edge면 STOP


----

## 라즈베리파이 연결 방법

1.sudo raspi-config 를 통해 i2c enable 설정

2.sudo vi /etc/modules 

아래 두 줄 추가 후 재부팅

i2c-bcm2708
i2c-dev

3.하드웨어 선 연결

라즈베리파이의 pin1, 3, 5, 6을 센서의 VCC, SDA, SCL, GND에 아래와 같이 연결

* Pin 1 - 3.3V connect to VCC
* Pin 3 - SDA connect to SDA
* Pin 5 - SCL connect to SCL
* Pin 6 - Ground connect to GND

4.테스트를 위한 파일 다운 및 테스트

sudo apt-get install i2c-tools

- 테스트

sudo i2cdetect -y 0 (라즈베리파이1버전) or

sudo i2cdetect -y 1 (라즈베리파이2)

- 아래와 같이 나오는 것 확인

![enter image description here](https://raw.githubusercontent.com/maestroxhyun/IoTLabs/master/assets/img/introduce_accelerator.png)

## 코드설명

플랫폼에는 4개의 파일이 들어있다.

* comp.sh (컴파일시 필요한 파일)
* libsensor.cpp (구현된 소스코드)
* libsensor.h (클래스 정의헤더파일)
* libsensorSample.cpp (테스트 코드)

(작성중..)
