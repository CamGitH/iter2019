package persistencia;

import java.sql.Date;


import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import negocio.Afiliado;
import negocio.Eps;
import negocio.MedicoEspecialista;
import negocio.MedicoGeneral;
import negocio.Servicio;
import negocio.Usuario;





public class PersistenciaEpsAndes {
	
	
	private static Logger log = Logger.getLogger(PersistenciaEpsAndes.class.getName());
	
	
	public final static String SQL = "javax.jdo.query.SQL";
	
	
	private static PersistenciaEpsAndes instance;
	
	
	private PersistenceManagerFactory pmf;
	
	
	private List <String> tablas;
	
	private SQLUtil sqlUtil;
	
	private SQLEps sqlEps;
	
	private SQLAfiliado sqlAfiliado;
	
	private SQLConsulta sqlConsulta;
	
	private SQLIps sqlIps;
	
	private SQLMedicamento sqlMedicamento;
	
	
	private SQLMedicoEspecialista sqlMedicoEspecialista;
	
	private SQLMedicoGeneral sqlMedicoGeneral;
	
	private SQLOrdenDeServicio sqlOrdenDeServicio;
	
	private SQLReceta sqlReceta;
	
	private SQLServicio sqlServicio;
	
	private SQLUsuario sqlUsuario;
	
	private SQLRecetan sqlRecetan;
	
	
	private PersistenciaEpsAndes()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("EpsAndes");
		crearClasesSQL();
		
		tablas= new LinkedList<String>();
		tablas.add("EpsAndes_sequence");
		tablas.add("EPS");
		tablas.add("AFILIADO");
		tablas.add("CONSULTA");
		tablas.add("IPS");
		tablas.add("MEDICAMENTO");
		tablas.add("MEDICO");
		tablas.add("MEDICO_ESPECIALISTA");
		tablas.add("MEDICO_GENERAL");
		tablas.add("ORDEN_DE_SERVICIO");
		tablas.add("RECETA");
		tablas.add("SERVICIO");
		tablas.add("USUARIO");
		tablas.add("RECETAN");
		
	}
	
	private PersistenciaEpsAndes(JsonObject tableConfig)
	{
		crearClasesSQL();
		tablas = leerNombresTablas(tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	
	}
	
	public static PersistenciaEpsAndes getInstance()
	{
		if(instance==null)
		{
			instance= new PersistenciaEpsAndes();
		}
		return instance;
	}
	
	public static PersistenciaEpsAndes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaEpsAndes (tableConfig);
		}
		return instance;
	}
	
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	
	private void crearClasesSQL()
	{
		
		sqlEps= new SQLEps(this);
		sqlAfiliado= new SQLAfiliado(this);
		sqlConsulta= new SQLConsulta(this);
		sqlIps= new SQLIps(this);
		sqlMedicamento= new SQLMedicamento(this);
		sqlMedicoEspecialista= new SQLMedicoEspecialista(this);
		sqlMedicoGeneral = new SQLMedicoGeneral(this);
		sqlOrdenDeServicio= new SQLOrdenDeServicio(this);
		sqlReceta= new SQLReceta(this);
		sqlServicio= new SQLServicio(this);
		sqlUsuario= new SQLUsuario(this);
		sqlUtil = new SQLUtil(this);
	}
	
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	
	public String darSeqEpsAndes ()
	{
		return tablas.get (0);
	}
	
	
	public String darTablaEps()
	{
		return tablas.get(1);
	}
	
	public String darTablaAfiliado()
	{
		return tablas.get(2);
	}
	public String darTablaConsulta()
	{
		return tablas.get(3);
	}
	
	public String darTablaIps()
	{
		return tablas.get(4);
	}
	
	public String darTablaMedicamento()
	{
		return tablas.get(5);
	}
	
	public String darTablaMedico()
	{
		return tablas.get(6);
	}
	
	
	public String darTablaMedicoEspecialista()
	{
		return tablas.get(7);
	}
	
	public String darTablaMedicoGeneral()
	{
		return tablas.get(8);
	}

	public String darTablaOrdenDeServicio()
	{
		return tablas.get(9);
	}
	
	public String darTablaReceta()
	{
		return tablas.get(10);
	}
	
	public String darTablaServicio()
	{
		return tablas.get(11);
	}
	
	public String darTablaUsuario()
	{
		return tablas.get(12);
	}
	
	public String darTablaRecetan()
	{
		return tablas.get(13);
	}
	
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }
	
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}
	
	public Eps adicionarEps(String nombre, String gerente)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlEps.adicionarEps(pm, nombre, gerente);
            tx.commit();
            
            log.trace ("Inserción de Eps: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Eps ( nombre, gerente);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	public Usuario adicionarUsuario(String correo, String nombre, String apellido, String tipoId, String nombreEps)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idUsuario = nextval ();
            long tuplasInsertadas = sqlUsuario.registrarUsuario(pm, idUsuario, correo, nombre, apellido, tipoId, nombreEps);
            tx.commit();
            
            log.trace ("Inserción de usuario: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Usuario(idUsuario, nombre, correo, apellido, tipoId, nombreEps);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	public long eliminarUsuarioPorId (long idUsuario) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlUsuario.eliminarUsuarioPorId(pm, idUsuario);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public List<Usuario> darUsuarios ()
	{
		return sqlUsuario.darUsuarios (pmf.getPersistenceManager());
	}
	
	public Afiliado adicionarAfiliado(Date pFecha, String pOrden)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idAfiliado = nextval ();
            long tuplasInsertadas = sqlAfiliado.adicionarAfiliado(pm, idAfiliado, pFecha, pOrden);
            tx.commit();
            
            log.trace ("Inserción de afiliado: " + idAfiliado + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Afiliado(idAfiliado, pFecha, pOrden);
        }
        catch (Exception e)
        {

        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarAfiliadoPorId (long idAfiliado) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlAfiliado.eliminarAfilaidoPorId(pm, idAfiliado);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {

        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public List<Afiliado> darAfiliados ()
	{
		return sqlAfiliado.darAfiliados (pmf.getPersistenceManager());
	}
	
	
	public MedicoGeneral adicionarMedicoGeneral(String pnombre, String pApellido , String pRegistro, String pIps)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idMedico = nextval ();
            long tuplasInsertadas = sqlMedicoGeneral.registrarMedicoGeneral(pm, idMedico, pnombre, pApellido, pRegistro, pIps);
            tx.commit();
            
            log.trace ("Inserción del medico general: " + idMedico + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new MedicoGeneral( pnombre, pApellido, idMedico,pRegistro, pApellido);
        }
        catch (Exception e)
        {

        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	public long eliminarMedicoGeneralPorId (long idMedico) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlMedicoGeneral.eliminarMedicoGeneralPorId(pm, idMedico);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public List<MedicoGeneral> darMedicosGenerales ()
	{
		return sqlMedicoGeneral.darMedicosGenerales (pmf.getPersistenceManager());
	}
	
	public List<Servicio> darServicios()
	{
		return sqlServicio.darServicios(pmf.getPersistenceManager());
	}
	
	
	public MedicoEspecialista adicionarMedicoEspecialista( String pEspeci)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idMedico = nextval ();
            long tuplasInsertadas = sqlMedicoEspecialista.registrarMedicoEspecialista(pm, idMedico, pEspeci);
            tx.commit();
            
            log.trace ("Inserción del medico especialista: " + idMedico + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new MedicoEspecialista(idMedico, pEspeci);
        }
        catch (Exception e)
        {

        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	public long eliminarMedicoEspecialistaPorId (long idMedico) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlMedicoEspecialista.eliminarMedicoEspecialistaPorId(pm, idMedico);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	public List<MedicoEspecialista> darMedicosEspecialistas ()
	{
		return sqlMedicoEspecialista.darMedicosEspecialistas (pmf.getPersistenceManager());
	}
	
	
}
