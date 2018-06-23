#NodeJS Express + MQTT mosca

###How to Run
<pre> 
    <code>
        npm install
        node bin/www
        node clientSub.js
    </code>
</pre>

1. Express와 Mqtt에 필요한 의존성(Dependency) 라이브러리들을 설치합니다.
2. NodeJS Express를 실행시킨다.
3. 구독받을 클라이언트를 실행시킵니다.
4. {Host IP}/pub/{Publish하고자 하는 메세지} 에 연결합니다.


    ex) 웹 브라우저의 주소창에 다음과 같이 입력합니다. 
    localhost:3000/pub/Test Message
    
clientSub.js의 콘솔창을 통해 확인할 수 있습니다. 

