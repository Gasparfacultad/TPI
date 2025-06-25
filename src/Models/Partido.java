package Models;

public class Partido {
    private Arbitro arbitro;
    private Equipo equipo;
    
    public Partido() {
    }

    public Partido(Arbitro arbitro, Equipo equipo) {
        this.arbitro = arbitro;
        this.equipo = equipo;
    }

    public Arbitro getArbitro() {
        return this.arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public Equipo getEquipo() {
        return this.equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "{" +
            " arbitro='" + getArbitro() + "'" +
            ", equipo='" + getEquipo() + "'" +
            "}";
    }

}
