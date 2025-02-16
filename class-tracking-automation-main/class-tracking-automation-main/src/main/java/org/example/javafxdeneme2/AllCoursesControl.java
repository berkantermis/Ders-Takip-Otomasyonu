    package org.example.javafxdeneme2;

    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Node;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.Button;
    import javafx.scene.image.ImageView;
    import javafx.scene.layout.HBox;
    import javafx.scene.layout.VBox;
    import javafx.stage.Screen;
    import javafx.stage.Stage;
    import org.example.javafxdeneme2.Courses.Course;
    import org.example.javafxdeneme2.Courses.CourseStore;
    import javafx.scene.control.Label;
    import javafx.scene.image.Image;

    import org.example.javafxdeneme2.interfaces.Basics;
    import java.util.List;

    import java.io.IOException;


    public class AllCoursesControl implements Basics {

        @FXML
        private VBox courseListContainer;

        private HBox createCourseCard(Course course) {
            // HBox ana konteyner
            HBox hbox = new HBox();
            hbox.setSpacing(20);
            hbox.setStyle("-fx-padding: 10px; -fx-border-color: #ddd; -fx-border-width: 1px; -fx-border-radius: 5px;");

            // Resim
            ImageView imageView = new ImageView(new Image(course.getImageUrl()));
            imageView.setFitWidth(300);
            imageView.setFitHeight(200);
            imageView.setPreserveRatio(true);

            // Bilgi alanları
            VBox detailsBox = new VBox();
            detailsBox.setSpacing(10);

            Label courseName = new Label("Kurs Adı: " + course.getName());
            courseName.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

            Label instructor = new Label("Eğitmen: " + course.getInstructor());
            instructor.setStyle("-fx-font-size: 14px;");

            Label duration = new Label("Süre: " + course.getDuration());
            duration.setStyle("-fx-font-size: 14px;");

            Label language = new Label("Eğitim Dili: " + course.getLanguage());
            duration.setStyle("-fx-font-size: 14px;");

            // Dersi İncele Butonu
            Button dersiInceleButton = new Button("Dersi İncele");
            dersiInceleButton.setStyle("-fx-background-color: #5cb85c; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5px 10px;");

            detailsBox.getChildren().addAll(courseName, instructor, duration, dersiInceleButton);

            dersiInceleButton.setOnAction(event -> {
                try {
                    ///////////////////////////////////////////////////////////////

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("course_detail.fxml"));
                    Parent courseDetailRoot = loader.load();
                    CourseDetailController controller = loader.getController();
                    controller.setCourseDetails(
                            course.getImageUrl(),
                            course.getName(),
                            course.getInstructor(),
                            course.getDuration(),
                            course.getDescription(),
                            course.getLanguage()
                    );

                    ///////////////////////////////////////////////////////////////

                    FXMLLoader loader2 = new FXMLLoader(getClass().getResource("course_screen.fxml"));
                    Parent root2 = loader2.load();
                    CourseScreenController controller1 = loader2.getController();
                    controller1.setCourseDetails(
                            course.getImageUrl(),
                            course.getName(),
                            course.getInstructor(),
                            course.getDuration(),
                            course.getDescription(),
                            course.getLanguage()
                    );

                    ////////////////////////////////////////////////////////////////

                    // Yeni sahneye geçiş yap
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(courseDetailRoot));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // HBox'a ekle
            hbox.getChildren().addAll(imageView, detailsBox);

            return hbox;

        }


        @FXML
        public void initialize() {
            // JSON'dan kursları al
            List<Course> courses = CourseStore.getCourses();

            // Her kurs için bir HBox (kart) oluştur ve ekle
            for (Course course : courses) {
                HBox courseCard = createCourseCard(course);
                courseListContainer.getChildren().add(courseCard);
            }
        }

        ////////////////////////////////////////////////////////////////////////////////////////////
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
                // Mevcut pencereyi al
                Stage stage = (Stage) cikis_yap_button.getScene().getWindow();

                // Giriş ekranını yükle
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
                // Mevcut pencereyi al
                Stage stage = (Stage) profilim_button.getScene().getWindow();

                // Giriş ekranını yükle
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
