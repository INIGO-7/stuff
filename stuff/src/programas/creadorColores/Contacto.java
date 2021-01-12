package programas.creadorColores;

public class Contacto {
	private String nombre;
	private String apellido;
	private String telefono;
	private String mail;
	private String twitter;
	private String instagram;
	
	
	public Contacto(String nombre, String apellido, String telefono, String mail, String twitter, String instagram) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.mail = mail;
		this.twitter = twitter;
		this.instagram = instagram;
	}
	
	public Contacto() {
		super();
		this.nombre = "";
		this.apellido = "";
		this.telefono = "";
		this.mail = "";
		this.twitter = "";
		this.instagram = "";
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	@Override
	public String toString() {
		return "Contacto [nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + ", mail=" + mail
				+ ", twitter=" + twitter + ", instagram=" + instagram + "]";
	}
	
	
}