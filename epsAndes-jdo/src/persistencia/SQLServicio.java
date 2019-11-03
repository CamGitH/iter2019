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

	
	
	
	public List<Servicio> darServicios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pe.darTablaMedicoGeneral ());
		q.setResultClass(Servicio.class);
		return (List<Servicio>) q.executeList();
	}
}
