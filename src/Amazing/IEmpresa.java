package Amazing;

import java.util.Map;

public interface IEmpresa {

	/**
	 * Registra un nuevo transporte tipo Automovil en el sistema con los siguientes 
	 * datos que corresponden a toda clase de transporte:
	 *  - patente, 
	 *  - volumen maximo de carga
	 *  - valor del viaje (que cobrara la empresa)
	 *  
	 * Ademas por ser Automovil se proporciona el dato:
	 *  - cantidad maxima de paquetes que transporta
	 *  
	 * Si esa patente ya esta en el sistema se debe generar una  excepcion.
	 */
	public void registrarAutomovil(String patente, int volMax, int valorViaje, int maxPaq);
	
	/**
	 * Registra un nuevo transporte tipo Utilitario en el sistema con los  
	 * datos correspondientes a toda clase de transporte y ademas:
	 * 
	 *  - un valor extra que cobra a la empresa si superan los 10 paquetes.
	 * 
	 * Si esa patente ya esta en el sistema se debe generar una  excepcion.
	 */
	public void registrarUtilitario(String patente, int volMax, int valorViaje, int valorExtra);
	
	/**
	 * Registra un nuevo transporte tipo Camion en el sistema con los  
	 * datos correspondiente a todo transporte y ademas:
	 * 
	 *  - un valor adicional que se multiplica por la cantidad de paquetes que le carguen.
	 * 
	 * Si esa patente ya esta en el sistema se debe generar una  excepcion.
	 */
	public void registrarCamion(String patente, int volMax, int valorViaje, int adicXPaq);
	
	/**
	 * Se registra un nuevo pedido en el sistema proporcionando los siguientes datos:
	 * - el nombre del cliente que lo solicita
	 * - su direccion
	 * - su dni
	 * 
	 * El sistema le asigna un numero de pedido unico y crea un carrito de ventas vacio.
	 * Devuelve el numero de pedido asignado.
	 * 
	 */
	public int registrarPedido(String cliente, String direccion, int dni);
	
	/**
	 * Se registra la solicitud de un nuevo paquete, el cual se agregara al carrito 
	 * del pedido indicado como un paquete de tipo ordinario. 
	 * Se ingresan los datos necesario para agregarlo:
	 *  - pedido al que corresponde agregarlo
	 *  - volumen del paquete a agregar
	 *  - precio del producto que contiene el paquete.
	 *  
	 *  Ademas por ser un paquete de tipo ordinario:
	 *  - costo del envio
	 * 
	 * Si el pedido ya esta terminado devuelve False. sino True.
	 * Si ese pedido no esta registrado en el sistema se debe generar una excepcion.
	 * 
	 * Devuelve el codigo de paquete (unico).
	 * 
	 */
	public int agregarPaquete(int codPedido, int volumen, int precio, int costoEnvio);
	
	/**
	 * Se registra la compra de un producto que se agregara al carrito del pedido dado 
	 * como paquete de tipo especial. 
	 * 
	 * Se ingresan los datos necesario para agregarlo:
	 *  - pedido al que corresponde agregarlo
	 *  - volumen del paquete a agregar
	 *  - precio del producto que contiene el paquete.
	 *  
	 *  Ademas por ser un paquete de tipo especial:
	 *  - porcentaje adicional (que se calcula y suma a su precio)
	 *  - adicional (se suma si el paquete tiene volumen>3000)
	 * 
	 * Si el pedido ya esta terminado devuelve False, sino devuelve True.
	 * Si ese pedido no esta registrado en el sistema se debe generar una  excepcion.
	 * 
	 * devuelve el codigo de paquete (unico).
	 * 
	 */
	public int agregarPaquete(int codPedido, int volumen, int precio, int porcentaje, int adicional);


	/**
	 * quita un paquete del pedido dado su codigo unico de paquete.
	 * 
	 * Demostrar la complejidad en terminos de O grande.
	 */
	public boolean quitarPaquete(int codPaquete);



	/**
	 * Se registra el cierre de un pedido registrado en la empresa, 
	 * dado su codigo.
	 * 
	 * Si ese codigo no esta en el sistema se debe generar una  excepcion.
	 *
	 */
	public void cerrarPedido(int codPedido);
	
	/**
	 * Se registra la carga de un transporte registrado en la empresa, dada su patente.
	 * 
	 * Devuelve un String con forma de listado donde cada renglon representa un 
	 * paquete cargado.
	 * Cada renglon debe respetar el siguiente formato: 
	 *      " + [ NroPedido - codPaquete ] direccion"
	 * por ejemplo:
	 *      " + [ 1002 - 101 ] Gutierrez 1147"
	 *      
	 * Si esa patente no esta en el sistema se debe generar una  excepcion. 
	 * Si el pedido no esta terminado devuelve una excepcion. 
	 * Si esta terminado y no hay productos comprados devuelve [].
	 *
	 */
	public String cargarTransporte(String patente);
	
	/**
	 * Se registra el costo del viaje de un transporte dado su patente
	 * Este costo es el que cobra el transporte (a la empresa) por entregar 
	 * la carga una vez que fue cargado con los paquetes.
	 * 
	 * Una vez cargado, aunque no se haya podido completar, si no hay más paquetes
	 * para agregarle, el transporte reparte los paquetes cargados.
	 *  
	 * Se devuelve el valor del viaje segun lo indicado en cada clase de transporte.
	 * Cada clase de transporte tiene su forma de calcular el costo del viaje.
	 *  
	 * Si esa patente no esta en el sistema se debe generar una excepcion.
	 * Si el transporte no esta cargado genera un excepcion.
	 * 
	 * Se debe resolver en O(1)
	 */
	public double costoEntrega(String patente);
	
	/**
	 * Devuelve la suma del precio facturado de todos los pedidos cerrados.
	 * 
	 * Se debe realizar esta operacion en O(1).
	 */
	public double facturacionTotalPedidosCerrados();
	
	/**
	 * Devuelve los pedidos cerrados y que no fueron entregados en su totalidad. 
	 * O sea, que tienen paquetes sin entregar.
	 * 
	 * Devuelve un diccionario cuya clave es el codigo del pedido y el valor es el nombre del 
	 * cliente que lo pidio.
	 * 
	 */
	public Map<Integer,String> pedidosNoEntregados();
	
	/**
	 * Se consideran transportes identicos a 2 transportes cargados con:
	 *   - distinta patente, 
	 *   - misma clase y 
	 *   - la misma carga.
	 * Se considera misma carga al tener la misma cantidad de paquetes con las 
	 * mismas caracteristicas:
	 *   - mismo volumen, 
	 *   - misma clase y 
	 *   - mismo precio.
	 *   VER EJEMPLO EN ENUNCIADO
	 */
	public boolean hayTransportesIdenticos();

}