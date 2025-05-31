package views.prestamo_fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.Area_Interes_Services;
import services.ServicesLocator;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    double x,y=0;

    @Override
    /*public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Principal.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 829, 516);
        scene.setOnMousePressed( event ->{
            x = event.getSceneX();
            y = event.getSceneY();
        });

        scene.setOnMouseDragged( evt->{
            stage.setX(evt.getScreenX()-x);
            stage.setY(evt.getScreenY()-y);
        });

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {

        launch();
        Area_Interes_Services a = ServicesLocator.getArea_Interes();
        try {
            System.out.println(a.buscarAreaInteres(1).getTipo_Area());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

        public void start(Stage stage) throws IOException {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("insertDesarrollador.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.show();
        }

        public static void main(String[] args) { launch(); }
    }