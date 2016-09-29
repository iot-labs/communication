package org.iotlabs.projects.led_tutorial.webclient;

import org.iotlabs.models.mqtt.MqttMsg;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class MqttController{
  public static void main(String[] args) {
    Map map = new HashMap<>();
    MqttMsg msg = new MqttMsg();
    MustacheTemplateEngine engine = new MustacheTemplateEngine();
    get("/LedSwitcher",(request,response)->new ModelAndView(msg,"LedSwitcher"),engine);
  }
}
