public abstract class Product {
    protected String productName;
    protected int quantity;
    protected String size;
    protected double price;
    protected String date;

    public Product(String productName, int quantity, String size, double price, String date) {
        this.productName = productName;
        this.quantity = quantity;
        this.size = size;
        this.price = price;
        this.date = date;
    }

    public String getName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }
    public String getDate() {
        return date;
    }
    public String getSize() {
        return size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return productName + " [Price: " + price + ", Quantity: " + quantity + ", Size: " + size + ", Date: " + date + "]";
    }
}
