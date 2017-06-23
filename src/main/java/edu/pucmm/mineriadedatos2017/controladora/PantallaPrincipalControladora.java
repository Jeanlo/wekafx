package edu.pucmm.mineriadedatos2017.controladora;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.PrefixSelectionComboBox;
import org.controlsfx.control.textfield.CustomTextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jeanl on 21 jun 2017.
 */
public class PantallaPrincipalControladora implements Initializable {

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
    private CustomTextField txtFjeldArchivo;

    @FXML
    private Button btnBuscarArchivo;

    @FXML
    private HBox hBox2;

    @FXML
    private PrefixSelectionComboBox<?> comboBoxSeleccioneAlgoritmo;

    @FXML
    private Button btnEjecutarAlgoritmo;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextArea txtArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
