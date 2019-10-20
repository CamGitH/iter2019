package persistencia;

import java.sql.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import negocio.Afiliado;


public class SQLAfiliado {
	
private final static String SQL = PersistenciaEpsAndes.SQL;
	
	
	private PersistenciaEpsAndes pe;
	
	
	
	public SQLAfiliado(PersistenciaEpsAndes pe)
	{
		this.pe=pe;
	}
	
	
	public long adicionarAfiliado (PersistenceManager pm, long id, Date pFecha, String pOrden) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.darTablaAfiliado () + "(id, FECHA_NACIMIENTO, ORDEN_DE_SERVICIO) values (?, ?, ?)");
        q.setParameters(id,pFecha, pOrden);
        return (long) q.executeUnique();
	}
	
	public long eliminarAfilaidoPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pe.darTablaAfiliado () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}
	
	@SuppressWarnings("unchecked")
	public List<Afiliado> darAfiliados (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pe.darTablaAfiliado ());
		q.setResultClass(Afiliado.class);
		return (List<Afiliado>) q.executeList();
	}
	
	public long reservarServicio(PersistenceManager pm, long codigoSevicio, long idAfiliado)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + pe.darTablaServicio () + "SET ID_AFILIADO=? WHERE CODIGO_SERVICIO = ?");
		q.setParameters(idAfiliado, codigoSevicio);
        return (long) q.executeUnique();
	}
	
	
	
	

}
