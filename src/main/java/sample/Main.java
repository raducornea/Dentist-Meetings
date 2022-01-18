package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException {
        Controller.define();
        Controller.initializer_type = "Main Screen";

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("scenes.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Programari Dentisti");
        stage.setScene(scene);

        stage.show();
    }

    @Override
    public void stop() throws SQLException {
        Controller.undefine();
        System.out.println("Stage is closing");
    }

    public static void main(String[] args) { launch(args); }
}