package edu.pucmm.mineriadedatos2017.main;

import edu.pucmm.mineriadedatos2017.util.FXMLUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by jeanl on 21 jun 2017.
 */
public class Main extends Application {

    private final String TITULO = "WekaFX";
    private final int MIN_WIDTH = 900;
    private final int MIN_HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = new Stage();
        primaryStage.setScene(new Scene(FXMLUtil.cargarFXML("/vista/PantallaPrincipal.fxml")));
        primaryStage.setTitle(TITULO);
        primaryStage.setMaximized(true);
        primaryStage.setMinWidth(MIN_WIDTH);
        primaryStage.setMinHeight(MIN_HEIGHT);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
