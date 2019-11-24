package io.turntabl;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class CategoryDAO{

    private static Scanner scanner = new Scanner(System.in);

    public void getCategoryNames() {
        String url = "jdbc:postgresql:northwind";
        try(Connection database = DriverManager.getConnection(url, "doreen-dodoo",  "turntabl")){
            System.out.println("Please enter the category name: ");
            String CategoryName = scanner.nextLine();
            PreparedStatement query = database.prepareStatement("select products.product_name, products.unit_price, categories.category_name from products inner join categories on products.category_id = categories.category_id where categories.category_name = ?");
            query.clearParameters();
            query.setString(1, CategoryName);
            ResultSet resultsFromSearch = query.executeQuery();
            if (!resultsFromSearch.isBeforeFirst()) {
                System.out.println("No category with such name exists!");
            } else {
                System.out.println("--------------------------------------------------------------------------------------------------------------------");
                System.out.println();
                System.out.printf("%-35s  %-25s %-35s", "Product_Name  ", "Unit_Price", "Category_Name" + '\n');
                System.out.println();
                System.out.println("--------------------------------------------------------------------------------------------------------------------");
                while (resultsFromSearch.next()) {
                    System.out.format("%-35s  %-25s %-35s", resultsFromSearch.getString("product_name"), resultsFromSearch.getString("unit_price"), resultsFromSearch.getString("category_name"));
                    System.out.println();

                }


            }
        }
        catch(SQLException sqlE){
            System.err.println("Connection error: " + sqlE);
        }


    }

    public void salesForCategory(){
        String url = "jdbc:postgresql:northwind";
         try(Connection database = DriverManager.getConnection(url, "doreen-dodoo",  "turntabl")){
            System.out.println("Please enter the category name: ");
            String CategoryName = scanner.nextLine();
            PreparedStatement query = database.prepareStatement("select products.product_name,  order_details.quantity, customers.contact_name, orders.order_date, categories.category_name from categories inner join products on categories.category_id = products.category_id inner join order_details on products.product_id = order_details.product_id inner join orders on order_details.order_id = orders.order_id inner join customers on orders.customer_id = customers.customer_id where categories.category_name = ?");
            query.clearParameters();
            query.setString(1, CategoryName);
            ResultSet resultsFromSearch = query.executeQuery();
            System.out.println("Sales report for a particular category");
            if (!resultsFromSearch.isBeforeFirst()) {
                System.out.println("No sales were made for this category");
            } else {
                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println();
                System.out.printf("%-35s %-35s %-25s %-25s %-35s", "Product_Name  ", "Category_Name", "Quantity", "Order_Date", "ContactName" + '\n');
                System.out.println();
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
                while (resultsFromSearch.next()) {
                    System.out.format("%-35s %-35s %-25s %-25s %-35s", resultsFromSearch.getString("product_name"), resultsFromSearch.getString("category_name"), resultsFromSearch.getString("quantity"), resultsFromSearch.getString("order_date"), resultsFromSearch.getString("contact_name"));
                    System.out.println();

                }

            }
        }
            catch(SQLException sqlE){
                System.err.println("Connection error: " + sqlE);
            }
        }

    }

