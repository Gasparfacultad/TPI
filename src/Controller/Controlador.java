package Controller;

import java.util.ArrayList;
import java.util.List;

import Models.Arbitro;
import Models.Campeonato;
import Models.Entrenador;
import Models.Equipo;
import Models.Jugador;
import Models.Partido;
import View.vistaGeneral;

public class Controlador {
    private Campeonato campeonato;
    private vistaGeneral vista;

    public void iniciar() {
        vista.mostrarMensaje("Bienvenido al sistema de gestión del " + campeonato.getNombre()) ;
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
                    vista.mostrarClasificacion(campeonato.getEquipos());
                    break;
                case 5:
                    vista.mostrarMensaje("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    vista.mostrarMensaje("Opción inválida. Por favor, intente de nuevo.");
            }
        } while (opcion != 5);
    }

    private void gestionarEquipos() {
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
    //logica para gestionar equipos
    private void crearEquipo() {
        String nombre = vista.pedirString("Ingrese el nombre del equipo: ");
        String nombreEntrenador = vista.pedirString("Ingrese el nombre del entrenador: ");
        
        Entrenador entrenador = new Entrenador(nombreEntrenador);
        Equipo nuevoEquipo = new Equipo(nombre, entrenador);
        campeonato.addEquipo(nuevoEquipo);
        vista.mostrarMensaje("Equipo '" + nombre + "' creado exitosamente.");
    }
    
    private void añadirJugadorAEquipo() {
        if (campeonato.getEquipos().isEmpty()) {
            vista.mostrarMensaje("No hay equipos registrados para añadir jugadores.");
            return;
        }
        int indiceEquipo = vista.pedirInt("Seleccione el número del equipo al que desea añadir un jugador: ") - 1;

        if (indiceEquipo >= 0 && indiceEquipo < campeonato.getEquipos().size()) {
            Equipo equipoSeleccionado = campeonato.getEquipos().get(indiceEquipo);
            
            String nombreJugador = vista.pedirString("Ingrese el nombre del jugador: ");
            String nroCamiseta = vista.pedirString("Ingrese el número de camiseta del jugador: ");
            
            Jugador nuevoJugador = new Jugador(nombreJugador, nroCamiseta, 0); 
            equipoSeleccionado.addJugador(nuevoJugador);
            vista.mostrarMensaje("Jugador '" + nombreJugador + "' añadido a '" + equipoSeleccionado.getNombre() + "' exitosamente.");
        } else {
            vista.mostrarMensaje("Número de equipo inválido.");
        }
    }

    private void verDetallesEquipo() {
    if (campeonato.getEquipos().isEmpty()) {
        vista.mostrarMensaje("No hay equipos registrados para ver detalles.");
        return;
    }

    vista.mostrarListaEquipos(campeonato.getEquipos());
    int indiceEquipo = vista.pedirInt("Seleccione el número del equipo para ver sus detalles: ") - 1;

    if (indiceEquipo < 0 || indiceEquipo >= campeonato.getEquipos().size()) {
        vista.mostrarMensaje("Número de equipo inválido.");
        return;
    }
    Equipo equipo = campeonato.getEquipos().get(indiceEquipo);

    vista.mostrarMensaje("\n--- Detalles del Equipo: " + equipo.getNombre() + " ---");
    vista.mostrarMensaje("Entrenador: " + (equipo.getEntrenador() != null ? equipo.getEntrenador().getNombre() : "N/A"));
    vista.mostrarMensaje("Partidos Jugados: " + equipo.getPartidosJugados());
    vista.mostrarMensaje("Goles a Favor: " + equipo.getGolesA_Favor());
    vista.mostrarMensaje("Goles en Contra: " + equipo.getGolesEnContra());
    vista.mostrarMensaje("Puntaje: " + equipo.getPuntaje());
    vista.mostrarMensaje("Jugadores:");
    if (equipo.getJugadores().isEmpty()) {
        vista.mostrarMensaje("  No hay jugadores en este equipo.");
    } else {
        for (Jugador jugador : equipo.getJugadores()) {
            vista.mostrarMensaje("  - " + jugador.getNombre() + 
                " (Camiseta: " + jugador.getNroCamiseta() + 
                ", Goles: " + jugador.getGoles() + ")");
        }
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
    //logica para gestionar arbitros
    
    private ArrayList<Arbitro> arbitrosRegistrados = new ArrayList<>();

    private void crearArbitro() {
        String nombreArbitro = vista.pedirString("Ingrese el nombre del árbitro: ");
        Arbitro nuevoArbitro = new Arbitro(nombreArbitro, 0); 
        arbitrosRegistrados.add(nuevoArbitro);
        vista.mostrarMensaje("Árbitro '" + nombreArbitro + "' creado exitosamente.");
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
    //logica para gestionar partidos

    private void registrarNuevoPartido() {
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

        Partido nuevoPartido = new Partido(arbitroSeleccionado, equipoLocal, equipoVisitante, golesLocal, golesVisitante);
        nuevoPartido.finalizarPartido(); // Esto actualiza las estadísticas de los equipos y el árbitro
        campeonato.addPartido(nuevoPartido);
        vista.mostrarMensaje("Partido registrado y estadísticas actualizadas exitosamente.");
    }
}