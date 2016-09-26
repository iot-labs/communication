package org.iotlabs.db.mybatis.mqttmsg;

import org.apache.ibatis.session.SqlSession;
import org.iotlabs.db.mybatis.MyBatisManager;
import org.iotlabs.models.mqtt.MqttMsg;

import java.util.HashMap;
import java.util.List;

public class MqttMsgDao {
  private SqlSession session = MyBatisManager.getSqlMapper().openSession();

  private HashMap<String,MqttMsg> mqttMsgSelectOne(String query,MqttMsg msg){
    HashMap<String,MqttMsg> result = session.selectOne(query,msg);
    session.close();
    return result;
  }
  private List<MqttMsg> mqttMsgSelectList(String query, MqttMsg msg){
    List<MqttMsg> result = session.selectList(query,msg);
    session.close();
    return result;
  }
  private int mqttMsgInsert(String query,MqttMsg map){
    return wrapSession(session.insert(query,map));
  }
  private int mqttMsgUpdate(String query,MqttMsg map){
    return wrapSession(session.update(query,map));
  }
  private int mqttMsgDelete(String query,MqttMsg map){
    return wrapSession(session.delete(query,map));
  }
  private int wrapSession(int result){
    if(result == 1)
      session.commit();
    else
      session.rollback();
    session.close();
    return result;
  }
}