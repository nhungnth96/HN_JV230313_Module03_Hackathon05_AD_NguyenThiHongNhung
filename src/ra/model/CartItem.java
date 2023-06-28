package ra.model;

import ra.config.InputMethods;

import java.util.List;

public class CartItem extends Product{
    private int cartItemId;
    private Product product;
    private double price;
    private int quantity;

    public CartItem() {
    }

    public CartItem(String productId, String productName, double productPrice, String description, int stock, Catalog catalog, boolean status, int cartItemId, Product product, double price, int quantity) {
        super(productId, productName, productPrice, description, stock, catalog, status);
        this.cartItemId = cartItemId;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return
                "ID: " + cartItemId +
                " | Product: " + product.getProductName() +
                " | Price: " + product.getProductPrice() +
                " | Quantity: " + quantity;

    }
    public void inputData(){

        System.out.println("Nhập số lượng muốn mua: ");
        quantity = InputMethods.getInteger();
        if(quantity < super.getStock()){
            setQuantity(quantity);
        } else {

        }

    }
}
