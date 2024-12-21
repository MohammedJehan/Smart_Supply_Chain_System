import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Supermarket extends User {
    Scanner scanner = new Scanner(System.in);
    private List<Order> orders;

    public Supermarket(int id, String username, String password, String name, String phoneNumber, String email) {
        super(id, username, password, name, phoneNumber, email);
        this.orders = new ArrayList<>();
    }

    public void browseAndOrder(List<Company> companies) {
        while (true) {
            System.out.println("\n1. Browse and Order Products\n2. Logout");
            int supermarketChoice = scanner.nextInt();
            scanner.nextLine();

            if (supermarketChoice == 1) {
                System.out.println("Choose Product Category:");
                System.out.println("1. Meat\n2. Cereal\n3. Cleaning\n4. Liquid");
                int categoryChoice = scanner.nextInt();
                scanner.nextLine();
                String category = ""; 
                switch (categoryChoice) {
                    case 1: category = "Meat"; break;
                    case 2: category = "Cereal"; break;
                    case 3: category = "Cleaning"; break;
                    case 4: category = "Liquid"; break;
                    default:
                        System.out.println("Invalid category!");
                        continue;
                }

                List<Product> availableProducts = new ArrayList<>();
                for (Company company : companies) {
                    for (Product product : company.getProductsByCategory(category)) {
                        availableProducts.add(product);
                        System.out.println((availableProducts.size()) + " " + product.getName() + " " + company.getName() + " " + product.getQuantity() + " " + product.getDate() + " " + product.getSize() + " " + product.getPrice());
                    }
                }

                if (availableProducts.isEmpty()) {
                    System.out.println("No products available in this category.");
                    continue;
                }

                System.out.print("Select the product number you want to order: ");
                int productNumber = scanner.nextInt();
                scanner.nextLine();

                if (productNumber < 1 || productNumber > availableProducts.size()) {
                    System.out.println("Invalid product number!");
                    continue;
                }

                Product selectedProduct = availableProducts.get(productNumber - 1);
                Company selectedCompany = null;
                for (Company company : companies) {
                    if (company.getProductsByCategory(category).contains(selectedProduct)) {
                        selectedCompany = company;
                        break;
                    }
                }
                System.out.print("Enter Quantity: ");
                int quantity = scanner.nextInt();
                scanner.nextLine();

                if (selectedProduct.getQuantity() < quantity) {
                    System.out.println("Insufficient quantity available.");
                    continue;
                }

                double totalPrice = selectedProduct.getPrice() * quantity;
                System.out.println("Order Details:");
                System.out.println("Product: " + selectedProduct.getName());
                    orders.add(new Order(selectedProduct, selectedCompany.getName(), quantity, name));
                System.out.println("Total Price: " + totalPrice);
                System.out.print("Confirm order? (yes/no): ");
                String confirm = scanner.nextLine();

                if (confirm.equalsIgnoreCase("yes")) {
                    selectedProduct.setQuantity(selectedProduct.getQuantity() - quantity);
                    orders.add(new Order(selectedProduct, selectedCompany.getName(), quantity, name));
                    System.out.println("Order placed successfully!");
                } else {
                    System.out.println("Order cancelled.");
                }
            } else if (supermarketChoice == 2) {
                System.out.println("Logging out...");
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    @Override
    public void displayDashboard() {
        System.out.println("Welcome, Supermarket: " + name);
        System.out.println("Your Orders:");
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}