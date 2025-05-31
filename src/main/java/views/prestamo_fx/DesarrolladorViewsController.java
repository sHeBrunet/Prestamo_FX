package views.prestamo_fx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.Desarrollador_Services;
import services.ServicesLocator;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DesarrolladorViewsController implements Initializable {

    @FXML
    private TextField nomDesarrolladorText;
    @FXML
    private Button buttonInsertDesarrollador;
    @FXML
    private Button buttonCancelDesarrollador;
    private Desarrollador_Services desarrolladorService = ServicesLocator.getDesarrollador();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonInsertDesarrollador.setOnAction(e -> insertarDesarrollador());
        buttonCancelDesarrollador.setOnAction(e -> cancelarOperacion());
    }

    private void insertarDesarrollador() {
        String nombre = nomDesarrolladorText.getText().trim();
        String error = validarNombre(nombre);

        if (error != null) {
            mostrarError(error);
        } else {
            try {
                insertDesarrollador(nombre);
            } catch (Exception ex) {
                mostrarError("Error: " + ex.getMessage());
            }
        }
    }

    private String validarNombre(String nombre) {
        String mensajeError = null;
        if (nombre.isEmpty()) {
            mensajeError = "El nombre no puede estar vacío";
        } else if (nombre.length() <= 2) {
            mensajeError = "El nombre es demasiado corto (mínimo 3 caracteres)";

        }else if (nombre.length() >= 50) {
            mensajeError = "El nombre es demasiado largo (máximo 49 caracteres)";

        }else if (!nombre.matches("^[\\p{L} .'-]+$"))
            mensajeError = "Solo se permiten letras y espacios ";

        return mensajeError;
    }

    private void insertDesarrollador(String nombre) throws SQLException, ClassNotFoundException {
        try {
            desarrolladorService.insertarDesarrollador(nombre);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mostrarMessage("Desarrollador insertado exitosamente");
        limpiarCampos();
        Stage stage = (Stage) buttonInsertDesarrollador.getScene().getWindow();
        stage.close();
    }

    private void mostrarMessage(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarError(String mensaje) {
        nomDesarrolladorText.getStyleClass().add("text-field-error");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de validación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.setOnHidden(e -> nomDesarrolladorText.getStyleClass().remove("text-field-error"));
        alert.showAndWait();
    }

    private void limpiarCampos() {
        nomDesarrolladorText.clear();
    }

    private void cancelarOperacion() {
        Stage stage = (Stage) buttonCancelDesarrollador.getScene().getWindow();
        stage.close();
    }
}