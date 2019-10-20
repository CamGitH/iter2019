package negocio;

public class OrdenDeServicio implements VOOrdenDeServicio{
	/* **********************
	 * 			Atributos
	 ***********************/

	private Long numero;
	private String tipo;

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
	}

	/**
	 * Constructor con valores
	 */
	public OrdenDeServicio (Long id, String t) 
	{
		this.setNumeroOrden(id);
		this.setTipo(t);
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

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	

	
}