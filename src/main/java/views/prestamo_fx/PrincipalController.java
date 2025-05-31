package views.prestamo_fx;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import models.*;
import services.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    // ------------------------------------------------------------Componentes FXML inyectados--------------------------------------------------------------------------------------
    @FXML private MenuItem btnVideojuegos, btnVideojuegosPlataforma, btnDesarrolladorVideojuego;
    @FXML private MenuItem btnCopias, btnCopiasPrestadas, btnCopiasDisponibles;
    @FXML private MenuItem btnPrestamos, btnPrestamosActivos, btnPrestamosFinalizados;
    @FXML private MenuItem btnSanciones, btnSancionesActivas, btnSancionesFinalizadas;
    @FXML private MenuItem btnUsuarios, btnUsuariosSancionados, btnUsuariosSinSanciones;
    @FXML private MenuItem btnReporte1, btnReporte2, btnReporte3, btnReporte4, btnReporte5, btnReporte6;
    @FXML private MenuItem btnEstadoCopia, btnPaisOrigen, btnTipoSancion, btnEstadoPrestamo, btnDesarrolladores, btnPlataformas,btnAreas;
    @FXML private StackPane contentPane;
    @FXML private StackPane loadingPane;
    @FXML private Button btnAgregar, btnEliminar, btnActualizar;


    //------------------------------------------------------Tablas-------------------------------------------------------------------------------------------------------
    @FXML private TableView<Videojuego> tablaVideojuegos;
    @FXML private TableView<Plataforma> tablaPlataformas;
    @FXML private TableView<Desarrollador> tablaDesarrolladores;
    @FXML private TableView<Copia_Videojuego> tablaCopias;
    @FXML private TableView<Sancion> tablaSanciones;
    @FXML private TableView<Prestamo> tablaPrestamos;
    @FXML private TableView<Usuario> tablaUsuarios;
    @FXML private TableView<Estado_Copia> tablaEstadoCopias;
    @FXML private TableView<Estado_Prestamo> tablaEstadoPrestamos;
    @FXML private TableView<Tipo_Sancion> tablaTipoSanciones;
    @FXML private TableView<Area_Interes> tablaAreasInteres;
    @FXML private TableView<Pais_Origen> tablaPaisesOrigen;
    @FXML private TableView<Plataforma_Videojuego> tablaPlataformaVideojuego;
    @FXML private TableView<Desarrollador_Videojuego> tablaDesarrolladorVideojuego;




    // ----------------------------------------------------------------Datos para las tablas-------------------------------------------------------------------------------------------
    private final ObservableList<Videojuego> videojuegoData = FXCollections.observableArrayList();
    private final ObservableList<Plataforma> plataformaData = FXCollections.observableArrayList();
    private final ObservableList<Desarrollador> desarrolladorData = FXCollections.observableArrayList();
    private final ObservableList<Copia_Videojuego> copiaData = FXCollections.observableArrayList();
    private final ObservableList<Sancion> sancionData = FXCollections.observableArrayList();
    private final ObservableList<Prestamo> prestamoData = FXCollections.observableArrayList();
    private final ObservableList<Usuario> usuarioData = FXCollections.observableArrayList();
    private final ObservableList<Estado_Copia> estadoCopiaData = FXCollections.observableArrayList();
    private final ObservableList<Estado_Prestamo> estadoPrestamoData = FXCollections.observableArrayList();
    private final ObservableList<Tipo_Sancion> tipoSancionData = FXCollections.observableArrayList();
    private final ObservableList<Area_Interes> areaInteresData = FXCollections.observableArrayList();
    private final ObservableList<Pais_Origen> paisOrigenData = FXCollections.observableArrayList();
    private final ObservableList<Plataforma_Videojuego> plataformaVideojuegoData = FXCollections.observableArrayList();
    private final ObservableList<Desarrollador_Videojuego> desarrolladorVideojuegoData = FXCollections.observableArrayList();

    //----------------------------------------------------------Services de la BD-------------------------------------------------------------------------------------------------------------
    private final Area_Interes_Services areaInteresServices = ServicesLocator.getArea_Interes();
    private final Copia_Videojuego_Services copiaVideojuegoServices = ServicesLocator.getCopia_Videojuego();
    private final Desarrollador_Services desarrolladorServices = ServicesLocator.getDesarrollador();
    private final Desarrollador_Videojuego_Services desarrolladorVideojuegoServices = ServicesLocator.getDesarrollador_Plataforma();
    private final Estado_Copia_Services estadoCopiaServices = ServicesLocator.getEstado_Copia();
    private final Estado_Prestamo_Services estadoPrestamoServices = ServicesLocator.getEstado_Prestamo();
    private final Pais_Origen_Services paisOrigenServices = ServicesLocator.getPais_Origen();
    private final Plataforma_Services plataformaServices = ServicesLocator.getPlataforma();
    private final Plataforma_Videojuego_Services plataformaVideojuegoServices = ServicesLocator.getPlataforma_Videojuego();
    private final Prestamo_Services prestamoServices = ServicesLocator.getPrestamo();
    private final Sancion_Services sancionServices = ServicesLocator.getSancion();
    private final Tipo_Sancion_Services tipoSancionServices = ServicesLocator.getTipo_Sancion();
    private final Usuario_Services usuarioServices = ServicesLocator.getUsuario();
    private final Videojuego_Services videojuegoServices = ServicesLocator.getVideojuego();

    // -----------------------------------------------------------------------------------------Tabla activa actualmente-----------------------------------------------------------------------
    private TableView<?> activeTable = null;
    private final MenuItem lastSelectedMenuItem = null;

    // Estilos
    private final String ACTIVE_MENU_STYLE = "-fx-background-color: #52458f; -fx-text-fill: white;";
    private final String DEFAULT_MENU_STYLE = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configureTables();
        setupEventHandlers();
        hideAllTables();
        loadingPane.setVisible(false);
        tablaVideojuegos.setVisible(true);
        try {
            loadVideojuegos();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    private void configureTables() {
        configureVideojuegoTable();
        configurePlataformaTable();
        configureDesarrolladorTable();
        configureCopiaTable();
        configureSancionTable();
        configurePrestamoTable();
        configureUsuarioTable();
        configureEstadoCopiaTable();
        configureEstadoPrestamoTable();
        configureTipoSancionTable();
        configureAreaInteresTable();
        configurePaisTable();
        configureDesarrolladorVideojuegoTable();
        configurePlataformaVideojuegoTable();
    }

    private void setupEventHandlers() {

            // -----------------------------------------------------------Manejadores para menú de Videojuegos------------------------------------------------------------------
            btnVideojuegos.setOnAction(e -> {
                try {
                    handleMenuItemClick("Videojuegos");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });

        btnAreas.setOnAction(e -> {
            try {
                handleMenuItemClick("Area");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

           btnVideojuegosPlataforma.setOnAction(e -> {
               try {
                   handleMenuItemClick("VideojuegosPlataforma");
               } catch (IOException ex) {
                   throw new RuntimeException(ex);
               } catch (ClassNotFoundException ex) {
                   throw new RuntimeException(ex);
               }
           });
            btnDesarrolladorVideojuego.setOnAction(e -> {
                try {
                    handleMenuItemClick("VideojuegoDesarrollador");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });

            // ------------------------------------------------------------Manejadores para menú de Copias
            btnCopias.setOnAction(e -> {
                try {
                    handleMenuItemClick("Copias");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });
            btnCopiasPrestadas.setOnAction(e -> {
                try {
                    handleMenuItemClick("CopiasPrestadas");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });
            btnCopiasDisponibles.setOnAction(e -> {
                try {
                    handleMenuItemClick("CopiasDisponibles");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });

            //---------------------------------------------------------- Manejadores para menú de Préstamos--------------------------------------------------------------
            btnPrestamos.setOnAction(e -> {
                try {
                    handleMenuItemClick("Prestamos");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });
            btnPrestamosActivos.setOnAction(e -> {
                try {
                    handleMenuItemClick("PrestamosActivos");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });
            btnPrestamosFinalizados.setOnAction(e -> {
                try {
                    handleMenuItemClick("PrestamosFinalizados");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });

            // ----------------------------------------------------------Manejadores para menú de Sanciones----------------------------------------------------------------
            btnSanciones.setOnAction(e -> {
                try {
                    handleMenuItemClick("Sanciones");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });
            btnSancionesActivas.setOnAction(e -> {
                try {
                    handleMenuItemClick("SancionesActivas");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });
            btnSancionesFinalizadas.setOnAction(e -> {
                try {
                    handleMenuItemClick("SancionesFinalizadas");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });

            // ------------------------------------------------------------Manejadores para menú de Usuarios----------------------------------------------------------------------
            btnUsuarios.setOnAction(e -> {
                try {
                    handleMenuItemClick("Usuarios");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });
            btnUsuariosSancionados.setOnAction(e -> {
                try {
                    handleMenuItemClick("UsuariosS");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });
            btnUsuariosSinSanciones.setOnAction(e -> {
                try {
                    handleMenuItemClick("UsuariosN");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });

            // ----------------------------------------------------------------------------------------------Manejadores para menú de Reportes------------------------------------------------
      /*  btnReporte1.setOnAction(e -> handleReporte("Reporte1"));
        btnReporte2.setOnAction(e -> handleReporte("Reporte2"));
        btnReporte3.setOnAction(e -> handleReporte("Reporte3"));
        btnReporte4.setOnAction(e -> handleReporte("Reporte4"));
        btnReporte5.setOnAction(e -> handleReporte("Reporte5"));
        btnReporte6.setOnAction(e -> handleReporte("Reporte6"));*/

            //------------------------------------------------------------ Manejadores para menú de Útiles--------------------------------------------------------------
            btnEstadoCopia.setOnAction(e -> {
                try {
                    handleMenuItemClick("EstadoCopia");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });
            btnPaisOrigen.setOnAction(e -> {
                try {
                    handleMenuItemClick("PaisOrigen");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });
            btnTipoSancion.setOnAction(e -> {
                try {
                    handleMenuItemClick("TipoSancion");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });
            btnEstadoPrestamo.setOnAction(e -> {
                try {
                    handleMenuItemClick("EstadoPrestamo");
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });
            btnDesarrolladores.setOnAction(e -> {
                try {
                    handleMenuItemClick("Desarrolladores");
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });
            btnPlataformas.setOnAction(e -> {
                try {
                    handleMenuItemClick("Plataformas");
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });

            // Manejadores para botones de acción
     /*   btnAgregar.setOnAction(e -> handleAddAction());
        btnEliminar.setOnAction(e -> handleDeleteAction());
        btnActualizar.setOnAction(e -> handleUpdateAction());*/

    }


    //----------------------------------------------------------------------Boton para la accion de los Reportes-------------------------------------------------------------------------
  /*  private void handleReporte(String tipoReporte) {
        showLoadingIndicator(true);

        new Thread(() -> {
            try {
                switch (tipoReporte) {
                    case "Reporte1":
                        List<Videojuego> reporte1 = ReporteServices.generarReporte1();
                        javafx.application.Platform.runLater(() -> {
                            videojuegoData.clear();
                            videojuegoData.addAll(reporte1);
                            showTable(tablaVideojuegos);
                        });
                        break;

                    case "Reporte2":
                        List<Videojuego> reporte2 = ReporteServices.generarReporte2();
                        javafx.application.Platform.runLater(() -> {
                            videojuegoData.clear();
                            videojuegoData.addAll(reporte2);
                            showTable(tablaVideojuegos);
                        });
                        break;

                    case "Reporte3":
                        List<Prestamo> reporte3 = ReporteServices.generarReporte3();
                        javafx.application.Platform.runLater(() -> {
                            prestamoData.clear();
                            prestamoData.addAll(reporte3);
                            showTable(tablaPrestamos);
                        });
                        break;

                    case "Reporte4":
                        List<Usuario> reporte4 = ReporteServices.generarReporte4();
                        javafx.application.Platform.runLater(() -> {
                            usuarioData.clear();
                            usuarioData.addAll(reporte4);
                            showTable(tablaUsuarios);
                        });
                        break;

                    case "Reporte5":
                        List<Usuario> reporte5 = ReporteServices.generarReporte5();
                        javafx.application.Platform.runLater(() -> {
                            usuarioData.clear();
                            usuarioData.addAll(reporte5);
                            showTable(tablaUsuarios);
                        });
                        break;

                    case "Reporte6":
                        List<Prestamo> reporte6 = ReporteServices.generarReporte6();
                        javafx.application.Platform.runLater(() -> {
                            prestamoData.clear();
                            prestamoData.addAll(reporte6);
                            showTable(tablaPrestamos);
                        });
                        break;
                }
            } catch (SQLException ex) {
                javafx.application.Platform.runLater(() ->
                        showErrorAlert("Error en reporte", "No se pudo generar el reporte: " + ex.getMessage()));
            } finally {
                javafx.application.Platform.runLater(() -> showLoadingIndicator(false));
            }
        }).start();
    }
*/


    // --------------------------------------------------------------------- CONFIGURACIÓN DE TABLAS ------------------------------------------------------------------------------------
    private void configureVideojuegoTable() {
        TableColumn<Videojuego, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("iD_Videojuego"));;

        TableColumn<Videojuego, String> tituloCol = new TableColumn<>("Título");
        tituloCol.setCellValueFactory(new PropertyValueFactory<>("Titulo_Juego"));

        TableColumn<Videojuego, Integer> anioCol = new TableColumn<>("Año");
        anioCol.setCellValueFactory(new PropertyValueFactory<>("Anio_Lanzamiento"));

        TableColumn<Videojuego, Integer> jugadoresCol = new TableColumn<>("Jugadores");
        jugadoresCol.setCellValueFactory(new PropertyValueFactory<>("Cantidad_Jugadores"));

        TableColumn<Videojuego, String> descCol = new TableColumn<>("Descripción");
        descCol.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));

        TableColumn<Videojuego, String> paisCol = new TableColumn<>("País");
        paisCol.setCellValueFactory(new PropertyValueFactory<>("ID_Pais_Origen"));

        TableColumn<Videojuego, Integer> copiasCol = new TableColumn<>("Cantidad Copias");
        copiasCol.setCellValueFactory(new PropertyValueFactory<>("Cantidad_Copias_Existencia"));

        tablaVideojuegos.getColumns().addAll(idCol, tituloCol, anioCol, jugadoresCol, descCol, paisCol, copiasCol);
        tablaVideojuegos.setItems(videojuegoData);
    }

    private void configurePlataformaTable() {
        TableColumn<Plataforma, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("ID_Plataforma"));

        TableColumn<Plataforma, String> nombreCol = new TableColumn<>("Plataforma");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("Nombre_Plataforma"));

        tablaPlataformas.getColumns().addAll(idCol, nombreCol);
        tablaPlataformas.setItems(plataformaData);
    }

    private void configureDesarrolladorTable() {
        TableColumn<Desarrollador, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("ID_Desarrollador"));

        TableColumn<Desarrollador, String> nombreCol = new TableColumn<>("Nombre");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("Nombre_Desarrollador"));

        tablaDesarrolladores.getColumns().addAll(idCol, nombreCol);
        tablaDesarrolladores.setItems(desarrolladorData);
    }

    private void configureCopiaTable() {
        TableColumn<Copia_Videojuego, Integer> videojuegoCol = new TableColumn<>("Videojuego");
        videojuegoCol.setCellValueFactory(new PropertyValueFactory<>("ID_Videojuego"));

        TableColumn<Copia_Videojuego, Integer> serieCol = new TableColumn<>("Núm Serie");
        serieCol.setCellValueFactory(new PropertyValueFactory<>("Num_Serie"));

        TableColumn<Copia_Videojuego, Integer> estadoCol = new TableColumn<>("Estado");
        estadoCol.setCellValueFactory(new PropertyValueFactory<>("ID_Estado_Copia"));

        tablaCopias.getColumns().addAll(videojuegoCol, serieCol, estadoCol);
        tablaCopias.setItems(copiaData);
    }

    private void configureSancionTable() {
        TableColumn<Sancion, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("ID_Sancion"));

        TableColumn<Sancion, String> fechaImpCol = new TableColumn<>("Imposición");
        fechaImpCol.setCellValueFactory(cellData -> {
            java.sql.Date fecha = cellData.getValue().getFecha_Imposicion();
            return new javafx.beans.property.SimpleStringProperty(formatDate(fecha));
        });

        TableColumn<Sancion, String> fechaFinCol = new TableColumn<>("Finalización");
        fechaFinCol.setCellValueFactory(cellData -> {
            java.sql.Date fecha = cellData.getValue().getFecha_Finalizacion();
            return new javafx.beans.property.SimpleStringProperty(formatDate(fecha));
        });

        TableColumn<Sancion, Integer> tipoCol = new TableColumn<>("Tipo");
        tipoCol.setCellValueFactory(new PropertyValueFactory<>("ID_Tipo_Sancion"));

        TableColumn<Sancion, Integer> usuarioCol = new TableColumn<>("Usuario");
        usuarioCol.setCellValueFactory(new PropertyValueFactory<>("ID_Usuario"));


        tablaSanciones.getColumns().addAll(idCol, fechaImpCol, fechaFinCol, tipoCol, usuarioCol);
        tablaSanciones.setItems(sancionData);
    }

    private void configurePrestamoTable(){

        TableColumn<Prestamo, Integer> idCol = new TableColumn<>("ID de Prestamo");
        idCol.setCellValueFactory(new PropertyValueFactory<>("ID_Prestamo"));


        TableColumn<Prestamo, Integer> idVCol = new TableColumn<>("ID de Videojuego");
        idVCol.setCellValueFactory(new PropertyValueFactory<>("iD_Videojuego"));

        TableColumn<Prestamo, Integer> numCol = new TableColumn<>("Numero Serie");
        numCol.setCellValueFactory(new PropertyValueFactory<>("Num_Serie"));

        TableColumn<Prestamo, Integer> usuarioCol = new TableColumn<>("Usuario");
        usuarioCol.setCellValueFactory(new PropertyValueFactory<>("ID_Usuario"));

        TableColumn<Prestamo, Integer> estadoCol = new TableColumn<>("Estado Prestamo");
        estadoCol.setCellValueFactory(new PropertyValueFactory<>("ID_Estado_Prestamo"));

        TableColumn<Prestamo, String> fechaIniCol = new TableColumn<>("Fecha Inicio");
        fechaIniCol.setCellValueFactory(cellData -> {
            java.sql.Date fecha = cellData.getValue().getFecha_Inicio();
            return new javafx.beans.property.SimpleStringProperty(formatDate(fecha));
        });

        TableColumn<Prestamo, String> fechaDevCol = new TableColumn<>("Fecha Devolucion");
        fechaDevCol.setCellValueFactory(cellData -> {
            java.sql.Date fecha = cellData.getValue().getFecha_Devolucion();
            return new javafx.beans.property.SimpleStringProperty(formatDate(fecha));
        });


        tablaPrestamos.getColumns().addAll(idCol,idVCol,numCol,usuarioCol,estadoCol,fechaIniCol,fechaDevCol);
        tablaPrestamos.setItems(prestamoData);
    }

    private void configureUsuarioTable(){
        TableColumn<Usuario, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("ID_Usuario"));

        TableColumn<Usuario, String> nombreCol = new TableColumn<>("Nombre");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("Nombre_Usuario"));

        TableColumn<Usuario, Integer> areaCol = new TableColumn<>("Area Interes");
        areaCol.setCellValueFactory(new PropertyValueFactory<>("ID_Area_Interes"));

        tablaUsuarios.getColumns().addAll(idCol, nombreCol,areaCol);
        tablaUsuarios.setItems(usuarioData);

    }

    private void configureEstadoCopiaTable(){
        TableColumn<Estado_Copia, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("ID_Estado_Copia"));

        TableColumn<Estado_Copia, String> nombreCol = new TableColumn<>("Nombre Estado");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("Tipo_Estado"));

        tablaEstadoCopias.getColumns().addAll(idCol, nombreCol);
        tablaEstadoCopias.setItems(estadoCopiaData);
    }

    private void configureEstadoPrestamoTable(){
        TableColumn<Estado_Prestamo, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("ID_Estado_Prestamo"));

        TableColumn<Estado_Prestamo, String> nombreCol = new TableColumn<>("Nombre Estado");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("Estado_Prestamo"));

        tablaEstadoPrestamos.getColumns().addAll(idCol, nombreCol);
        tablaEstadoPrestamos.setItems(estadoPrestamoData);
    }

    private void configureTipoSancionTable(){
        TableColumn<Tipo_Sancion, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("ID_Tipo_Sancion"));

        TableColumn<Tipo_Sancion, String> nombreCol = new TableColumn<>("Nombre Tipo");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("Tipo_Sancion"));

        tablaTipoSanciones.getColumns().addAll(idCol, nombreCol);
        tablaTipoSanciones.setItems(tipoSancionData);
    }

    private void configureAreaInteresTable(){
        TableColumn<Area_Interes, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("ID_Area_Interes"));

        TableColumn<Area_Interes, String> nombreCol = new TableColumn<>("Nombre Area");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("Tipo_Area"));

        tablaAreasInteres.getColumns().addAll(idCol, nombreCol);
        tablaAreasInteres.setItems(areaInteresData);
    }

    private void configurePaisTable(){
        TableColumn<Pais_Origen, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("ID_Pais"));

        TableColumn<Pais_Origen, String> nombreCol = new TableColumn<>("Nombre Pais");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("Nombre_Pais"));

        tablaPaisesOrigen.getColumns().addAll(idCol, nombreCol);
        tablaPaisesOrigen.setItems(paisOrigenData);
    }

    private void configurePlataformaVideojuegoTable(){
        TableColumn<Plataforma_Videojuego, Integer> idPCol = new TableColumn<>("ID_Plataforma");
        idPCol.setCellValueFactory(new PropertyValueFactory<>("ID_Plataforma"));

        TableColumn<Plataforma_Videojuego, Integer> idVCol = new TableColumn<>("ID_Videojuego");
        idVCol.setCellValueFactory(new PropertyValueFactory<>("ID_Videojuego"));

        tablaPlataformaVideojuego.getColumns().addAll(idPCol, idVCol);
        tablaPlataformaVideojuego.setItems(plataformaVideojuegoData);
    }

    private void configureDesarrolladorVideojuegoTable(){
        TableColumn<Desarrollador_Videojuego, Integer> idPCol = new TableColumn<>("ID Desarrollador");
        idPCol.setCellValueFactory(new PropertyValueFactory<>("ID_Desarrollador"));

        TableColumn<Desarrollador_Videojuego, Integer> idVCol = new TableColumn<>("ID_Videojuego");
        idVCol.setCellValueFactory(new PropertyValueFactory<>("ID_Videojuego"));

        tablaDesarrolladorVideojuego.getColumns().addAll(idPCol, idVCol);
        tablaDesarrolladorVideojuego.setItems(desarrolladorVideojuegoData);
    }



    // -----------------------------------------------------------------------MÉTODOS DE CARGA DE DATOS-------------------------------------------------------------------------------
    // -------------------------------------------------------MÉTODOS DE CARGA PARA VIDEOJUEGOS ----------------------------------------------------
    private void loadVideojuegos() throws IOException, ClassNotFoundException {
        try {
            List<Videojuego> videojuegos = videojuegoServices.load_videojuego();
            videojuegoData.clear();
            videojuegoData.addAll(videojuegos);
        } catch (SQLException ex) {
            showErrorAlert("Error al cargar videojuegos", ex.getMessage());
        }
    }

    private void loadPlataformasVideojuego() throws IOException, ClassNotFoundException {
        try {
            List<Plataforma_Videojuego> plataformasV = plataformaVideojuegoServices.loadVideojuegoPlataforma();
            plataformaVideojuegoData.clear();
            plataformaVideojuegoData.addAll(plataformasV);
        } catch (SQLException ex) {
            showErrorAlert("Error al cargar plataformas", ex.getMessage());
        }
    }

    private void loadDesarrolladoresVideojuego() throws IOException, ClassNotFoundException {
        try {
            List<Desarrollador_Videojuego> desarrolladoresV = desarrolladorVideojuegoServices.allVideojuegoDesarrollador();
            desarrolladorVideojuegoData.clear();
            desarrolladorVideojuegoData.addAll(desarrolladoresV);
        } catch (SQLException ex) {
            showErrorAlert("Error al cargar desarrolladores", ex.getMessage());
        }
    }


    // ------------------------------------------------------------ MÉTODOS DE CARGA PARA COPIAS --------------------------------------------------------
    private void loadCopias() throws IOException, ClassNotFoundException {
        try {
            List<Copia_Videojuego> copias = copiaVideojuegoServices.load_copia();
            copiaData.clear();
            copiaData.addAll(copias);
        } catch (SQLException ex) {
            showErrorAlert("Error al cargar copias", ex.getMessage());
        }
    }

    private void loadCopiasByEstado(int tipo) throws IOException, ClassNotFoundException {
        try {
            List<Copia_Videojuego> copias = new ArrayList<>();
            copiaData.clear();
            if(tipo==1) copias = copiaVideojuegoServices.load_copia_prestadas(); else copias = copiaVideojuegoServices.load_copia_disponibles();
            copiaData.addAll(copias);
        } catch (SQLException ex) {
            showErrorAlert("Error al cargar copias por estado", ex.getMessage());
        }
    }

    // ---------------------------------------------------------------MÉTODOS DE CARGA PARA PRÉSTAMOS ----------------------------------------------
    private void loadPrestamos() throws IOException, ClassNotFoundException {
        try {
            List<Prestamo> prestamos = prestamoServices.load_prestamo();
            prestamoData.clear();
            prestamoData.addAll(prestamos);
        } catch (SQLException ex) {
            showErrorAlert("Error al cargar préstamos", ex.getMessage());
        }
    }

    private void loadPrestamosByEstado(int tipo) throws IOException, ClassNotFoundException {
        try {
            List<Prestamo> prestamos = new ArrayList<>();
            prestamoData.clear();
            if(tipo==1) prestamos = prestamoServices.load_prestamo_activos(); else prestamos = prestamoServices.load_prestamo_finalizados();
            prestamoData.addAll(prestamos);
        } catch (SQLException ex) {
            showErrorAlert("Error al cargar préstamos por estado", ex.getMessage());
        }
    }

    // -------------------------------------------------------MÉTODOS DE CARGA PARA SANCIONE------------------------------------------------------------
    private void loadSanciones() throws IOException, ClassNotFoundException {
        try {
            List<Sancion> sanciones =  sancionServices.load_sancion();
            sancionData.clear();
            sancionData.addAll(sanciones);
        } catch (SQLException ex) {
            showErrorAlert("Error al cargar sanciones", ex.getMessage());
        }
    }

    private void loadSancionesByEstado(int tipo) throws IOException, ClassNotFoundException {
        try {
            List<Sancion> sanciones = new ArrayList<>();
            sancionData.clear();
            if (tipo == 1) sanciones = sancionServices.load_sancion_activa(); else sancionServices.load_sancion_finalizadas();
            sancionData.addAll(sanciones);
        } catch (SQLException ex) {
            showErrorAlert("Error al cargar sanciones por estado", ex.getMessage());
        }
    }

    // --------------------------------------------------- MÉTODOS DE CARGA PARA USUARIOS --------------------------------------------------------------
    private void loadUsuarios() throws IOException, ClassNotFoundException {
        try {
            List<Usuario> usuarios = usuarioServices.load_usuario();
            usuarioData.clear();
            usuarioData.addAll(usuarios);
        } catch (SQLException ex) {
            showErrorAlert("Error al cargar usuarios", ex.getMessage());
        }
    }

    /*private void loadUsuariosBySancion(boolean sancionados) {
        try {
          //  List<Usuario> usuarios = sancionados ?
               //     UsuarioServices.obtenerUsuariosSancionados() :
                 //   UsuarioServices.obtenerUsuariosSinSanciones();

            usuarioData.clear();
            //usuarioData.addAll(usuarios);
        } catch (SQLException ex) {
            showErrorAlert("Error al cargar usuarios", ex.getMessage());
        }
    }*/

    // -----------------------------------------------------------------------------------MÉTODOS DE CARGA PARA ÚTILES ----------------------------------------------------------------------
    private void loadEstadosCopia() throws IOException, ClassNotFoundException {
        try {
            List<Estado_Copia> estados = estadoCopiaServices.allEstadosCopias();
            estadoCopiaData.clear();
            estadoCopiaData.addAll(estados);
        } catch (SQLException ex) {
            showErrorAlert("Error al cargar estados de copia", ex.getMessage());
        }
    }

    private void loadPaisesOrigen() throws IOException, ClassNotFoundException {
        try {
            List<Pais_Origen> paises = paisOrigenServices.load_pais();
            paisOrigenData.clear();
            paisOrigenData.addAll(paises);
        } catch (SQLException ex) {
            showErrorAlert("Error al cargar países de origen", ex.getMessage());
        }
    }

    private void loadTiposSancion() throws IOException, ClassNotFoundException {
        try {
            List<Tipo_Sancion> tipos = tipoSancionServices.load_tipo_sancion();
            tipoSancionData.clear();
            tipoSancionData.addAll(tipos);
        } catch (SQLException ex) {
            showErrorAlert("Error al cargar tipos de sanción", ex.getMessage());
        }
    }

    private void loadAreaInteres(){
        try {
            List<Area_Interes> areas = areaInteresServices.listado_de_areas();
            areaInteresData.clear();
            areaInteresData.addAll(areas);
        } catch (SQLException | IOException ex) {
            showErrorAlert("Error al cargar estados de préstamo", ex.getMessage());
        }
    }

    private void loadEstadosPrestamo() throws IOException, ClassNotFoundException {
        try {
            List<Estado_Prestamo> estados = estadoPrestamoServices.load_estado_prestamo();
            estadoPrestamoData.clear();
            estadoPrestamoData.addAll(estados);
        } catch (SQLException ex) {
            showErrorAlert("Error al cargar estados de préstamo", ex.getMessage());
        }
    }

    private void loadPlataformas() throws IOException, ClassNotFoundException {
        try {
            List<Plataforma> plataformas = plataformaServices.load_plataforma();
            plataformaData.clear();
            plataformaData.addAll(plataformas);
        } catch (SQLException ex) {
            showErrorAlert("Error al cargar plataformas", ex.getMessage());
        }
    }

    private void loadDesarrolladores() throws IOException, ClassNotFoundException {
        try {
            List<Desarrollador> desarrolladores = desarrolladorServices.load_desarrollador();
            desarrolladorData.clear();
            desarrolladorData.addAll(desarrolladores);
        } catch (SQLException ex) {
            showErrorAlert("Error al cargar desarrolladores", ex.getMessage());
        }
    }


    //-------------------------------------------------------------------------------Funciones de los CRUD---------------------------------------------------------------------------------------
/*    private void handleAddAction() {
        if (activeTable == tablaVideojuegos) {
            agregarVideojuego();
        } else if (activeTable == tablaPlataformas) {
            agregarPlataforma();
        } else if (activeTable == tablaCopias) {
            agregarCopia();
        }
        // ... casos para otras tablas
    }

    private void agregarVideojuego() {
        // Lógica para mostrar diálogo de agregar videojuego
        // Ejemplo simplificado:
        Videojuego nuevoVideojuego = VideojuegoDialog.mostrarDialogoAgregar();
        if (nuevoVideojuego != null) {
            try {
                VideojuegoServices.agregarVideojuego(nuevoVideojuego);
                videojuegoData.add(nuevoVideojuego);
            } catch (SQLException ex) {
                showErrorAlert("Error al agregar", ex.getMessage());
            }
        }
    }
*/
 /*   private void handleDeleteAction() {
        if (activeTable == null || activeTable.getSelectionModel().isEmpty()) {
            showWarning("Selección requerida", "Por favor seleccione un elemento");
            return;
        }

        Object selectedItem = activeTable.getSelectionModel().getSelectedItem();
        if (!confirmarEliminacion()) return;

        try {
            if (activeTable == tablaVideojuegos) {
                Videojuego videojuego = (Videojuego) selectedItem;
                VideojuegoServices.eliminarVideojuego(videojuego.getID_Videojuego());
                videojuegoData.remove(videojuego);
            } else if (activeTable == tablaPlataformas) {
                Plataforma plataforma = (Plataforma) selectedItem;
                PlataformaServices.eliminarPlataforma(plataforma.getID_Plataforma());
                plataformaData.remove(plataforma);
            } else if (activeTable == tablaCopias) {
                Copia_Videojuego copia = (Copia_Videojuego) selectedItem;
                CopiaServices.eliminarCopia(copia.getNum_Serie());
                copiaData.remove(copia);
            }
            // ... casos para otras tablas
        } catch (SQLException ex) {
            showErrorAlert("Error al eliminar", ex.getMessage());
        }
    }
*/
    private boolean confirmarEliminacion() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar eliminación");
        alert.setHeaderText("¿Está seguro de eliminar este elemento?");
        alert.setContentText("Esta acción no se puede deshacer.");

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

   /* private void handleUpdateAction() {
        if (activeTable == null || activeTable.getSelectionModel().isEmpty()) {
            showWarning("Selección requerida", "Por favor seleccione un elemento");
            return;
        }

        Object selectedItem = activeTable.getSelectionModel().getSelectedItem();
        try {
            if (activeTable == tablaVideojuegos) {
                //actualizarVideojuego((Videojuego) selectedItem);
            } else if (activeTable == tablaPlataformas) {
                //actualizarPlataforma((Plataforma) selectedItem);
            } else if (activeTable == tablaCopias) {
              //  actualizarCopia((Copia_Videojuego) selectedItem);
            }
            // ... casos para otras tablas
        } catch (SQLException ex) {
            showErrorAlert("Error al actualizar", ex.getMessage());
        }
    }
*/


    // ----------------------------------------------------------------------------------- UTILIDADES -------------------------------------------------------------------------------------------
    // Utilidades
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showWarning(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Animación para mostrar/ocultar submenús
    private void animateMenuContainer(double targetWidth) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(300),
                        new KeyValue(contentPane.prefWidthProperty(), targetWidth)
                ));
        timeline.play();
    }

    private void showTable(TableView<?> table) {
        hideAllTables();
        table.setVisible(true);
        activeTable = table;

        // Animación de transición
        FadeTransition ft = new FadeTransition(Duration.millis(300), table);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    private void hideAllTables() {
        TableView<?>[] allTables = {
                tablaVideojuegos, tablaPlataformas, tablaDesarrolladores,
                tablaCopias, tablaSanciones, tablaPrestamos, tablaUsuarios,
                tablaEstadoCopias, tablaEstadoPrestamos, tablaTipoSanciones, tablaAreasInteres,tablaDesarrolladorVideojuego,
                tablaPlataformaVideojuego,tablaPaisesOrigen
        };

        for (TableView<?> table : allTables) {
            table.setVisible(false);
        }
    }
    private void disableAllTables() {
        TableView<?>[] allTables = {
                tablaVideojuegos, tablaPlataformas, tablaDesarrolladores,
                tablaCopias, tablaSanciones, tablaPrestamos, tablaUsuarios,
                tablaEstadoCopias, tablaEstadoPrestamos, tablaTipoSanciones, tablaAreasInteres,tablaDesarrolladorVideojuego,
                tablaPlataformaVideojuego,tablaPaisesOrigen
        };

        for (TableView<?> table : allTables) {
            table.setDisable(true);
        }
    }
    private void handleMenuItemClick(String entidad) throws IOException, ClassNotFoundException {
        switch (entidad){
            case  "Videojuegos":{
                hideAllTables();
                disableAllTables();
                tablaVideojuegos.setDisable(false);
                tablaVideojuegos.setVisible(true);
                loadVideojuegos();
            }break;

            case  "VideojuegosPlataforma":{
                hideAllTables();
                disableAllTables();
                tablaPlataformaVideojuego.setDisable(false);
                tablaPlataformaVideojuego.setVisible(true);
                loadPlataformasVideojuego();
            }break;

            case  "VideojuegoDesarrollador":{
                hideAllTables();
                disableAllTables();
                tablaDesarrolladorVideojuego.setDisable(false);
                tablaDesarrolladorVideojuego.setVisible(true);
                loadDesarrolladoresVideojuego();
            }break;

            case  "Copias":{
                hideAllTables();
                disableAllTables();
                tablaCopias.setDisable(false);
                tablaCopias.setVisible(true);
                loadCopias();
            }break;

            case  "CopiasPrestadas":{
                hideAllTables();
                disableAllTables();
                tablaCopias.setDisable(false);
                tablaCopias.setVisible(true);
                loadCopiasByEstado(1);
            }break;

            case  "CopiasDisponibles":{
                hideAllTables();
                disableAllTables();
                tablaCopias.setDisable(false);
                tablaCopias.setVisible(true);
                loadCopiasByEstado(2);
            }break;

            case  "Prestamos":{
                hideAllTables();
                disableAllTables();
                tablaPrestamos.setDisable(false);
                tablaPrestamos.setVisible(true);
                loadPrestamos();
            }break;

            case  "PrestamosActivos":{
                hideAllTables();
                disableAllTables();
                tablaPrestamos.setDisable(false);
                tablaPrestamos.setVisible(true);
                loadPrestamosByEstado(1);
            }break;

            case  "PrestamosFinalizados":{
                hideAllTables();
                disableAllTables();
                tablaPrestamos.setDisable(false);
                tablaPrestamos.setVisible(true);
                loadPrestamosByEstado(2);
            }break;

            case  "Sanciones":{
                hideAllTables();
                disableAllTables();
                tablaSanciones.setDisable(false);
                tablaSanciones.setVisible(true);
                loadSanciones();
            }break;

            case  "SancionesActivas":{
                hideAllTables();
                disableAllTables();
                tablaSanciones.setDisable(false);
                tablaSanciones.setVisible(true);
                loadSancionesByEstado(1);
            }break;

            case  "SancionesFinalizadas":{
                hideAllTables();
                disableAllTables();
                tablaSanciones.setDisable(false);
                tablaSanciones.setVisible(true);
                loadSancionesByEstado(2);
            }break;

            case  "Usuarios":{
                hideAllTables();
                disableAllTables();
                tablaUsuarios.setDisable(false);
                tablaUsuarios.setVisible(true);
                loadUsuarios();
            }break;

            case  "UsuariosS":{
                hideAllTables();
                disableAllTables();
                tablaUsuarios.setDisable(false);
                tablaUsuarios.setVisible(true);
                loadVideojuegos();
            }break;

            case  "UsuariosN": {
                hideAllTables();
                disableAllTables();
                tablaUsuarios.setDisable(false);
                tablaUsuarios.setVisible(true);
                loadVideojuegos();
            } break;

            case  "EstadoCopia": {
                hideAllTables();
                disableAllTables();
                tablaEstadoCopias.setDisable(false);
                tablaEstadoCopias.setVisible(true);
                loadEstadosCopia();
            } break;

            case  "PaisOrigen": {
                hideAllTables();
                disableAllTables();
                tablaPaisesOrigen.setDisable(false);
                tablaPaisesOrigen.setVisible(true);
                loadPaisesOrigen();
            } break;

            case  "TipoSancion": {
                hideAllTables();
                disableAllTables();
                tablaTipoSanciones.setDisable(false);
                tablaTipoSanciones.setVisible(true);
                loadTiposSancion();
            } break;

            case  "EstadoPrestamo": {
                hideAllTables();
                disableAllTables();
                tablaEstadoPrestamos.setDisable(false);
                tablaEstadoPrestamos.setVisible(true);
                loadEstadosPrestamo();
            } break;

            case  "Desarrolladores": {
                hideAllTables();
                disableAllTables();
                tablaDesarrolladores.setDisable(false);
                tablaDesarrolladores.setVisible(true);
                loadDesarrolladores();
            } break;

            case  "Plataformas": {
                hideAllTables();
                disableAllTables();
                tablaPlataformas.setDisable(false);
                tablaPlataformas.setVisible(true);
                loadPlataformas();
            } break;

            case  "Area": {
                hideAllTables();
                disableAllTables();
                tablaAreasInteres.setDisable(false);
                tablaAreasInteres.setVisible(true);
                loadAreaInteres();
            } break;
        }
    }

    private void showLoadingIndicator(boolean show) {
        loadingPane.setVisible(show);
        if (show) {
            FadeTransition ft = new FadeTransition(Duration.millis(300), loadingPane);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.play();
        }
    }



    private String formatDate(java.sql.Date date) {
        if (date == null) return "N/A";
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
}