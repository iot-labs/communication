package org.iotlabs.communication.mqtt;

import org.iotlabs.models.mqtt.MqttMsg;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class MqttController{
  public static void main(String[] args) {
    Map map = new HashMap<>();
    MqttMsg msg = new MqttMsg();
    ThymeleafTemplateEngine engine = new ThymeleafTemplateEngine();
    map.put("hello","Hello World");
    get("/hello",(request,response)->new ModelAndView(map,"hello"),engine);
    get("/LedSwitcher",(request,response)->new ModelAndView(msg,"LedSwitcher"),engine);
  }
}
