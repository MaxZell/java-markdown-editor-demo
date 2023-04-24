module com.example.mddemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.commonmark;
    requires javafx.web;


    opens com.example.mddemo to javafx.fxml;
    exports com.example.mddemo;
}