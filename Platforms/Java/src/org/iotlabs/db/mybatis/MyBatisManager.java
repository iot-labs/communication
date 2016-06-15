package org.iotlabs.db.mybatis;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisManager {
	
	private static final SqlSessionFactory sqlMapper;
	static {
		try {
			String resource = "org/iotlabs/db/mybatis/Configuration.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	private MyBatisManager() {
	}
	public static SqlSessionFactory getSqlMapper() {
		return sqlMapper;
	}
}
