package negocio;

public class Medico implements VOMedico{
	
	private long id;
	
	private String registroMedico;
	
	private String ips;
	
	public Medico()
	{
		this.setId(0);
		this.setRegistroMedico("");
		this.setIps("");
	}
	
	public Medico(long pId, String pReg, String pIps)
	{
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
	

}
