package org.iotlabs.db.mybatis.mqttmsg;

import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;
import org.iotlabs.db.mybatis.MyBatisManager;
import org.iotlabs.models.mqtt.MqttMsg;

import java.util.HashMap;
import java.util.List;

public class MqttMsgDao {
  private Gson gson = new Gson();
  private SqlSession session = MyBatisManager.getSqlMapper().openSession();
  private int result;

  private HashMap<String,MqttMsg> mqttMsgSelectOne(String query,MqttMsg msg){
    return session.selectOne(query,msg);
  }
  private List<MqttMsg> mqttMsgSelectList(String query, MqttMsg msg){
    return session.selectList(query,msg);
  }
  private int mqttMsgInsert(String query,MqttMsg map){
    this.result = session.insert(query,map);
    if(result == 1)
      session.commit();
    else
      session.rollback();
    return result;
  }
  private int mqttMsgUpdate(String query,MqttMsg map){
    this.result = session.update(query,map);
    if(result == 1)
      session.commit();
    else
      session.rollback();
    return result;
  }
  private int mqttMsgDelete(String query,MqttMsg map){
    this.result = session.delete(query,map);
    if(result == 1)
      session.commit();
    else
      session.rollback();
    return result;
  }
}