package persistencia;

import java.util.List;


import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import negocio.MedicoGeneral;
import negocio.OrdenDeServicio;

public class SQLMedicoGeneral {
	
	private final static String SQL = PersistenciaEpsAndes.SQL;
	
	
	private PersistenciaEpsAndes pe;
	
	
	
	public SQLMedicoGeneral(PersistenciaEpsAndes pe)
	{
		this.pe=pe;
	}
	
	public long registrarMedicoGeneral (PersistenceManager pm, long id, String nombre, String apellido, String pReg, String pIps) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.darTablaMedico() + "(apellido, id,  nombre, regisotromedico, Ips) values (?, ?, ?, ?, ?)");
        q.setParameters(apellido, id, nombre, pReg, pIps );
        return (long) q.executeUnique();
	}
	
	public long eliminarMedicoGeneralPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pe.darTablaMedico () + " WHERE identificacion = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}
	
	public List<MedicoGeneral> darMedicosGenerales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pe.darTablaMedico ());
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
