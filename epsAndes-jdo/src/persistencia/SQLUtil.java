package persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLUtil {
	
	
	private final static String SQL = PersistenciaEpsAndes.SQL;
	
	
	private PersistenciaEpsAndes pe;
	
	
	
	public SQLUtil(PersistenciaEpsAndes pe)
	{
		this.pe=pe;
	}
	
	
	public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pe.darSeqEpsAndes () + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}
	
	
	public long [] limpiarParranderos (PersistenceManager pm)
	{
        Query qAfliados = pm.newQuery(SQL, "DELETE FROM " + pe.darTablaAfiliado ());          
        Query qConsultas = pm.newQuery(SQL, "DELETE FROM " + pe.darTablaConsulta());
        Query qEps = pm.newQuery(SQL, "DELETE FROM " + pe.darTablaEps());
        Query qIps = pm.newQuery(SQL, "DELETE FROM " + pe.darTablaIps());
        Query qMedicamento = pm.newQuery(SQL, "DELETE FROM " + pe.darTablaMedicamento());
        Query qMedico = pm.newQuery(SQL, "DELETE FROM " + pe.darTablaMedico());
        Query qMedicoEspecialista = pm.newQuery(SQL, "DELETE FROM " + pe.darTablaMedicoEspecialista());
        Query qMedicoGeneral = pm.newQuery(SQL, "DELETE FROM " + pe.darTablaMedico());
        Query qOrdenDeServicio = pm.newQuery(SQL, "DELETE FROM " + pe.darTablaOrdenDeServicio());
        Query qRecetas = pm.newQuery(SQL, "DELETE FROM " + pe.darTablaReceta());
        Query qRecetan = pm.newQuery(SQL, "DELETE FROM " + pe.darTablaRecetan());
        Query qServicios = pm.newQuery(SQL, "DELETE FROM " + pe.darTablaServicio());
        Query qUsuarios = pm.newQuery(SQL, "DELETE FROM " + pe.darTablaUsuario());

        long afiliadosEliminados = (long) qAfliados.executeUnique ();
        long consultasEliminados = (long) qConsultas.executeUnique ();
        long epsEliminadas = (long) qEps.executeUnique ();
        long ipsEliminadas = (long) qIps.executeUnique ();
        long medicamentosEliminados = (long) qMedicamento.executeUnique ();
        long medicosEliminados = (long) qMedico.executeUnique ();
        long medicosEspecialistasEliminados = (long) qMedicoEspecialista.executeUnique ();
        long medicosGeneralesEliminados = (long) qMedicoGeneral.executeUnique ();
        long ordenesDeServicioEliminados = (long) qOrdenDeServicio.executeUnique ();
        long recetaEliminados = (long) qRecetas.executeUnique ();
        long recetanEliminados = (long) qRecetan.executeUnique ();
        long serviciosEliminados = (long) qServicios.executeUnique ();
        long usuariosEliminados = (long) qUsuarios.executeUnique ();
        
        return new long[] {afiliadosEliminados, consultasEliminados, epsEliminadas, ipsEliminadas, 
        		medicamentosEliminados, medicosEliminados, medicosEspecialistasEliminados, medicosGeneralesEliminados,
        		ordenesDeServicioEliminados, recetaEliminados, recetanEliminados, serviciosEliminados, usuariosEliminados};
	}

}
