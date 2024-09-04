import javax.swing.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
        add(new Libro("56461","El Principito","Suzanne Collins","14/9/2008"));
    }};

    static Map<Integer,String> estados=Map.of(
            1,"pendiente",
            2,"entregado",
            3,"devuelto"
    );
    static Map<Libro,Integer> numeroCopiasEntregadas=new HashMap<>();
    static List<ReservarLibro> pendientesLibros= new ArrayList<>();
    public static void main(String[] args)  {
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
        System.out.println("\t\t\t\tSISTEMA DE GESTION DE RESERVAS DE LIBROS");
        System.out.println("Ingrese su identificación de usuario");
        String identificacionUsuario= JOptionPane.showInputDialog(null,"");
        if (identificacionUsuario!=null && identificacionUsuario.length() ==9){
            Usuario encontrado=usuarioExiste(usuarios,identificacionUsuario);
            if(encontrado!=null){
                System.out.println("\t\t\t\tLista de libros a reservar");
                for(int i =0;i<libros.size();i++){
                    System.out.println((i+1)+") "+libros.get(i).toString());
                }
                try {
                    opcionesReserva(encontrado);
                } catch (IOException e) {
                    throw new RuntimeException(e);
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
    public static void opcionesReserva(Usuario encontrado) throws IOException {
        boolean repetir=true;
        while (repetir){
            System.out.println("[R]eservar [E]ditar Reserva [C]ancelar reserva");
            char opcion=(char)System.in.read();
            System.in.read();
            switch (Character.toLowerCase(opcion)){
                case 'r':
                    List<ReservarLibro> lista=crearListaReserva(Reserva.MAXLIBROS);
                    if(lista==null){
                        continue;
                    }
                    Random random = new Random();
                    String nuevoIdentificador=String.valueOf(1000000+random.nextInt(9000000));
                    Reserva nuevaReserva=new Reserva(nuevoIdentificador,encontrado);
                    nuevaReserva.setLibros(lista);
                    reservas.add(nuevaReserva);
                    repetir=false;
                    break;
                case 'e':
                    editarReserva(encontrado.getIdentificacion());
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Opcion no registrada");
            }
        }
        siGLibro();
    }
    public static void editarReserva(String identificador){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("[A]gregar mas dias [D]evolver libro [S]olicitar libro disponible");
            String entrada=sc.next();
            List<ReservarLibro> reservasUsuario=new ArrayList<>();
            if(entrada.equalsIgnoreCase("a")){
                System.out.println("Libros disponibles a extender mas dias");
                for(Reserva reserva:reservas){
                    if(!reserva.getUsuario().getIdentificacion().equals(identificador)){
                        continue;
                    }
                    for (ReservarLibro libro: reserva.getLibros()){
                        if(libro.getEstado() == estados.get(2) && calcularDiasRestantes(libro.getFechaReserva(),libro.getFechaDevolucion())<=14 && estaEnEspera(libro.getLibro())){
                            reservasUsuario.add(libro);
                        }
                    }
                }
                if(reservasUsuario.size()==0){
                    JOptionPane.showMessageDialog(null,"No hay libros disponibles a extender mas dias ");
                    break;
                }
                for(int i=0;i<reservasUsuario.size();i++){
                    System.out.println((i+1)+") "+reservasUsuario.get(i).getLibro().getTitulo());
                }
                System.out.println("Escoja el libro a extender los dias");
                String console=sc.next();
                if(esNumero(console)){
                    int opcion =Integer.parseInt(console);
                    if(opcion<1 || opcion>reservasUsuario.size()){
                        JOptionPane.showMessageDialog(null,"Opcion incorrecta");
                        continue;
                    }
                    ReservarLibro reserva=reservasUsuario.get(opcion-1);
                    Date nuevaFechaDevolucion=calcularFechaDevolucion(reserva.getFechaDevolucion(),14);
                    System.out.println(reserva.getFechaDevolucion());
                    reserva.setFechaDevolucion(nuevaFechaDevolucion);
                    JOptionPane.showMessageDialog(null,"Se ha añadido mas dias para el libro: "+ reserva.getLibro().getTitulo()+ ", su nueva fecha de entrega sería "+reserva.getFechaDevolucion());
                    int respesta = JOptionPane.showConfirmDialog(null,"¿Desea seguir aqui? ","aviso",JOptionPane.YES_NO_OPTION);
                    if(respesta == JOptionPane.NO_OPTION) break;

                }

            }
            else if(entrada.equalsIgnoreCase("d")){
                for(Reserva reserva:reservas){
                    if(!reserva.getUsuario().getIdentificacion().equals(identificador)){
                        continue;
                    }
                    for(ReservarLibro libro : reserva.getLibros()){
                        if(libro.getEstado().equals(estados.get(2))){
                            reservasUsuario.add(libro);
                        }
                    }
                }
                if(reservasUsuario.size() == 0){
                    JOptionPane.showMessageDialog(null,"No hay libros que tenga que devolver");
                    break;
                }
                System.out.println("Libros que tiene que devolver");
                for (int i=0;i<reservasUsuario.size();i++){
                    ReservarLibro reserva=reservasUsuario.get(i);
                    System.out.println((i+1)+") "+reserva.getLibro().getTitulo()+ " - con "+calcularDiasRestantes(reserva.getFechaReserva(),reserva.getFechaDevolucion())+" dias restantes");
                }
                System.out.println("Escoja el libro a devolver");
                String console=sc.next();
                if(esNumero(console)) {
                    int opcion = Integer.parseInt(console);
                    if (opcion < 1 || opcion > reservasUsuario.size()) {
                        JOptionPane.showMessageDialog(null, "Opcion incorrecta");
                        continue;
                    }
                    ReservarLibro escogida=reservasUsuario.get(opcion-1);
                    escogida.setEstado(estados.get(3));
                    numeroCopiasEntregadas.put(escogida.getLibro(),numeroCopiasEntregadas.get(escogida.getLibro())-1);
                    System.out.println("Se ha devuelto correctamente "+ escogida.getLibro().getTitulo());
                    int respesta = JOptionPane.showConfirmDialog(null,"¿Desea seguir aqui? ","aviso",JOptionPane.YES_NO_OPTION);
                    if(respesta == JOptionPane.NO_OPTION) break;
                }
            }
        }
    }
    public static  boolean estaEnEspera(Libro libro){
        for (ReservarLibro reserva:pendientesLibros){
            if(reserva.getLibro() == libro) return false;
        }
        return true;
    }
    public static List<ReservarLibro> crearListaReserva(Integer maximoReservas){
        List<ReservarLibro> listaReserva= new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Integer opcion = -1;
        boolean repetir=true;
        int contador=0;
        while (repetir) {
            if(contador==maximoReservas) {
                JOptionPane.showMessageDialog(null,"No puede seguir agregando mas libros");
                return listaReserva;
            }

            System.out.print("Ingrese el número del libro: ");
            String console=sc.next();
            if (esNumero(console)) {
                opcion = Integer.parseInt(console);
                if(opcion>0 && opcion<=libros.size()){
                    Libro libro = libros.get(opcion-1);
                    Integer copiaEntregadas= numeroCopiasEntregadas.get(libro);
                    if(copiaEntregadas ==null || copiaEntregadas<libro.getNumeroCopias()){
                        Random random= new Random();
                        String nuevoIdentificadorReserva=String.valueOf(10000 + random.nextInt(90000));
                        Date fechaActual=new Date();
                        ReservarLibro nuevaReservaLibro= new ReservarLibro(nuevoIdentificadorReserva,fechaActual,calcularFechaDevolucion(fechaActual,14),libro,estados.get(2));
                        listaReserva.add(nuevaReservaLibro);
                        copiaEntregadas=copiaEntregadas==null?1:++copiaEntregadas;
                        numeroCopiasEntregadas.put(libro,copiaEntregadas);
                        contador++;
                        System.out.println("Se ha agregado exitosamente "+libro.getTitulo());
                    }
                    else {
                        int respuesta=JOptionPane.showConfirmDialog(null,"¿Desea agregar "+libro.getTitulo()+"  como pendiente?","Aviso",JOptionPane.YES_NO_OPTION);
                        if(respuesta == JOptionPane.YES_OPTION){
                            Boolean libroPendiente= false;
                            for (ReservarLibro libroReservado:listaReserva){
                                if(libroReservado.getLibro()==libro)
                                {
                                    libroPendiente=true;
                                    break;
                                }

                            }
                            if(!libroPendiente){
                                Random random= new Random();
                                String nuevoIdentificadorReserva=String.valueOf(10000 + random.nextInt(90000));
                                ReservarLibro nuevaReservaPendiente= new ReservarLibro(nuevoIdentificadorReserva,libro,estados.get(1));
                                listaReserva.add(nuevaReservaPendiente);
                                pendientesLibros.add(nuevaReservaPendiente);
                            }
                            else {
                                JOptionPane.showMessageDialog(null,"El libro ya se encuentra en su lista");
                            }

                        }
                    }
                    int respuesta=JOptionPane.showConfirmDialog(null,"¿Desea continuar agregando libros?","Aviso",JOptionPane.YES_NO_OPTION);
                    if (respuesta == JOptionPane.NO_OPTION){
                        repetir=false;
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"El numero que ingreso no es valido por favor ingrese el numero del libro mostrado en pantalla");
                }
            }
            else if(console.equalsIgnoreCase("e")){
                if(listaReserva.size()==0){
                    JOptionPane.showMessageDialog(null,"No hay elementos que editar");
                    continue;
                }

                List<ReservarLibro> pendientes=listaReserva.stream().filter(reserva -> estados.get(1).equalsIgnoreCase(reserva.getEstado())).collect(Collectors.toList());
                if(pendientes.size()==0){
                    JOptionPane.showMessageDialog(null,"No hay elementos pendientes");
                    continue;
                }
                while (true){
                    System.out.println("Escoja el libro a editar ");
                    for(int i=0;i<pendientes.size();i++){
                        System.out.println((i+1)+") "+pendientes.get(i).getLibro().getTitulo());
                    }
                    if(!sc.hasNextInt()){
                        sc.next();
                        continue;
                    }
                    int eleccion=sc.nextInt();
                    if (eleccion<1 || eleccion>pendientes.size()){
                        JOptionPane.showMessageDialog(null,"Escoja dentro del rango");
                        continue;
                    }
                    Libro libro=pendientes.get(eleccion).getLibro();
                    if(!(numeroCopiasEntregadas.get(libro)< libro.getNumeroCopias())){
                        JOptionPane.showInputDialog(null,"No hay copias disponibles");
                        continue;
                    }
                    ReservarLibro editarReserva=pendientes.get(eleccion);
                    editarReserva.setEstado(estados.get(2));
                    Date fechaAhora=new Date();
                    editarReserva.setFechaReserva(fechaAhora);
                    editarReserva.setFechaReserva(calcularFechaDevolucion(fechaAhora,14));
                    numeroCopiasEntregadas.put(libro,numeroCopiasEntregadas.get(libro)+1);
                    pendientes.remove(eleccion);
                    pendientesLibros.remove(editarReserva);
                    System.out.println("Se ha editado correctamente ");
                    int respuesta=JOptionPane.showConfirmDialog(null,"¿Desea seguir editando otra reserva?","aviso",JOptionPane.YES_NO_OPTION);
                    if(respuesta==JOptionPane.NO_OPTION){
                        break;
                    }
                }

            }
            else if(console.equalsIgnoreCase("d")){
                if(listaReserva.size() ==0 ){
                    JOptionPane.showMessageDialog(null,"No hay elementos que eliminar, reserve su libro primero ");
                    continue;
                }
                System.out.println("Escoja el libro a eliminar de la reserva: ");
                while (true){
                    for (int i=0;i<listaReserva.size();i++){
                        System.out.println((i+1)+") "+listaReserva.get(i).getLibro().getTitulo());
                    }
                    if(!sc.hasNextInt()){
                        sc.next();
                        JOptionPane.showMessageDialog(null,"Ingrese un numero correcto");
                        continue;
                    }
                    opcion=sc.nextInt();
                    if(opcion<1 || opcion>listaReserva.size()){
                        JOptionPane.showMessageDialog(null,"Ingrese el numero del libro correctamente ");
                        continue;
                    }
                    ReservarLibro libroExtraido= listaReserva.remove(opcion-1);
                    numeroCopiasEntregadas.put(libroExtraido.getLibro(),numeroCopiasEntregadas.get(libroExtraido.getLibro())-1);
                    System.out.println("Se ha eliminado correctamente: "+ libroExtraido.getLibro().getTitulo());
                    int respuesta=JOptionPane.showConfirmDialog(null,"¿Desea eliminar otro libro de su lista?","aviso",JOptionPane.YES_NO_OPTION);
                    if(respuesta == JOptionPane.NO_OPTION) break;
                }
            }
            else if(console.equalsIgnoreCase("c")){
                int respuesta =JOptionPane.showConfirmDialog(null,"¿Desea cancelar la reserva?","aviso",JOptionPane.YES_NO_OPTION);
                if(respuesta == JOptionPane.YES_OPTION) return null;
            }
            else {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
            }
        }
        return listaReserva;
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
    public static Usuario usuarioExiste(List<Usuario> usuarios,String identificadorUsuario){
        for(Usuario usuario:usuarios){
            if(usuario.getIdentificacion().equals(identificadorUsuario)) return usuario;
        }
        return null;
    }
    public static boolean esNumero(String dato){
        try {
            Integer.parseInt(dato);
            return true;
        }catch (NumberFormatException error){
            return false;
        }
    }
    public static int calcularDiasRestantes(Date fechaInicial,Date fechaFinal){
        long diasEnMilisegundos=fechaFinal.getTime()-fechaInicial.getTime();
        return (int)(diasEnMilisegundos/(1000 * 60 * 60 * 24));
    }
    public  static Date calcularFechaDevolucion(Date fechaReserva,int dias){
        Calendar fechaFutura=Calendar.getInstance();
        fechaFutura.setTime(fechaReserva);
        fechaFutura.add(Calendar.DAY_OF_YEAR,dias);
        return fechaFutura.getTime();
    }
}