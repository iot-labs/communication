package org.iotlabs.db.mybatis.mqttmsg;

import org.iotlabs.models.mqtt.MqttMsg;

import java.util.HashMap;
import java.util.List;

public interface MqttMsgService{
  HashMap<String,MqttMsg> mqttMsgSelectOne(String query, MqttMsg msg);
  List<MqttMsg> mqttMsgSelectList(String query, MqttMsg msg);
  int mqttMsgInsert(String query,MqttMsg map);
  int mqttMsgUpdate(String query,MqttMsg map);
  int mqttMsgDelete(String query,MqttMsg map);
}
