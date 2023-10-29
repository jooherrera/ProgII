	package Amazing;

public abstract class PaqueteAEntregar {
	private int id, volumen, precio;
	private String direccionEntrega;
	private boolean cargado;

	public PaqueteAEntregar(int id, int volumen, int precio, String dirEntrega) {
		this.id = id;
		this.volumen = volumen;
		this.precio = precio;
		this.direccionEntrega = dirEntrega;
	}

	public int devolverCostoTotal() {
		return this.costoBase();
	};

	private int costoBase() {
		return this.precio;
	}
	
	public abstract String toString();

	public boolean cabeEn(int volumen) {
		return this.volumen <= volumen;
	}

	public boolean tamanioMenorQue(int volumen) {
		return this.volumen <= volumen;
	}

	public boolean tamanioMayorQue(int volumen) {
		return this.volumen >= volumen;
	}

	public String devolverCodigoUnico() {
		return "" + this.id;
	}
	
	public String devolverDirEntrega() {
		return this.direccionEntrega;
	}
	

	public int cargarATransporte() {
		if (cargado)
			throw new RuntimeException("El paquete ya está cargado.");

		this.cargado = true;
		return this.volumen;
	}

}
