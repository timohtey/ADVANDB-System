package db;

import java.sql.Connection;
import java.util.ResourceBundle;

public abstract class DBConnectionFactory {
    private static String driverName="";
    private static String url="";
    private static String username="";
    private static String password="";
    private static int typeOfFactory=0;
    private static final int MYSQL = 1;
    private static final int ORACLE = 2;
    
    public static DBConnectionFactory getInstace(){
        ResourceBundle rb = ResourceBundle.getBundle("database");
        driverName = rb.getString("driverName");
        url = rb.getString("url");
        username = rb.getString("username");
        password = rb.getString("password");
        typeOfFactory = Integer.parseInt(rb.getString("typeOfFactory"));
        switch (typeOfFactory){
            case MYSQL: return new DBConnectionFactoryImpl();
            case ORACLE: return new DBConnectionFactoryImpl();
        }
        return new DBConnectionFactoryImpl();
    } 
            
    public abstract Connection getConnection();

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
