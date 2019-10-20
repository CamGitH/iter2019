package negocio;

public interface VOMedicoGeneral{
	/* **********************
	 * 			Métodos 
	 ***********************/
     /**
	 * @return  Id
	 */
	public long getId();
	
	/**
	 * @return Nombre
	 */
	public String getNombre();

	/**
	 * @return Apellido
	 */
	public String getApellido();
}