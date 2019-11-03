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

	
	
	
	public List<Servicio> darServicios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pe.darTablaMedicoGeneral ());
		q.setResultClass(Servicio.class);
		return (List<Servicio>) q.executeList();
	}
}
