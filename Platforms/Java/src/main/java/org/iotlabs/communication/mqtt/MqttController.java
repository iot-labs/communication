package org.iotlabs.communication.mqtt;

import com.google.gson.Gson;

import static spark.Spark.*;

public class MqttController{

  static{
    get("/index",(request,response)->"request");
  }
}
