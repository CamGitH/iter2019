package negocio;

import java.util.ArrayList;

public class Ips implements VOIps{
	
	
	private String nombre;
	
	private String localizacion;
	
	private String recepcionista;
	
	private String eps;
	
	private ArrayList<Servicio> servicios;
	
	public Ips()
	{
		this.setNombre("");
		this.setLocalizacion("");
		this.setRecepcionista("");
		this.setEps("");
		this.setServicios(new ArrayList<Servicio>());
	}
	
	public Ips(String pNombre, String pLoc, String pRec, String pEps, ArrayList<Servicio> pServicios)
	{
		this.setNombre(pNombre);
		this.setLocalizacion(pLoc);
		this.setRecepcionista(pRec);
		this.setEps(pEps);
		this.setServicios(pServicios);
	}
	
	
	public ArrayList<Servicio> darServicios()
	{
		return servicios;
	}
	
	
	public void setServicios(ArrayList<Servicio> pServicios)
	{
		this.servicios=pServicios;
	}
	
	public void añadirServicio( Servicio pServicio)
	{
		servicios.add(pServicio);
	}
	
	public void eliminarServicio(Servicio pServicio)
	{
		servicios.remove(pServicio);
	}
	
	public void eliminarServicio(int pServicio)
	{
		servicios.remove(pServicio);
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
