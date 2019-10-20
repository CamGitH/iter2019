package persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import negocio.Medico;
import negocio.MedicoGeneral;
import negocio.OrdenDeServicio;

public class SQLMedicoGeneral {
	
	private final static String SQL = PersistenciaEpsAndes.SQL;
	
	
	private PersistenciaEpsAndes pe;
	
	
	
	public SQLMedicoGeneral(PersistenciaEpsAndes pe)
	{
		this.pe=pe;
	}
	
	public long registrarMedicoGeneral (PersistenceManager pm, long id, String nombre, String apellido) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.darTablaMedicoGeneral () + "(nombre, apellido,  identificacion) values (?, ?, ?)");
        q.setParameters(nombre, apellido, id);
        return (long) q.executeUnique();
	}
	
	public long eliminarMedicoGeneralPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pe.darTablaMedicoGeneral () + " WHERE identificacion = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}
	
	public List<MedicoGeneral> darMedicosGenerales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pe.darTablaMedicoGeneral ());
		q.setResultClass(MedicoGeneral.class);
		return (List<MedicoGeneral>) q.executeList();
	}
	
	public long asignarOrdenAfiliado(PersistenceManager pm, long numeroOrden, String pTipo, long idAfiliado)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pe.darTablaOrdenDeServicio () + "(numeroOrden, tipo) values (?,  ?)");
		q.setParameters(numeroOrden, pTipo);
		Query q1= pm.newQuery(SQL, "UPDATE"+ pe.darTablaAfiliado()+ "SET Orden_de_servicio = ? WHERE ID =? " );
		q1.setParameters(numeroOrden, idAfiliado);
		return (long) q.executeUnique();
		
	}
	

}
