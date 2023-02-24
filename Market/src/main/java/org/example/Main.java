package org.example;
import org.example.HibernateUtil.DatabaseSave;
import org.example.products.Products;
import org.example.products.milkProduct.Milk;
import org.example.products.milkProduct.SourCream;
import org.example.products.vegetables.carrot;
import org.example.products.vegetables.potato;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Products> productsArrayList = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);

    public static void persMenu(String logg){
        if(DatabaseSave.isPersonal(logg)){
            System.out.println("1.добавить предмет\n2.Удалить предмет");
            int num = scan.nextInt();
            switch (num) {
                case 1 -> {
                    System.out.println("Enter name of product you need to add\n(Milk, Sour cream, Carrot, Potato)");
                    String pro = scan.next();
                    switch (pro) {
                        case "Milk" -> {
                            System.out.println("Enter price");
                            double mprice = scan.nextInt();
                            System.out.println("Enter L of Milk");
                            int Lmilk = scan.nextInt();
                            System.out.println("Enter best before date (DDMMYYYY)");
                            int bbdmilk = scan.nextInt();
                            System.out.println("Enter % of fat");
                            double percFat = scan.nextDouble();
                            System.out.println("Enter type of package");
                            String pack = scan.next();
                            System.out.println("Enter calcium content");
                            int calmilk = scan.nextInt();
                            System.out.println("Enter lactose content");
                            int lacmilk = scan.nextInt();
                            System.out.println("Enter nutritional value content");
                            int nutmilk = scan.nextInt();
                            System.out.println("Enter manufacturer");
                            String manuf = scan.next();
                            Milk m = new Milk("Milk", mprice, Lmilk, bbdmilk, percFat, pack, calmilk, lacmilk, nutmilk, manuf);
                            DatabaseSave.save(m);
                        }
                        case "Sour cream" -> {
                            System.out.println("Enter price");
                            double SCprice = scan.nextInt();
                            System.out.println("Enter L of sour cream");
                            int LsourC = scan.nextInt();
                            System.out.println("Enter % of fat");
                            double percFatSC = scan.nextDouble();
                            System.out.println("Enter best before date (DDMMYYYY)");
                            int bbdSC = scan.nextInt();
                            System.out.println("Enter calcium content");
                            int calSC = scan.nextInt();
                            System.out.println("Enter lactose content");
                            int lacSC = scan.nextInt();
                            System.out.println("Enter nutritional value content");
                            int nutSC = scan.nextInt();
                            System.out.println("Enter manufacturer");
                            String manufSC = scan.next();
                            SourCream s = new SourCream("Sour cream", SCprice, LsourC, percFatSC, bbdSC, calSC, lacSC, nutSC, manufSC);
                            DatabaseSave.save(s);
                        }
                        case "Carrot" -> {
                            System.out.println("Enter price");
                            double Cprice = scan.nextInt();
                            System.out.println("Enter kg");
                            int Ckg = scan.nextInt();
                            System.out.println("Enter nutritional value content");
                            int nutC = scan.nextInt();
                            System.out.println("Enter best before date (DDMMYYYY)");
                            int bbdC = scan.nextInt();
                            System.out.println("is grew up in greenhouse?");
                            boolean isGreenC = scan.nextBoolean();
                            System.out.println("is carrot purified?");
                            boolean ispurC = scan.nextBoolean();
                            carrot c = new carrot("Carrot", Cprice, Ckg, nutC, bbdC, isGreenC, ispurC, "root plant");
                            DatabaseSave.save(c);
                        }
                        case "Potato" -> {
                            System.out.println("Enter price");
                            double Pprice = scan.nextInt();
                            System.out.println("Enter kg");
                            int Pkg = scan.nextInt();
                            System.out.println("Enter nutritional value content");
                            int nutP = scan.nextInt();
                            System.out.println("Enter best before date (DDMMYYYY)");
                            int bbdP = scan.nextInt();
                            System.out.println("is grew up in greenhouse?");
                            boolean isGreenP = scan.nextBoolean();
                            System.out.println("is potato purified?");
                            boolean ispurP = scan.nextBoolean();
                            potato p = new potato("Potato", Pprice, bbdP, nutP, Pkg, isGreenP, ispurP, "vascular plants");
                            DatabaseSave.save(p);
                        }
                    }
                }
                case 2 -> {
                    printOutAll();
                    System.out.println("Напишите название продукта который хотите удалить с базы данных");
                    String dbname = scan.next();
                    System.out.println("Напишите айди продукта который хотите удалить с базы данных");
                    int id = scan.nextInt();
                    deleteObject(logg, dbname, id);
                }
            }
            mainMenu(logg);
        }
        else{
            System.exit(0);
        }
    }

    static void orderdb(int id1, String log, String order, int mass, int price){

        String userName = "root";
        String password = "1234567887";
        String connectionURL = "jdbc:mysql://localhost:3306/shop";

        try (Connection conn = DriverManager.getConnection(connectionURL, userName, password)) {
            PreparedStatement statement = null;

            String sql = "INSERT INTO ord(id, loginname, ord, ordermass, orderprice) VALUES (?, ?, ?, ?, ?)";

            try {
                statement = conn.prepareStatement(sql);
                statement.setInt(1, id1);
                statement.setString(2, log);
                statement.setString(3, order);
                statement.setInt(4, mass);
                statement.setInt(5, price);
                statement.executeUpdate();
            } catch (SQLException e ) {
                e.printStackTrace();
            } finally {
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch (SQLException e) {
            System.err.println("Error while inserting data into the database: " + e.getMessage());
        }
    }

    static void deleteObject(String log, String dbname, int id) {
        String userName = "root";
        String password = "1234567887";
        String connectionURL = "jdbc:mysql://localhost:3306/shop";
        try (Connection conn = DriverManager.getConnection(connectionURL, userName, password);
             Statement statement = conn.createStatement()) {
            int rows = statement.executeUpdate("DELETE FROM " + dbname + " WHERE Id = " + id);
        } catch (SQLException e) {
            System.out.println("Error: unable to connect to database!");
            e.printStackTrace();
            System.exit(0);
        }
    }

    static void mainMenu(String log) {
        System.out.println("1. Заказать продукт\n2. Вывести список продуктов.\n3. Меню персонала.\n0. Выйти.");
        int number = scan.nextInt();
        switch (number) {
            case 1 -> cat(log);
            case 2 -> {
                System.out.println("напишите имя продукта чей список хотите увидеть (milk, sourcream, potato, carrot");
                String dbname = scan.next();
                printOut(log, dbname, true);
            }
            case 3 -> persMenu(log);
            case 0 -> System.exit(0);
        }
    }

    static void printOutAll() {
        String userName = "root";
        String password = "1234567887";
        String connectionURL = "jdbc:mysql://localhost:3306/shop";
        try (Connection conn = DriverManager.getConnection(connectionURL, userName, password);
             Statement statement = conn.createStatement()) {

            ResultSet milk = statement.executeQuery("select id, name, price, mass from milk");
            System.out.println("id\tname\t\tprice\tmass");
            while (milk.next()) {
                String Name = milk.getString("name");
                int ID = milk.getInt("id");
                float price = milk.getFloat("price");
                int mass = milk.getInt("mass");
                System.out.println(ID + "\t" + Name +
                        "\t\t" + price + "\t" + mass);
            }

            ResultSet SourCream = statement.executeQuery("select id, name, price, mass from sourcream");
            while (SourCream.next()) {
                String Name = SourCream.getString("name");
                int ID = SourCream.getInt("id");
                float price = SourCream.getFloat("price");
                int mass = SourCream.getInt("mass");
                System.out.println(ID + "\t" + Name +
                        "\t\t" + price + "\t" + mass);
            }

            ResultSet carrot = statement.executeQuery("select id, name, price, mass from carrot");
            while (carrot.next()) {
                String Name = carrot.getString("name");
                int ID = carrot.getInt("id");
                float price = carrot.getFloat("price");
                int mass = carrot.getInt("mass");
                System.out.println(ID + "\t" + Name +
                        "\t\t" + price + "\t" + mass);
            }

            ResultSet potato = statement.executeQuery("select id, name, price, mass from potato");
            while (potato.next()) {
                String Name = potato.getString("name");
                int ID = potato.getInt("id");
                float price = potato.getFloat("price");
                int mass = potato.getInt("mass");
                System.out.println(ID + "\t" + Name +
                        "\t\t" + price + "\t" + mass);
            }
        } catch (SQLException e) {
            System.out.println("Error: unable to connect to database!");
            e.printStackTrace();
            System.exit(0);
        }
    }
    static int getid(String log) {
        String userName = "root";
        String password = "1234567887";
        String connectionURL = "jdbc:mysql://localhost:3306/shop";

        try (Connection conn = DriverManager.getConnection(connectionURL, userName, password);
             Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery("select * from customers");
            while (rs.next()) {
                if (log.equals(rs.getString(2)))
                    return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error: unable to connect to database!");
            e.printStackTrace();
            System.exit(0);
        }
        return 0;
    }

    static int getprod(int id, String prod, String query) {
        String userName = "root";
        String password = "1234567887";
        String connectionURL = "jdbc:mysql://localhost:3306/shop";

        try (Connection conn = DriverManager.getConnection(connectionURL, userName, password);
             Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery("select mass, price from " + prod + " where id = " + id);
            while (rs.next()) {
                if(query == "mass")
                    return rs.getInt(1);
                if(query == "price")
                    return rs.getInt(2);
            }
        }catch (SQLException e) {
            System.out.println("Error: unable to connect to database!");
            e.printStackTrace();
            System.exit(0);
        }
        return 0;
    }

    static void cat(String log){

        String product = "";
        int pur = 0;
        int green = 0;

        System.out.println("1)Vegetables\n2)Milk products\n0)Main menu");
        int choice = scan.nextInt();
        switch (choice){
            case 1:
                System.out.println("1)Potato\n2)Carrot");
                int choice1 = scan.nextInt();
                switch (choice1) {
                    case 1 -> {
                        product = "potato";
                        System.out.println("1)Purified\n2)Not purified");
                        int choice11 = scan.nextInt();
                        switch (choice11) {
                            case 1 -> {
                                pur = 1;
                                System.out.println("1)Grew up in greenhouse\n2)Natural");
                                int choice111 = scan.nextInt();
                                switch (choice111) {
                                    case 1 -> green = 1;
                                    case 2 -> green = 0;
                                }
                            }
                            case 2 -> {
                                pur = 0;
                                System.out.println("1)Grew up in greenhouse\n2)Natural");
                                int choice112 = scan.nextInt();
                                green = switch (choice112) {
                                    case 1 -> 1;
                                    case 2 -> 0;
                                    default -> green;
                                };
                            }
                        }
                    }
                    case 2 -> {
                        product = "carrot";
                        System.out.println("1)Purified\n2)Not purified");
                        int choice12 = scan.nextInt();
                        switch (choice12) {
                            case 1 -> {
                                pur = 1;
                                System.out.println("1)Grew up in greenhouse\n2)Natural");
                                int choice111 = scan.nextInt();
                                green = switch (choice111) {
                                    case 1 -> 1;
                                    case 2 -> 0;
                                    default -> green;
                                };
                            }
                            case 2 -> {
                                pur = 0;
                                System.out.println("1)Grew up in greenhouse\n2)Natural");
                                int choice112 = scan.nextInt();
                                green = switch (choice112) {
                                    case 1 -> 1;
                                    case 2 -> 0;
                                    default -> green;
                                };
                            }
                        }
                    }
                }

                prod(log, product, false, pur, green);
                System.out.println("введите айди интересующего вас продукта");
                int pid = scan.nextInt();
                int cid = getid(log);
                int prodmassv = getprod(pid, product, "mass");
                int prodpricev = getprod(pid, product, "price");
                orderdb(cid, log, product , prodmassv, prodpricev);
                deleteObject(log, product, pid);
                System.out.println("ваш заказ принят! ожидайте.");
                mainMenu(log);


            case 2:
                System.out.println("1)Milk\n2)Sour cream");
                int choicem = scan.nextInt();
                switch (choicem){
                    case 1:
                        System.out.println("доступные продукты в категорий Молока");
                        printOut(log, "milk", false);
                        System.out.println("введите айди интересующего вас продукта");
                        int pid1 = scan.nextInt();
                        int cid1 = getid(log);
                        int prodmass = getprod(pid1, "milk", "mass");
                        int prodprice = getprod(pid1, "milk", "price");
                        orderdb(cid1, log, "milk" , prodmass, prodprice);
                        deleteObject(log, "milk", pid1);
                        System.out.println("ваш заказ принят! ожидайте.");
                    case 2:
                        System.out.println("доступные продукты в категорий сметана");
                        printOut(log, "sourcream",false);
                        System.out.println("введите айди интересующего вас продукта");
                        int pid2 = scan.nextInt();
                        int cid2 = getid(log);
                        int prodmass2 = getprod(pid2, "sourcream", "mass");
                        int prodprice2 = getprod(pid2, "sourcream", "price");
                        orderdb(cid2, log, "sourcream" , prodmass2, prodprice2);
                        deleteObject(log, "sourcream", pid2);
                        System.out.println("ваш заказ принят! ожидайте.");
                    default:
                        System.out.println("Вы ввели неверное число!");
                        System.exit(0);
                }
            case 0:
                mainMenu(log);
                break;
            default:
                System.out.println("You entered incorrect number");
                System.exit(0);
        }
    }

    static void prod(String log, String dbname, boolean need, int pur, int green) {
        String userName = "root";
        String password = "1234567887";
        String connectionURL = "jdbc:mysql://localhost:3306/shop";

        try (Connection conn = DriverManager.getConnection(connectionURL, userName, password);
             Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery("select * from " + dbname);

            System.out.println("id\tname\t\tprice\tmass");
            while (rs.next()) {
                if (green == (rs.getInt(5)) && pur == (rs.getInt(6))) {
                    String Name = rs.getString("name");
                    int ID = rs.getInt("id");
                    float price = rs.getFloat("price");
                    int mass = rs.getInt("mass");
                    System.out.println(ID + "\t" + Name +
                            "\t\t" + price + "\t" + mass);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: unable to connect to database!");
            e.printStackTrace();
            System.exit(0);
        }
        if(need == true)
            mainMenu(log);
    }



    static void printOut(String log, String dbname, boolean need) {
        String userName = "root";
        String password = "1234567887";
        String connectionURL = "jdbc:mysql://localhost:3306/shop";
        try (Connection conn = DriverManager.getConnection(connectionURL, userName, password);
             Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery("select id, name, price, mass from " + dbname);

            System.out.println("id\tname\t\tprice\tmass");
            while (rs.next()) {
                String Name = rs.getString("name");
                int ID = rs.getInt("id");
                float price = rs.getFloat("price");
                int mass = rs.getInt("mass");
                System.out.println(ID + "\t" + Name +
                        "\t\t" + price + "\t" + mass);
            }
        } catch (SQLException e) {
            System.out.println("Error: unable to connect to database!");
            e.printStackTrace();
            System.exit(0);
        }
        if(need == true)
            mainMenu(log);
    }


    public static void main(String[] args) {

        productsArrayList.addAll(DatabaseSave.getDataFromDB(Milk.class));
        productsArrayList.addAll(DatabaseSave.getDataFromDB(SourCream.class));

        productsArrayList.addAll(DatabaseSave.getDataFromDB(carrot.class));
        productsArrayList.addAll(DatabaseSave.getDataFromDB(potato.class));

        Scanner sc = new Scanner(System.in);



        System.out.println("1)sign in\n2)sign up");

        int choice = sc.nextInt();
        switch(choice){
            case 1:
                System.out.println("Enter login and password:");
                String loginin= sc.next();
                String passwordin = sc.next();
                if(DatabaseSave.signIn(loginin, passwordin)){

                    //---------------------------------------------------------------------------------------

                    mainMenu(loginin);

                    //---------------------------------------------------------------------------------------

                }else {
                    break;
                }
            case 2:
                System.out.println("write login");
                String loginsignup = sc.next();
                System.out.println("write password");
                String passwordsignup = sc.next();

                DatabaseSave.signUp(loginsignup, passwordsignup);
                break;
            case 3:
                System.out.println(getprod(23, "potato", "price"));
                break;
            default:
                System.out.println("write correct number");
        }
    }
}