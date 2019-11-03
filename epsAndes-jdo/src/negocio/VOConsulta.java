package negocio;

public interface VOConsulta{
	/* **********************
	 * 			M�todos 
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