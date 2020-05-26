package es.urjc.code.teVendoLaMoto;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUsersLoader {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void initDatabase() {

    	//userRepository.save(new User("user", "pass","nombre usuario de prueba", "DNI usuario de prueba", "e-mail de usuario de prueba", "telefono de usuario de prueba","ROLE_USER"));
		//userRepository.save(new User("admin", "adminpass","nombre admin de prueba", "DNI admin de prueba", "e-mail de admin de prueba", "telefono de admin de prueba", "ROLE_USER", "ROLE_ADMIN"));
    }

}
