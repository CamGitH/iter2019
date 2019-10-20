package negocio;

public class Medicamento implements VOMedicamento{
	
	
	private String nombre;
	
	private String empresa;
	
	
	public Medicamento()
	{
		this.setNombre("");
		this.setEmpresa("");
	}
	
	public Medicamento( String pNombre, String pEmp)
	{
		this.setNombre(pNombre);
		this.setEmpresa(pEmp);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	

}
