package negocio;

import java.sql.Date;

import java.util.List;

import com.google.gson.JsonObject;

import persistencia.PersistenciaEpsAndes;




public class EpsAndes {
	
	private PersistenciaEpsAndes pe;
	
	
	public EpsAndes()
	{
		pe= PersistenciaEpsAndes.getInstance();
	}
	
	public EpsAndes(JsonObject tableConfig)
	{
		pe= PersistenciaEpsAndes.getInstance(tableConfig);
	}
	
	
	public void cerrarUnidadPersistencia()
	{
		pe.cerrarUnidadPersistencia();
	}
	
	
	
	public Eps agregarEps(String nombre, String gerente)
	{
		Eps eps = pe.adicionarEps(nombre, gerente);
        return eps;
	}
	
	public Ips agregarIps(String nombre, String localizacion, String recepcionista, String nombreEps)
	{
		Ips ips = pe.adicionarIps(nombre, localizacion, recepcionista, nombreEps);
        return ips;
	}
	
	public CampaņaPrevencion registrarCampaņaPrevencion( int pAfiliados, Date pFechaI, Date pFechaF)
	{
		CampaņaPrevencion campaņa = pe.registrarCampaņa(pAfiliados, pFechaI, pFechaF);

        return campaņa;
	}
	
	
	public Usuario agregarUsuario(String correo, String nombre, String apellido, String tipoId, String nombreEps)
	{
		Usuario usuario = pe.adicionarUsuario(correo, nombre, apellido, tipoId, nombreEps);
        return usuario;
	}
	
	public long eliminarUsuario(long idUsuario)
	{
		long resp = pe.eliminarUsuarioPorId(idUsuario);
        return resp;
	}
	
	public List<Usuario> darUsuarios ()
	{
        List<Usuario> usuarios = pe.darUsuarios ();	
        return usuarios;
	}
	
	public Afiliado agregarAfiliado(Date pFecha, String pOrden)
	{
		Afiliado afiliado = pe.adicionarAfiliado(pFecha, pOrden);
        return afiliado;
	}
	
	public long eliminarAfiliado(long idAfiliado)
	{
		long resp = pe.eliminarAfiliadoPorId(idAfiliado);
        return resp;
	}
	
	
	public List<Afiliado> darAfiliados ()
	{
        List<Afiliado> afiliados = pe.darAfiliados ();	
        return afiliados;
	}
	
	public List<Servicio> darServicios()
	{
        List<Servicio> servicios = pe.darServicios ();	
        return servicios;
	}
	
	public long reservarServicio(long pCodigo, int pReservas) {
		
		return pe.reservarServicio(pCodigo, pReservas);
		
	}
	
	public long cancelarServicioCampaņa(long pCodigo)
	{
		return pe.cancelarServicioCampaņa(pCodigo);
	}
	
	public long deshabilitarServicio(long pCodigo)
	{
		return pe.deshabilitarServico(pCodigo);
	}
	public long rehabilitarServicio(long pCodigo)
	{
		return pe.rehabilitarServico(pCodigo);
	}
	
	public Servicio darServiciosporNombre(String pNombre)
	{
        List<Servicio> servicios = pe.darServicioPorNombre(pNombre);
        return !servicios.isEmpty () ? servicios.get (0) : null;
	}
	public List<Servicio> darServiciosporCampaņa(long pIdCampaņa)
	{
        List<Servicio> servicios = pe.darServicioPorCampaņa(pIdCampaņa);
        return servicios;
	}
	
	
	
	public MedicoGeneral agregarMedicoGeneral(String pNombre, String pApellido, String pReg, String pIps)
	{
		MedicoGeneral medicoGeneral = pe.adicionarMedicoGeneral(pNombre, pApellido, pReg, pIps);

        return medicoGeneral;
	}
	
	public List<Usuario> consultaReq9(boolean pFecha, boolean pIps, boolean pTipoServicio, Date fechaI, Date fechaF, String ips, String tipo, boolean order, String criterio , boolean agrupamiento, String criterioA)
	{
		List<Usuario> reps= pe.consultaReq9(pFecha, pIps, pTipoServicio, fechaI, fechaF, ips, tipo, order, criterio, agrupamiento, criterioA);
		return reps;
	}
	
	public List<Usuario> consultaReq10(boolean pFecha, boolean pIps, boolean pTipoServicio, Date fechaI, Date fechaF, String ips, String tipo, boolean order, String criterio , boolean agrupamiento, String criterioA)
	{
		List<Usuario> reps= pe.consultaReq10(pFecha, pIps, pTipoServicio, fechaI, fechaF, ips, tipo, order, criterio, agrupamiento, criterioA);
		return reps;
	}
	
	
	public long eliminarMedicoGeneral(long idMedicoGeneral)
	{
		long resp = pe.eliminarMedicoGeneralPorId(idMedicoGeneral);
        return resp;
	}
	
	public List<MedicoGeneral> darMedicosGenerales ()
	{

        List<MedicoGeneral> medicoGenerals = pe.darMedicosGenerales ();	
        return medicoGenerals;
	}
	
	
	public MedicoEspecialista agregarMedicoEspecialista(String pNombre, String pApellido, String pEspeci)
	{
		MedicoEspecialista MedicoEspecialista = pe.adicionarMedicoEspecialista( pEspeci);

        return MedicoEspecialista;
	}
	
	public long eliminarMedicoEspecialista(long idMedicoEspecialista)
	{
		long resp = pe.eliminarMedicoEspecialistaPorId(idMedicoEspecialista);
        return resp;
	}
	
	public List<MedicoEspecialista> darMedicosEspecialistas ()
	{
        List<MedicoEspecialista> medicosEspecialistas = pe.darMedicosEspecialistas ();	
        return medicosEspecialistas;
	}
	
}
