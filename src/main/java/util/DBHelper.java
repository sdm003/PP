package util;

import dao.factory.UserDaoFactory;
import dao.factory.UserDaoFactoryHibernate;
import dao.factory.UserDaoFactoryJDBC;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBHelper {
    private static DBHelper dbHelper;

    private DBHelper() {
    }

    public static DBHelper getInstance() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
        }
        return dbHelper;
    }

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").          //login
                    append("password=root");       //password

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
    public SessionFactory getSessionFactory() {
        Configuration configuration = getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/db_example?serverTimezone=UTC");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "validate");
        return configuration;
    }

    public static UserDaoFactory getUserDaoByConfig(){
        Properties properties = new Properties();
        UserDaoFactory userDaoFactory;
        try (InputStream is = new FileInputStream("/home/sdm/snap/Projects/PreProject/src/main/resources/config.properties")) {
            properties.load(is);
            switch (properties.getProperty("typeOfDao")) {
                case "jdbc":
                    userDaoFactory = new UserDaoFactoryJDBC();
                case "hibernate":
                    userDaoFactory = new UserDaoFactoryHibernate();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + properties.getProperty("typeOfDao"));
            }
            return userDaoFactory;

        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Unexpected value: " + properties.getProperty("typeOfDao"));
        }
    }
}
