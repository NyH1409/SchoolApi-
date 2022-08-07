package com.example.demo.service.JDBCStudentsService.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConnection {
  public static Statement DBStatement() throws ClassNotFoundException, SQLException, IOException {
    Properties properties = new Properties();
    FileInputStream fileImp = new FileInputStream("C:/Users/Ny Hasina/Downloads/demo/src/main/resources/application.properties");
    properties.load(fileImp);
    Class.forName("org.postgresql.Driver");

    Connection connection = DriverManager.getConnection(properties.getProperty("spring.datasource.url"), properties.getProperty("spring.datasource.username"), properties.getProperty("spring.datasource.password"));

    Statement stm = connection.createStatement();

    return stm;
  }
}
