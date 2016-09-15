package org.iotlabs.communication.mqtt.persistences;

import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import java.util.Enumeration;

public class MongoPersistence implements MqttClientPersistence {
    @Override
    public void open(String clientId, String serverURI) throws MqttPersistenceException {

    }

    @Override
    public void close() throws MqttPersistenceException {

    }

    @Override
    public void put(String key, MqttPersistable persistable) throws MqttPersistenceException {

    }

    @Override
    public MqttPersistable get(String key) throws MqttPersistenceException {
        return null;
    }

    @Override
    public void remove(String key) throws MqttPersistenceException {

    }

    @Override
    public Enumeration keys() throws MqttPersistenceException {
        return null;
    }

    @Override
    public void clear() throws MqttPersistenceException {

    }

    @Override
    public boolean containsKey(String key) throws MqttPersistenceException {
        return false;
    }
}
