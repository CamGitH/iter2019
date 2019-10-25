package persistencia;

import java.util.List;


import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import negocio.MedicoEspecialista;

public class SQLMedicoEspecialista {
	
	
	private final static String SQL = PersistenciaEpsAndes.SQL;
	
	
	private PersistenciaEpsAndes pe;
	
	
	
	public SQLMedicoEspecialista(PersistenciaEpsAndes pe)
	{
		this.pe=pe;
	}
	
	public long registrarMedicoEspecialista (PersistenceManager pm, long id, String nombre, String apellido, String especialidad) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.darTablaMedicoEspecialista() + "(nombre, apellido, ESPECIALIDAD, identificacion) values (?, ?, ?, ?)");
        q.setParameters(nombre, apellido, especialidad, id);
        return (long) q.executeUnique();
	}
	
	public long eliminarMedicoEspecialistaPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pe.darTablaMedicoEspecialista () + " WHERE identificacion = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}
	
	@SuppressWarnings("unchecked")
	public List<MedicoEspecialista> darMedicosEspecialistas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pe.darTablaMedicoEspecialista ());
		q.setResultClass(MedicoEspecialista.class);
		return (List<MedicoEspecialista>) q.executeList();
	}

}
