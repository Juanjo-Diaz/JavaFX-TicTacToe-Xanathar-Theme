import javafx.application.Application; // App JavaFX principal
import javafx.fxml.FXMLLoader; // Carga de FXML
import javafx.scene.Scene; // Escena que contiene la UI
import javafx.stage.Stage; // Ventana principal
import javafx.scene.Parent; // Nodo raíz de la escena

public class TicTacToe extends Application { // Clase de arranque de la app
    @Override
    public void start(Stage primaryStage) throws Exception { // Punto de entrada JavaFX
        Parent root = FXMLLoader.load(getClass().getResource("TicTacToe.fxml"));
        Scene scene = new Scene(root, 1440, 1024); // le he cambiade el tamaño a la ventana para que se ajuste mejor el background al programa
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStage.setTitle("Tic Tac Toe - Tres en Raya");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}