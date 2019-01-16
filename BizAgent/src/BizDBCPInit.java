import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class BizDBCPInit {
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String USER_NAME = "root";
	private final String PASSWORD = "sjk4556!!22";
	private final String DB_URL = "jdbc:mysql://210.114.225.53/dhn?characterEncoding=utf8";  
	
	private BizDBCPInit() {
	
	}
	
	private static class Singleton {
		private static final BizDBCPInit instance = new BizDBCPInit();
	}
	
	public static BizDBCPInit getInstance() {
		return Singleton.instance;
	}
	
	public void initConnectionPool() {
		try {
			Class.forName(JDBC_DRIVER);
		
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(DB_URL, USER_NAME, PASSWORD);

			//close 할경우 종료하지 않고 커넥션 풀에 반환
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);

			//커넥션이 유효한지 확인
			poolableConnFactory.setValidationQuery(" SELECT 1 FROM DUAL ");

			//커넥션 풀의 설정 정보를 생성
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();

			//유효 커넥션 검사 주기
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 1L);

			//풀에 있는 커넥션이 유효한지 검사 유무 설정
			poolConfig.setTestWhileIdle(true);

			//기본값  : false /true 일 경우 validationQuery 를 매번 수행한다.
			poolConfig.setTestOnBorrow(false);

			//커넥션 최소갯수 설정
			poolConfig.setMinIdle(10);

		    poolConfig.setMaxIdle(50);

			//커넥션 최대 갯수 설정
			poolConfig.setMaxTotal(50);

			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<PoolableConnection>(poolableConnFactory,poolConfig);

			//PoolableConnectionFactory 커넥션 풀 연결
			poolableConnFactory.setPool(connectionPool);

			//커넥션 풀을 제공하는 jdbc 드라이버 등록
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");

			PoolingDriver driver = (PoolingDriver)DriverManager.getDriver("jdbc:apache:commons:dbcp:");

			driver.registerPool("cp", connectionPool);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static final Connection getConnection() throws Exception {

	    String jdbcDriver = "jdbc:apache:commons:dbcp:cp";

	            return DriverManager.getConnection(jdbcDriver);

	    }
}