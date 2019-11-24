package io.turntabl;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.Scanner;

public class JavaDb {
    //    public static String input(){
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Please enter name you want to search for: ");
//        return scanner.nextLine();
//
    private static Scanner scanner = new Scanner(System.in);
    private static  String url = "jdbc:postgresql:northwind";

    public static void DisplayAllCustomers(){
         try(Connection database = DriverManager.getConnection(url, "doreen-dodoo",  "turntabl")) {
            Statement query2 = database.createStatement();
            ResultSet searchResults = query2.executeQuery("select * from customers");
                System.out.println("--------------------------------------------------------------------------------------------------------------------");
                System.out.printf("%-10s %-30s %-30s %-15s %-30s", "Customer_id  ", "Company_name", "Contact_name ", "Country", "Telephone");
                System.out.println("--------------------------------------------------------------------------------------------------------------------");
                while (searchResults.next()) {
                    System.out.format("%-10s %-30s %-30s %-15s %-30s", searchResults.getString("CustomerID"), searchResults.getString("CompanyName"),
                            searchResults.getString("ContactName"), searchResults.getString("Country"), searchResults.getString("Phone"));
                    System.out.println();
//                    System.out.format("%-10s %-30s %-30s %-15s %-30s", searchResults.getString("customer_id"), searchResults.getString("company_name"),
//                            searchResults.getString("contact_name"), searchResults.getString("country"), searchResults.getString("phone"));
//                    System.out.println();
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
            ResultSet searchResults = query2.executeQuery("select * from customers where 'contact_name' like '"+searchName+"%'");
            if (!searchResults.next()) {
                System.out.println("No user with such name exists in the database");
            } else {
                System.out.println("--------------------------------------------------------------------------------------------------------------------");
                System.out.printf("%-10s %-30s %-30s %-15s %-30s", "Customer_id  ", "Company_name", "Contact_name ", "Country", "Telephone");
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

    public static void main(String[] args){
      DisplayAllCustomers();
     searchFunctionCustomers();

    }
}
