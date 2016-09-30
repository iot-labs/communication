package org.iotlabs.projects.led_tutorial.webclient;

import org.apache.log4j.Logger;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.port;

public class MqttController{
  private static Logger log = Logger.getLogger(MqttController.class);

  public static void main(String[] args) {
    log.debug("===================로그");
    Map map = new HashMap<>();
    map.put("text","텍스트");
    port(8080);
    get("/",(req,res)->new ModelAndView(map,"main"),new ThymeleafTemplateEngine());
    get("/led",(req,res)->new ModelAndView(map,"/ledswitcher"),new ThymeleafTemplateEngine());
  }
}
