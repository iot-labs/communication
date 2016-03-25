# IoT Labs - 쉬운 IoT 개발 환경을 위한 프레임워크
@(_Open Frontier)[IoT Labs]


IoT Labs 는 IoT 개발에 어려움이 되는 (통신/저장/분석)을 프레임워크로 제공하여 보다 쉬운 개발 환경 제공을  목표로 합니다.

----

<!-- TOC depthFrom:1 depthTo:6 withLinks:1 updateOnSave:1 orderedList:0 -->

- [IoT Labs - 쉬운 IoT 개발 환경을 위한 프레임워크](#iot-labs-iot-)
	- [News](#news)
	- [IoT Labs 소개](#iot-labs-)
		- [구현 범위](#-)
	- [진행상황](#진행상황)
	- [API Docs](#api-docs)
	- [참여](#)
		- [제안 / 이슈 / 테스트](#-)
		- [개발 참여](#-)
	- [라이센스](#)

<!-- /TOC -->

----

## News
* 2016.00.00 : Github init.

## IoT Labs 소개
IoT 디바이스는 잘 다루지만 Web 개발에 익숙하지 않은 개발자를 위해 만들었습니다.
자신의 IoT 아이디어를 쉽게 구현 할 수 있도록 도움을 주려는 것이 이 프로젝트의 목적 입니다.

프로젝트는 최대한 쉬운 기술을 이용하여 개발이 되며, 상세한 설명을 중요시 합니다. 많은 예제와 사례를 통해 쉽게 접근 할 수 있도록 되어 있습니다.

## Overview

* 통신
	* <img src="https://raw.githubusercontent.com/jongkwang/IoTLabs/master/assets/img/overview1.png">
* 데이터 저장
	* <img src="https://raw.githubusercontent.com/jongkwang/IoTLabs/master/assets/img/overview2.png">
* 데이터 분석
	* <img src="https://raw.githubusercontent.com/jongkwang/IoTLabs/master/assets/img/overview3.png">
*  API 연동
	* <img src="https://raw.githubusercontent.com/jongkwang/IoTLabs/master/assets/img/overview4.png">

----

### 프로젝트 목표

전세계적으로 IoT 의 영향력이 확대 되고, 우리나라에서도 많은 분야해서 IoT 를 접목하는 시도가 있습니다. 이에 따라 많은 학생이나 입문자들이 IoT 아이템을 개발/발전 시켜나가고 있지만, S/W 를 전공하지 않은 학생이나 일반인들에게는 큰 진입장벽이 되는 부분이 있습니다.

IoT Labs 에서는 이 진입장벽을 낮춤으로써 S/W  를 전공하지 않은 분들도 쉽게 IoT 아이디어를 개발/발전 시킬 수 있도록 돕고 싶습니다.

----

### 구현 범위

- Device 통신
	- MQTT , Web Socket , Bluetooth
- Data 저장
	- JDBC , JSON Store , Redis
- Data 분석 (Dashboard)
	- (미정. 제안 보내주세요)
- API 연동
	- Facebook
	- Twitter
	- Gmail 

----

## 진행상황

- Device 통신 : `진행중` ![](https://img.shields.io/badge/Development-Processing-green.svg?style=flat)
- Data 저장 : `진행중` ![](https://img.shields.io/badge/Development-Processing-green.svg?style=flat)
- Data 분석 (Dashboard) : `예정됨` ![](https://img.shields.io/badge/Development-Scheduled-green.svg?style=flat)
- API 연동 : `예정됨` ![](https://img.shields.io/badge/Development-Scheduled-green.svg?style=flat)

----

## 사례조사

> IoT 관련 라이브러리와 프로젝트등의 리서치

### 국내

#### Thing+

<img src="https://raw.githubusercontent.com/jongkwang/IoTLabs/master/assets/img/case_research1.png">

IoT 클라우드 플랫폼을 기반으로 IoT 서비스를 구축 할 수 있도록 도와준다.

> Ref. : https://thingplus.net/

##### 특징
* IoT Device 간 연결 작업 지원 <code>(우리 프로젝트인 IoT Labs 와 같은 기능 제공)</code>
* 클라우드 기반으로 동작한다 <code>(IoT Labs 에 없는 기능)</code>
* 포털을 이용한 서비스 제공 <code>(IoT Labs 와 비슷한 기능)</code>
* API 기능 제공 <code>(IoT Labs 와 비슷한 기능)</code>

<img src="https://raw.githubusercontent.com/jongkwang/IoTLabs/master/assets/img/case_research2.png">
> Image from Thing+ (https://thingplus.net/)


##### Thing+ 의 UI 기능

* 대시보드 제공
	* 센서 데이터를 대시보드 형태로 제공
* 룰과 타임라인 제공
	* IFTTT 처럼 조건에 따른 액션(Action)을 정할 수 있음
* 차트와 통계 제공
	* 상세한 데이터는 Chart 로 분석
	* 기간별 통계 분석(Statistics)

----

#### LG CNS - IoT 플랫폼

<img src="https://raw.githubusercontent.com/jongkwang/IoTLabs/master/assets/img/case_research3.png">

LG CNS 에서 제공하는 IoT 플랫폼. <small>*아직 이름이 정해지진 않았음*</small>

> Ref. : http://blog.lgcns.com/817

##### 특징

* Gateway와 Edge Device 관련 기술 
* 대량의 데이터를 안정적으로 전달할 수 있는 통신 기술
* 이벤트 처리와 데이터 분석 및 추천을 위한 빅데이터(Big Data) 관련 기술
* 위의 언급한 기술들의 Base 제공 및 융합을 지원할 플랫폼과 Enabler 관련 기술
* 인증/권한을 통한 데이터 보안뿐만 아니라 디바이스와 칩(Chip) 레벨의 보안 기술 
* 사용자와 상호 작용할 수 있는 UI/UX 기술

<img src="https://raw.githubusercontent.com/jongkwang/IoTLabs/master/assets/img/case_research4.png">
> Image from LG CNS (http://blog.lgcns.com/817)

----


### 국외

#### AWS IoT

<img src="https://raw.githubusercontent.com/jongkwang/IoTLabs/master/assets/img/case_research_aws_iot1.png" height=100>

LG CNS 에서 제공하는 IoT 플랫폼. <small>*아직 이름이 정해지진 않았음*</small>

> Ref. : http://blog.lgcns.com/817

##### 특징

* 디바이스 연결  및 관리
	* MQTT
	* WebSockets
	* HTTP
* 디바이스 연결 및 데이터 보안
	* 모든 연결 지점에서 인증 및 엔드-투-엔드 암호화를 제공
	* 세분화된 권한이 설정된 정책을 적용
* 디바이스 데이터 처리
	* 기존의 AWS 서비스 들과 연계 가능 : AWS Lambda, Amazon Kinesis, Amazon S3, Amazon Machine Learning, Amazon DynamoDB, Amazon CloudWatch, Amazon Elasticsearch Service
* 디바이스 상태를 확인 및 설정
	* 상태를 확인하거나 설정
	* 디바이스가 언제나 온라인인 것처럼 표시

<img src="https://raw.githubusercontent.com/jongkwang/IoTLabs/master/assets/img/case_research_aws_iot2.png">
> Image from AWS (https://aws.amazon.com/ko/iot/how-it-works/)

----

#### GE (General Electric)

> *(내용이 많아서 문서 이동 됨) - [바로가기](https://github.com/jongkwang/IoTLabs/wiki/Case-Research---%EC%82%AC%EB%A1%80%EC%A1%B0%EC%82%AC)*

----

#### 인텔(Intel)

> *(내용이 많아서 문서 이동 됨) - [바로가기](https://github.com/jongkwang/IoTLabs/wiki/Case-Research---%EC%82%AC%EB%A1%80%EC%A1%B0%EC%82%AC)*

----

#### Splunk

> *(내용이 많아서 문서 이동 됨) - [바로가기](https://github.com/jongkwang/IoTLabs/wiki/Case-Research---%EC%82%AC%EB%A1%80%EC%A1%B0%EC%82%AC)*

----

## 참여

## Issue
사용문의 또는 질문 사항들은 [Github 이슈](https://github.com/jongkwang/IoTLabs/issues)에 올려 주시면 바로 처리 해 드리겠습니다.
많은 의견 부탁드립니다.

## Contribution
IoT Labs를 개선해주세요. Contribution은 언제나 환영합니다.

## Support

* 이 프로젝트는 [공개SW 개잘자 Lab](http://devlab.oss.kr/)의 지원을 받고 있습니다.


## License
* [IoT Labs](https://github.com/jongkwang/IoTLabs) 는 [MIT](https://opensource.org/licenses/MIT) 라이센스를 따르며,
* 함께 사용된 SW와 폰트의 라이센스도 준수해야 합니다.
* 함께 사용된 SW 와 폰트
	* Font
		* [스케치명조](http://www.asiasoft.co.kr/) 폰트가 사용 되었습니다.
	* Open Source
		* [Paho](http://www.eclipse.org/paho/) - [Eclipse Public License 1.0](http://projects.eclipse.org/content/eclipse-public-license-1.0)
		* [RabbitMQ](https://www.rabbitmq.com/) - [Mozilla Public License](https://www.rabbitmq.com/mpl.html)
```text
The MIT License (MIT)

Copyright (c) 2016 IoT Labs (https://github.com/jongkwang/IoTLabs)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
