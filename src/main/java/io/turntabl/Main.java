package io.turntabl;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        DAOFactory factory = new DAOFactory();
        CustomerDAO customer = factory.getCustomerDAO();
        CategoryDAO category = factory.getCategoryDAO();
        ProductDAO product = factory.getProductsDAO();
        SalesDAO sales = factory.getSalesDAO();
//        customer.getProductDetails();
//        category.salesForCategory();
//        category.getCategoryNames();
//        product.getProductNames();
//        sales.sales();

    }
}
