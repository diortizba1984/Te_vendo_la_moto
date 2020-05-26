package es.urjc.code.teVendoLaMoto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta,Long> {

	Venta findByMoto(Moto moto);
	//Venta findByNombre (String nombre);

}

