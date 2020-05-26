package es.urjc.code.teVendoLaMoto;



import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity

public class Anuncio {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private long id = -1;
	private String nombre;
	private String asunto;
	
	@ManyToOne 
	private User usuario;
	
	@OneToOne (cascade = CascadeType.ALL)
	private Moto moto;
	
	public Anuncio () {}

	public Anuncio(String nombre, String asunto) {
		super();
		this.nombre = nombre;
		this.asunto = asunto;
		this.usuario = new User ();
		this.moto = new Moto();
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

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public Moto getMoto() {
		return moto;
	}

	public void setMoto(Moto moto) {
		this.moto = moto;
	}
	
	


	@Override
	public String toString() {
		return "Anuncio [nombre=" + nombre + ", asunto=" + asunto + "]";
	}
	
	
	
	
	
	
	

	
}
