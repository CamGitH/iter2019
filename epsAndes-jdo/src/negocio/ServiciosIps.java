package negocio;

public class ServiciosIps implements VOServiciosIps{
	
	private String nombreIps;
	
	private long codigoServicio;
	
	
	public ServiciosIps()
	{
		this.setCodigoServicio(0);
		this.setNombreIps("");
	}
	
	public ServiciosIps(long pCodigoServicio, String pNombreIps)
	{
		this.setCodigoServicio(pCodigoServicio);
		this.setNombreIps(pNombreIps);
	}

	public long darCodigoServicio() {
		return codigoServicio;
	}

	public void setCodigoServicio(long codigoServicio) {
		this.codigoServicio = codigoServicio;
	}

	public String darNombreIps() {
		return nombreIps;
	}

	public void setNombreIps(String nombreIps) {
		this.nombreIps = nombreIps;
	}
	
	
	

}
