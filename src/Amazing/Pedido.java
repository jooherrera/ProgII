package Amazing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pedido {
    private int numPedido;
    private Cliente datosCliente;
    private Map<Integer, PaqueteAEntregar> carrito;
    private int facturacion;
    private boolean cerrado;

    public Pedido(int numPedido, String nombre, String direccion, int dni) {
        this.numPedido = numPedido;
        this.datosCliente = new Cliente(dni, nombre, direccion);
        this.carrito = new HashMap<Integer, PaqueteAEntregar>();
        this.facturacion = 0;
        this.cerrado = false;
    }

    public void agregarPaquete(int codigoPaquete, PaqueteAEntregar paquete) {
        if (!cerrado ) {
            carrito.put(codigoPaquete, paquete);
            facturacion += paquete.devolverCostoTotal();
        }
    }

    public boolean eliminarPaquete(int id) {
        if (!cerrado && carrito.containsKey(id)) {
            PaqueteAEntregar paquete = carrito.get(id);
            facturacion -= paquete.devolverCostoTotal();
            carrito.remove(id);
            return true;
        }
        return false;
    }

    public List<PaqueteAEntregar> obtenerPaquetesMaxVolumen(int valor) {
        List<PaqueteAEntregar> paquetesMaxVolumen = new ArrayList<>();
        for (PaqueteAEntregar paquete : carrito.values()) {
            if (paquete.cabeEn(valor) ) {
                paquetesMaxVolumen.add(paquete);
            }
        }
        return paquetesMaxVolumen;
    }

    public List<PaqueteAEntregar> obtenerpaquetesMinVolumen(int valor) {
        List<PaqueteAEntregar> paquetesMinVolumen = new ArrayList<>();
        for (PaqueteAEntregar paquete : carrito.values()) {
            if (paquete.cabeEn(valor) ) {
                paquetesMinVolumen.add(paquete);
            }
        }
        return paquetesMinVolumen;
    }

    public PaqueteAEntregar entregarPaquete(int id) {
        if (cerrado && carrito.containsKey(id)) {
            PaqueteAEntregar paquete = carrito.get(id);
            carrito.remove(id);
            return paquete;
        }
        return null;
    }

    public double devolverTotalAPagar() {
        return facturacion;
    }

    public String consultarPaquetesNoEntregados() {
        StringBuilder paquetesNoEntregados = new StringBuilder();
        for (PaqueteAEntregar paquete : carrito.values()) {
            paquetesNoEntregados.append(paquete.toString()).append("\n");
        }
        return paquetesNoEntregados.toString();
    }

    public void cerrarPedido() {
        cerrado = true;
    }

    @Override
    public String toString() {
        return "Número de Pedido: " + numPedido + "\n" +
                "Cliente: " + datosCliente.toString() + "\n" +
                "Facturación Total: " + facturacion + "\n" +
                "Estado: " + (cerrado ? "Cerrado" : "Abierto") + "\n";
    }
}