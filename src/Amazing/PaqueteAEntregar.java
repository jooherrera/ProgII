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

	public double devolverCostoTotal() {
		return this.costoBase();
	};

	private double costoBase() {
		return this.precio;
	}
	
	@Override
	public String toString() {
		return "id=" + id + ", volumen=" + volumen + ", precio=" + precio + ", direccionEntrega="
				+ direccionEntrega + ", cargado=" + cargado + "]";
	}

	public boolean cabeEn(int volumen) {
		return this.volumen <= volumen;
	}

	public boolean tamanioMenorQue(int volumen) {
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
		if (cargado)
			throw new RuntimeException("El paquete ya está cargado.");

		this.cargado = true;
		return this.volumen;
	}
	
	
	

}
