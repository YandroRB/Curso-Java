import java.util.Date;

public class ReservarLibro {
    private String identifReservaLibro;
    private Date fechaReserva;
    private Date fechaDevolucion;
    private Libro libro;
    private String estado;



    public ReservarLibro(String identifReservaLibro, Libro libro, String estado) {
        this.identifReservaLibro = identifReservaLibro;
        this.libro = libro;
        this.estado = estado;
    }

    public ReservarLibro(String identifReservaLibro, Date fechaReserva, Date fechaDevolucion, Libro libro, String estado) {
        this.identifReservaLibro = identifReservaLibro;
        this.fechaReserva = fechaReserva;
        this.fechaDevolucion = fechaDevolucion;
        this.libro = libro;
        this.estado = estado;
    }

    public String getIdentifReservaLibro() {
        return identifReservaLibro;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "{\n" +
                "identifReservaLibro=" + identifReservaLibro + ",\n" +
                "fechaReserva=" + fechaReserva + ",\n"+
                "fechaDevolucion=" + fechaDevolucion + ",\n"+
                "libro=" + libro +",\n"+
                "estado=" + estado + "\n"+
                '}';
    }
}
