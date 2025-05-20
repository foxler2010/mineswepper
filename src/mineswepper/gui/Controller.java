package mineswepper.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.event.ActionEvent;

public class Controller {
    @FXML private ToggleGroup difficulty;
    @FXML private MenuItem easyDiff;
    @FXML private MenuItem intermediateDiff;
    @FXML private MenuItem expertDiff;
    
    @FXML private Button face;
    
    @FXML
    public void newGame() {
        // TODO
    }
    
    @FXML
    public void setDifficulty(ActionEvent event) {
        // TODO
        newGame();
    }
    
    @FXML
    public void exit() {
        Platform.exit();
    }
    
    @FXML
    public void howToPlay() {
        // TODO
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Hello, World!");
        alert.setTitle("How To Play");
        alert.show();
    }
    
    @FXML
    public void about() {
        // TODO
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Hello, World!");
        alert.setTitle("About Mineswepper");
        alert.show();
    }
} // end class Controller
