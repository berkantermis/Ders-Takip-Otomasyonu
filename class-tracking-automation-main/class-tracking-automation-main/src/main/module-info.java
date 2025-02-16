module org.example.javafxdeneme2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;

    opens org.example.javafxdeneme2 to javafx.fxml;
    exports org.example.javafxdeneme2;
}