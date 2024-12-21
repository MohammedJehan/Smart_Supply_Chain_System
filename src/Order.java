class Order {
    private String productName;
    private String companyName;
    private int quantity;
    private double totalPrice;
    private String customerName;

    public Order(Product product, String companyName, int quantity, String customerName) {
        this.productName = product.getName();
        this.companyName = companyName;
        this.quantity = quantity;
        this.totalPrice = product.getPrice() * quantity;
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "Order: " + productName + " from " + companyName + " for " + quantity + " units (Total: $" + totalPrice + ") by " + customerName;
    }
}