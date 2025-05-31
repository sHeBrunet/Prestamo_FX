module views.prestamo_fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens views.prestamo_fx to javafx.fxml;
    opens models to javafx.base;
    exports views.prestamo_fx;
}