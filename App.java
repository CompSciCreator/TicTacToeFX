import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

    private char currentPlayer = 'X';
    private Button[][] buttons = new Button[5][5];

    public static void ain(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = createAndSetupGridPane();

        Scene scene = new Scene(gridPane, 300, 300);
        primaryStage.setTitle("TicTacToe By Andrew");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createAndSetupGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j] = createButton();
                gridPane.add(buttons[i][j], i, j);
            }
        }

        return gridPane;
    }

    private Button createButton() {
        Button button = new Button();
        button.setPrefSize(60, 60);
        button.setOnAction(e -> onButtonClick(button));
        return button;
    }

    private void onButtonClick(Button button) {
        if (!button.getText().equals("")) {
            return;
        }

        button.setText(String.valueOf(currentPlayer));
        if (checkForWinner()) {
            announceWinner();
            return;
        }

        // Switch player
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private boolean checkForWinner() {
        // Check rows and columns
        for (int i = 0; i < 5; i++) {
            if (checkRow(i) || checkColumn(i)) {
                return true;
            }
        }

        // Check diagonals
        return checkDiagonal() || checkAntiDiagonal();
    }

    private boolean checkRow(int row) {
        return checkCell(row, 0, 0, 1);
    }

        private boolean checkColumn(int col) {
            return checkCell(0, col, 1, 0);
    }

        private boolean checkDiagonal() {
            return checkCell(0, 0, 1, 1);
    }

        private boolean checkAntiDiagonal() {
            return checkCell(0, 4, 1, -1);
    }

    private boolean checkCell(int startRow, int startCol, int rowIncrement, int colIncrement) {
        char firstCell = buttons[startRow][startCol].getText().charAt(0);

        for (int i = 0; i < 4; i++) {
            int row = startRow + i * rowIncrement;
            int col = startCol + i * colIncrement;

            if (buttons[row][col].getText().charAt(0) != firstCell) {
                return false;
            }
        }

        return true;
    }

    private void announceWinner() {
        System.out.println("Player " + currentPlayer + " wins!");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
