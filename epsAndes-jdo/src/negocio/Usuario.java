package negocio;

public class Usuario implements VOUsuario{
	
	private long id;
	
	private String nombre;
	
	private String correo;
	
	private String apellido;
	
	private String tipoId;
	
	private String eps;
	
	public Usuario()
	{
		this.setId(0);
		this.setNombre("");
		this.setCorreo("");
		this.setApellido("");
		this.setTipoId("");
		this.setEps("");
	}
	
	public Usuario(long pId, String pNombre, String pCorreo, String pApellido, String pTipoId, String pEps)
	{
		this.setId(pId);
		this.setNombre(pNombre);
		this.setCorreo(pCorreo);
		this.setApellido(pApellido);
		this.setTipoId(pTipoId);
		this.setEps(pEps);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTipoId() {
		return tipoId;
	}

	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}

	public String getEps() {
		return eps;
	}

	public void setEps(String eps) {
		this.eps = eps;
	}
	
	

}
