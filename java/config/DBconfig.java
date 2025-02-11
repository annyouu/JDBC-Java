package config;

import java.io.InputStream;
import java.util.Properties;

public class DBconfig {
    private Properties dbinfo = new Properties();
    
    public DBconfig() {
        dbinfo = new Properties();
        
        // プロパティファイルを相対パスで読み込む
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config/dbconfig.properties")) {
            if (input == null) {
                System.out.println("プロパティファイルが見つかりません");
                return;
            }
            dbinfo.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // プロパティファイルの内容を返す
    public String getUrl() {
        return dbinfo.getProperty("url");
    }

    public String getUser() {
        return dbinfo.getProperty("user");
    }

    public String getPassword() {
        return dbinfo.getProperty("password");
    }
}