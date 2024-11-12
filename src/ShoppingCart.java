// Define the ShoppingCart class to manage a collection of products and calculate total cost

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements IShoppingCard {
    List<Product> products;
    private final XMLRepository productRepository = new XMLRepository("database.xml");

    // Variable to track the total cost of products in the cart
    double totalCost = 0;

    public ShoppingCart() {
        this.products = productRepository.loadProducts();
        for (Product product : products) {
            this.totalCost += product.getPrice();
        }
    }

    // Method to add a product to the cart and update the total cost
    @Override
    public boolean addProduct(Product product) {
        boolean result = productRepository.saveProducts(product);
        if (result) {
            this.totalCost += product.getPrice();
            this.products.add(product);
            return true;
        }
        return false;
    }

    // Method to remove a product from the cart and update the total cost
    @Override
    public boolean removeProduct(Product product) {
        boolean remove = productRepository.deleteProduct(product.getName());
        if (remove) {
            this.products.remove(product);
            this.totalCost -= product.getPrice();
        }
        return remove;
    }

    // Method to retrieve the current total cost of products in the cart
    @Override
    public double getTotalCost() {
        return totalCost;
    }
}