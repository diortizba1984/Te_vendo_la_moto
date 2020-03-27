package es.urjc.code.teVendoLaMoto;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;



@Entity

public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private long id;
	    
    private String matricula;
    private String marca;
    private String modelo;
    private String color;
    private int cilindrada;
    private double kilometros;
    private int anioMatriculacion;
    private double precio;
    
	@OneToOne
	private User propietario;
    
    protected Moto(){}

    public Moto(String matricula, String marca, String modelo, String color,int cilindrada, double kilometros, int anioMatriculacion, double precio, User propietario)
    {
    	super();
    	this.matricula = matricula;
        this.marca = marca;
        this.color = color;
        this.cilindrada = cilindrada;
        this.kilometros = kilometros;
        this.anioMatriculacion = anioMatriculacion;
        this.precio = precio;
        this.propietario = propietario;
    }
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
      

    public int getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}

	public double getKilometros() {
        return kilometros;
    }

    public void setKilometros(double kilometros) {
        this.kilometros = kilometros;
    }

    public int getAnioMatriculacion() {
        return anioMatriculacion;
    }

    public void setAnioMatriculacion(int anioMatriculacion) {
        this.anioMatriculacion = anioMatriculacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public User getPropietario() {
        return propietario;
    }

    public void setPropietario(User propietario) {
        this.propietario = propietario;
    }
       
	@Override
	public String toString() {
		return "Coche [Matricula=" + matricula + ", marca=" + marca + ", color=" + color + ", kilometros=" + kilometros + ", Año Matriculacion=" + anioMatriculacion + 
				", Precio=" + precio +"]";
	}
	
}