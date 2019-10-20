package negocio;

public class MedicoGeneral implements VOMedicoGeneral{
	
	
	private long id;
	
	private String nombre;
	
	private String apellido;
	
	public MedicoGeneral()
	{
		this.setId(0);
		this.setNombre("");
		this.setApellido("");
	}

	
	public MedicoGeneral(long pId, String pnombre, String pApellido)
	{
		
		this.setId(pId);
		this.setNombre(pnombre);
		this.setApellido(pApellido);
		
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

}
