package org.example.javafxdeneme2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.example.javafxdeneme2.interfaces.Basics;

import java.io.IOException;

public class MainController implements Basics {

    @FXML
    private Button cikis_yap_button;

    @FXML
    private Button derslerim_button;

    @FXML
    private Button tum_kurslar_button;

    @FXML
    private Button profilim_button;

    @Override
    public void cikis_yap() {
        try {
            Stage stage = (Stage) cikis_yap_button.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setFullScreen(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tum_kurslar_giris() {
        try {
            Stage stage = (Stage) tum_kurslar_button.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("all_courses.fxml"));
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

    @Override
    public void profilim_giris(){
        try {
            Stage stage = (Stage) profilim_button.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("profile.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            stage.setScene(scene);
            stage.setMaximized(true);
            stage.setFullScreen(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void derslerim_giris(){
        try {
            Stage stage = (Stage) derslerim_button.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("my_courses.fxml"));
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
