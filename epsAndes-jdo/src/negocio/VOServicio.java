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
	 * @return  Cantidad de Reservas
	 */
	public int getCantidadReservas();
}