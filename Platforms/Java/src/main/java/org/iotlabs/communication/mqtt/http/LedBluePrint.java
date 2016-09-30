package org.iotlabs.communication.mqtt.http;

import org.apache.log4j.Logger;
import org.iotlabs.communication.mqtt.MqttProxy;
import org.iotlabs.webserver.blueprints.AbstractBluePrint;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static spark.Spark.get;

public class LedBluePrint extends AbstractBluePrint {
  private static Logger log = Logger.getLogger(LedBluePrint.class);
  public LedBluePrint(){
    super("led");
  }
  @Override
  public void register(){
    get(getEndPoint("ledswitcher"),(req, res) ->renderContent("/templates/ledswitcher.html"));
    get(getEndPoint("red"), (req, res) ->{
      String clientId = req.queryParams("client_id");
      String topic = req.queryParams("topic");
      String payload = req.queryParams("payload");
      if (!topic.startsWith("/")){
        topic = "/" + topic;
      }
      MqttProxy.getInstance().publish(topic+"/"+clientId, payload, 1, false);
      return "send";
    });
    get(getEndPoint("green"), (req, res) ->{

    });
    get(getEndPoint("blue"), (req, res) ->{

    });
  }
  private String renderContent(String htmlFile) {
    try {
      // If you are using maven then your files
      // will be in a folder called resources.
      // getResource() gets that folder
      // and any files you specify.
      URL url = getClass().getResource(htmlFile);

      // Return a String which has all
      // the contents of the file.
      Path path = Paths.get(url.toURI());
      return new String(Files.readAllBytes(path), Charset.forName("utf-8"));
    } catch (IOException | URISyntaxException e) {
      // Add your own exception handlers here.
      log.error("tttttt", e);
    }
    return null;
  }
}

