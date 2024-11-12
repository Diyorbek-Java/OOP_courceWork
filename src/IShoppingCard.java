public interface IShoppingCard {
    boolean addProduct(Product newProduct);
    boolean removeProduct(Product removingProduct);
    double getTotalCost();

}
