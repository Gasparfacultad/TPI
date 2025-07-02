package Models;

public class Partido {
    private Arbitro arbitro;
    private Equipo equipoLocal;
    private Equipo equipoVisitante; 
    private int golesLocal;
    private int golesVisitante;
    
    public Partido() {
    }

    public Partido(Arbitro arbitro, Equipo equipoLocal, Equipo equipoVisitante, int golesLocal, int golesVisitante) {
        this.arbitro = arbitro;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
    }

    public Arbitro getArbitro() {
        return this.arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public Equipo getEquipoLocal() {
        return this.equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return this.equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public int getGolesLocal() {
        return this.golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getGolesVisitante() {
        return this.golesVisitante;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    public void finalizarPartido() {
        
        if (this.arbitro != null) {
            this.arbitro.setPartidosDirigidos(this.arbitro.getPartidosDirigidos() + 1);
        }

        
        if (this.equipoLocal != null) {
            int puntosLocal = 0;
            if (golesLocal > golesVisitante) { 
                puntosLocal = 3;
            } else if (golesLocal == golesVisitante) { 
                puntosLocal = 1;
            }
            this.equipoLocal.actualizarEstadisticas(golesLocal, golesVisitante, puntosLocal);
        }

        
        if (this.equipoVisitante != null) {
            int puntosVisitante = 0;
            if (golesVisitante > golesLocal) { 
                puntosVisitante = 3;
            } else if (golesLocal == golesVisitante) { 
                puntosVisitante = 1;
            }
            this.equipoVisitante.actualizarEstadisticas(golesVisitante, golesLocal, puntosVisitante);
        }
    }

    @Override
    public String toString() {
        return "{" +
            " arbitro='" + (arbitro != null ? arbitro.getNombre() : "N/A") + "'" +
            ", equipoLocal='" + (equipoLocal != null ? equipoLocal.getNombre() : "N/A") + "'" +
            ", equipoVisitante='" + (equipoVisitante != null ? equipoVisitante.getNombre() : "N/A") + "'" +
            ", resultado='" + golesLocal + "-" + golesVisitante + "'" +
            "}";
    }
}