import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();
        List<Company> companies = new ArrayList<>();
        List<Supermarket> supermarkets = new ArrayList<>();
        while (true) {
            System.out.println("\nWelcome to the Supermarket-Company System");
            System.out.println("1. Register\n2. Login\n3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerUser(scanner, admin, companies, supermarkets);
                    break;

                case 2:
                    loginUser(scanner, admin, companies);
                    break;

                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void registerUser(Scanner scanner, Admin admin, List<Company> companies, List<Supermarket> supermarkets) {
        System.out.println("Register as:\n1. Company\n2. Supermarket");
        int userType = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        if (userType == 1) {
            Company company = new Company(admin.users.size() + 1, username, password, name, phone, email);
            admin.addUser(company);
            companies.add(company);
        } else {
            Supermarket supermarket = new Supermarket(admin.users.size() + 1, username, password, name, phone, email);
            admin.addUser(supermarket);
            supermarkets.add(supermarket);
        }
    }

    private static void loginUser(Scanner scanner, Admin admin, List<Company> companies) {
        System.out.print("Enter Username: ");
        String loginUsername = scanner.nextLine();
        System.out.print("Enter Password: ");
        String loginPassword = scanner.nextLine();

        if (loginUsername.equals("admin") && loginPassword.equals("admin")) {
            System.out.println("Admin login successful!");
            System.out.println("Admin Panel:");
            admin.displayUsers();
            return;
        }

        boolean loggedIn = false;
        for (User user : admin.users) {
            if (user.username.equals(loginUsername) && user.password.equals(loginPassword)) {
                loggedIn = true;
                System.out.println("Login successful!");
                user.displayDashboard();

                if (user instanceof Company) {
                    handleCompanyActions(scanner, (Company) user);
                } else if (user instanceof Supermarket) {
                    ((Supermarket) user).browseAndOrder(companies);
                }
            }
        }
        if (!loggedIn) {
            System.out.println("Invalid username or password.");
        }
    }

    private static void handleCompanyActions(Scanner scanner, Company company) {
        boolean wh = true;
        while (wh) {
            System.out.println("\n1. Add Product\n2. View Warehouse\n3. Logout");
            int companyChoice = scanner.nextInt();
            scanner.nextLine();
        
            switch (companyChoice) {
                case 1:
                    addProduct(scanner, company);
                    break;

                case 2:
                    viewWarehouse(company);
                    break;

                case 3:
                    System.out.println("Logging out...");
                    wh = false;
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
            if (companyChoice == 3) break;
        }
    }

    private static void addProduct(Scanner scanner, Company company) {
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
                return;
        }

        System.out.print("Enter Product Name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter Size: ");
        String size = scanner.next();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter Date (yyyy-mm-dd): ");
        String date = scanner.next();

        Product product = null;
        switch (category) {
            case "Meat": product = new Meat(productName, quantity, size, price, date); break;
            case "Cereal": product = new Cereal(productName, quantity, size, price, date); break;
            case "Cleaning": product = new Cleaning(productName, quantity, size, price, date); break;
            case "Liquid": product = new Liquid(productName, quantity, size, price, date); break;
        }

        if (product != null) {
            company.addProduct(category, product);
        }
    }

    private static void viewWarehouse(Company company) {
        System.out.println("Warehouse:");
        for (String cat : company.getCategories()) {
            System.out.println("Category: " + cat);
            for (Product prod : company.getProductsByCategory(cat)) {
                System.out.println(prod.getName() + " - Quantity: " + prod.getQuantity());
            }
        }
    }
}
