package org.iotlabs;

import io.moquette.interception.AbstractInterceptHandler;
import io.moquette.interception.messages.InterceptPublishMessage;
import org.apache.commons.cli.*;
import org.iotlabs.communication.mqtt.SimpleMqttBroker;

import java.io.IOException;
import java.util.Arrays;

/**
 * the very entrance of IoTLabs
 */
public class Runner {

    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.parsingArg(args);
    }

    private Options options = new Options();

    private void parsingArg(String[] args) {
        CommandLineParser commandLineParser = new DefaultParser();
        options.addOption(Option.builder("mqtt").hasArg(true).build());
        CommandLine cmd;
        try {
            cmd = commandLineParser.parse( options, args);
        } catch (ParseException e) {
            usage();
            return;
        }
        if (cmd.hasOption("mqtt")) {
            String mqttOptionValue = cmd.getOptionValue("mqtt");
            if (mqttOptionValue.equals("start_broker")) {
                try {
                    startMqttBroker();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            usage();
        }
    }

    private void startMqttBroker() throws IOException {
        SimpleMqttBroker.getInstance().start(Arrays.asList(new AbstractInterceptHandler() {
            @Override
            public void onPublish(InterceptPublishMessage msg) {
                System.out.println("== Publish Event Recv ==");
                System.out.println("From : " + msg.getClientID());
                System.out.println("Topic : " + msg.getTopicName());
                System.out.println("Content : " + new String(msg.getPayload().array()));
            }
        }));
    }

    private void usage() {
        System.out.println("Usage: -mqtt start_broker");
    }


}
