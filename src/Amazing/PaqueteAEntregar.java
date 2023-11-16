package Amazing;

public abstract class PaqueteAEntregar {
	private int id, volumen, precio;
	private String direccionEntrega;
	private boolean cargado;

	public PaqueteAEntregar(int id, int volumen, int precio, String dirEntrega) {
		this.id = validarId(id);
		this.volumen = validarVolumen(volumen);
		this.precio = validarPrecio(precio);
		this.direccionEntrega = validarDireccionEntrega(dirEntrega);
	}

	public double devolverCostoTotal() {
		return this.precio;
	};

	public boolean cabeEn(int volumen) {
		return this.volumen <= volumen;
	}

	public boolean tamanioMenorIgualQue(int volumen) {
		return this.volumen < volumen;
	}

	public boolean tamanioMayorQue(int volumen) {
		return this.volumen >= volumen;
	}

	public String devolverCodigoUnico() {
		return "" + this.id;
	}

	public int codigoUnicoNum() {
		return this.id;
	}

	public String devolverDirEntrega() {
		return this.direccionEntrega;
	}

	public int cargarATransporte() {
		if (estaCargado())
			return 0;

		this.cargado = true;
		return this.volumen;
	}

	public boolean estaCargado() {
		return cargado;
	}

	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(", volumen:").append(volumen).append(", precio:").append(precio);
		return sBuilder.toString();
	}

	// ------------PRIVATE---------------

	// Método para validar el ID del paquete
	private int validarId(int id) {
		if (id <= 0)
			throw new RuntimeException("El ID del paquete debe ser mayor que cero.");
		return id;
	}

	// Método para validar el volumen del paquete
	private int validarVolumen(int volumen) {
		if (volumen <= 0)
			throw new RuntimeException("El volumen del paquete debe ser mayor que cero.");
		return volumen;
	}

	// Método para validar el precio del paquete
	private int validarPrecio(int precio) {
		if (precio <= 0)
			throw new RuntimeException("El precio del paquete debe ser mayor que cero.");
		return precio;
	}

	// Método para validar la dirección de entrega del paquete
	private String validarDireccionEntrega(String dirEntrega) {
		if (dirEntrega == null || dirEntrega.isEmpty())
			throw new RuntimeException("La dirección de entrega no puede ser nula ni estar vacía.");
		return dirEntrega;
	}

	// -----------NUEVOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;

		PaqueteAEntregar paq = (PaqueteAEntregar) obj;

		return paq.esMismoVolumen(this.volumen) && paq.esMismoPrecio(this.precio);
	}

	private boolean esMismoVolumen(int volumen) {
		return this.volumen == volumen;
	}

	private boolean esMismoPrecio(int precio) {
		return this.precio == precio;
	}

}
