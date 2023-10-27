package Amazing;

public abstract class PaqueteAEntregar {
private int id, volumen,precio;
private String direccionEntrega;
private boolean cargado;

	public PaqueteAEntregar(int id,int volumen, int precio, String dirEntrega) {
		this.id = id;
		this.volumen = volumen;
		this.precio = precio;
		this.direccionEntrega = dirEntrega;
	}
	
	public abstract int devolverCostoTotal();
	public abstract String toString();
	
	public boolean cabeEn(int volumen) {
		return this.volumen <= volumen;
	};
	
	public void cargarATransporte() {
		this.cargado = true;
	}
	
}
