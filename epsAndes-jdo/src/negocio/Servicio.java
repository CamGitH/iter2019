package negocio;

public class Servicio implements VOServicio{
	/* **********************
	 * 			Atributos
	 ***********************/

	private Long codigoServicio;
	private String horario;
	private int capacidad;
	private String IPS;
	private String orden;
	private String afiliado;

	/* **********************
	 * 			Métodos
	 ***********************/
	/**
	 * Constructor por defecto
	 */
	public Servicio () 
	{
		this.setCodigoServicio((long) 0);
		this.setHorario("");
		this.setCapacidad(0);
		this.setIPS("");
		this.setOrden("");
		this.setAfiliado("");
	}

	/**
	 * Constructor con valores
	 */
	public Servicio (Long cod, String h, int cap, String ip, String o, String a) 
	{
		this.setCodigoServicio(cod);
		this.setHorario(h);
		this.setCapacidad(cap);
		this.setIPS(ip);
		this.setOrden(o);
		this.setAfiliado(a);
	}

	

	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString() 
	{
		return "Servicio ["+ codigoServicio + ", "+ horario + ", "+ capacidad + ", "+ IPS + ", " + orden + ", " + afiliado + "]";
	}

	public void setCapacidad(int pcapacidad) {
		this.capacidad = pcapacidad;
	}
	
	public void setCodigoServicio(Long pCodigo) {
		this.codigoServicio = pCodigo;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}


	public void setIPS(String iPS) {
		IPS = iPS;
	}

	public String getAfiliado() {
		return afiliado;
	}

	public void setAfiliado(String afiliado) {
		this.afiliado = afiliado;
	}

	@Override
	public long getCodigoServicio() {
		// TODO Auto-generated method stub
		return codigoServicio;
	}

	@Override
	public int getCapacidad() {
		// TODO Auto-generated method stub
		return capacidad;
	}

	@Override
	public String getIps() {
		// TODO Auto-generated method stub
		return IPS;
	}

}