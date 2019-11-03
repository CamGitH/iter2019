package negocio;

public class Servicio implements VOServicio{
	
	
	public enum TipoServicio
	{
		Terapia,
		Radiografia,
		Hospitalizacion,
		ExamenDeSangre,
		JornadaVacunacion
		
	}
	/* **********************
	 * 			Atributos
	 ***********************/

	protected long codigoServicio;
	protected String horario;
	protected int capacidad;
	protected String IPS;
	protected String orden;
	protected int reservas;
	private TipoServicio tipo;

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
		this.setReservas(0);
		this.setTipo(null);
	}

	/**
	 * Constructor con valores
	 */
	public Servicio (Long cod, String h, int cap, String ip, String o, int pReservas, TipoServicio pTipo) 
	{
		this.setCodigoServicio(cod);
		this.setHorario(h);
		this.setCapacidad(cap);
		this.setIPS(ip);
		this.setOrden(o);
		this.setReservas(pReservas);
		this.setTipo(pTipo);
	}

	

	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString() 
	{
		return "Servicio ["+ codigoServicio + ", "+ horario + ", "+ capacidad + ", "+ IPS + ", " + orden + ", " + reservas + "]";
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

	public int getReservas() {
		return reservas;
	}

	public void setReservas(int pReservas) {
		this.reservas = pReservas;
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

	public TipoServicio getTipo() {
		return tipo;
	}

	public void setTipo(TipoServicio tipo) {
		this.tipo = tipo;
	}

}