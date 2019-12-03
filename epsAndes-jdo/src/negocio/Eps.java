package negocio;

public class Eps implements VOEps{
	
	private String nombre;
	
	private String gerente;
	
	private long campa�a;
	
	public Eps()
	{
		this.setGerente("");
		this.setNombre("");
		this.setCampa�a(0);
	}
	
	public Eps(String pNombre, String pGerente, long pCampa�a)
	{
		this.setGerente(pGerente);
		this.setNombre(pNombre);
		this.setCampa�a(pCampa�a);
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

	public long getCampa�a() {
		return campa�a;
	}

	public void setCampa�a(long campa�a) {
		this.campa�a = campa�a;
	}

}
