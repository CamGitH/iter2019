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
	private TipoServicio tipo;
	private int cantidadReservas;
	private long idCampaña;

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
		this.setTipo(null);
		this.setCantidadReservas(0);
		this.setIdCampaña(0);
	}

	/**
	 * Constructor con valores
	 */
	public Servicio (Long cod, String h, int cap, String ip,  TipoServicio pTipo, int cr, long pIdCampaña)
	{
		this.setCodigoServicio(cod);
		this.setHorario(h);
		this.setCapacidad(cap);
		this.setIPS(ip);

		this.setTipo(pTipo);
        this.setCantidadReservas(cr);
        this.setIdCampaña(pIdCampaña);
	}

	

	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString() 
	{
		return "Servicio ["+ codigoServicio + ", "+ horario + ", "+ capacidad + ", "+ IPS  + ", " + cantidadReservas + "]";
	}

	public void setCapacidad(int pcapacidad) {
		this.capacidad = pcapacidad;
	}
	
	public void setCodigoServicio(Long pCodigo) {
		this.codigoServicio = pCodigo;
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


	public void setCantidadReservas(int pReservas) {
		this.cantidadReservas = pReservas;
	}


    public int getCantidadReservas() {
        return cantidadReservas;
    }
	@Override
	public long getCodigoServicio() {
        return codigoServicio;
    }

	@Override
	public int getCapacidad() {
		return capacidad;
	}

	@Override
	public String getIps() {
		return IPS;
	}

	public TipoServicio getTipo() {
		return tipo;
	}

	public void setTipo(TipoServicio tipo) {
		this.tipo = tipo;
	}

	public long getIdCampaña() {
		return idCampaña;
	}

	public void setIdCampaña(long idCampaña) {
		this.idCampaña = idCampaña;
	}

}