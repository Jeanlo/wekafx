package edu.pucmm.mineriadedatos2017.main;

import edu.pucmm.mineriadedatos2017.util.FXMLUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by jeanl on 21 jun 2017.
 */
public class Main extends Application {
    private static final Logger logger = LogManager.getLogger();

    private final String TITULO = "WekaFX";
    private final int MIN_WIDTH = 900;
    private final int MIN_HEIGHT = 600;

    public static void main(String[] args) {
        logger.info("Iniciando programa.");
        Application.launch(Main.class, args);
        logger.info("Programa terminado.");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        logger.info("Iniciando pantalla pricipal.");
        primaryStage = new Stage();
        primaryStage.setScene(new Scene(FXMLUtil.cargarFXML("/vista/PantallaPrincipal.fxml")));
        primaryStage.setTitle(TITULO);
        primaryStage.setMaximized(true);
        primaryStage.setMinWidth(MIN_WIDTH);
        primaryStage.setMinHeight(MIN_HEIGHT);
        primaryStage.show();
    }
}
