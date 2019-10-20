package persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLIps {
	
	private final static String SQL = PersistenciaEpsAndes.SQL;
	
	
	private PersistenciaEpsAndes pe;
	
	
	
	public SQLIps(PersistenciaEpsAndes pe)
	{
		this.pe=pe;
	}
	
	
	public long  registrarLaPrestacionDeServicio(PersistenceManager pm, long codigoServicio, long idAfiliado)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + pe.darTablaServicio () + "SET ID_AFILIADO = ? WHERE CODIGO_DE_SERVICIO = ?");
		q.setParameters(idAfiliado, codigoServicio);
		return (long) q.executeUnique();
	}

}
