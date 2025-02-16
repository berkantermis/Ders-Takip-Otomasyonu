package org.example.javafxdeneme2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.javafxdeneme2.User.RegularUser;
import org.example.javafxdeneme2.User.User;
import org.example.javafxdeneme2.User.UserStore;
import java.io.IOException;
import java.util.regex.Pattern;

public class LoginController {

    @FXML
    private TextField email_input;

    @FXML
    private TextField password_input;

    @FXML
    private Button sign_in_button;

    @FXML
    protected void user_control(){
        String user_email = email_input.getText();
        String user_password = password_input.getText();

        if (!isValidEmail(user_email)) {
            showAlert(Alert.AlertType.WARNING, "Giriş Hatası", "Emailiniz @gmail.com formatında olmalıdır!");
            return;
        }

        if (user_email.isEmpty() || user_password.isEmpty()){
            showAlert(Alert.AlertType.WARNING,  "Giriş Hatası", "Email ve şifre alanları boş bırakılamaz!");
            return;
        }

        if (UserStore.validateUser(user_email,user_password)) {
            try {
                Stage stage = (Stage) sign_in_button.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                stage.setMaximized(true);
                stage.setFullScreen(false);
            }catch (IOException e){
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Giriş Hatası", "Geçersiz email veya şifre!");
        }
    }

    @FXML
    protected void user_register(){
        String user_email = email_input.getText();
        String user_password = password_input.getText();

        if (user_email.isEmpty() || user_password.isEmpty()){
            showAlert(Alert.AlertType.WARNING,  "Kayıt Hatası", "Email ve şifre alanları boş bırakılamaz!");
            return;
        }

        if (UserStore.validateEmail(user_email)) {
            showAlert(Alert.AlertType.WARNING, "Kayıt Hatası", "Kullanıcı zaten mevcut!");
            return;
        }

        RegularUser regularUser = new RegularUser(user_email,user_password);

        if(!regularUser.password_validate()){
            showAlert(Alert.AlertType.WARNING, "Kayıt Hatası", "Şifreniz minimum 6 karakterden oluşmalı!");
            return;
        }

        // Email formatını kontrol et
        if (!isValidEmail(user_email)) {
            showAlert(Alert.AlertType.WARNING, "Kayıt Hatası", "Emailiniz @gmail.com formatında olmalıdır!");
            return;
        }

        RegularUser new_user = new RegularUser(user_email,user_password);
        UserStore.addUser(new_user);

        showAlert(Alert.AlertType.INFORMATION, "Kayıt Başarılı", "Kullanıcı başarıyla kaydedildi!");
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean isValidEmail(String email) {
        String gmailRegex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        Pattern pattern = Pattern.compile(gmailRegex);
        return pattern.matcher(email).matches();
    }
}