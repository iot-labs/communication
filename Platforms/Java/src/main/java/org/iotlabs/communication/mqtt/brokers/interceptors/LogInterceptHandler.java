package org.iotlabs.communication.mqtt.brokers.interceptors;

import io.moquette.interception.InterceptHandler;
import io.moquette.interception.messages.*;
import org.apache.log4j.Logger;
import org.iotlabs.util.StringUtils;

public class LogInterceptHandler implements InterceptHandler {

    private static final Logger logger = Logger.getLogger(LogInterceptHandler.class);

    @Override
    public void onPublish(InterceptPublishMessage msg) {
        logger.info("== Publish Event Received ==");
        logger.info("From : " + msg.getClientID());
        logger.info("Topic : " + msg.getTopicName());
        logger.info("Content : " + StringUtils.getString(msg.getPayload().array()));
    }

    @Override
    public void onSubscribe(InterceptSubscribeMessage msg) {
        logger.info("== Subscribe Event Received ==");
        logger.info("From : " + msg.getClientID());
        logger.info("Topic Filter : " + msg.getTopicFilter());
        logger.info("Qos : " + msg.getRequestedQos());
    }

    @Override
    public void onConnect(InterceptConnectMessage msg) {
        logger.info("== Connect Event Received ==");
        logger.info("From : " + msg.getClientID());
    }

    @Override
    public void onDisconnect(InterceptDisconnectMessage msg) {
        logger.info("== Disconnect Event Received ==");
        logger.info("From : " + msg.getClientID());
    }

    @Override
    public void onUnsubscribe(InterceptUnsubscribeMessage msg) {
        logger.info("== UnSubscribe Event Received ==");
        logger.info("From : " + msg.getClientID());
        logger.info("Topic Filter : " + msg.getTopicFilter());
    }
}
