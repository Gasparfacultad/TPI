package View;

import Models.Arbitro;
import Models.Campeonato;
import Models.Entrenador;
import Models.Equipo;
import Models.Jugador;
import Models.Partido;
import java.util.ArrayList;
import java.util.Scanner;

public class vistaGeneral {

    private Scanner scanner;

    public vistaGeneral() {
        scanner = new Scanner(System.in);
    }

    public String pedirString(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public int pedirInt(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextInt();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarMenuPrincipal(Campeonato campeonato) {
        System.out.println("\n--- Menú Principal del Campeonato " + campeonato.getNombre() + " ---");
        System.out.println("1. Gestionar Equipos");
        System.out.println("2. Gestionar Árbitros");
        System.out.println("3. Gestionar Partidos");
        System.out.println("4. Salir");
    }

    public void mostrarMenuEquipos() {
        System.out.println("\n--- Gestión de Equipos ---");
        System.out.println("1. Crear Equipo");
        System.out.println("2. Añadir Jugador a Equipo");
        System.out.println("3. Listar Equipos");
        System.out.println("4. Ver Detalles de Equipo");
        System.out.println("5. Volver al Menú Principal");
    }

    public void mostrarMenuArbitros() {
        System.out.println("\n--- Gestión de Árbitros ---");
        System.out.println("1. Crear Árbitro");
        System.out.println("2. Listar Árbitros");
        System.out.println("3. Volver al Menú Principal");
    }

    public void mostrarMenuPartidos() {
        System.out.println("\n--- Gestión de Partidos ---");
        System.out.println("1. Registrar Nuevo Partido");
        System.out.println("2. Listar Partidos Jugados");
        System.out.println("3. Volver al Menú Principal");
    }

    public void mostrarListaEquipos(ArrayList<Equipo> equipos) {
        if (equipos.isEmpty()) {
            mostrarMensaje("No hay equipos registrados.");
            return;
        }
        mostrarMensaje("\n--- Lista de Equipos ---");
        for (int i = 0; i < equipos.size(); i++) {
            mostrarMensaje((i + 1) + ". " + equipos.get(i).getNombre());
        }
    }

    public void mostrarDetallesEquipo(Equipo equipos) {
        if (equipos == null) {
            mostrarMensaje("Equipo no encontrado.");
            return;
        }
        mostrarMensaje("\n--- Detalles del Equipo: " + equipos.getNombre() + " ---");
        mostrarMensaje("Entrenador: " + (equipos.getEntrenador() != null ? equipos.getEntrenador().getNombre() : "N/A"));
        mostrarMensaje("Partidos Jugados: " + equipos.getPartidosJugados());
        mostrarMensaje("Goles a Favor: " + equipos.getGolesA_Favor());
        mostrarMensaje("Goles en Contra: " + equipos.getGolesEnContra());
        mostrarMensaje("Puntaje: " + equipos.getPuntaje());
        mostrarMensaje("Jugadores:");
        if (equipos.getJugadores().isEmpty()) {
            mostrarMensaje("  No hay jugadores en este equipos.");
        } else {
            for (Jugador jugador : equipos.getJugadores()) {
                mostrarMensaje("  - " + jugador.getNombre() + " (Camiseta: " + jugador.getNroCamiseta() + ", Goles: " + jugador.getGoles() + ")");
            }
        }
    }

    public void mostrarListaArbitros(ArrayList<Arbitro> arbitros) {
        if (arbitros.isEmpty()) {
            mostrarMensaje("No hay árbitros registrados.");
            return;
        }
        mostrarMensaje("\n--- Lista de Árbitros ---");
        for (int i = 0; i < arbitros.size(); i++) {
            mostrarMensaje((i + 1) + ". " + arbitros.get(i).getNombre() + " (Partidos Dirigidos: " + arbitros.get(i).getPartidosDirigidos() + ")");
        }
    }

    public void mostrarListaPartidos(ArrayList<Partido> partidos) {
        if (partidos.isEmpty()) {
            mostrarMensaje("No hay partidos registrados.");
            return;
        }
        mostrarMensaje("\n--- Lista de Partidos Jugados ---");
        for (int i = 0; i < partidos.size(); i++) {
            Partido p = partidos.get(i);
            mostrarMensaje((i + 1) + ". " + p.getEquipoLocal().getNombre() + " " + p.getGolesLocal() + " - " + p.getGolesVisitante() + " " + p.getEquipoVisitante().getNombre() + " (Árbitro: " + p.getArbitro().getNombre() + ")");
        }
    }

    public void mostrarClasificacion(ArrayList<Equipo> equipos) {
        if (equipos.isEmpty()) {
            mostrarMensaje("No hay equipos para mostrar la clasificación.");
            return;
        }

        // Ordenar equipos por puntaje (descendente), luego por diferencia de goles, luego por goles a favor
        equipos.sort((e1, e2) -> {
            int comparacionPuntaje = Integer.compare(e2.getPuntaje(), e1.getPuntaje());
            if (comparacionPuntaje != 0) {
                return comparacionPuntaje;
            }
            int diferenciaGoles1 = e1.getGolesA_Favor() - e1.getGolesEnContra();
            int diferenciaGoles2 = e2.getGolesA_Favor() - e2.getGolesEnContra();
            int comparacionDiferenciaGoles = Integer.compare(diferenciaGoles2, diferenciaGoles1);
            if (comparacionDiferenciaGoles != 0) {
                return comparacionDiferenciaGoles;
            }
            return Integer.compare(e2.getGolesA_Favor(), e1.getGolesA_Favor());
        });

        mostrarMensaje("\n--- Clasificación del Campeonato ---");
        mostrarMensaje(String.format("%-5s %-20s %-10s %-10s %-10s %-10s %-10s", "Pos.", "Equipo", "PJ", "GF", "GC", "DG", "Pts."));
        mostrarMensaje("--------------------------------------------------------------------------------");
        for (int i = 0; i < equipos.size(); i++) {
            Equipo e = equipos.get(i);
            int diferenciaGoles = e.getGolesA_Favor() - e.getGolesEnContra();
            mostrarMensaje(String.format("%-5d %-20s %-10d %-10d %-10d %-10d %-10d",
                (i + 1), e.getNombre(), e.getPartidosJugados(), e.getGolesA_Favor(), e.getGolesEnContra(), diferenciaGoles, e.getPuntaje()));
        }
    }

    public void cerrarScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}