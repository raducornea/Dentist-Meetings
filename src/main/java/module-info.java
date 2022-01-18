module com.example.programari {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mybatis;
    requires org.apache.commons.lang3;


    opens sample to javafx.fxml;
    exports sample;
}