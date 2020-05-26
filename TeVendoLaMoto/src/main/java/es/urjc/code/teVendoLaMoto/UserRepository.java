package es.urjc.code.teVendoLaMoto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

//import antlr.collections.List;

public interface UserRepository extends JpaRepository<User, Long> {
	User findById(long id);
	//List<User> findByDni(String dni);
	User findByName(String name);
	//User findByNombre_completo (String nombre_completo);
	User findByEmail (String email);
	
	List<User> findByDni(String dni);
	
	Page<User> findAll(Pageable page);
	//User deleteByDni (List<User> user); 
	
	//List<User> findByEmailIgnoreCase(String email);
	
}
