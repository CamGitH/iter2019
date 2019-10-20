package negocio;

public class MedicoEspecialista implements VOMedicoEspecialista {
	
	
	private long id;
	
	private String nombre;
	
	private String apellido;
	
	private String especialidad;
	
	
	public MedicoEspecialista()
	{
		this.setId(0);
		this.setNombre("");
		this.setApellido("");
		this.setEspecialidad("");
	}
	
	public MedicoEspecialista(long pId, String pNombre, String pApellido, String pEspecialidad)
	{
		this.setId(pId);
		this.setNombre(pNombre);
		this.setApellido(pApellido);
		this.setEspecialidad(pEspecialidad);
		
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	

}
