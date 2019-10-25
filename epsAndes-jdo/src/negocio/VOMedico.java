package negocio;

public interface VOMedico{
	/* **********************
	 * 			Métodos 
	 ***********************/
	
	/**
	 * @return nombre
	 */
	public String getNombre();
	
	/**
	 * @return Apellido
	 */
	public String getApellido();
	
     /**
	 * @return  Id
	 */
	public long getId();
	
	/**
	 * @return RegistroMedico
	 */
	public String getRegistroMedico();

	/**
	 * @return Ips
	 */
	public String getIps();
}