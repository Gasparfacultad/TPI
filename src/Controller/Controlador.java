package Controller;

import java.util.List;

import Models.Arbitro;
import Models.Campeonato;
import Models.Equipo;
import Models.Jugador;
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
                    vista.mostrarClasificacion(campeonato.getEquiposParticipantes());
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
                    vista.mostrarListaEquipos(campeonato.getEquiposParticipantes());
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

    private List<Arbitro> getArbitrosRegistrados() {
        return campeonato.getArbitros(); 
    }

    private void crearArbitro() {
        String nombreArbitro = vista.pedirString("Ingrese el nombre del árbitro: ");
        Arbitro nuevoArbitro = new Arbitro(nombreArbitro);
        campeonato.addArbitro(nuevoArbitro);
        vista.mostrarMensaje("Árbitro '" + nombreArbitro + "' creado exitosamente.");
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


}   