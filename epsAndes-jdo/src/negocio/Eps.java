package negocio;

public class Eps implements VOEps{
	
	private String nombre;
	
	private String gerente;
	
	private long campaña;
	
	public Eps()
	{
		this.setGerente("");
		this.setNombre("");
		this.setCampaña(0);
	}
	
	public Eps(String pNombre, String pGerente, long pCampaña)
	{
		this.setGerente(pGerente);
		this.setNombre(pNombre);
		this.setCampaña(pCampaña);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGerente() {
		return gerente;
	}

	public void setGerente(String gerente) {
		this.gerente = gerente;
	}

	public long getCampaña() {
		return campaña;
	}

	public void setCampaña(long campaña) {
		this.campaña = campaña;
	}

}
