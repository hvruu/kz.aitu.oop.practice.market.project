package org.example.HibernateUtil;
import org.example.products.Products;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.List;

public class DatabaseSave {

    private static final DatabaseSave instance = new DatabaseSave();
    private DatabaseSave(){}
    public static DatabaseSave getInstance() {
        return instance;
    }
    public static void save(Products products) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.saveOrUpdate(products);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void signUp(String loginUp, String passwordUp){

        String userName = "root";
        String password = "1234567887";
        String connectionURL = "jdbc:mysql://localhost:3306/shop";

        try (Connection connection = DriverManager.getConnection(connectionURL, userName, password)) {
            PreparedStatement statement = connection.prepareStatement("insert into customers (login, password) values (?, ?);");
            statement.setString(1, loginUp);
            statement.setString(2, passwordUp);
            statement.executeUpdate();

            System.out.println("You signed up successfully. Rerun program.");
        } catch (SQLException e) {
            System.err.println("Error while inserting data into the database: " + e.getMessage());
        }
    }

    public static boolean isPersonal(String loginp){
        String userName = "root";
        String password = "1234567887";
        String connectionURL = "jdbc:mysql://localhost:3306/shop";
        String stat = "Personal";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: unable to load driver class!");
            e.printStackTrace();
            return false;
        }

        try (Connection conn = DriverManager.getConnection(connectionURL, userName, password);
             Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery("select * from customers");
            while (rs.next()) {
                if (loginp.equals(rs.getString(2)) && stat.equals(rs.getString(6))) {
                    return true;
                }
            }
            System.out.println("Access denied");
            return false;
        } catch (SQLException e) {
            System.out.println("Error: unable to connect to database!");
            e.printStackTrace();
            return false;
        }

    }
    public static boolean signIn(String loginIn, String passwordIn) {
        String userName = "root";
        String password = "1234567887";
        String connectionURL = "jdbc:mysql://localhost:3306/shop";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: unable to load driver class!");
            e.printStackTrace();
            return false;
        }

        try (Connection conn = DriverManager.getConnection(connectionURL, userName, password);
             Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery("select * from customers");
            while (rs.next()) {
                if (loginIn.equals(rs.getString(2)) && passwordIn.equals(rs.getString(3))) {
                    return true;
                }
            }
            System.out.println("incorrect login or password");
            return false;
        } catch (SQLException e) {
            System.out.println("Error: unable to connect to database!");
            e.printStackTrace();
            return false;
        }
    }

    public static void delete(Products products) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.delete(products);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static <T> List<T> getDataFromDB(Class<T> entityClass) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //return session.get(Car.class,0); // return one object
            return session.createQuery("FROM " + entityClass.getName(), entityClass).list(); // return list of objects
        }
    }
}
