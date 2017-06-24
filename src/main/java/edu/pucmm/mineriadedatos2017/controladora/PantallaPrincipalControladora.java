package edu.pucmm.mineriadedatos2017.controladora;

import edu.pucmm.mineriadedatos2017.alerta.Alerta;
import edu.pucmm.mineriadedatos2017.algoritmos.AlgoritmoJ48;
import edu.pucmm.mineriadedatos2017.algoritmos.AlgoritmoNaiveBayes;
import edu.pucmm.mineriadedatos2017.enums.Algoritmo;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.controlsfx.control.PrefixSelectionComboBox;
import org.controlsfx.control.textfield.CustomTextField;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jeanl on 21 jun 2017.
 */
public class PantallaPrincipalControladora implements Initializable {
    private static final Logger logger = LogManager.getLogger();

    //Propiedad para controlar los botones y activarlos y desactivarlos cuando se necesite
    private StringProperty archivo;

    private FileChooser fileChooser;
    private File file;

    @FXML
    private StackPane stackPane;

    @FXML
    private BorderPane borderPane;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu menu;

    @FXML
    private MenuItem menuItemAbrir;

    @FXML
    private MenuItem menuItemSalir;

    @FXML
    private VBox vBox;

    @FXML
    private ImageView imageView;

    @FXML
    private HBox hBox;

    @FXML
    private CustomTextField txtFieldArchivo;

    @FXML
    private Button btnBuscarArchivo;

    @FXML
    private HBox hBox2;

    @FXML
    private PrefixSelectionComboBox<Algoritmo> comboBoxSeleccioneAlgoritmo;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextArea txtArea;

    //Consctructor de la clase controladora
    public PantallaPrincipalControladora() {
        archivo = new SimpleStringProperty(null, "archivo", "");
        fileChooser = new FileChooser();
    }

    //Funcion para inicializar los componentes de la pantalla
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logger.info("Iniciando componentes de la pantalla principal.");
        setDragDrop();
        setCampos();
        resetearCampos();
    }

    @FXML
    void btnBuscarArchivoClick(ActionEvent event) {
        logger.info("Intentando buscar archivo.");
        buscarArchivo();
    }

    @FXML
    void comboBoxSeleccionAlgoritmoClick(ActionEvent event) {
        logger.info("Intentando ejecutar algoritmo.");
        ejecutarAlgoritmo();
    }

    @FXML
    void menuItemAbrirArchivoClick(ActionEvent event) {
        logger.info("Intentando buscar archivo.");
        buscarArchivo();
    }

    @FXML
    void menuItemSalirClick(ActionEvent event) {
        logger.info("Saliendo del programa.");
        salir();
    }

    //Funcion para configurar los drag and drop del componente de textfield y la pantalla entera
    void setDragDrop() {
        vBox.setOnDragOver(event -> {
            if (event.getDragboard().hasFiles())
                event.acceptTransferModes(TransferMode.COPY);
        });
        vBox.setOnDragDropped(event -> {
            if (event.getDragboard().getFiles().get(0).getName().toLowerCase().endsWith(".arff") && event.getDragboard().hasFiles()) {
                logger.info("Cargando archivo.");
                txtFieldArchivo.setText(event.getDragboard().getFiles().get(0).getAbsolutePath());
                logger.info("Archivo cargado.");
                pantallaAlFrente();
                comboBoxSeleccioneAlgoritmo.getSelectionModel().clearSelection();
            } else
                new Alerta(Alert.AlertType.INFORMATION).aviso("Debe de arrastrar un archivo que sea de extension arff.");
        });

        txtFieldArchivo.setOnDragOver(event -> {
            if (event.getDragboard().hasFiles())
                event.acceptTransferModes(TransferMode.COPY);
        });
        txtFieldArchivo.setOnDragDropped(event -> {
            if (event.getDragboard().getFiles().get(0).getName().toLowerCase().endsWith(".arff") && event.getDragboard().hasFiles()) {
                logger.info("Cargando archivo.");
                txtFieldArchivo.setText(event.getDragboard().getFiles().get(0).getAbsolutePath());
                logger.info("Archivo cargado.");
                pantallaAlFrente();
                comboBoxSeleccioneAlgoritmo.getSelectionModel().clearSelection();
            } else
                new Alerta(Alert.AlertType.INFORMATION).aviso("Debe de arrastrar un archivo que sea de extension arff.");
        });
    }

    //Funcion que inicializa los campos de algunos componentes
    void setCampos() {
        imageView.setImage(new Image("/fotos/wekafx_logo.png"));

        comboBoxSeleccioneAlgoritmo.disableProperty().bind(txtFieldArchivo.textProperty().isEmpty());
        comboBoxSeleccioneAlgoritmo.setItems(FXCollections.observableArrayList(Algoritmo.values()));

        txtFieldArchivo.textProperty().bindBidirectional(archivo);

        fileChooser.setTitle("Buscar algoritmo");
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo WEKA (*.arff)"));

        txtArea.setEditable(false);
    }

    //Funcion que resetea los campos de los componentes
    void resetearCampos() {
        logger.info("Reseteando campos.");
        txtArea.clear();
        comboBoxSeleccioneAlgoritmo.getSelectionModel().clearSelection();
        txtFieldArchivo.clear();
    }

    //Funcion para salir del programa
    void salir() {
        if (new Alerta(Alert.AlertType.WARNING).cerrarPrograma())
            Platform.exit();
    }

    //Funcion para buscar el archivo
    void buscarArchivo() {
        logger.info("Cargando archivo.");
        archivo.setValue(archivo());
        logger.info("Archivo " + archivo.toString() + " cargado.");
    }

    //Funcion que devuelve el archivo encontrado
    String archivo() {
        logger.info("Buscando archivo.");
        file = fileChooser.showOpenDialog(btnBuscarArchivo.getScene().getWindow());

        if (file != null) {
            return file.getAbsolutePath();
        } else {
            return null;
        }
    }

    //Funcion que trae la pantalla al frente
    void pantallaAlFrente() {
        logger.info("Pantalla principal al frente.");
        Stage stage = (Stage) btnBuscarArchivo.getScene().getWindow();
        stage.toFront();
        comboBoxSeleccioneAlgoritmo.requestFocus();
    }

    //Funcion que ejecuta el algoritmo a seleccionar
    void ejecutarAlgoritmo() {
        switch (comboBoxSeleccioneAlgoritmo.getSelectionModel().getSelectedItem()) {
            case J48:
                algortimoJ48();
                break;
            case NAIVE_BAYES:
                algoritmoNaiveBayes();
                break;
            default:
                break;
        }
    }

    void algortimoJ48() {
        AlgoritmoJ48 j48 = new AlgoritmoJ48(file);
        txtArea.setText(j48.imprimir().toString());
    }

    void algoritmoNaiveBayes() {
        AlgoritmoNaiveBayes naiveBayes = new AlgoritmoNaiveBayes(file);
        txtArea.setText(naiveBayes.imprimir().toString());
    }
}
