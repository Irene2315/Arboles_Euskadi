package clases;

public class Arbol {
	private int id;
	private String nombreComun;
	private String nombreCientifico;
	private String habitat;
	private int altura;
	private String origen;
	
	public Arbol(int id, String nombreComun, String nombreCientifico, String habitat, int altura, String origen) {
		super();
		this.id = id;
		this.nombreComun = nombreComun;
		this.nombreCientifico = nombreCientifico;
		this.habitat = habitat;
		this.altura = altura;
		this.origen = origen;
	}
	
	public Arbol() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreComun() {
		return nombreComun;
	}

	public void setNombreComun(String nombreComun) {
		this.nombreComun = nombreComun;
	}

	public String getNombreCientifico() {
		return nombreCientifico;
	}

	public void setNombreCientifico(String nombreCientifico) {
		this.nombreCientifico = nombreCientifico;
	}

	public String getHabitat() {
		return habitat;
	}

	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}
	
	
	
	

}