public class Usuario {
    private String nombres;
    private String apellidos;
    private  String identificacion;
    private String correo="desconocido";

    public Usuario(String nombres,String identificacion,String apellidos){
        this.nombres=nombres;
        this.apellidos=apellidos;
        this.identificacion=identificacion;
    }
    public Usuario(String nombres,String identificacion,String apellidos, String correo){
        this.correo=correo;
        this.identificacion=identificacion;
        this.apellidos=apellidos;
        this.nombres=nombres;
    }
    public String getNombres(){
        return this.nombres;
    }
    public String getApellidos(){
        return this.apellidos;
    }
    public String getIdentificacion(){
        return this.identificacion;
    }
    public String getCorreo(){
        return this.correo;
    }
    public void setNombres(String nombres){
        this.nombres=nombres;
    }
    public void setApellidos(String apellidos){
        this.apellidos=apellidos;
    }
    public void setCorreo(String correo){
        this.correo=correo;
    }
    @Override
    public String toString(){
        return "{\n" +
                "Identificacion: "+this.identificacion+",\n" +
                "Nombres: "+this.nombres+",\n" +
                "Apellidos: "+this.apellidos+",\n" +
                "Correo: "+this.correo+"\n" +
                "}";

    }

}
