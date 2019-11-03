package persistencia;

import java.sql.Date;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLCampa�aDePrevencion {
	
	private final static String SQL = PersistenciaEpsAndes.SQL;
	
	
	private PersistenciaEpsAndes pe;
	
	
	
	public SQLCampa�aDePrevencion(PersistenciaEpsAndes pe)
	{
		this.pe=pe;
	}
	
	
	
	public long registrarCampa�aDePrevencion (PersistenceManager pm, long id,int pAfiliados, Date pFechaI, Date pFechaF) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.darTablaCDprevencion()  + "(id, afiliadoEsperados, fechaInicial, fechaFinal) values (?, ?, ?, ?)");
        q.setParameters(id, pAfiliados, pFechaI, pFechaF);
        return (long) q.executeUnique();
	}

}
