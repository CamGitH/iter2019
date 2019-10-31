package negocio;

import java.sql.Date;
import java.util.ArrayList;

public interface VOCampañaPrevencion {
	
	/**
	 * @return  NombreCampaña
	 */
	public String getNombre();
	
	/**
	 * @return  afiliadosEsperados
	 */
	public int getAfiliadosEsperados();
	
	
	/**
	 * @return  fechaInicial
	 */
	public Date getFechaInicial();
	
	
	/**
	 * @return  fechaFinal
	 */
	public Date getFechaFinal();
	
	
	/**
	 * @return  listaEps
	 */
	public ArrayList<Eps> getListaEps();
	
	/**
	 * @return  Servicios
	 */
	public ArrayList<Servicio> getServicios();
	

}
