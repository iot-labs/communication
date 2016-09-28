package org.iotlabs.communication.mqtt;

import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class MqttController{
  public static void main(String[] args) {
    Map map = new HashMap<>();
    map.put("hello","Hello World");
    get("/hello",(request,response)->new ModelAndView(map,"hello"),new ThymeleafTemplateEngine());
  }
}
