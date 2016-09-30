#How to run MQTT Server

* login github and download IotLabs
	* Go to `https://github.com/jongkwang/IoTLabs`
	* click `Clone or download`
	* unzip after click `download ZIP`

* Get MQTT Server's home directory on cmd
	* Turn on the CMD
	* get directory `./IoTLabs/Platforms/Java`

* Run MQTT Server
	* execute command `graldew -q start_mqtt_broker`
	* check message `MQTT Broker started`


# MQTT Receiver

* Check MQTT Receiver data
	* open file `./Platforms/Java/src/main/resources/config/mqtt_receiver.json`
	* check receiver's information
***
	{
      "broker_url" : "tcp://localhost:1883",
      "client_id" : "test_client_1",
      "topic" : "/sensor/test1",
      "qos" : 1,
      "is_persist" : true
    }
***
* Get MQTT Server's home directory on cmd
	* Turn on another the CMD
	* get directory `./IoTLabs/Platforms/Java`
	* execute command `graldew -q register_receivers -Pf=src/main/resources/config/mqtt_receiver.json`
	* check message `Receivers in ~ registered`
	* also check message from MQTT Server

