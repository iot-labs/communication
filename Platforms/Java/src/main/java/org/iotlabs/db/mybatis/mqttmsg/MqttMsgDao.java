package org.iotlabs.db.mybatis.mqttmsg;

import org.apache.ibatis.session.SqlSession;
import org.iotlabs.db.mybatis.MyBatisManager;
import org.iotlabs.models.mqtt.MqttMsg;

import java.util.HashMap;
import java.util.List;

class MqttMsgDao implements MqttMsgService{
  private SqlSession session = MyBatisManager.getSqlMapper().openSession();
  public HashMap<String,MqttMsg> mqttMsgSelectOne(String query,MqttMsg msg){
    HashMap<String,MqttMsg> result = session.selectOne(query,msg);
    session.close();
    return result;
  }
  public List<MqttMsg> mqttMsgSelectList(String query, MqttMsg msg){
    List<MqttMsg> result = session.selectList(query,msg);
    session.close();
    return result;
  }
  public int mqttMsgInsert(String query,MqttMsg map){
    return wrapSession(session.insert(query,map));
  }
  public int mqttMsgUpdate(String query,MqttMsg map){
    return wrapSession(session.update(query,map));
  }
  public int mqttMsgDelete(String query,MqttMsg map){
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