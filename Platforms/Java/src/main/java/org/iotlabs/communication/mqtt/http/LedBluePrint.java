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
        MqttProxy.getInstance().initPublisherPool(5, "tcp://192.168.0.159:1883", false);
    }
    @Override
    public void register(){
        get(getEndPoint("ledswitcher"),(req, res) ->renderContent("/templates/ledswitcher.html"));
        get(getEndPoint("red"), (req, res) ->{
            MqttProxy.getInstance().publish("/IoTLabs/LED", "r", 1, false);
            return "send";
        });
        get(getEndPoint("green"), (req, res) ->{
            MqttProxy.getInstance().publish("/IoTLabs/LED", "g", 1, false);
            return "send";
        });
        get(getEndPoint("blue"), (req, res) ->{
            MqttProxy.getInstance().publish("/IoTLabs/LED", "b", 1, false);
            return "send";
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

