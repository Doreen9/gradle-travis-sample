package io.turntabl;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.Scanner;

public class JavaDb {

    private static Scanner scanner = new Scanner(System.in);
    private static  String url = "jdbc:postgresql:northwind";

    public static void DisplayAllCustomers(){
        try(Connection database = DriverManager.getConnection(url, "doreen-dodoo",  "turntabl")) {
            Statement query2 = database.createStatement();
            ResultSet searchResults = query2.executeQuery("select * from customers");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            System.out.println();
            System.out.printf("%-10s %-30s %-30s %-15s %-30s", "Cus_id  ", "Company_name", "Contact_name ", "Country", "Telephone" + '\n');
            System.out.println();
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            while (searchResults.next()) {
                    System.out.format("%-10s %-30s %-30s %-15s %-30s", searchResults.getString("customer_id"), searchResults.getString("company_name"),
                            searchResults.getString("contact_name"), searchResults.getString("country"), searchResults.getString("phone"));
                    System.out.println();
            }
        }catch(SQLException sqlE){
            System.err.println("Connection error: " + sqlE);
        }


    }

    public static void searchFunctionCustomers(){

        try(Connection database = DriverManager.getConnection(url, "doreen-dodoo",  "turntabl")) {
            Statement query2 = database.createStatement();
            System.out.println("Please enter the customer name you want to search for: ");
            String searchName = scanner.nextLine();
            PreparedStatement query = database.prepareStatement("select * from customers where contact_name like ?");
            query.clearParameters();
            query.setString(1, searchName+'%');
            ResultSet searchResults = query.executeQuery();
            if (!searchResults.isBeforeFirst()) {
                System.out.println("No customer with such name exists in the database");
            } else {
                System.out.println("--------------------------------------------------------------------------------------------------------------------");
                System.out.println();
                System.out.printf("%-10s %-30s %-30s %-15s %-30s", "Cus_id  ", "Company_name", "Contact_name ", "Country", "Telephone" + '\n');
                System.out.println();
                System.out.println("--------------------------------------------------------------------------------------------------------------------");
                while (searchResults.next()) {
                    System.out.format("%-10s %-30s %-30s %-15s %-30s", searchResults.getString("customer_id"), searchResults.getString("company_name"),
                            searchResults.getString("contact_name"), searchResults.getString("country"), searchResults.getString("phone"));
                    System.out.println();
                }
            }
        }catch(SQLException sqlE){
            System.err.println("Connection error: " + sqlE);
        }


    }

    public static void searchFunctionCategories(){

         try(Connection database = DriverManager.getConnection(url, "doreen-dodoo",  "turntabl")) {
            System.out.println("Please enter the category name you want to search for: ");
            String searchName = scanner.nextLine();
            PreparedStatement query = database.prepareStatement("select * from categories where category_name like ?");
            query.clearParameters();
            query.setString(1, searchName+'%');
            ResultSet searchResults = query.executeQuery();
               if (!searchResults.isBeforeFirst()) {
                   System.out.println("No such category exist");
               }
               else{
                System.out.println("--------------------------------------------------------------------------------------------------------------------");
                System.out.println();
                System.out.printf("%-10s %-30s %-30s", "Cat_id  ", "Category_name", "Contact_description" + '\n');
                System.out.println();
                System.out.println("--------------------------------------------------------------------------------------------------------------------");

                while (searchResults.next()) {
                        System.out.format("%-10d %-30s %-30s", searchResults.getInt("category_id"), searchResults.getString("category_name"),
                                searchResults.getString("description"));
                        System.out.println();
//
                    }
                }

        }catch(SQLException sqlE){
            System.err.println("Connection error: " + sqlE);
        }


    }
    public static void searchFunctionEmployees(){

         try(Connection database = DriverManager.getConnection(url, "doreen-dodoo",  "turntabl")) {
            PreparedStatement query = database.prepareStatement("select * from employees where first_name like ? ");
            query.clearParameters();
            System.out.println("Please enter the employee name you want to search for: ");
            String searchName = scanner.nextLine();
            query.setString(1, searchName+'%');
            ResultSet searchResults = query.executeQuery();
            if (!searchResults.isBeforeFirst()) {
                System.out.println("No employee with such name exists in the database");
            } else {
                System.out.println("--------------------------------------------------------------------------------------------------------------------");
                System.out.println();
                System.out.printf("%-10s %-30s %-30s %-30s %-15s", "Emp_ID", "First_Name", "Last_Name", "Country", "Title" + '\n');
                System.out.println();
                System.out.println("--------------------------------------------------------------------------------------------------------------------");
                while (searchResults.next()) {
                    System.out.format("%-10s %-30s %-30s %-30s %-15s", searchResults.getString("employee_id"), searchResults.getString("first_name"),
                            searchResults.getString("last_name"), searchResults.getString("country"), searchResults.getString("title"));
                    System.out.println();
//
                }
            }
        }catch(SQLException sqlE){
            System.err.println("Connection error: " + sqlE);
        }


    }
    public static void searchFunctionProducts(){

         try(Connection database = DriverManager.getConnection(url, "doreen-dodoo",  "turntabl")) {
            PreparedStatement query = database.prepareStatement("select * from products where product_name like ? ");
            query.clearParameters();
            System.out.println("Please enter the product name you want to search for: ");
            String searchName = scanner.nextLine();
            query.setString(1, searchName+'%');
            ResultSet searchResults = query.executeQuery();
            if (!searchResults.isBeforeFirst()) {
                System.out.println("No product with such name exists in the database");
            } else {
                System.out.println("--------------------------------------------------------------------------------------------------------------------");
                System.out.println();
                System.out.printf("%-10s %-30s %-30s", "Prod_id  ", "Product_name", "Units_in_stock", "Unit_price" + '\n');
                System.out.println();
                System.out.println("--------------------------------------------------------------------------------------------------------------------");
                while (searchResults.next()) {
                    System.out.format("%-10s %-30s %-30d %-20f", searchResults.getString("product_id"), searchResults.getString("product_name"),
                            searchResults.getInt("units_in_stock"), searchResults.getFloat("unit_price"));
                    System.out.println();
//
                }
            }
        }catch(SQLException sqlE){
            System.err.println("Connection error: " + sqlE);
        }


    }

    public static void main(String[] args){
     // searchFunctionCategories();
     //searchFunctionEmployees();
     //searchFunctionProducts();
       // DisplayAllCustomers();
        searchFunctionCustomers();


    }
}
