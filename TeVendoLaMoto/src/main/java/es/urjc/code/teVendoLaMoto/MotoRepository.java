package es.urjc.code.teVendoLaMoto;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MotoRepository extends JpaRepository<Moto, Long> {
	
	Moto findByMatricula(String matricula);
	Moto findByMarca(String marca);
	Moto findById(long id);
	Moto findByCilindrada( int cilindrada);

}
