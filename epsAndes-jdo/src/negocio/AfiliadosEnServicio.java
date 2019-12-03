package negocio;

public class AfiliadosEnServicio implements VOAfiliadosEnServicio{
	
	
	private long codigoServicio;
	
	
	private String idUsuario;
	
	private long numeroOrden;
	
	
	public AfiliadosEnServicio()
	{
		this.setCodigoServicio(0);
		this.setIdUsuario("");
		this.setNumeroOrden(0);
		
	}
	
	public AfiliadosEnServicio(String pId, long pCodigo, long pOrden)
	{
		this.setCodigoServicio(pCodigo);
		this.setIdUsuario(idUsuario);
		this.setNumeroOrden(pOrden);
	}


	public long darCodigoServicio() {
		return codigoServicio;
	}


	public void setCodigoServicio(long codigoServicio) {
		this.codigoServicio = codigoServicio;
	}


	public String darIdAfiliado() {
		return idUsuario;
	}


	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public long getNumeroOrden() {
		return numeroOrden;
	}

	public void setNumeroOrden(long numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	

	

}
