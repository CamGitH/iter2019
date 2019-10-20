package negocio;

public interface VOConsulta{
	/* **********************
	 * 			Métodos 
	 ***********************/
     /**
	 * @return  CodigoServicio
	 */
	public long getCodigoServicio();
	
	/**
	 * @return Tipo
	 */
	public String getTipo();

	/**
	 * @return Triage
	 */
	public String getTriage();

	/**
	 * @return Receta
	 */
	public String getReceta();
}