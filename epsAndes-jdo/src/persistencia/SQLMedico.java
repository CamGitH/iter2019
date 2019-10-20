package persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import negocio.Medico;

public class SQLMedico {
	
	
	private final static String SQL = PersistenciaEpsAndes.SQL;
	
	
	private PersistenciaEpsAndes pe;
	
	
	
	public SQLMedico(PersistenciaEpsAndes pe)
	{
		this.pe=pe;
	}
	
	
	public long registrarMedico (PersistenceManager pm, long id, String registroMedico, String nombreIps) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.darTablaMedico () + "(identificacion, registroMedico, nombre_ips) values (?, ?, ?)");
        q.setParameters(id, registroMedico, nombreIps);
        return (long) q.executeUnique();
	}
	
	public long eliminarMedicoPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pe.darTablaMedico () + " WHERE identificacion = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}
	
	public List<Medico> darMedicos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pe.darTablaMedico ());
		q.setResultClass(Medico.class);
		return (List<Medico>) q.executeList();
	}

}
