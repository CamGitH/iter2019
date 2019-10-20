package negocio;

public class Receta implements VOReceta{
	
	private long id;
	
	
	private String orden;
	
	public Receta()
	{
		this.setId(0);
		this.setOrden("");
	}
	
	public Receta(long pId, String pOrden)
	{
		this.setId(pId);
		this.setOrden(pOrden);
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getOrden() {
		return orden;
	}


	public void setOrden(String orden) {
		this.orden = orden;
	}
	
	

}
