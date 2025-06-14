package mineswepper.gui;

import java.io.IOException;
import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
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
    
    private final ScheduledService<Void> updater = new ScheduledService<>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<>() {
                @Override
                protected Void call() {
                    update();
                    return null;
                }
            };
        }
    };
    
    public void initialize() {
        this.stage = Main.getStage();
        difficulty = Difficulty.EASY;
        gridHelper = new GridHelper(grid);
        newGame();
        // start the game's update loop in another thread:
        updater.start();
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
    
    private void update() {
        Platform.runLater(() -> mineCount.setText(String.valueOf(game.getMinesLeft())));
        if (face.isPressed()) {
            Platform.runLater(() -> face.setText("OwO"));
        } else {
            Platform.runLater(() -> face.setText("UwU"));
        }
        Platform.runLater(() -> timer.setText(String.valueOf(game.getTime())));
        for (int x = 0; x < difficulty.getWidth(); x++) {
            for (int y = 0; y < difficulty.getHeight(); y++) {
                int finalY = y;
                int finalX = x;
                if (game.isCleared(finalX, finalY)) {
                    Platform.runLater(() -> gridHelper.drawEmptySquare(finalX, finalY));
                    if (game.getCell(finalX, finalY) instanceof Mine) {
                        Platform.runLater(() -> gridHelper.drawMine(finalX, finalY));
                    } else if (game.getCell(finalX, finalY) instanceof NumberCell) {
                        Platform.runLater(() -> gridHelper.drawNumber(finalX, finalY, ((NumberCell) game.getCell(finalX, finalY)).getNumMines()));
                    }
                } else if (game.isFlagged(finalX, finalY)) {
                    Platform.runLater(() -> gridHelper.drawFlag(finalX, finalY));
                } else {
                    Platform.runLater(() -> gridHelper.drawFullSquare(finalX, finalY));
                } // end if
            } // end inner for
        } // end outer for
    } // end update()
    
    @FXML
    public void gridPressed(MouseEvent event) {
        if (event.getButton().equals(MouseButton.SECONDARY)) {
            int cellX = CoordHelper.gridToCell(event.getX());
            int cellY = CoordHelper.gridToCell(event.getY());
            game.toggleFlag(cellX, cellY);
        }
    }
    
    @FXML
    public void gridReleased(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            int cellX = CoordHelper.gridToCell(event.getX());
            int cellY = CoordHelper.gridToCell(event.getY());
            game.clear(cellX, cellY);
        }
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
