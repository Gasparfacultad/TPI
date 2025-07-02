package Controller;

import Models.Arbitro;
import Models.Campeonato;
import Models.Entrenador;
import Models.Equipo;
import Models.Jugador;
import Models.Partido;
import View.vistaGeneral;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import Connection.conexion;
import java.sql.Statement;

public class Controlador {
    private Campeonato campeonato;
    private vistaGeneral vista;

    public Controlador() {
        this.vista = new vistaGeneral();
        String nombre = vista.pedirString("Ingrese el nombre del campeonato: ");
        this.campeonato = new Campeonato(nombre);
        
    }

    public void iniciar() throws SQLException {
        vista.mostrarMensaje("Bienvenido al sistema de gestión del Campeonato") ;
        int opcion;
        do {
            vista.mostrarMenuPrincipal(campeonato);
            opcion = vista.pedirInt("Ingrese su opción: ");

            switch (opcion) {
                case 1:
                    gestionarEquipos();
                    break;
                case 2:
                    gestionarArbitros();
                    break;
                case 3:
                    gestionarPartidos();
                    break;
                case 4:
                    vista.mostrarMensaje("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    vista.mostrarMensaje("Opción inválida. Por favor, intente de nuevo.");
            }
        } while (opcion != 4);

        vista.cerrarScanner(); // Cerrar el scanner al finalizar el programa
    }

    private void gestionarEquipos() throws SQLException {
        int opcion;
        do {
            vista.mostrarMenuEquipos();
            opcion = vista.pedirInt("Ingrese su opción: ");

            switch (opcion) {
                case 1:
                    crearEquipo();
                    break;
                case 2:
                    añadirJugadorAEquipo();
                    break;
                case 3:
                    vista.mostrarListaEquipos(campeonato.getEquipos());
                    break;
                case 4:
                    verDetallesEquipo();
                    break;
                case 5:
                    vista.mostrarMensaje("Volviendo al Menú Principal...");
                    break;
                default:
                    vista.mostrarMensaje("Opción inválida. Por favor, intente de nuevo.");
            }
        } while (opcion != 5);
    }

    private void crearEquipo() throws SQLException {
        try (Connection conn = conexion.getConnection()) {
            String nombreEquipo = vista.pedirString("Ingrese el nombre del equipo: ");
            String nombreEntrenador = vista.pedirString("Ingrese el nombre del entrenador: ");
            Entrenador entrenador = new Entrenador(nombreEntrenador);
            Equipo nuevoEquipo = new Equipo(nombreEquipo, entrenador);
            campeonato.addEquipo(nuevoEquipo);
            vista.mostrarMensaje("Equipo '" + nombreEquipo + "' creado exitosamente.");
            
            String sqlInsertEntrenador = "INSERT INTO entrenadores (nombre) VALUES (?)";
            int idEntrenador = -1;
            try (PreparedStatement pstmt = conn.prepareStatement(sqlInsertEntrenador, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, nombreEntrenador);
                pstmt.executeUpdate();

                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    idEntrenador = rs.getInt(1);
                }
            }
            // Insertar el equipo con el id del entrenador
            String sqlInsertEquipo = "INSERT INTO equipos (nombre, entrenador_id) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlInsertEquipo)) {
                pstmt.setString(1, nombreEquipo);
                pstmt.setInt(2, idEntrenador);
                pstmt.executeUpdate();
            }
        } catch (InputMismatchException e) {
            vista.mostrarMensaje("Entrada inválida. Por favor, ingrese un valor correcto.");
        }
    }

    private void añadirJugadorAEquipo() {
        try (Connection conn = conexion.getConnection()) {
        if (campeonato.getEquipos().isEmpty()) {
            vista.mostrarMensaje("No hay equipos registrados para añadir jugadores.");
            return;
        }

        vista.mostrarListaEquipos(campeonato.getEquipos());
        int indiceEquipo = vista.pedirInt("Seleccione el número del equipo al que desea añadir un jugador: ") - 1;

        if (indiceEquipo >= 0 && indiceEquipo < campeonato.getEquipos().size()) {
            Equipo equipoSeleccionado = campeonato.getEquipos().get(indiceEquipo);
            
            String nombreJugador = vista.pedirString("Ingrese el nombre del jugador: ");
            String nroCamiseta = vista.pedirString("Ingrese el número de camiseta del jugador: ");
            
            Jugador nuevoJugador = new Jugador(nombreJugador, nroCamiseta, 0);
            equipoSeleccionado.addJugador(nuevoJugador);
            vista.mostrarMensaje("Jugador '" + nombreJugador + "' añadido a '" + equipoSeleccionado.getNombre() + "' exitosamente.");
            
            String sqlBuscarEquipo = "SELECT id_equipo FROM equipos WHERE nombre = ?";
            int equipoId = -1;
            try (PreparedStatement stmt = conn.prepareStatement(sqlBuscarEquipo)) {
                stmt.setString(1, equipoSeleccionado.getNombre());
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    equipoId = rs.getInt("id_equipo");
                }
            }
            // Insertar el jugador en la base de datos
            String sqlInsertJugador = "INSERT INTO jugadores (nombre, nro_camiseta, equipo_id) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlInsertJugador)) {
                pstmt.setString(1, nuevoJugador.getNombre());
                pstmt.setString(2, nuevoJugador.getNroCamiseta());
                pstmt.setInt(3, equipoId);
                pstmt.executeUpdate();
            }
        } else {
            vista.mostrarMensaje("Número de equipo inválido.");
        }
            } catch (SQLException e) {
            vista.mostrarMensaje("Error al añadir el jugador al equipo: " + e.getMessage());
        }
    }

    private void verDetallesEquipo() {
        if (campeonato.getEquipos().isEmpty()) {
            vista.mostrarMensaje("No hay equipos registrados para ver detalles.");
            return;
        }

        vista.mostrarListaEquipos(campeonato.getEquipos());
        int indiceEquipo = vista.pedirInt("Seleccione el número del equipo para ver sus detalles: ") - 1;

        if (indiceEquipo >= 0 && indiceEquipo < campeonato.getEquipos().size()) {
            Equipo equipoSeleccionado = campeonato.getEquipos().get(indiceEquipo);
            vista.mostrarDetallesEquipo(equipoSeleccionado);
        } else {
            vista.mostrarMensaje("Número de equipo inválido.");
        }
    }

    private void gestionarArbitros() {
        int opcion;
        do {
            vista.mostrarMenuArbitros();
            opcion = vista.pedirInt("Ingrese su opción: ");

            switch (opcion) {
                case 1:
                    crearArbitro();
                    break;
                case 2:
                    vista.mostrarListaArbitros(getArbitrosRegistrados()); 
                    break;
                case 3:
                    vista.mostrarMensaje("Volviendo al Menú Principal...");
                    break;
                default:
                    vista.mostrarMensaje("Opción inválida. Por favor, intente de nuevo.");
            }
        } while (opcion != 3);
    }

    
    private ArrayList<Arbitro> arbitrosRegistrados = new ArrayList<>();

    private void crearArbitro() {
        try (Connection conn = conexion.getConnection()) {
        String nombreArbitro = vista.pedirString("Ingrese el nombre del árbitro: ");
        Arbitro nuevoArbitro = new Arbitro(0, nombreArbitro, 0);
        arbitrosRegistrados.add(nuevoArbitro);
        vista.mostrarMensaje("Árbitro '" + nombreArbitro + "' creado exitosamente.");
        
        // Insertar el árbitro en la base de datos
        String sqlInsertArbitro = "INSERT INTO arbitros (nombre, partidos_dirigidos) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlInsertArbitro)) {
            pstmt.setString(1, nombreArbitro);
            pstmt.setInt(2, nuevoArbitro.getPartidosDirigidos());
            pstmt.executeUpdate();
        }
    } catch (SQLException e) {
        vista.mostrarMensaje("Error al crear el árbitro: " + e.getMessage());
    }
}

    private ArrayList<Arbitro> getArbitrosRegistrados() {
        return arbitrosRegistrados;
    }

    private void gestionarPartidos() {
        int opcion;
        do {
            vista.mostrarMenuPartidos();
            opcion = vista.pedirInt("Ingrese su opción: ");

            switch (opcion) {
                case 1:
                    registrarNuevoPartido();
                    break;
                case 2:
                    vista.mostrarListaPartidos(campeonato.getPartidosJugados());
                    break;
                case 3:
                    vista.mostrarMensaje("Volviendo al Menú Principal...");
                    break;
                default:
                    vista.mostrarMensaje("Opción inválida. Por favor, intente de nuevo.");
            }
        } while (opcion != 3);
    }

    private void registrarNuevoPartido() {
    Connection conn = null;
    try {
        conn = conexion.getConnection();
        conn.setAutoCommit(false); // Iniciar transacción

        if (campeonato.getEquipos().size() < 2) {
            vista.mostrarMensaje("Se necesitan al menos dos equipos para registrar un partido.");
            return;
        }
        if (arbitrosRegistrados.isEmpty()) {
            vista.mostrarMensaje("No hay árbitros registrados para dirigir un partido.");
            return;
        }

        vista.mostrarListaEquipos(campeonato.getEquipos());
        int indiceLocal = vista.pedirInt("Seleccione el número del equipo LOCAL: ") - 1;
        int indiceVisitante = vista.pedirInt("Seleccione el número del equipo VISITANTE: ") - 1;

        if (indiceLocal < 0 || indiceLocal >= campeonato.getEquipos().size() ||
            indiceVisitante < 0 || indiceVisitante >= campeonato.getEquipos().size()) {
            vista.mostrarMensaje("Selección de equipo inválida.");
            return;
        }

        if (indiceLocal == indiceVisitante) {
            vista.mostrarMensaje("Un equipo no puede jugar contra sí mismo. Por favor, seleccione equipos diferentes.");
            return;
        }

        Equipo equipoLocal = campeonato.getEquipos().get(indiceLocal);
        Equipo equipoVisitante = campeonato.getEquipos().get(indiceVisitante);

        vista.mostrarListaArbitros(arbitrosRegistrados);
        int indiceArbitro = vista.pedirInt("Seleccione el número del árbitro para el partido: ") - 1;

        if (indiceArbitro < 0 || indiceArbitro >= arbitrosRegistrados.size()) {
            vista.mostrarMensaje("Selección de árbitro inválida.");
            return;
        }
        Arbitro arbitroSeleccionado = arbitrosRegistrados.get(indiceArbitro);

        int golesLocal = vista.pedirInt("Ingrese los goles del equipo LOCAL (" + equipoLocal.getNombre() + "): ");
        int golesVisitante = vista.pedirInt("Ingrese los goles del equipo VISITANTE (" + equipoVisitante.getNombre() + "): ");

        
        String sqlInsertPartido = "INSERT INTO partidos (arbitro_id, equipo_local_id, equipo_visitante_id, goles_local, goles_visitante) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sqlInsertPartido, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, obtenerIdArbitroBD(arbitroSeleccionado.getNombre(), conn));
            pstmt.setInt(2, obtenerIdEquipoBD(equipoLocal.getNombre(), conn));
            pstmt.setInt(3, obtenerIdEquipoBD(equipoVisitante.getNombre(), conn));
            pstmt.setInt(4, golesLocal);
            pstmt.setInt(5, golesVisitante);
            
            pstmt.executeUpdate();
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idPartido = generatedKeys.getInt(1);
                }
            }
        }

        Partido nuevoPartido = new Partido(arbitroSeleccionado, equipoLocal, equipoVisitante, golesLocal, golesVisitante);
        nuevoPartido.finalizarPartido();
        campeonato.addPartido(nuevoPartido);

        
        actualizarEstadisticasEquipoBD(equipoLocal, conn);
        actualizarEstadisticasEquipoBD(equipoVisitante, conn);
        actualizarEstadisticasArbitroBD(arbitroSeleccionado, conn);

        conn.commit(); // Confirmar transacción
        vista.mostrarMensaje("Partido registrado y estadísticas actualizadas exitosamente.");

    } catch (SQLException e) {
        try {
            if (conn != null) conn.rollback();
        } catch (SQLException ex) {
            vista.mostrarMensaje("Error al hacer rollback: " + ex.getMessage());
        }
        vista.mostrarMensaje("Error al registrar el partido: " + e.getMessage());
    } finally {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            vista.mostrarMensaje("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
private int obtenerIdArbitroBD(String nombreArbitro, Connection conn) throws SQLException {
    String sql = "SELECT id_arbitro FROM arbitros WHERE nombre = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, nombreArbitro);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("id_arbitro");
            }
        }
    }
    throw new SQLException("Árbitro no encontrado en BD");
}

private int obtenerIdEquipoBD(String nombreEquipo, Connection conn) throws SQLException {
    String sql = "SELECT id_equipo FROM equipos WHERE nombre = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, nombreEquipo);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("id_equipo");
            }
        }
    }
    throw new SQLException("Equipo no encontrado en BD");
}

private void actualizarEstadisticasEquipoBD(Equipo equipo, Connection conn) throws SQLException {
    String sql = "UPDATE equipos SET partidos_jugados = ?, goles_a_favor = ?, goles_en_contra = ?, puntaje = ? WHERE id_equipo = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, equipo.getPartidosJugados());
        pstmt.setInt(2, equipo.getGolesA_Favor());
        pstmt.setInt(3, equipo.getGolesEnContra());
        pstmt.setInt(4, equipo.getPuntaje());
        pstmt.setInt(5, obtenerIdEquipoBD(equipo.getNombre(), conn));
        pstmt.executeUpdate();
    }
}

private void actualizarEstadisticasArbitroBD(Arbitro arbitro, Connection conn) throws SQLException {
    String sql = "UPDATE arbitros SET partidos_dirigidos = ? WHERE id_arbitro = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, arbitro.getPartidosDirigidos());
        pstmt.setInt(2, obtenerIdArbitroBD(arbitro.getNombre(), conn));
        pstmt.executeUpdate();
    }
}
}