#Raspberry Pi NodeJS MQTT CLIENT

###How to Run
<pre> 
    <code>
        npm install
        npm start
    </code>
</pre>

1. Mqtt Node 동작에 필요한 의존성(Dependency) 라이브러리들을 설치합니다.
2. NodeJS App을 실행시킨다.
3. 발행할 토픽을 입력한다.
4. 세팅한 GPIO 핀으로 부터 읽힌 값을 해당 토픽으로 Publish한다.


    ex) 
    ========================================
    ====   NodeJS + MQTT based on RPi   ====
    ========================================
    발행할 토픽을 입력하세요(exit 입력 시 종료).')
    > Pub_Topic
    > {다른 토픽 입력 시 다른 토픽으로 값 전송}
    
현재 보내는 토픽에 대한 값은 mqtt_clinet.js의 콘솔창 및 Broker(NodeJS Express + MQTT 프로젝트)을 통해 확인할 수 있습니다. 

