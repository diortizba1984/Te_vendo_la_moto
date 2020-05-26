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
public class OfertaCompra {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    
    private String fOferta;
    private Double precioOferta;
    

    @OneToOne 
     private User comprador;
    
    @OneToOne
    private User vendedor;
    
	@OneToOne 
	private Moto moto;
	
	protected OfertaCompra() {}
	public OfertaCompra(String fOferta, Double precioOferta) {
		
		super();
		this.fOferta = fOferta;
		this.precioOferta = precioOferta;
		this.comprador = new User();
		this.vendedor = new User();
		this.moto = new Moto();
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getFechaOferta() {
		return fOferta;
	}


	public void setFechaOferta(String fOferta) {
		this.fOferta = fOferta;
	}


	public Double getPrecioOferta() {
		return precioOferta;
	}


	public void setPrecioOferta(Double precioOferta) {
		this.precioOferta = precioOferta;
	}


	public User getComprador() {
		return comprador;
	}


	public void setComprador(User comprador) {
		this.comprador = comprador;
	}
	
	public User getVendedor() {
		return vendedor;
	}


	public void setVendedor(User vendedor) {
		this.vendedor = vendedor;
	}


	public Moto getMoto() {
		return moto;
	}


	public void setMoto(Moto moto) {
		this.moto = moto;
	}
	
	@Override
	public String toString() {
		return "Oferta [Fecha Oferta=" + fOferta + ", precioOferta=" + precioOferta + " " + moto +"]";
	}
    
}
