package negocio;

public class Ips implements VOIps{
	
	
	private String nombre;
	
	private String localizacion;
	
	private String recepcionista;
	
	private String eps;
	
	public Ips()
	{
		this.setNombre("");
		this.setLocalizacion("");
		this.setRecepcionista("");
		this.setEps("");
	}
	
	public Ips(String pNombre, String pLoc, String pRec, String pEps)
	{
		this.setNombre(pNombre);
		this.setLocalizacion(pLoc);
		this.setRecepcionista(pRec);
		this.setEps(pEps);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public String getRecepcionista() {
		return recepcionista;
	}

	public void setRecepcionista(String recepcionista) {
		this.recepcionista = recepcionista;
	}

	public String getEps() {
		return eps;
	}

	public void setEps(String eps) {
		this.eps = eps;
	}

}
