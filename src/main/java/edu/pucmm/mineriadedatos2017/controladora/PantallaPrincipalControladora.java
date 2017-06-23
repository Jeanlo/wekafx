package edu.pucmm.mineriadedatos2017.controladora;

import edu.pucmm.mineriadedatos2017.alerta.Alerta;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.controlsfx.control.PrefixSelectionComboBox;
import org.controlsfx.control.textfield.CustomTextField;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jeanl on 21 jun 2017.
 */
public class PantallaPrincipalControladora implements Initializable {

    //Propiedad para controlar los botones y activarlos y desactivarlos cuando se necesite
    private StringProperty archivo;

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
    private Button btnEjecutarAlgoritmo;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextArea txtArea;

    //Consctructor de la clase controladora
    public PantallaPrincipalControladora() {
        archivo = new SimpleStringProperty(null, "archivo", "");
    }

    //Funcion para inicializar los componentes de la pantalla
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCampos();
        resetearCampos();
    }

    @FXML
    void btnBuscarArchivoClick(ActionEvent event) {
        buscarArchivo();
    }

    @FXML
    void btnEjecutarAlgoritmoClick(ActionEvent event) {
        ejecutarAlgoritmo();
    }

    @FXML
    void comboBoxSeleccionAlgoritmoClick(ActionEvent event) {

    }

    @FXML
    void menuItemAbrirArchivoClick(ActionEvent event) {
        buscarArchivo();
    }

    @FXML
    void menuItemSalirClick(ActionEvent event) {
        salir();
    }

    void setCampos() {
        imageView.setImage(new Image("/fotos/wekafx_logo.png"));

        btnBuscarArchivo.setDisable(false);

        comboBoxSeleccioneAlgoritmo.disableProperty().bind(txtFieldArchivo.textProperty().isEmpty());
        btnEjecutarAlgoritmo.disableProperty().bind(txtFieldArchivo.textProperty().isEmpty());

        btnEjecutarAlgoritmo.disableProperty().bind(comboBoxSeleccioneAlgoritmo.valueProperty().isNull());
        comboBoxSeleccioneAlgoritmo.setItems(FXCollections.observableArrayList(Algoritmo.values()));

        txtFieldArchivo.setEditable(false);
        txtFieldArchivo.textProperty().bind(archivo);
    }

    void resetearCampos() {
        txtArea.clear();
        comboBoxSeleccioneAlgoritmo.getSelectionModel().clearSelection();
        txtFieldArchivo.clear();
    }

    void salir() {
        if (new Alerta(Alert.AlertType.WARNING).cerrarPrograma())
            Platform.exit();
    }

    void buscarArchivo() {
        archivo.setValue(archivo());
    }

    String archivo() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);

        return file.getAbsolutePath();
    }

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

    }

    void algoritmoNaiveBayes() {

    }
}
