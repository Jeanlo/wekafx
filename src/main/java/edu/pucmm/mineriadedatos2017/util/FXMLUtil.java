package edu.pucmm.mineriadedatos2017.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * Created by jeanl on 21 jun 2017.
 */
public class FXMLUtil {

    public static Parent cargarFXML(String rutaFXML) {
        try {
            Parent parent = FXMLLoader.load(FXMLUtil.class.getResource(rutaFXML));
            return parent;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
