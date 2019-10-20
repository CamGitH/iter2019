package negocio;

public interface VOUsuario {
	/* **********************
	 * 			Métodos 
	 ***********************/
     /**
	 * @return El id 
	 */
	public long getId();
	
	/**
	 * @return el nombre 
	 */
	public String getNombre();
	
	/**
	 * @return el correo
	 */
	public String getCorreo();
	
	/**
	 * @return el apellido
	 */
	public String getApellido();
	
	/**
	 * @return el tipoID
	 */
	public String getTipoId();

	
	/**
	 * @return la eps
	 */
	public String getEps();
}