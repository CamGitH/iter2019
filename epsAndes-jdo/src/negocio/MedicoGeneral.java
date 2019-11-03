package negocio;

public class MedicoGeneral implements VOMedicoGeneral{
	
	protected String nombre;
	
	protected String apellido;
	
	protected long id;
	
	protected String registroMedico;
	
	protected String ips;
	
	public MedicoGeneral()
	{
		this.setNombre("");
		this.setApellido("");
		this.setId(0);
		this.setRegistroMedico("");
		this.setIps("");
	}
	
	public MedicoGeneral(String pNombre, String pApellido, long pId, String pReg, String pIps)
	{
		this.setNombre(pNombre);
		this.setApellido(pApellido);
		this.setId(pId);
		this.setRegistroMedico(pReg);
		this.setIps(pIps);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRegistroMedico() {
		return registroMedico;
	}

	public void setRegistroMedico(String registroMedico) {
		this.registroMedico = registroMedico;
	}

	public String getIps() {
		return ips;
	}

	public void setIps(String ips) {
		this.ips = ips;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
