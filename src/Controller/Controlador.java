package Controller;

import Models.Campeonato;
import Models.Entrenador;
import Models.Equipo;
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
    private void añadirJugadorAEquipo(){
        
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
        List<Equipo> equipos = campeonato.getEquipos();

        if (equipos.size() < 2) {
            vista.mostrarMensaje("No hay suficientes equipos registrados para jugar un partido.");
        return;
    }

    
    vista.mostrarListaEquipos(equipos);
    // Selección del primer equipo
    int indiceLocal = vista.pedirInt("Seleccione el número del equipo LOCAL: ") - 1;

    if (indiceLocal < 0 || indiceLocal >= equipos.size()) {
        vista.mostrarMensaje("Selección inválida.");
        return;
    }

    // Selección del segundo equipo
    int indiceVisitante = vista.pedirInt("Seleccione el número del equipo VISITANTE: ") - 1;
    if (indiceVisitante < 0 || indiceVisitante >= equipos.size() || indiceVisitante == indiceLocal) {
        vista.mostrarMensaje("Selección inválida o equipo duplicado.");
        return;
    }

    Equipo local = equipos.get(indiceLocal);
    Equipo visitante = equipos.get(indiceVisitante);

    // Ingreso de goles
    int golesLocal = vista.pedirInt("Ingrese los goles del equipo " + local.getNombre() + ": ");
    int golesVisitante = vista.pedirInt("Ingrese los goles del equipo " + visitante.getNombre() + ": ");

    // Crear y registrar el partido
    Partido partido = new Partido(local, visitante, golesLocal, golesVisitante);
    campeonato.agregarPartido(partido);

    vista.mostrarMensaje("Partido registrado correctamente.");
    vista.mostrarMensaje("hola");
    }
}
