package negocio;

import java.sql.Date;

public class OrdenDeServicio implements VOOrdenDeServicio{
	/* **********************
	 * 			Atributos
	 ***********************/

	private Long numero;
	private String tipo;
	private Date fecha;

	/* **********************
	 * 			Métodos
	 ***********************/
	/**
	 * Constructor por defecto
	 */
	public OrdenDeServicio () 
	{
		this.setNumeroOrden((long) 0);
		this.setTipo("");
		this.setFecha(Date.valueOf("12-07-2000"));
	}

	/**
	 * Constructor con valores
	 */
	public OrdenDeServicio (Long id, String t, Date f)
	{
		this.setNumeroOrden(id);
		this.setTipo(t);
		this.setFecha(f);
	}

	

	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString() 
	{
		return "Sirven [" + numero + ", " + tipo + "]";
	}

	public long getNumeroOrden() {
		return numero;
	}

	public void setNumeroOrden(Long numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}


	public void setFecha(Date fecha) {
		this.fecha=fecha;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public Date getFecha() {
		return fecha;
	}
	
}