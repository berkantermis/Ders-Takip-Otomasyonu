package org.example.javafxdeneme2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.layout.VBox;
import org.example.javafxdeneme2.Lesson.Lesson;
import org.example.javafxdeneme2.Lesson.LessonStore;
import org.example.javafxdeneme2.Lesson.Section;

import org.example.javafxdeneme2.interfaces.Basics;

import java.util.List;

import java.io.IOException;

public class CourseScreenController implements Basics {

    @FXML
    private VBox sectionsContainer;

    @FXML
    private MediaView mediaView;

    @FXML
    private Label lessonName;

    @FXML
    private Label sectionName;

    @FXML
    private Button back_button;

    @FXML
    private ImageView play_button, pause_button;

    private List<Section> sections;

    private MediaPlayer mediaPlayer;

    private String selectedCourse;
    private String courseName;

    public CourseScreenController() {
        this.selectedCourse = "";
    }

    public void setCourseDetails(String imageUrl, String courseName, String instructor, String duration, String description, String language) {
        selectedCourse=selectedJSON(courseName);
    }

    // Kurs adına göre JSON yolunu belirler
    public String  selectedJSON(String courseName) {
        if (courseName.equals("Java Programlama")) {
            return "src/main/data/courses_data/java.json";
        } else if (courseName.equals("Python Programlama")) {
            return "src/main/data/courses_data/python.json";
        } else if (courseName.equals("C++ Programlama")) {
            return "src/main/data/courses_data/cpp.json";
        } else if (courseName.equals("WEB Programlama")) {
            return "src/main/data/courses_data/web.json";
        } else {
            System.out.println("Kurs bulunamadı!");
            return null;
        }
    }

    ////////////////////////////
    ////////////////////////////
    ////////////////////////////
    @FXML
    public void initialize() {

        if (selectedCourse == null || selectedCourse.isEmpty()) {
            System.err.println("Kurs yolu seçilmedi. Varsayılan olarak Python kursu yüklenecek.");
            selectedCourse = "src/main/data/courses_data/java.json";
        }

        try {
            sections = LessonStore.loadSections(selectedCourse);

            for (Section section : sections) {
                Label sectionLabel = new Label(section.getSectionName());
                sectionLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
                sectionsContainer.getChildren().add(sectionLabel);

                for (Lesson lesson : section.getLessons()) {
                    HBox lessonItem = new HBox();
                    lessonItem.setSpacing(10);


                    Hyperlink lessonLink = new Hyperlink(lesson.getlessonName());
                    lessonLink.setOnAction(e -> loadLesson(lesson, section.getSectionName()));

                    CheckBox completedCheckbox = new CheckBox();
                    completedCheckbox.setSelected(lesson.isCompleted());
                    completedCheckbox.setOnAction(e -> {
                        boolean isSelected = completedCheckbox.isSelected();

                        lesson.setCompleted(isSelected);
                        System.out.println(lesson.getlessonName() + " " + (isSelected ? "tamamlandı" : "tamamlanmadı"));

                        try {
                            sections = LessonStore.loadSections(selectedCourse);

                            for (Section section1 : sections) {
                                for (Lesson l : section1.getLessons()) {
                                    if (l.getlessonName().equals(lesson.getlessonName())) {
                                        l.setCompleted(isSelected);
                                        break;
                                    }
                                }
                            }

                            LessonStore.saveSections(selectedCourse, sections);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });

                    lessonItem.getChildren().addAll(lessonLink, completedCheckbox);
                    sectionsContainer.getChildren().add(lessonItem);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadLesson(Lesson lesson, String sectionName) {
        this.sectionName.setText("Bölüm: " + sectionName);
        this.lessonName.setText("Ders: " + lesson.getlessonName());
        Media media = new Media(getClass().getResource(lesson.getVideoUrl()).toExternalForm());

        mediaPlayer = new MediaPlayer(media);

        mediaView.setMediaPlayer(mediaPlayer);

        play_button.setVisible(true);
        pause_button.setVisible(false);
    }

    @FXML
    public void playVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
            play_button.setVisible(false);
            pause_button.setVisible(true);
        }
    }

    @FXML
    public void pauseVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            play_button.setVisible(true);
            pause_button.setVisible(false);
        }
    }

    @FXML
    protected void goBack() {
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

    /////////////////////////////////////////////////////////////////////////
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
    //////////////////////////////////////////////////////////////////////////

}
