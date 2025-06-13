package mineswepper;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage stage;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        Main.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gui/mineswepper.fxml"));
        try {
            fxmlLoader.load();
            BorderPane root = fxmlLoader.getRoot();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error Loading UI." + e.getMessage()).showAndWait();
        } // end try-catch
    } // end start()
    
    public static Stage getStage() {
        return Main.stage;
    }
} // end class Main
