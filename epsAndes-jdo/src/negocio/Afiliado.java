package negocio;

import java.sql.Date;

public class Afiliado implements VOAfiliado{
	
	
	private long id;
	
	private Date fechaDeNaciemiento;
	
	private String ordenDeServicio;
	
	
	public Afiliado() {
		
		this.setId(0);
		this.setFechaDeNacimiento(null);
		this.setOrdenDeServicio("");
	}
	
	public Afiliado (Long pId, Date pFecha, String pOrden)
	{
		this.setId(pId);
		this.setFechaDeNacimiento(pFecha);
		this.setOrdenDeServicio(pOrden);
	}

	@Override
	public long getId() {
		return id;
	}
	
	public void setId(long pId)
	{
		this.id=pId;
	}
	
	public void setFechaDeNacimiento(Date pFecha)
	{
		this.fechaDeNaciemiento=pFecha;
	}
	
	public void setOrdenDeServicio(String pOrden)
	{
		this.ordenDeServicio=pOrden;
	}

	@Override
	public Date getFechaNacimiento() {
		return fechaDeNaciemiento;
	}

	@Override
	public String getOrdenServicio() {
		return ordenDeServicio;
	}

}
