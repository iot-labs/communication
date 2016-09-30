# IoT Labs - 쉬운 IoT 개발 환경을 위한 프레임워크 <br/> IoT Labs - A Framework for Simpler IoT Development Environment
@(_Open Frontier)[IoT Labs]

IoT Labs는 IoT 개발에 어려움이 되는 (통신/저장/분석)을 프레임워크로 제공하여 보다 쉬운 개발 환경 제공을  목표로 합니다. <br/>
IoT Labs aims to provide a framework for (communication/storage/analysis) in IoT development for increased productivity.

----

<!-- TOC depthFrom:1 depthTo:1 withLinks:1 updateOnSave:1 orderedList:0 -->

- [Dashboard - 공개SW 해커톤](#dashboard)
- [IoT Labs - English](#iotlabs-english)
- [IoT Labs - Korean](#iotlabs-korean)

<!-- /TOC -->

----

## <a name="dashboard">Dashboard - 공개SW 해커톤

> 공개SW 해커톤 관련해서 꼭 보셔야 하는 것은 이곳에 정리하겠습니다.

#### 해커톤 공지
* 번개 공지
 * 일시 : 화요일 19시
 * 장소 : 서울 강남구 테헤란로 311, 7층 KossLab
 * 안건 : Free Talking
 * 식사 : 자장면 or 인근 식당
* 하단 커뮤니케이션 채널에 가입해주세요. (Facebook & Slack)
* Github 사용법 또는 막히는 것이 있으면 언제든 Slack으로 제게 도움을 요청하세요!! (저는 항상 StandBy 상태 입니다)

#### 커뮤니케이션
* Github : https://github.com/jongkwang/IoTLabs
* 커뮤니티 : https://www.facebook.com/groups/IoTLabs
* 채팅(Slack) : iotlabs-team.slack.com
 * Design / General / HW / SW / Random : 5개 채널 운영중
  * 채널에 가입 안하시면 채팅이 보이지 않습니다.

#### 해커톤에서 필수 이수 사항
* 해커톤에서 이것은 해주셔야 "이수"로 인정하겠습니다.
 * 커밋 3~5회
 * 하지만 커밋 3회가 어려운 것 잘 알고 있습니다.
 * 그래서 쉽게 목표를 달성하실 수 있도록 안내를 다시 배포 하겠습니다. (상세 방법 작성 중...)

#### 해커톤 이수를 위한 추천 작업
* 해커톤에서 3번 이상 커밋이 있는 분을 이수로 간주 하도록 하겠습니다.
* Github 사용법을 모르시면, `Team Viewer` 라는 프로그램 설치 후 제게 전화 해 주세요. o1o-9123-066o 김종광
 * 한분한분 원격제어로 봐드리겠습니다. 꼭 전화 주세요!!
* README.md 파일 맞춤법을 수정해 주세요.
 * 저번에 수정하신 분들 대부분이 Version Conflict 발생하여 Pull Request 가 Reject 되었습니다.
 * 새로 받으셔서(Pull) 다시 고쳐 주셔야 합니다.
 * URL : https://github.com/jongkwang/IoTLabs/blob/master/README.md
* 기존 코드에 주석을 넣어주세요.
 * 한글 주석도 좋습니다
 * 중요하지 않은 주석도 좋습니다.!! (이건 참여에 의미가 있으니까요)
 * 주석 입력 대상 예시 : https://github.com/jongkwang/IoTLabs/blob/master/Platforms/Java/src/test/java/org/iotlabs/communication/mqtt/clients/BaseClientTest.java
 * 클래스 주석이나 메소드 주석이 없으니까 넣어주시면 좋겠어요!!
 * 권장 포멧 : http://javacan.tistory.com/entry/24 참조
* Gradle 튜토리얼을 만들어 주세요
 * Github 에서 Download 받아서 Gradlew 로 MQTT서버 띄우는 과정을
 * `IoTLabs/Documents/Tutorial/README_여러분아이디.md` 파일로 만들어 주세요.


#### 현재 진행 상황 - 지진알림이 + LED 램프
* H/W : 송정우님께서 진행 중이십니다.

#### 현재 진행 상황 - 화분
* H/W : 황종순님께서 진행 중이십니다.

#### 현재 진행 상황 - Environment
* Environment : 이성민님
* MySQL + MyBatis : jayong 님
* Machine 구성 : 김종광

#### 현재 진행 상황 - 참여하시고 싶으신 분

* 사실, 팀장의 역할은 일을 잘 시키는 것에 있습니다.
 * 그런데 제가 역량 부족으로 일을 원할하게 시키지 못하고 있는점 이해해 주세요 ㅠㅠ (정보가 너무 없어요 ㅠㅠ)
* 지금이라도 "슬랙 > wanted 채널"을 통해서 손들어 주시면 할 일을 같이 고민 하도록 하겠습니다. !!
* 적극참여자는 Wanted 채널로 남겨주시고요.
* 소극적 참여자는 참여 방법을 안내해 드리겠습니다. ^^



----

# <a name="iotlabs-english">IoT Labs - A Framework for Simpler IoT Development Environment
@(_Open Frontier)[IoT Labs]


IoT Labs aims to provide a framework for (communication/storage/analysis) in IoT development for increased productivity.

----

<!-- TOC depthFrom:1 depthTo:6 withLinks:1 updateOnSave:1 orderedList:0 -->

- [IoT Labs - A Framework for Simpler IoT Development Environment](#iotlabs-english)
	- [News](#news-english)
	- [Introduction to IoT Labs](#introduction-to-iot-labs)
		- [Scope](#scope)
	- [Current Status](#current-status)
	- [API Docs](#-)
	- [Get Involved](#get-involved)
		- [Issues](#issues)
		- [Development Participation](#development-participation)
		- [Sponsor](#sponsor)
	- [License](#license)

<!-- /TOC -->

----
## Build Status
#### Platform/Java
[![Build Status](https://travis-ci.org/toori67/IoTLabs.svg?branch=master)](https://travis-ci.org/toori67/IoTLabs)
[![Coverage Status](https://coveralls.io/repos/github/toori67/IoTLabs/badge.svg?branch=master)](https://coveralls.io/github/toori67/IoTLabs?branch=master)

## <a name="news-english">News
* 2016.06.23 : MQTT Broker functioning (24/7)

## Introduction to IoT Labs
IoT Labs was created to help IoT device developers who are not familiar with web development environments.
The goal of this project is to help developers realize their IoT ideas.

This project aims to utilize the most common and simple technologies, and emphasizes detailed description of its features. Developers will be able to learn and understand IoT Labs through many of its examples and real-world application case studies.

## Overview

* Communications
	* <img src="https://raw.githubusercontent.com/jongkwang/IoTLabs/master/assets/img/overview1.png">
* Data Storage
	* <img src="https://raw.githubusercontent.com/jongkwang/IoTLabs/master/assets/img/overview2.png">
* Data Analysis
	* <img src="https://raw.githubusercontent.com/jongkwang/IoTLabs/master/assets/img/overview3.png">
*  API
	* <img src="https://raw.githubusercontent.com/jongkwang/IoTLabs/master/assets/img/overview4.png">

----

### Project Goal

The influence of IoT technology is increasing and there have been many attempts to incorporate IoT technology in various fields. Many students and less-experienced developers are trying to create and improve their IoT ideas. But there is a steep learning curve for those who have no background in software engineering.

IoT Labs strives to help those who have great ideas but are not familiar with software engineering by lowering the learning curve of software development.

----

### Scope

- Device Communication
	- MQTT, Web Socket, Bluetooth
- Data Storage
	- JDBC, JSON Store, Redis
- Data Analysis (Dashboard)
	- (Undecided. Suggestions are welcome.)
- API
	- Facebook
	- Twitter
	- Gmail

----

## Current Status

- Device Communication :  ![](https://img.shields.io/badge/Development-Processing-green.svg?style=flat)
- Data Storage :  ![](https://img.shields.io/badge/Development-Processing-green.svg?style=flat)
- Data Analysis (Dashboard) :  ![](https://img.shields.io/badge/Development-Scheduled-green.svg?style=flat)
- API :  ![](https://img.shields.io/badge/Development-Scheduled-green.svg?style=flat)

----

## Related Work

> Research on IoT-related libraries and projects

### Korea

#### Thing+

<img src="https://raw.githubusercontent.com/jongkwang/IoTLabs/master/assets/img/case_research1.png">

Helps create IoT services through an IoT cloud-based platform.

> Ref. : https://thingplus.net/

##### Characteristics
* Helps to connect IoT devices <code>(Feature provided by IoT Labs)</code>
* Cloud-based <code>(Feature not provided by IoT Labs)</code>
* Provides service through a web portal <code>(Similar feature provided by IoT Labs)</code>
* Provides API <code>(Similar feature provided by IoT Labs)</code>

<img src="https://raw.githubusercontent.com/jongkwang/IoTLabs/master/assets/img/case_research2.png">
> Image from Thing+ (https://thingplus.net/)


##### UI Features in Thing+

* Dashboard
	* Provides sensor data in a dashboard
* Rule and Timeline
	* Provides conditional action functions similar to IFTTT
* Charts and Statistics
	* Detailed data analysis through charts
	* Temporal and statistical data analysis

----

#### LG CNS - IoT Platform

<img src="https://raw.githubusercontent.com/jongkwang/IoTLabs/master/assets/img/case_research3.png">

IoT platform provided by LG CNS. <small>*Offical name to be determined*</small>

> Ref. : http://blog.lgcns.com/817

##### Characteristics

* Technology for gateways and edge devices
* Communications technology to transfer large data with reliability
* Big data technology used in event processing and data analysis & suggestions based on user data
* Technology for platforms and enablers for basing and integrating above-mentioned functionalities
* Data security through authentication/privilege and device-level, chip-level security
* UI/UX technologies for user interaction

<img src="https://raw.githubusercontent.com/jongkwang/IoTLabs/master/assets/img/case_research4.png">
> Image from LG CNS (http://blog.lgcns.com/817)

----


### International

#### AWS IoT

<img src="https://raw.githubusercontent.com/jongkwang/IoTLabs/master/assets/img/case_research_aws_iot1.png" height=100>


##### Characteristics

* Device Communication and Management
	* MQTT
	* WebSockets
	* HTTP
* Device Connection and Data Security
	* Authentication at all checkpoints and end-to-end encryption
	* Fine-tuned privilege policies
* Device Data Processing
	* Integration with existing AWS services: AWS Lambda, Amazon Kinesis, Amazon S3, Amazon Machine Learning, Amazon DynamoDB, Amazon CloudWatch, Amazon Elasticsearch Service
* Checking and Changing Device Status
	* Ability to check or change device status
	* Can set device to appear "turned on" indefinitely

<img src="https://raw.githubusercontent.com/jongkwang/IoTLabs/master/assets/img/case_research_aws_iot2.png">
> Image from AWS (https://aws.amazon.com/ko/iot/how-it-works/)

----

#### GE (General Electric)

> *(Moved to separate file for better organization) - [Link](https://github.com/jongkwang/IoTLabs/wiki/Case-Research---%EC%82%AC%EB%A1%80%EC%A1%B0%EC%82%AC)*

----

#### Intel

> *(Moved to separate file for better organization) - [Link](https://github.com/jongkwang/IoTLabs/wiki/Case-Research---%EC%82%AC%EB%A1%80%EC%A1%B0%EC%82%AC)*

----

#### Splunk

> *(Moved to separate file for better organization) - [Link](https://github.com/jongkwang/IoTLabs/wiki/Case-Research---%EC%82%AC%EB%A1%80%EC%A1%B0%EC%82%AC)*

----

## Get Involved

## Issues
Please post any questions or suggestions as a [Github Issue](https://github.com/jongkwang/IoTLabs/issues).
A response will be provided as soon as possible.
All questions and suggestions are welcome.

## Development Participation
Help us improve IoT Labs. Contributions are always welcome.

## Sponsor

* This project is sponsored by [KossLab](http://devlab.oss.kr/).


## License
* [IoT Labs](https://github.com/jongkwang/IoTLabs) is under [MIT](https://opensource.org/licenses/MIT) license.
* The respective licenses of all software and fonts must be honored.
* Software and fonts used:
	* Font
		* [스케치명조](http://www.asiasoft.co.kr/)
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
---

# <a name="iotlabs-korean">IoT Labs - 쉬운 IoT 개발 환경을 위한 프레임워크
@(_Open Frontier)[IoT Labs]


IoT Labs 는 IoT 개발에 어려움이 되는 (통신/저장/분석)을 프레임워크로 제공하여 보다 쉬운 개발 환경 제공을  목표로 합니다.

----

<!-- TOC depthFrom:1 depthTo:6 withLinks:1 updateOnSave:1 orderedList:0 -->

- [IoT Labs - 쉬운 IoT 개발 환경을 위한 프레임워크](#iotlabs-korean)
	- [News](#news-korean)
	- [IoT Labs 소개](#introduction-korean)
		- [구현 범위](#구현-범위)
	- [진행상황](#진행상황)
	- [API Docs](#-)
	- [참여](#참여)
		- [제안 / 이슈 / 테스트](#issue)
		- [개발 참여](#contribution-korean)
	- [라이센스](#license-korean)

<!-- /TOC -->

----
## Build Status
#### Platform/Java
[![Build Status](https://travis-ci.org/toori67/IoTLabs.svg?branch=master)](https://travis-ci.org/toori67/IoTLabs)
[![Coverage Status](https://coveralls.io/repos/github/toori67/IoTLabs/badge.svg?branch=master)](https://coveralls.io/github/toori67/IoTLabs?branch=master)


## <a name="news-korean">News
* 2016.06.23 : MQTT Broker 동작중 (24/7)

## <a name="introduction-korean">IoT Labs 소개
IoT 디바이스는 잘 다루지만 Web 개발에 익숙하지 않은 개발자를 위해 만들었습니다.
자신의 IoT 아이디어를 쉽게 구현 할 수 있게 도움을 주려는 것이 이 프로젝트의 목적 입니다.

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

전 세계적으로 IoT 의 영향력이 확대 되고, 우리나라에서도 많은 분야에서 IoT 를 접목하는 시도가 있습니다. 이에 따라 많은 학생이나 입문자들이 IoT 아이템을 개발/발전 시켜나가고 있지만, S/W 를 전공하지 않은 학생이나 일반인들에게는 큰 진입장벽이 되는 부분이 있습니다.

IoT Labs 에서는 이 진입장벽을 낮춤으로써 S/W  를 전공하지 않은 분들도 쉽게 IoT 아이디어를 개발/발전 시킬 수 있도록 돕고 싶습니다.

----

### 구현 범위

- Device 통신
	- MQTT, Web Socket, Bluetooth
- Data 저장
	- JDBC, JSON Store, Redis
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

## <a name="contribution-korean">Contribution
IoT Labs를 개선해주세요. Contribution은 언제나 환영합니다.

## Support

* 이 프로젝트는 [공개SW 개발자 Lab](http://devlab.oss.kr/)의 지원을 받고 있습니다.


## <a name="license-korean">License
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
