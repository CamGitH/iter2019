package persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import negocio.Servicio;



public class SQLServicio {
	
	private final static String SQL = PersistenciaEpsAndes.SQL;
	
	
	private PersistenciaEpsAndes pe;
	
	
	
	public SQLServicio(PersistenciaEpsAndes pe)
	{
		this.pe=pe;
	}
	
	
	public long reservarServicio(PersistenceManager pm, int pReservas, long pCodigo)
	{
		Query q= pm.newQuery(SQL, "UPDATE "+ pe.darTablaServicio()+"SET capacidad = capacidad - ? , reservas = reservas + ?  WHERE  CodigoServicio =  ?");
		q.setParameters(pReservas, pReservas, pCodigo);
		return (long) q.executeUnique();
		
	}
	
	public Servicio darServicioPorId (PersistenceManager pm, long idServicio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pe.darTablaServicio() + " WHERE id = ?");
		q.setResultClass(Servicio.class);
		q.setParameters(idServicio);
		return (Servicio) q.executeUnique();
	}
	
	public List<Servicio> darServiciosPorNombre (PersistenceManager pm, String nombreServicio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pe.darTablaServicio() + " WHERE nombre = ?");
		q.setResultClass(Servicio.class);
		q.setParameters(nombreServicio);
		return (List<Servicio>) q.executeList();
	}

	public List<Servicio> darServiciosPorCampaña (PersistenceManager pm, long idCampaña) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pe.darTablaServicio() + " WHERE ID_Campaña = ?");
		q.setResultClass(Servicio.class);
		q.setParameters(idCampaña);
		return (List<Servicio>) q.executeList();
	}
	
	public long cancelarServiciosDeCampaña(PersistenceManager pm, long pCodigo)
	{
		Query q= pm.newQuery(SQL, "UPDATE "+ pe.darTablaServicio()+"SET ID_CAMPAÑA= NULL, CAPACIDAD = CAPACIDAD + 1  WHERE  CodigoServicio =  ?");
		q.setParameters(pCodigo);
		return (long) q.executeUnique();
	}
	
	public long deshabilitarServicio(PersistenceManager pm, long pCodigo)
	{
		Query q= pm.newQuery(SQL, "UPDATE "+ pe.darTablaServicio()+"SET  CAPACIDAD = 0  WHERE  CodigoServicio =  ?");
		q.setParameters(pCodigo);
		return (long) q.executeUnique();
	}
	
	public long rehabilitarServicio(PersistenceManager pm, long pCodigo)
	{
		Query q= pm.newQuery(SQL, "UPDATE "+ pe.darTablaServicio()+"SET  CAPACIDAD = CAPACIDAD + 50  WHERE  CodigoServicio =  ?");
		q.setParameters(pCodigo);
		return (long) q.executeUnique();
	}
	
	
	public List<Servicio> darServicios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pe.darTablaMedicoGeneral ());
		q.setResultClass(Servicio.class);
		return (List<Servicio>) q.executeList();
	}
}
