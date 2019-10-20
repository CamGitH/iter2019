package persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import negocio.Usuario;

public class SQLUsuario {
	
	private final static String SQL = PersistenciaEpsAndes.SQL;
	
	
	private PersistenciaEpsAndes pe;
	
	
	
	public SQLUsuario(PersistenciaEpsAndes pe)
	{
		this.pe=pe;
	}
	
	public long registrarUsuario (PersistenceManager pm, long id,String correo, String nombre, String apellido, String tipoId, String nombreEps) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pe.darTablaUsuario()  + "(id, correo, nombre, apellido, tipoId, nombre_Eps) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(id,correo , nombre, apellido, tipoId, nombreEps);
        return (long) q.executeUnique();
	}
	
	public long eliminarUsuarioPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pe.darTablaUsuario() + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}
	
	public List<Usuario> darUsuarios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pe.darTablaUsuario());
		q.setResultClass(Usuario.class);
		return (List<Usuario>) q.executeList();
	}
	
	
	
}
