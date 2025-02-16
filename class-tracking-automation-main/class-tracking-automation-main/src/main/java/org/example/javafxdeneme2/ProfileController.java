package org.example.javafxdeneme2;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.example.javafxdeneme2.User.RegularUser;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ProfileController {

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Button back_button1;

    private RegularUser person;

    public void initialize() {
        try {
            // GSON nesnesini oluştur
            Gson gson = new Gson();

            // JSON verisini bir dizi (List) olarak oku
            FileReader reader = new FileReader("src/main/data/users_data.json");
            Type listType = new com.google.gson.reflect.TypeToken<List<RegularUser>>(){}.getType();
            List<RegularUser> users = gson.fromJson(reader, listType);



            if (!users.isEmpty()) {
                person = users.get(0);
                usernameLabel.setText(person.getEmail());
                passwordLabel.setText(person.getPassword());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack(){
        try {
            Stage stage = (Stage) back_button1.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
            stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
            stage.setX(Screen.getPrimary().getVisualBounds().getMinX());
            stage.setY(Screen.getPrimary().getVisualBounds().getMinY());
            stage.setMaximized(true);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
