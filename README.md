# IoT Labs - 쉬운 IoT 개발 환경을 위한 프레임워크 <br/> IoT Labs - A Framework for Simpler IoT Development Environment
@(_Open Frontier)[IoT Labs]

IoT Labs는 IoT 개발에 어려움이 되는 (통신/저장/분석)을 프레임워크로 제공하여 보다 쉬운 개발 환경 제공을 목표로 합니다. <br/>
IoT Labs aims to provide a framework for (communication/storage/analysis) in IoT development for increased productivity.

----

<!-- TOC depthFrom:1 depthTo:1 withLinks:1 updateOnSave:1 orderedList:0 -->

- [IoT Labs - English](#iotlabs-english)

<!-- /TOC -->

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
[![Build Status](https://api.travis-ci.org/jongkwang/IoTLabs.svg?branch=master)](https://travis-ci.org/jongkwang/IoTLabs)
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
