package negocio;

import java.sql.Date;
import java.util.ArrayList;

public class CampañaPrevencion implements VOCampañaPrevencion{
	
	
	private String nombre;
	
	private int afiliadosEsperados;
	
	private Date fechaInicial;
	
	private Date fechaFinal;
	
	private ArrayList<Eps> listaEps;
	
	private ArrayList<Servicio> servicios;
	
	public CampañaPrevencion()
	{
		this.nombre="";
		this.afiliadosEsperados=0;
		this.fechaInicial= null;
		this.fechaFinal=null;
		this.listaEps=null;
		this.servicios=null;
		
	}
	
	public CampañaPrevencion(String pNombre, int pAfiliados, Date pFechaInicial, Date pFechaFinal)
	{
		this.setNombre(pNombre);
		this.setAfiliadosEsperados(pAfiliados);
		this.setFechaInicial(pFechaInicial);
		this.setFechaFinal(pFechaFinal);
		this.listaEps=new ArrayList<Eps>();
		this.servicios=new ArrayList<Servicio>();
	}
	
	public void añadirEps(Eps pEps)
	{
		listaEps.add(pEps);
	}
	

	public ArrayList<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(ArrayList<Servicio> servicios) {
		this.servicios = servicios;
	}

	public ArrayList<Eps> getListaEps() {
		return listaEps;
	}

	public void setListaEps(ArrayList<Eps> listaEps) {
		this.listaEps = listaEps;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public int getAfiliadosEsperados() {
		return afiliadosEsperados;
	}

	public void setAfiliadosEsperados(int afiliadosEsperados) {
		this.afiliadosEsperados = afiliadosEsperados;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	
	
	
	
	

	

}
