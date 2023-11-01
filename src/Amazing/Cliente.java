package Amazing;

class Cliente {
	private int dni;
	private String nombre;
	private String direccion;

	public Cliente(int dni, String nombre, String direccion) {
		this.dni = this.validarDni(dni);
		this.nombre = this.validarNombre(nombre);
		this.direccion = this.validarDireccion(direccion);
	}

	public String domicilio() {
		return this.devolverDireccion();
	}

	public String identificacion() {
		return this.devolverNombre();
	}

	private String devolverDireccion() {
		return direccion;
	}

	@Override
	public String toString() {
		return "Cliente { dni=" + dni + ", nombre=" + nombre + ", direccion=" + direccion + " }";
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
		return cadena.length() == 0;
	}

	private int validarDni(int dni) {
		if (this.esNulo(dni) || this.esMenorA1(dni))
			throw new RuntimeException("Dni inválido.");
		return dni;
	}

	private String validarNombre(String nombre) {
		if (this.esNulo(nombre) || this.estaVacio(nombre))
			throw new RuntimeException("Parametro: " + nombre + " inválido");
		return nombre;
	}

	private String validarDireccion(String direccion) {
		if (this.esNulo(nombre) || this.estaVacio(direccion))
			throw new RuntimeException("Parametro: 'direccion' inválido");
		return direccion;
	}
}