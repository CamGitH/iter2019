package negocio;

public class Eps implements VOEps{
	
	private String nombre;
	
	private String gerente;
	
	public Eps()
	{
		this.setGerente("");
		this.setNombre("");
	}
	
	public Eps(String pNombre, String pGerente)
	{
		this.setGerente(pGerente);
		this.setNombre(pNombre);
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

}
