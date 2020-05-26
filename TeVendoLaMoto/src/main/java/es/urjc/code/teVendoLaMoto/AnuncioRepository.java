package es.urjc.code.teVendoLaMoto;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import antlr.collections.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;


@CacheConfig(cacheNames="anuncios")
public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

	@CacheEvict(allEntries=true)
	Anuncio save(Anuncio anuncio);
	
	@Cacheable
	Anuncio findByNombre(String nombre);
	@Cacheable
	Anuncio findById(long id);
	Anuncio findByAsunto(String asunto);
	Anuncio findByNombreAndAsunto(String nombre, String asunto);
	//List findByMoto(Moto moto);
	Anuncio findByMoto(Moto moto);
	@Cacheable
	Page<Anuncio> findAll(Pageable page);
}
