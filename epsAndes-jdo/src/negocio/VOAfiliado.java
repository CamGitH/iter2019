package negocio;

import java.sql.Date;

public interface VOAfiliado {
	/* **********************
	 * 			Métodos 
	 ***********************/
     /**
	 * @return El id
	 */
	public long getId();
	
	/**
	 * @return el fecha cde nacimiento
	 */
	public Date getFechaNacimiento();

	/**
	 * @return el orden de servicio
	 */
	public String getOrdenServicio();
}