class Cliente {
    private int dni;
    private String nombre;
    private String direccion;

    public Cliente(int dni, String nombre, String direccion) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String devolverDireccion() {
        return direccion;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\n" +
                "DNI: " + dni + "\n" +
                "Dirección: " + direccion + "\n";
    }
}