#NodeJS Express + MQTT mosca

###How to Run
<pre> 
    <code>
        npm install
        npm start
        node clientSub.js {구독할 토픽}
    </code>
</pre>

1. Express와 Mqtt에 필요한 의존성(Dependency) 라이브러리들을 설치합니다.
2. NodeJS Express를 실행시킨다.
3. 구독받을 클라이언트를 토픽 아규먼트와 함께 실행시킵니다.
4. {Domain or Host IP} 에 연결합니다.


    ex) 토픽과 메세지 입력 폼을 통해 발행합니다. 
    localhost:3000/pub/{Topic}/{Message}
    
clientSub.js의 콘솔창을 통해 확인할 수 있습니다. 

