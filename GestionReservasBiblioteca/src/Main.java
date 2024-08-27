import javax.swing.*;
import java.util.*;

public class Main {
    static List<Usuario> usuarios= new ArrayList<>(){{
        add(new Usuario("Santiago Byron","564225213","Rodriguez Bone"));
        add(new Usuario("Jose Luis","452307465","Oswaldo Hugo"));
        add(new Usuario("Pedro Luis","895124126","Gomez Martinez"));
        add(new Usuario("Ana Carme","653218541","Salazar Perez"));
        add(new Usuario("Laura Gabriela","214537412","Lopez Gomez"));
    }};
    static List<Reserva> reservas= new ArrayList<>();
    static List<Libro> libros = new ArrayList<>(){{
        add(new Libro("56231","Cien años de soledad","Gabriel García Márquez","5/6/1967"));
        add(new Libro("56531","1984","George Orwell","8/6/1949"));
        add(new Libro("56924","El gran Gatsby","F. Scott Fitzgerald","10/4/1925"));
        add(new Libro("56842","Orgullo y prejuicio","Jane Austen","28/1/1813"));
        add(new Libro("56231","Los juegos del hambre","Suzanne Collins","14/9/2008"));
    }};

    Map<Integer,String> estados=Map.of(
            1,"pendiente",
            2,"entregado",
            3,"devuelto"
    );
    public static void main(String[] args) {
        siGLibro();

/*
        ReservarLibro nuevaReserva= new ReservarLibro("562315456",libros.get(2),estados.get(1));
        Date fechaReserva= new Date();
        ReservarLibro nuevaReserva2= new ReservarLibro("569615456",fechaReserva,calcularFechaDevolucion(fechaReserva,14),libros.get(2),estados.get(2));

        ReservarLibro nuevaReserva3 = new ReservarLibro("569615356",fechaReserva,calcularFechaDevolucion(fechaReserva,14),libros.get(1),estados.get(2));

        Calendar nuevoCalendario= new GregorianCalendar(2024,7,28,13,15,40);
        Date nuevaFechaReserva=nuevoCalendario.getTime();

        ReservarLibro nuevaReserva4 = new ReservarLibro("568635456",nuevaFechaReserva,calcularFechaDevolucion(nuevaFechaReserva,14),libros.get(3),estados.get(2));

        Reserva nuevaReservaUsuario= new Reserva("321541",usuarios.get(3));
        nuevaReservaUsuario.agregarLibro(nuevaReserva);
        nuevaReservaUsuario.agregarLibro(nuevaReserva3);
        nuevaReservaUsuario.agregarLibro(nuevaReserva4);
        nuevaReservaUsuario.agregarLibro(nuevaReserva4);

        Reserva nuevaReservaUsuario2= new Reserva("321532",usuarios.get(2));
        nuevaReservaUsuario2.agregarLibro(nuevaReserva);
        nuevaReservaUsuario2.agregarLibro(nuevaReserva);
        nuevaReservaUsuario2.agregarLibro(nuevaReserva);
        nuevaReservaUsuario2.agregarLibro(nuevaReserva);

        reservas.add(nuevaReservaUsuario);
        reservas.add(nuevaReservaUsuario2);

        for(Reserva reserva:reservas){
            System.out.println(reserva);
            System.out.println("\n\n");
        }



        //System.out.println(nuevaReservaUsuario);


        for(Usuario usuario:usuarios){
            System.out.println(usuario);
        }
        for (Libro libro:libros){
            System.out.println(libro);
        }

 */
    }
    public static void siGLibro(){
        for(Usuario usuario:usuarios){
            System.out.println(usuario);
        }
        System.out.println("\t\t\t\tSISTEMA DE GESTION DE RESERVAS DE LIBROS");
        System.out.println("Ingrese su identificación de usuario");
        String identificacionUsuario= JOptionPane.showInputDialog(null,"");
        if (identificacionUsuario!=null && identificacionUsuario.length() ==9){
            boolean existe=usuarioExiste(usuarios,identificacionUsuario);
            if(existe){
                System.out.println("\t\t\t\tLista de libros a reservar");
                for(int i =0;i<libros.size();i++){
                    System.out.println((i+1)+") "+libros.get(i).toString());
                }
            }
            else{
                System.out.println("¿Desea registrarse?");
                int respuesta=JOptionPane.showConfirmDialog(null,"","Aviso",JOptionPane.YES_NO_OPTION);
                if(respuesta == JOptionPane.YES_OPTION) {
                    Usuario nuevoUsuario=crearUsuario();
                    usuarios.add(nuevoUsuario);
                    System.out.println("Se creado correctamente su usuario, su identificador es: ");
                    JOptionPane.showMessageDialog(null,nuevoUsuario.getIdentificacion());
                    siGLibro();
                }
                else if(respuesta == JOptionPane.NO_OPTION){
                    siGLibro();
                }
            }
        }
        else{
            System.out.println("Ingrese bien su identificación");
            siGLibro();
        }
    }
    public static Usuario crearUsuario(){
        Random random = new Random();
        String nuevoIdentificadorUsuario=String.valueOf(100000000 + random.nextInt(900000000));
        System.out.println("Ingrese sus datos: ");
        String nombresUsuario=null;
        String apellidosUsuario=null;
        String correoUsuario=null;
        while (nombresUsuario==null || apellidosUsuario == null || correoUsuario==null || nombresUsuario.isBlank() || apellidosUsuario.isBlank() || correoUsuario.isBlank()){
            nombresUsuario=JOptionPane.showInputDialog(null,"Nombres, Ejemplo: Lucia Maria");
            apellidosUsuario=JOptionPane.showInputDialog(null,"Apellidos, Ejemplo: Rodriguez Baltazar");
            correoUsuario=JOptionPane.showInputDialog(null,"Correo, Ejemplo: luciarodriguezb@gmail.com");
            if(nombresUsuario==null || apellidosUsuario == null || correoUsuario==null || nombresUsuario.isBlank() || apellidosUsuario.isBlank() || correoUsuario.isBlank()){
                JOptionPane.showMessageDialog(null,"Algunos datos no han sido ingresados correctamente");
                int respuestaDialogo=JOptionPane.showConfirmDialog(null,"¿Desea cancelar el registro?","Aviso",JOptionPane.YES_NO_OPTION);
                if(respuestaDialogo == JOptionPane.YES_OPTION) siGLibro();
            }

        }
        return new Usuario(nombresUsuario,nuevoIdentificadorUsuario,apellidosUsuario,correoUsuario);

    }
    public static boolean usuarioExiste(List<Usuario> usuarios,String identificadorUsuario){
        for(Usuario usuario:usuarios){
            if(usuario.getIdentificacion().equals(identificadorUsuario)) return true;
        }
        return false;
    }
    public  static Date calcularFechaDevolucion(Date fechaReserva,int dias){
        long diasEnMilisegundos=dias*24*60*60*1000;
        return new Date(fechaReserva.getTime()+diasEnMilisegundos);
    }
}