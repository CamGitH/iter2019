package negocio;

import java.sql.Date;

public interface VOOrdenDeServicio{
	/* **********************
	 * 			M�todos 
	 ***********************/
     /**
	 * @return  NumeroOrden
	 */
	public long getNumeroOrden();
	
	/**
	 * @return Tipo
	 */
	public String getTipo();

	/**
	 * @return fecha
	 */
	public Date getFecha();
}
