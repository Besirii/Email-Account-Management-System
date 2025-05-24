package emailapp;
import java.util.Scanner;

public class EmailApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();

        // Load saved users
        userManager.loadUsersFromFile();

        System.out.println("🌐 Welcome to Company Email System!");

        boolean exit = false;
        while (!exit) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Create new email");
            System.out.println("2. Login to existing email");
            System.out.println("3. Show all users");
            System.out.println("0. Exit");

            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (option) {
                case 1:
                    System.out.print("First Name: ");
                    String first = scanner.nextLine();
                    System.out.print("Last Name: ");
                    String last = scanner.nextLine();
                    Email newUser = new Email(first, last);
                    userManager.addUser(newUser);
                    System.out.println("✅ Email created: " + newUser.getEmail());
                    break;

                case 2:
                    System.out.print("Enter your company email: ");
                    String loginEmail = scanner.nextLine();
                    Email user = userManager.findUserByEmail(loginEmail);

                    if (user != null) {
                        System.out.print("Enter your password: ");
                        String loginPassword = scanner.nextLine();

                        if (user.authenticate(loginPassword)) {
                            System.out.println("✅ Login successful!");
                            user.profileMenu();
                            userManager.saveUsersToFile();
                        } else {
                            System.out.println("❌ Incorrect password. Access denied.");
                        }
                    } else {
                        System.out.println("❌ Email not found.");
                    }
                    break;


                case 3:
                    System.out.println("\n📄 Registered Users:");
                    for (Email u : userManager.getAllUsers()) {
                        System.out.println(" - " + u.getEmail());
                    }
                    break;

                case 0:
                    System.out.println("💾 Saving data and exiting...");
                    userManager.saveUsersToFile();
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
