import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Company extends User {
    private Map<String, List<Product>> categorizedProducts;
    
    public Company(int id, String username, String password, String name, String phoneNumber, String email) {
        super(id, username, password, name, phoneNumber, email);
        this.categorizedProducts = new HashMap<>();
        categorizedProducts.put("Meat", new ArrayList<>());
        categorizedProducts.put("Cereal", new ArrayList<>());
        categorizedProducts.put("Cleaning", new ArrayList<>());
        categorizedProducts.put("Liquid", new ArrayList<>());
    }

    public void addProduct(String category, Product product) {
        if (categorizedProducts.containsKey(category)) {
            categorizedProducts.get(category).add(product);
            System.out.println("Product added: " + product.getName() + " under category: " + category);
        } else {
            System.out.println("Invalid category!");
        }
    }

    public List<String> getCategories() {
        return new ArrayList<>(categorizedProducts.keySet());
    }

    public List<Product> getProductsByCategory(String category) {
        return categorizedProducts.getOrDefault(category, new ArrayList<>());
    }

    public Product getProductByNameAndCategory(String productName, String category) {
        for (Product product : getProductsByCategory(category)) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }
    public String getName() {
        return name;
    }
    @Override
    public void displayDashboard() {
        System.out.println("Welcome, Company: " + name);
        System.out.println("Your Products by Category:");
        for (String category : categorizedProducts.keySet()) {
            System.out.println("Category: " + category);
            for (Product product : categorizedProducts.get(category)) {
                System.out.println(product);
            }
        }
    }
}