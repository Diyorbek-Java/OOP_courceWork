// Define the Product class, which represents individual products in the shopping cart
public class Product {
    private static int idCount=1;
    // Private fields for product name and price
    private int id;
    private String name;
    private Double price;


    // Constructor with parameters to initialize the product with a name and price
    public Product(String name, Double price) {
        id = ++idCount;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter for the product name
    public String getName() {
        return name;
    }

    // Setter for the product name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for the product price
    public Double getPrice() {
        return price;
    }

    // Setter for the product price
    public void setPrice(Double price) {
        this.price = price;
    }
}


