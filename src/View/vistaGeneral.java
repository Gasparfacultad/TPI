package View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Models.Campeonato;
import Models.Equipo;
import Models.Jugador;

public class vistaGeneral {
    private Scanner scanner;

    public vistaGeneral() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public String pedirString(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public int pedirInt(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextInt();
    }

    public void mostrarMenuPrincipal(Campeonato campeonato) {
        System.out.println("\n--- Menú Principal del Campeonato " + campeonato.getNombre() + " ---");
        System.out.println("1. Gestionar Equipos");
        System.out.println("2. Gestionar Arbitros");
        System.out.println("3. Gestionar Partidos");
        System.out.println("4. Ver Clasificacion del Campeonato");
        System.out.println("5. Salir");
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
        System.out.println("\n--- Lista de Equipos ---");
        if (equipos.isEmpty()) {
            System.out.println("No hay equipos registrados.");
            return;
        }
        for (int i = 0; i < equipos.size(); i++) {
            System.out.println((i + 1) + ". " + equipos.get(i).getNombre());
        }
    }

}