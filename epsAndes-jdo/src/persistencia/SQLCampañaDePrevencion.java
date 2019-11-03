package persistencia;

import java.sql.Date;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLCampañaDePrevencion {
	
	private final static String SQL = PersistenciaEpsAndes.SQL;
	
	
	private PersistenciaEpsAndes pe;
	
	
	
	public SQLCampañaDePrevencion(PersistenciaEpsAndes pe)
	{
		this.pe=pe;
	}
	
	
	
	public long registrarCampañaDePrevencion (PersistenceManager pm, long id,int pAfiliados, Date pFechaI, Date pFechaF) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.darTablaCDprevencion()  + "(id, afiliadoEsperados, fechaInicial, fechaFinal) values (?, ?, ?, ?)");
        q.setParameters(id, pAfiliados, pFechaI, pFechaF);
        return (long) q.executeUnique();
	}

}
