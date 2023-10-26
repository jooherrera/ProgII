package Amazing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Transporte extends Amazing implements IEmpresa {
    protected String patente;
    protected int volMax;
    protected int volActual;
    protected int valorViaje;
    protected int canPaqActual;
    protected Map<Integer, PaqueteAEntregar> cargamento;

    public Transporte(String patente, int volMax, int valorViaje) {
        this.patente = patente;
        this.volMax = volMax;
        this.volActual = 0;
        this.valorViaje = valorViaje;
        this.canPaqActual = 0;
        this.cargamento = new HashMap<>();
    }

    @Override
    public String cargarTransporte(String patente) {
        if (!transportes.containsKey(patente)) {
            throw new IllegalArgumentException("Patente no encontrada.");
        }
        Transporte transporte = transportes.get(patente);

        StringBuilder cargaTransporte = new StringBuilder();

        for (Map.Entry<Integer, PaqueteAEntregar> entry : transporte.cargamento.entrySet()) {
            int numPedido = entry.getKey();
            int codPaquete = entry.getValue().getId();
            String direccion = entry.getValue().getDireccionEntrega();

            cargaTransporte.append(" + [").append(numPedido).append(" - ").append(codPaquete).append("] ").append(direccion).append("\n");
        }

        return cargaTransporte.toString();
    }

    @Override
    public double costoEntrega(String patente) {
        if (!transportes.containsKey(patente)) {
            throw new IllegalArgumentException("Patente no encontrada.");
        }
        Transporte transporte = transportes.get(patente);
        if (transporte.cargamento.isEmpty()) {
            throw new IllegalArgumentException("El transporte no está cargado.");
        }
        return consultarCostoEntrega();
    }

    @Override
    public boolean hayTransportesIdenticos() {
        for (Transporte transporte1 : transportes.values()) {
            for (Transporte transporte2 : transportes.values()) {
                if (transporte1 != transporte2 && transporte1.esIgual(transporte2)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Patente: " + patente + "\n" +
                "Volumen Máximo: " + volMax + "\n" +
                "Volumen Actual: " + volActual + "\n" +
                "Valor del Viaje: " + valorViaje + "\n" +
                "Cantidad de Paquetes Actual: " + canPaqActual + "\n";
    }

    protected abstract double consultarCostoEntrega();
    protected abstract boolean esIgual(Transporte otro);

    public boolean cargarPaquete(PaqueteAEntregar paquete) {
        int paqueteVolumen = paquete.getVolumen();
        if (volActual + paqueteVolumen <= volMax && canPaqActual < getMaxPaquetes()) {
            cargamento.put(paquete.getId(), paquete);
            volActual += paqueteVolumen;
            canPaqActual++;
            paquete.cargarTransporte();
            return true;
        }
        return false;
    }

    public boolean descargarPaquete(int idPaquete) {
        if (cargamento.containsKey(idPaquete)) {
            PaqueteAEntregar paquete = cargamento.get(idPaquete);
            volActual -= paquete.getVolumen();
            canPaqActual--;
            cargamento.remove(idPaquete);
            return true;
        }
        return false;
    }

    public boolean esIgual(Transporte otro) {
        return this.getClass() == otro.getClass() &&
                this.volMax == otro.volMax &&
                this.valorViaje == otro.valorViaje &&
                this.canPaqActual == otro.canPaqActual &&
                this.cargamento.equals(otro.cargamento);
    }

    protected abstract int getMaxPaquetes();
}