package io.turntabl;

public class DAOFactory {
    public CustomerDAO getCustomerDAO() {
        return new CustomerDAO();
    }

    public CategoryDAO getCategoryDAO() {
        return new CategoryDAO();
    }

    public SalesDAO getSalesDAO() {
        return new SalesDAO();
    }

    public ProductDAO getProductsDAO() {
        return new ProductDAO();
    }

}
