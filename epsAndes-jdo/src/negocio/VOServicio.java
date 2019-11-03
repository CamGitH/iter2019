package negocio;

public interface VOServicio{
	/* **********************
	 * 			M�todos 
	 ***********************/
     /**
	 * @return  CodigoServicio
	 */
	public long getCodigoServicio();
	
	/**
	 * @return Horario
	 */
	public String getHorario();

	/**
	 * @return Capacidad
	 */
	public int getCapacidad();
	
	/**
	 * @return Ips
	 */
	public String getIps();
	
	/**
	 * @return Orden
	 */
	public String getOrden();
	
	/**
	 * @return  Reservas
	 */
	public int getReservas();
}