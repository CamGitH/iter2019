package negocio;

public class AfiliadosEnServicio implements VOAfiliadosEnServicio{
	
	
	private long codigoServicio;
	
	
	private String idUsuario;
	
	
	public AfiliadosEnServicio()
	{
		this.setCodigoServicio(0);
		this.setIdUsuario("");
		
	}
	
	public AfiliadosEnServicio(String pId, long pCodigo)
	{
		this.setCodigoServicio(pCodigo);
		this.setIdUsuario(idUsuario);
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

	

	

}
