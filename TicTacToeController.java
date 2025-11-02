import javafx.fxml.FXML; // Anotaciones para vincular FXML
import javafx.scene.control.Button; // Botones del tablero y controles
import javafx.scene.image.Image; // Imagen para fichas
import javafx.scene.image.ImageView; // Vista para mostrar las imágenes en botones
import javafx.scene.text.Text; // Texto de estado

import java.util.Objects; // Utilidad para validaciones

public class TicTacToeController { // Controlador de la vista TicTacToe
    @FXML private Button b00, b01, b02;
    @FXML private Button b10, b11, b12;
    @FXML private Button b20, b21, b22;
    @FXML private Text statusText;

    private boolean playerX = true; // true = X, false = O

    // Board state for win/draw checks ('X', 'O', or '\0')
    private final char[][] board = new char[3][3];

    // Images for X and O
    private Image imgX; // "image9.png"
    private Image imgO; // "image7.png"

    @FXML
    // Inicializa referencias, carga imágenes y limpia el tablero
    private void initialize() {
        // Map each button to its grid coordinates via userData
        b00.setUserData(new int[]{0, 0});
        b01.setUserData(new int[]{0, 1});
        b02.setUserData(new int[]{0, 2});
        b10.setUserData(new int[]{1, 0});
        b11.setUserData(new int[]{1, 1});
        b12.setUserData(new int[]{1, 2});
        b20.setUserData(new int[]{2, 0});
        b21.setUserData(new int[]{2, 1});
        b22.setUserData(new int[]{2, 2});

        // Load images from resources in the same package/folder
        imgX = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image9.png")));
        imgO = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image7.png")));

        // Ensure board is clear
        resetBoardStateOnly();
    }

    @FXML
    // Gestiona un clic en una casilla y realiza la jugada
    private void handleMove(javafx.event.ActionEvent event) {
        Button btn = (Button) event.getSource();
        if (btn.getGraphic() != null) return; // already occupied

        int[] rc = (int[]) btn.getUserData();
        int r = rc[0], c = rc[1];

        // Place current player's mark
        if (playerX) {
            btn.setGraphic(makeFittedView(imgX));
            board[r][c] = 'X';
        } else {
            btn.setGraphic(makeFittedView(imgO));
            board[r][c] = 'O';
        }

        if (checkWinChar()) {
            statusText.setText("¡Ganó " + (playerX ? "X" : "O") + "!");
            disableBoard();
        } else if (isBoardFullChar()) {
            statusText.setText("¡Empate!");
        } else {
            playerX = !playerX;
            statusText.setText("Turno de " + (playerX ? "X" : "O"));
        }
    }

    @FXML
    // Reinicia el tablero y el estado del juego
    private void resetGame() {
        for (Button btn : new Button[]{b00, b01, b02, b10, b11, b12, b20, b21, b22}) {
            btn.setText("");
            btn.setGraphic(null);
            btn.setDisable(false);
        }
        resetBoardStateOnly();
        playerX = true;
        statusText.setText("Turno de X");
    }

    private void resetBoardStateOnly() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '\0';
            }
        }
    }

    @FXML
    private void exitGame() {
        System.exit(0);
    }

    private boolean checkWinChar() {
        // Rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '\0' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) return true;
            if (board[0][i] != '\0' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) return true;
        }
        // Diagonals
        if (board[0][0] != '\0' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) return true;
        if (board[0][2] != '\0' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) return true;
        return false;
    }

    private boolean isBoardFullChar() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '\0') return false;
            }
        }
        return true;
    }

    private void disableBoard() {
        for (Button btn : new Button[]{b00, b01, b02, b10, b11, b12, b20, b21, b22}) {
            btn.setDisable(true);
        }
    }

    private ImageView makeFittedView(Image img) {
        ImageView iv = new ImageView(img);
        iv.setPreserveRatio(true);
        iv.setFitWidth(90);
        iv.setFitHeight(90);
        return iv;
    }
}