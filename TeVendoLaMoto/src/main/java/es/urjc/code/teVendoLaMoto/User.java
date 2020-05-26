package es.urjc.code.teVendoLaMoto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Entity
@Component
@SessionScope
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String password;
	
	private String nombre_completo;
	private String dni;
	private String email;
	private String telefono;
	
	@OneToMany (mappedBy="usuario")
	private List<Anuncio> anuncio;
	
	@OneToMany (mappedBy="propietario")
	private List<Moto> moto;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private Venta venta;
	
	@OneToOne(cascade= {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
	private OfertaCompra ofertacompra;
	

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;

	public User() {
	}
	//User u1 = new User("dortiz","67890","Damian Ortiz","50574692D","demn007@gmail.com","648799392","ROLE_USER");
	public User(String name, String password,String nombre_completo, String dni ,String email,String telefono, String... roles)
	{
		this.name = name;
		this.password = new BCryptPasswordEncoder().encode(password);
		this.nombre_completo = nombre_completo;
		this.dni = dni;
		this.email = email;
		this.telefono = telefono;
		this.roles = new ArrayList<>(Arrays.asList(roles));
	}
	
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getNombreCompleto() {
		return nombre_completo;
	}
	
	public void removeAnuncio(Anuncio anuncio) {
		getAnuncio().remove(anuncio);
		anuncio.setUsuario(null);
	}
	
	public List<Anuncio> getAnuncio() {
		return anuncio;
	}	
	public Venta getVenta() {
		return venta;
	}
	public List<Moto> getMoto() {
		return moto;
	}
	public OfertaCompra getOfertaCompra() {
		return ofertacompra;
	}	
	
	public void setNombreCompleto(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}

	public String getNombre_completo() {
		return nombre_completo;
	}
	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}
	public OfertaCompra getOfertacompra() {
		return ofertacompra;
	}
	public void setOfertacompra(OfertaCompra ofertacompra) {
		this.ofertacompra = ofertacompra;
	}
	public void setAnuncio(List<Anuncio> anuncio) {
		this.anuncio = anuncio;
	}
	public void setMoto(List<Moto> moto) {
		this.moto = moto;
	}
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}

	


