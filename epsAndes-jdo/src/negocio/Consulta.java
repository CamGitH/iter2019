package negocio;

public class Consulta extends Servicio implements VOConsulta{
	
	private long codigoServicio;
	
	private String tipoConsulta;
	
	private String triage;
	
	private String receta;
	
	
	public Consulta ()
	{
		this.setCodigoServicio(0);
		this.setTipoConsulta("");
		this.setTriage("");
		this.setReceta("");
	}
	public Consulta( Long cod, String pTipo, String pTriage, String pReceta)
	{
		this.setCodigoServicio(cod);
		this.setTipoConsulta(pTipo);
		this.setTriage(pTriage);
		this.setReceta(pReceta);
	}
	
	public long getCodigoServicio() {
		return codigoServicio;
	}

	public void setCodigoServicio(long codigoServicio) {
		this.codigoServicio = codigoServicio;
	}

	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipo) {
		this.tipoConsulta = tipo;
	}

	public String getTriage() {
		return triage;
	}

	public void setTriage(String triage) {
		this.triage = triage;
	}

	public String getReceta() {
		return receta;
	}

	public void setReceta(String receta) {
		this.receta = receta;
	}

	

}
