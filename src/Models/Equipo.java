package Models;
import java.util.ArrayList;

public class Equipo {
    private String nombre;
    private Entrenador entrenador;
    private ArrayList<Jugador> jugadores;
    private int partidosJugados;
    private int golesA_Favor;
    private int golesEnContra;
    private int puntaje;

    public Equipo() {
        this.jugadores = new ArrayList<>();
    }

    public Equipo(String nombre, Entrenador entrenador, ArrayList<Jugador> jugadores, int partidosJugados, int golesA_Favor, int golesEnContra, int puntaje) {
        this.nombre = nombre;
        this.entrenador = entrenador;
        this.jugadores = jugadores;
        this.partidosJugados = partidosJugados;
        this.golesA_Favor = golesA_Favor;
        this.golesEnContra = golesEnContra;
        this.puntaje = puntaje;
    }

    public Equipo(String nombre, Entrenador entrenador) {
        this.nombre = nombre;
        this.entrenador = entrenador;
        this.jugadores = new ArrayList<>();
        this.partidosJugados = 0;
        this.golesA_Favor = 0;
        this.golesEnContra = 0;
        this.puntaje = 0;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Entrenador getEntrenador() {
        return this.entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public ArrayList<Jugador> getJugadores() {
        return this.jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public int getPartidosJugados() {
        return this.partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public int getGolesA_Favor() {
        return this.golesA_Favor;
    }

    public void setGolesA_Favor(int golesA_Favor) {
        this.golesA_Favor = golesA_Favor;
    }

    public int getGolesEnContra() {
        return this.golesEnContra;
    }

    public void setGolesEnContra(int golesEnContra) {
        this.golesEnContra = golesEnContra;
    }

    public int getPuntaje() {
        return this.puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public void addJugador(Jugador jugador) {
        if (jugador != null && !this.jugadores.contains(jugador)) {
            this.jugadores.add(jugador);
        }
    }

    public void actualizarEstadisticas(int golesAFavor, int golesEnContra, int puntosObtenidos) {
        this.partidosJugados++;
        this.golesA_Favor += golesAFavor;
        this.golesEnContra += golesEnContra;
        this.puntaje += puntosObtenidos;
    }

    @Override
    public String toString() {
        return "{" +
            " nombre='" + getNombre() + "'" +
            ", entrenador='" + (entrenador != null ? entrenador.getNombre() : "N/A") + "'" +
            ", jugadores=" + getJugadores().size() + " jugadores" +
            ", partidosJugados='" + getPartidosJugados() + "'" +
            ", golesA_Favor='" + getGolesA_Favor() + "'" +
            ", golesEnContra='" + getGolesEnContra() + "'" +
            ", puntaje='" + getPuntaje() + "'" +
            "}";
    }
}