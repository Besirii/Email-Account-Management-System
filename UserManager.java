package emailapp;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<Email> users = new ArrayList<>();
    private static final String FILE_NAME = "users.ser";

    public void addUser(Email user) {
        users.add(user);
        saveUsersToFile();  // save immediately
    }

    public Email findUserByEmail(String email) {
        for (Email user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    public List<Email> getAllUsers() {
        return users;
    }

    public void saveUsersToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(users);
        } catch (IOException e) {
            System.out.println("⚠️ Failed to save users: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadUsersFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            users = (List<Email>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ Failed to load users: " + e.getMessage());
        }
    }
}
