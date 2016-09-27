package org.iotlabs.db.mybatis.mqttmsg;

import org.apache.ibatis.session.SqlSession;
import org.iotlabs.db.mybatis.MyBatisManager;
import org.iotlabs.models.mqtt.MqttMsg;

import java.util.HashMap;
import java.util.List;

class MqttMsgDao{
  private SqlSession session = MyBatisManager.getSqlMapper().openSession();
  private int result;
  public HashMap<String,MqttMsg> mqttMsgSelectOne(String query,MqttMsg msg){
    HashMap<String,MqttMsg> result = null;
    try {
      result = session.selectOne(query, msg);
    }finally {
      session.close();
    }
    return result;
  }
  public List<MqttMsg> mqttMsgSelectList(String query, MqttMsg msg){
    List<MqttMsg> result = null;
    try{
      result = session.selectList(query,msg);
    }finally {
      session.close();
    }
    return result;
  }
  public int mqttMsgInsert(String query,MqttMsg map){
    try{
      this.result = wrapSession(session.insert(query,map));
    }finally {
      session.close();
    }
    return this.result;
  }
  public int mqttMsgUpdate(String query,MqttMsg map){
    try{
      this.result = wrapSession(session.update(query,map));
    }finally {
      session.close();
    }
    return this.result;
  }
  public int mqttMsgDelete(String query,MqttMsg map){
    try{
      this.result = wrapSession(session.delete(query,map));
    }finally {
      session.close();
    }
    return this.result;
  }
  private int wrapSession(int result){

  if (result == 1)
    session.commit();
  else
    session.rollback();

    return result;
  }
}