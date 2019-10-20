package negocio;

public interface VOMedico{
	/* **********************
	 * 			Métodos 
	 ***********************/
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