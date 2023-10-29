package Amazing;

class Cliente {
    private int dni;
    private String nombre;
    private String direccion;

    public Cliente(int dni, String nombre, String direccion) {
        this.dni = validarDni(dni);
        this.nombre = validarNombre(nombre);
        this.direccion = validarDireccion(direccion);
    }

    public String domicilio() {
    	return this.devolverDireccion();
    }

    public String identificacion() {
    	return this.devolverNombre();
    }
    
    @Override
    public String toString() {
        return "Nombre: " + nombre + "\n" +
                "DNI: " + dni + "\n" +
                "Dirección: " + direccion + "\n";
    }
    
    
    private String devolverDireccion() {
        return direccion;
    }
    private String devolverNombre() {
    	return this.nombre;
    }
    
    private boolean esNulo(Object param) {
    	return param == null;
    }
    
    private boolean esMenorA1(int dni) {
    	return dni < 1;
    }
    
    private boolean estaVacio(String cadena) {
    	return cadena.length() == 0 ;
    }
    
    private int validarDni(int dni) {
    	if(esNulo(dni) || esMenorA1(dni))
    		throw new Error("Dni inválido.");
    	return dni;
    }
    
    private String validarNombre(String nombre) {
    	if(esNulo(nombre)|| estaVacio(nombre))
    		throw new Error("Parametro: " + nombre + " inválido");
    	return nombre;
    }
    
    private String validarDireccion(String direccion) {
    	if(esNulo(nombre)|| estaVacio(direccion))
    		throw new Error("Parametro: 'direccion' inválido");
    	return direccion;
    }
}