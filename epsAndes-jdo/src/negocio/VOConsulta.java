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
	public String getTipoConsulta();

	/**
	 * @return Triage
	 */
	public String getTriage();

	/**
	 * @return Receta
	 */
	public String getReceta();
}