package negocio;

public class Recetan implements VORecetan{

	
	private String idReceta;
	
	private String medicamento;
	
	
	public Recetan()
	{
		this.setIdReceta("");
		this.setMedicamento("");
	}
	
	public Recetan(String pID, String pMedicamento)
	{
		this.setIdReceta(pID);
		this.setMedicamento(pMedicamento);
	}

	public String getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}

	public String getIdReceta() {
		return idReceta;
	}

	public void setIdReceta(String idReceta) {
		this.idReceta = idReceta;
	}
}
