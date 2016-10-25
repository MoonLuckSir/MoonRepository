package com.yitong.commons.util;

import java.beans.PropertyVetoException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

 
import javax.resource.spi.ConnectionManager;
import javax.sql.DataSource;
 
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.impl.DefaultConnectionTester;
import com.yitong.commons.model.Properties;

import java.sql.Connection;

/**
 * tomcat数据库连接池管理类<br>
 * 使用为tomcat部署环境<br>
 * 需要在类路径下准备数据库连接配置文件dbcp.properties
 * 
 *
 * 
 */

public final  class DBManager {
		private static String  driverClassName  = Properties.getString("driverClassName");
		private static String  url  = Properties.getString("url");
		private static int  maxPoolSize  = Properties.getInt("maxPoolSize");
		private static int  minPoolSize  = Properties.getInt("minPoolSize");
		private static int  initialPoolSize  = Properties.getInt("initialPoolSize");
		private static int  maxIdleTime  = Properties.getInt("maxIdleTime");
		private static int  checkoutTimeout  = Properties.getInt("checkoutTimeout");
		
	    private static DBManager dbPool;  
	    private ComboPooledDataSource dataSource;  
	   
	    static {  
	        dbPool = new DBManager();  
	    }  
	  
	    public DBManager() {  
	    	
	        try {  
	            dataSource = new ComboPooledDataSource();  
	            dataSource.setUser(null);  
	            dataSource.setPassword(null);  
	            dataSource.setJdbcUrl(url);  
	            dataSource.setDriverClass(driverClassName);  
	            // 设置初始连接池的大小！  
	            dataSource.setInitialPoolSize(initialPoolSize);  
	            // 设置连接池的最小值！   
	            dataSource.setMinPoolSize(minPoolSize);  
	            // 设置连接池的最大值！   
	            dataSource.setMaxPoolSize(maxPoolSize);  
	          
	            // 设置连接池的最大空闲时间！  
	            dataSource.setMaxIdleTime(maxIdleTime);  
	            
	        
	            
	        } catch (PropertyVetoException e) {  
	            throw new RuntimeException(e);  
	        }  
	    }  
	  
	    public final static DBManager getInstance() {  
	        return dbPool;  
	    }  
	  
	    public final Connection getConnection() {  
	        try {  
	            return dataSource.getConnection();  
	        } catch (SQLException e) {  
	            throw new RuntimeException("无法从数据源获取连接 ", e);  
	        }  
	    }  
	  
	    
	    public static void main(String[] args) throws SQLException {  
	        Connection con = null;  
	        try {  
	            con = DBManager.getInstance().getConnection();  
	            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM LESOGO_USER");  
	            while (rs.next()) {  
	                System.out.println(rs.getObject(1));  
	                System.out.println(rs.getObject(2));  
	                System.out.println(rs.getObject(3));  
	            }  
	        } catch (Exception e) {  
	        } finally {  
	            if (con != null)  
	                con.close();  
	        }  
	    }  
	
	    
	    /**
	     * 关闭连接
	     * 
	     * @param conn
	     *            需要关闭的连接
	     */

	    public static void closeConn(Connection conn,Statement stat) {
	        try {
	        	  if (stat != null) {
	        		  stat.close();
		            }
	            if (conn != null && !conn.isClosed()) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
}
