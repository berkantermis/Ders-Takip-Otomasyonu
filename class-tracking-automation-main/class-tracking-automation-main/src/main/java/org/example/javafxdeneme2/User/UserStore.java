package org.example.javafxdeneme2.User;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.regex.Pattern;

import com.google.gson.reflect.TypeToken;

public class UserStore {

    private static final String FILE_PATH = "src/main/data/users_data.json";
    private static List<RegularUser> users;

    static {
        // JSON dosyasını okuma
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Gson gson = new Gson();
            Type userListType = new TypeToken<List<RegularUser>>() {}.getType();
            users = gson.fromJson(reader, userListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Kullanıcıyı doğrulama
    public static boolean validateUser(String email, String password) {
        return users.stream()
                .anyMatch(user -> user.getEmail().equals(email) && user.getPassword().equals(password));
    }

    public static boolean validateEmail(String email){
        return users.stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }

    // Kullanıcıyı dosyaya eklemek
    public static void addUser(RegularUser user) {
        users.add(user);
        saveUsers();
    }

    // JSON dosyasına kullanıcıları kaydetmek
    private static void saveUsers() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new Gson();
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
