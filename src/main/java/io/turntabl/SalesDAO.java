package io.turntabl;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class SalesDAO{
    private static Scanner scanner = new Scanner(System.in);
    public void sales() throws ParseException{
    String url = "jdbc:postgresql:northwind";
     try(Connection database = DriverManager.getConnection(url, "doreen-dodoo",  "turntabl")) {
         System.out.println("Please enter the starting date: ");
         String startDate = scanner.nextLine();
         System.out.println("Please enter the ending date: ");
         String endDate = scanner.nextLine();
         SimpleDateFormat formatStart = new SimpleDateFormat("yyyy-MM-dd");
         SimpleDateFormat formatEnd = new SimpleDateFormat("yyyy-MM-dd");
         java.util.Date date1 = formatStart.parse(startDate);
         java.util.Date date2 = formatEnd.parse(endDate);
         java.sql.Date startingDate = new java.sql.Date(date1.getTime());
         java.sql.Date endingDate = new java.sql.Date(date2.getTime());
         PreparedStatement query = database.prepareStatement("select products.product_name,  order_details.quantity, customers.contact_name, orders.order_date from products inner join order_details on products.product_id = order_details.product_id inner join orders on order_details.order_id = orders.order_id inner join customers on orders.customer_id = customers.customer_id where orders.order_date between ? and ?");
         query.clearParameters();
         query.setDate(1, startingDate);
         query.setDate(2, endingDate);
         ResultSet resultsFromSearch = query.executeQuery();
         if (!resultsFromSearch.isBeforeFirst()) {
             System.out.println("No customer with such name exists!");
         } else {
             System.out.println("Sales report between " + startingDate + " and " + endingDate);
             System.out.println("--------------------------------------------------------------------------------------------------------------------");
             System.out.println();
             System.out.printf("%-35s %-35s %-25s %-25s", "Product_Name  ", "Customer_Name", "Quantity", "Order_Date" + '\n');
             System.out.println();
             System.out.println("--------------------------------------------------------------------------------------------------------------------");
             while (resultsFromSearch.next()) {
                 System.out.format("%-35s %-35s %-25s %-25s", resultsFromSearch.getString("product_name"), resultsFromSearch.getString("contact_name"), resultsFromSearch.getString("quantity"), resultsFromSearch.getString("order_date"));
                 System.out.println();

             }


         }
     }
     catch(SQLException sqlE){
        System.err.println("Connection error: " + sqlE);
    }

}


}
