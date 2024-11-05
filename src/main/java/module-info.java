module org.example.mangodash {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.json;
    requires java.desktop;


    opens org.example.mangodash to javafx.fxml;
    exports org.example.mangodash;
}