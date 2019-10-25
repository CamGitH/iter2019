package negocio;

public class MedicoEspecialista implements VOMedicoEspecialista {
	
	
	private long id;
	
	
	private String especialidad;
	
	
	public MedicoEspecialista()
	{
		this.setId(0);
		this.setEspecialidad("");
	}
	
	public MedicoEspecialista(long pId, String pEspecialidad)
	{
		this.setId(pId);
		
		this.setEspecialidad(pEspecialidad);
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	

}
