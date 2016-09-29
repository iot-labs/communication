#How to run MQTT Server (Powered by mosquitto)

* Download the platform from repository and unzip it.

  <center><img src = "../../assets/img/readme_mqtt/clone.png"/></center>

* Turn on the CMD and change the directory to the MQTT Server's home directory

  <center><img src = "../../assets/img/readme_mqtt/cmd.png"/></center>

  ```

  C:\Users\Administration > cd [$APP_HOME]

  ```

* Type following statement to run MQTT Server

  ```

  [$APP_HOME] > graldew -q start_mqtt_broker

  ```

* After starting task is done, a message 'MQTT Broker started' comes up.

  <center><img src = "../../assets/img/readme_mqtt/mqtt_start.png"/></center>
  
# MQTT Receiver Registration

* Make JSON file to register MQTT Receivers.

  ** Example file
  
     >> ../Platforms/Java/src/main/resources/config/mqtt_receiver.json
     
  
