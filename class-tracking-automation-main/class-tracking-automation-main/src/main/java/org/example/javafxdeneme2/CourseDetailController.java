package org.example.javafxdeneme2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class CourseDetailController {

    public static String course_name;

    @FXML
    private Button back_button;

    @FXML
    private Button startCourse_button;

    @FXML
    private void goBack() {
        try {
            Stage stage = (Stage) back_button.getScene().getWindow();
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

    @FXML
    protected void startCourse(){
        try {
            Stage stage = (Stage) startCourse_button.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("course_screen.fxml"));
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

    ////////////////////////////////////////////

    @FXML
    private ImageView courseImage;

    @FXML
    private Label courseNameLabel;

    @FXML
    private Label instructorLabel;

    @FXML
    private Label durationLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label languageLabel;

    // Verileri dinamik olarak yüklemek için bir metot
    public void setCourseDetails(String imageUrl, String courseName, String instructor, String duration, String description, String language) {
        courseImage.setImage(new Image(imageUrl));
        courseNameLabel.setText("Kurs Adı: " + courseName);
        instructorLabel.setText("Eğitmen: " + instructor);
        durationLabel.setText("Süre: " + duration);
        languageLabel.setText("Eğitim Dili: " + language);
        descriptionLabel.setText(description);
        course_name=courseName;
    }
}
