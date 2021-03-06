package persistencia;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import org.datanucleus.enhancer.methods.SetNormal;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import negocio.Afiliado;
import negocio.Campa�aPrevencion;
import negocio.Eps;
import negocio.Ips;
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
	
	private SQLCampa�aDePrevencion sqlCampa�aPrevencion;
	
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
		pmf = JDOHelper.getPersistenceManagerFactory("Parranderos");
		crearClasesSQL();
		
		tablas= new LinkedList<String>();
		tablas.add("EpsAndes_sequence");
		tablas.add("EPS");
		tablas.add("AFILIADO");
		tablas.add("CDEPREVENCION");
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
		sqlCampa�aPrevencion= new SQLCampa�aDePrevencion(this);
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
		return tablas.get(5);
	}
	
	public String darTablaAfiliado()
	{
		return tablas.get(1);
	}
	
	public String darTablaAS()
	{
		return tablas.get(2);
	}
	
	public String darTablaCDprevencion()
	{
		return tablas.get(3);
	}
	public String darTablaConsulta()
	{
		return tablas.get(4);
	}
	
	public String darTablaIps()
	{
		return tablas.get(7);
	}
	
	public String darTablaMedicamento()
	{
		return tablas.get(8);
	}
	
	public String darTablaMedico()
	{
		return tablas.get(9);
	}
	
	
	public String darTablaMedicoEspecialista()
	{
		return tablas.get(6);
	}
	
	

	public String darTablaOrdenDeServicio()
	{
		return tablas.get(10);
	}
	
	public String darTablaReceta()
	{
		return tablas.get(11);
	}
	
	public String darTablaServicio()
	{
		return tablas.get(13);
	}
	
	public String darTablaUsuario()
	{
		return tablas.get(15);
	}
	
	public String darTablaRecetan()
	{
		return tablas.get(12);
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
            
            log.trace ("Inserci�n de Eps: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
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
	
	public Ips adicionarIps(String nombre, String localizacion, String recepcionista, String nombreEps)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlIps.adicionarIps(pm, nombre, localizacion, recepcionista, nombreEps);
            tx.commit();
            
            log.trace ("Inserci�n de Eps: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Ips ( nombre, localizacion, recepcionista, nombreEps, new ArrayList<Servicio>());
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
            
            log.trace ("Inserci�n de usuario: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
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
	
	public Campa�aPrevencion registrarCampa�a(int pAfiliados, Date pFechaI, Date pFechaF)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idCampa�a = nextval ();
            long tuplasInsertadas = sqlCampa�aPrevencion.registrarCampa�aDePrevencion(pm, idCampa�a, pAfiliados, pFechaI, pFechaF);
            tx.commit();
            
            log.trace ("Inserci�n de afiliado: " + idCampa�a + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Campa�aPrevencion(idCampa�a, pAfiliados, pFechaI, pFechaF);
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
            
            log.trace ("Inserci�n de afiliado: " + idAfiliado + ": " + tuplasInsertadas + " tuplas insertadas");
            
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
            
            
            return new MedicoGeneral( pnombre, pApellido, idMedico,pRegistro, pApellido);
        }
        catch (Exception e)
        {
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
            
            log.trace ("Inserci�n del medico especialista: " + idMedico + ": " + tuplasInsertadas + " tuplas insertadas");
            
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
	
	
	public List<Usuario> consultaReq10(boolean pFecha, boolean pIps, boolean pTipoServicio, Date fechaI, Date fechaF, String ips, String tipo, boolean order, String criterio, boolean agrupamiento, String criterioA)
	{
		PersistenceManager pm=pmf.getPersistenceManager();
		Query q=null;
 		String sentencia="";
		String orderBy="ORDER BY ";
		String group="GROUP BY ";
		if(((pIps^pTipoServicio)^(pFecha))==true)
		{
			if(pTipoServicio==true)
			{
				sentencia="SELECT z.*\r\n" + 
						"FROM \r\n" + 
						"( Select x.codigo_servicio, y.*\r\n" + 
						"From "+this.darTablaAS()+" x INNER JOIN "+this.darTablaUsuario()+ "y ON(x.id_afiliado=y.id)) z INNER Join servicio s On(z.Codigo_servicio=s.Codigo_de_servicio)\r\n"+
						"WHERE NOT s.tipo_servicio= ?";
				if(order)
				{
					orderBy="z."+criterio;
					sentencia+=orderBy;
				}
				if(agrupamiento)
				{
					group="z."+criterioA;
					sentencia+=group;
				}
				
				
				q = pm.newQuery(SQL, sentencia);
				q.setParameters( tipo );
				q.setResultClass(Usuario.class);
				return (List<Usuario>) q.executeList();
			}
			if(pFecha==true)
			{
				sentencia="SELECT y.*, x.id_afiliado\r\n" + 
						"FROM "+this.darTablaAS()+" x INNER JOIN "+ this.darTablaUsuario()+ " y ON(x.id_afiliado=y.id)\r\n" + 
						"WHERE NOT x.FECHA > ? AND NOT x.FECHA < ?";
				if(order)
				{
					orderBy="y."+criterio;
					sentencia+=orderBy;
				}
				if(agrupamiento)
				{
					group="y."+criterioA;
					sentencia+=group;
				}
				
				q = pm.newQuery(SQL, sentencia);
				q.setParameters( fechaI, fechaF );
				q.setResultClass(Usuario.class);
			}
			if(pIps)
			{
				sentencia="SELECT\r\n" + 
						"    a.*\r\n" + 
						"FROM \r\n" + 
						"(SELECT\r\n" + 
						"afiliado_servicio.codigo_servicio,\r\n" + 
						"usuario.*\r\n" + 
						"FROM afiliado_servicio, usuario\r\n" + 
						"WHERE usuario.id= afiliado_servicio.id_afiliado) a INNER JOIN servicios_ips on(servicios_ips.codigo_servicio=a.codigo_servicio)\r\n" + 
						"Where servicios_ips.nombre_ips =  ? \r\n" ;
				
				if(order)
				{
					orderBy="a."+criterio;
					sentencia+=orderBy;
				}
				if(agrupamiento)
				{
					group="a."+criterioA;
					sentencia+=group;
				}
				
				q = pm.newQuery(SQL, sentencia);
				q.setParameters( ips);
				q.setResultClass(Usuario.class);
			}
			
		}
		return (List<Usuario>) q.executeList();
		
		
		
		
	}
	
	
	public List<Usuario> consultaReq9(boolean pFecha, boolean pIps, boolean pTipoServicio, Date fechaI, Date fechaF, String ips, String tipo, boolean order, String criterio, boolean agrupamiento, String criterioA)
	{
		PersistenceManager pm=pmf.getPersistenceManager();
		Query q=null;
 		String sentencia="";
		String orderBy="ORDER BY ";
		String group="GROUP BY ";
		if(((pIps^pTipoServicio)^(pFecha))==true)
		{
			if(pTipoServicio==true)
			{
				sentencia="SELECT z.*\r\n" + 
						"FROM \r\n" + 
						"( Select x.codigo_servicio, y.*\r\n" + 
						"From "+this.darTablaAS()+" x INNER JOIN "+this.darTablaUsuario()+ "y ON(x.id_afiliado=y.id)) z INNER Join servicio s On(z.Codigo_servicio=s.Codigo_de_servicio)\r\n"+
						"WHERE s.tipo_servicio= ?";
				if(order)
				{
					orderBy="z."+criterio;
					sentencia+=orderBy;
				}
				if(agrupamiento)
				{
					group="z."+criterioA;
					sentencia+=group;
				}
				
				
				q = pm.newQuery(SQL, sentencia);
				q.setParameters( tipo );
				q.setResultClass(Usuario.class);
				return (List<Usuario>) q.executeList();
			}
			if(pFecha==true)
			{
				sentencia="SELECT y.*, x.id_afiliado\r\n" + 
						"FROM "+this.darTablaAS()+" x INNER JOIN "+ this.darTablaUsuario()+ " y ON(x.id_afiliado=y.id)\r\n" + 
						"WHERE x.FECHA > ? AND x.FECHA < ?";
				if(order)
				{
					orderBy="y."+criterio;
					sentencia+=orderBy;
				}
				if(agrupamiento)
				{
					group="y."+criterioA;
					sentencia+=group;
				}
				
				q = pm.newQuery(SQL, sentencia);
				q.setParameters( fechaI, fechaF );
				q.setResultClass(Usuario.class);
			}
			if(pIps)
			{
				sentencia="SELECT\r\n" + 
						"  SELECT\r\n" + 
						"    a.*\r\n" + 
						"FROM \r\n" + 
						"(SELECT\r\n" + 
						"afiliado_servicio.codigo_servicio,\r\n" + 
						"usuario.*\r\n" + 
						"FROM afiliado_servicio, usuario\r\n" + 
						"WHERE usuario.id= afiliado_servicio.id_afiliado) a INNER JOIN servicios_ips on(servicios_ips.codigo_servicio=a.codigo_servicio)\r\n" + 
						"Where servicios_ips.nombre_ips = ? \r\n" + 
						"; ";
				
				if(order)
				{
					orderBy="a."+criterio;
					sentencia+=orderBy;
				}
				if(agrupamiento)
				{
					group="a."+criterioA;
					sentencia+=group;
				}
				
				q = pm.newQuery(SQL, sentencia);
				q.setParameters( ips);
				q.setResultClass(Usuario.class);
			}
			
		}
		return (List<Usuario>) q.executeList();
		
		
		
		
	}
	
	
	
	
	
	public List<MedicoEspecialista> darMedicosEspecialistas ()
	{
		return sqlMedicoEspecialista.darMedicosEspecialistas (pmf.getPersistenceManager());
	}
	
	public List<Servicio> darServicios()
	{
		return sqlServicio.darServicios(pmf.getPersistenceManager());
	}
	
	public List<Servicio> darServicioPorNombre( String pNombre)
	{
		return sqlServicio.darServiciosPorNombre(pmf.getPersistenceManager(), pNombre);
	}
	
	public List<Servicio> darServicioPorCampa�a( long pIdCampa�a)
	{
		return sqlServicio.darServiciosPorCampa�a(pmf.getPersistenceManager(), pIdCampa�a);
	}
	
	public long cancelarServicioCampa�a(long pCodigo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlServicio.cancelarServiciosDeCampa�a(pm,  pCodigo);
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
	
	
	public long deshabilitarServico(long pCodigo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlServicio.deshabilitarServicio(pm,  pCodigo);
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
	
	public long rehabilitarServico(long pCodigo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlServicio.rehabilitarServicio(pm,  pCodigo);
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
	
	public long reservarServicio (long pCodigo, int pReservas)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlServicio.reservarServicio(pm, pReservas, pCodigo);
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
	
	
	
	
	
}
