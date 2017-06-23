package edu.pucmm.mineriadedatos2017.alerta;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Alerta extends Alert {

    Alert alert;

    public Alerta(AlertType alertType) {
        super(alertType);
        alert = new Alert(alertType);
    }

    public Alerta(AlertType alertType, String contentText, ButtonType... buttons) {
        super(alertType, contentText, buttons);
        alert = new Alert(alertType, contentText, buttons);
    }

    public boolean cerrarPrograma() {
        alert.setTitle("Aviso!");
        alert.setHeaderText("Cerrando programa.");
        alert.setContentText("Esta seguro que desea cerrar el programa?");
        alert.setResizable(false);

        ButtonType buttonSi = new ButtonType("Si");
        ButtonType buttonNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonNo, buttonSi);

        Optional<ButtonType> resultado = alert.showAndWait();

        return resultado.get() == buttonSi;
    }

    public void aviso(String contenido) {
        alert.setTitle("Aviso!");
        alert.setHeaderText("Esta tratando de copiar un archivo invalido.");
        alert.setContentText(contenido);
    }
}
