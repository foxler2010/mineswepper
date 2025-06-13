package mineswepper.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.event.ActionEvent;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mineswepper.Main;
import mineswepper.cells.Mine;
import mineswepper.cells.NumberCell;
import mineswepper.game.Difficulty;
import mineswepper.game.Game;

import java.io.IOException;

public class Controller {
    
    // not used in this class, but it needs to be created for the FXML to work
    @SuppressWarnings("unused")
    @FXML private ToggleGroup diffToggleGroup;
    
    @FXML private MenuItem easyDiff;
    @FXML private MenuItem intermediateDiff;
    @FXML private MenuItem expertDiff;
    @FXML private Label mineCount;
    @FXML private Button face;
    @FXML private Label timer;
    @FXML private Canvas grid;
    
    private Game game;
    private Stage stage;
    private Difficulty difficulty;
    private GridHelper gridHelper;
    
    public void initialize() {
        this.stage = Main.getStage();
        difficulty = Difficulty.EASY;
        gridHelper = new GridHelper(grid);
        newGame();
    }
    
    @FXML
    public void newGame() {
        // initialize the game state
        game = new Game(difficulty);
        // set grid size to match number of cells
        grid.setWidth(difficulty.getWidth() * CoordHelper.cellSize());
        grid.setHeight(difficulty.getHeight() * CoordHelper.cellSize());
        // resize stage to fit everything
        stage.sizeToScene();
        // display the Cells
        //gridHelper.drawGrid(difficulty);
        update();
    } // end newGame()
    
    @FXML
    public void setDifficulty(ActionEvent event) {
        MenuItem diffOption = (MenuItem) event.getSource();
        if (diffOption.equals(expertDiff)) {
            difficulty = Difficulty.EXPERT;
        } else if (diffOption.equals(intermediateDiff)) {
            difficulty = Difficulty.INTERMEDIATE;
        } else if (diffOption.equals(easyDiff)) {
            difficulty = Difficulty.EASY;
        }
        newGame();
    }
    
    @FXML
    private void update() {
        mineCount.setText(String.valueOf(game.getMinesLeft()));
        if (face.isPressed()) {
            face.setText("OwO");
        } else {
            face.setText("UwU");
        }
        for (int x = 0; x < difficulty.getWidth(); x++) {
            for (int y = 0; y < difficulty.getHeight(); y++) {
                if (game.isCleared(x, y)) {
                    gridHelper.drawEmptySquare(x, y);
                if (game.getCell(x, y) instanceof Mine) {
                    gridHelper.drawMine(x, y);
                } else if (game.getCell(x, y) instanceof NumberCell) {
                    gridHelper.drawNumber(x, y, ((NumberCell) game.getCell(x, y)).getNumMines());
                }
            } else if (game.isFlagged(x, y)) {
                    gridHelper.drawFlag(x, y);
                } else {
                    gridHelper.drawFullSquare(x, y);
                }
            }
        }
    }
    
    @FXML
    public void gridPressed(MouseEvent event) {
        if (event.getButton().equals(MouseButton.SECONDARY)) {
            int cellX = CoordHelper.gridToCell(event.getX());
            int cellY = CoordHelper.gridToCell(event.getY());
            game.toggleFlag(cellX, cellY);
        }
        update();
    }
    
    @FXML
    public void gridReleased(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            int cellX = CoordHelper.gridToCell(event.getX());
            int cellY = CoordHelper.gridToCell(event.getY());
            game.clear(cellX, cellY);
        }
        update();
    }
    
    @FXML
    public void exit() {
        Platform.exit();
    }
    
    @FXML
    public void howToPlay() {
        Stage howToPlay = new Stage();
        howToPlay.setTitle("How To Play");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("howToPlay.fxml"));
        try {
            fxmlLoader.load();
            VBox root = fxmlLoader.getRoot();
            Scene scene = new Scene(root);
            howToPlay.setScene(scene);
            howToPlay.show();
        } catch (IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error Loading UI." + e.getMessage()).showAndWait();
        } // end try-catch
    }
    
    @FXML
    public void about() {
        // TODO
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Hello, World!");
        alert.setTitle("About Mineswepper");
        alert.show();
    }
} // end class Controller
