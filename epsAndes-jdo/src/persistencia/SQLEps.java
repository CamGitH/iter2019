package persistencia;

import javax.jdo.PersistenceManager;


import javax.jdo.Query;

public class SQLEps {
	
	private final static String SQL = PersistenciaEpsAndes.SQL;
	
	
	private PersistenciaEpsAndes pe;
	
	
	
	public SQLEps(PersistenciaEpsAndes pe)
	{
		this.pe=pe;
	}
	
	
	public long adicionarEps(PersistenceManager pm, String nombreEps,String gerenteEps) 
	{
		 Query q = pm.newQuery(SQL, "INSERT INTO " + pe.darTablaEps () + "( nombre, gerente) values (?, ?)");
//	     q.setParameters(nombreEps, gerenteEps);
	     return (long) q.execute(nombreEps, gerenteEps);
	}
	
	public long eliminarEpsPorNombre (PersistenceManager pm, String nombreEps)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pe.darTablaEps () + " WHERE nombre = ?");
        q.setParameters(nombreEps);
        return (long) q.executeUnique();
	}

}
