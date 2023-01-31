module com.sample.hex {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.sample.hex to javafx.fxml;
    exports com.sample.hex;
}