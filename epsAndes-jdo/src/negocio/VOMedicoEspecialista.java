package negocio;

public interface VOMedicoEspecialista{
	/* **********************
	 * 			M�todos 
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

	/**
	 * @return Especialidad
	 */
	public String getEspecialidad();
}