package es.urjc.code.teVendoLaMoto;


import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.code.teVendoLaMoto.Anuncio;
import es.urjc.code.teVendoLaMoto.AnuncioRepository;
import es.urjc.code.teVendoLaMoto.Moto;
import es.urjc.code.teVendoLaMoto.MotoRepository;
import es.urjc.code.teVendoLaMoto.User;
import es.urjc.code.teVendoLaMoto.UserRepository;



	
	@Controller
	//@RestController
	public class TablonController {
		
		//Si se abre la URL http://127.0.0.1:8080/h2-console y se configura
		//la URL JDBC con el valor jdbc:h2:mem:testdb se puede acceder a la 
		//base de datos de la aplicación

		@Autowired
		private AnuncioRepository repository;
		
		@Autowired
		private MotoRepository motoRepository;
		
		@Autowired
		private UserRepository usuarioRepository;
		
		@Autowired
		private OfertaCompraRepository ofertaCompraRepository;
		
		
		@Autowired
		private VentaRepository ventaRepository;

		@PostConstruct
		public void init() {
			
			/*Rellenamos la base con un administrador, dos usuarios que venden y un usuario que va a realizar la compra*/
			
			
			User u1 = new User("dortiz","dortizpass","Damian Ortiz Barahona","50574692D","demn007@gmail.com","648799392","ROLE_USER");
			User u2 = new User("prodriguez","prodriguezpass","Paula Rodriguez de Zoluaga","73263327S","prr.zoluaga@wanadoo.com","64537872", "ROLE_USER");
			User u3 = new User("btercero","bterceropass","Benedicto tercero","59563719W","benedict@plus.com","634522718","ROLE_USER"); 
			User u4 = new User("admin","adminpass","Administrador_General","55555566N","admin@admin.com","739993816","ROLE_USER", "ROLE_ADMIN");
			User u5 = new User("armando","armandopass","Armando Elslam ","83826171S","sta@plas.com","7651591","ROLE_USER");
			
		
			/*Insertamos 2 motos*/
			
			Moto c1 = new Moto("0830DTJ", "YAMAHA","YZF-R6","NEGRA",600,30.000,2009,12000.00,u5);
			Moto c2 = new Moto("2052GCP", "HONDA","CBR1000RR Fireblade","BLANCO",250,10.000,2011,15000.00,u2);
			
			
			/*Persisto a  los usuarios*/
			

			usuarioRepository.save(u1);
			usuarioRepository.save(u2);
			usuarioRepository.save(u3);
			usuarioRepository.save(u4);
			usuarioRepository.save(u5);
			
	        /*Persisto las motos */
			
			motoRepository.save(c1);
			motoRepository.save(c2);
			
			/*Creamos anuncios y los asignamos a los usuarios*/
			
			Anuncio a1 = new Anuncio("Moto Yamaha 600cc","en prefecto estado");
			Anuncio a2 = new Anuncio("Moto Honda 250cc","todas las revisiones hechas");
			a1.setUsuario(u1);
			a2.setUsuario(u2);
			a1.setMoto(c1);
			a2.setMoto(c2);
			
			/*Creamor una oferta de compra */
			
			OfertaCompra comp1 = new OfertaCompra("17/03/2019", 10.000);
			comp1.setMoto(c1);
			comp1.setComprador(u1);
			comp1.setVendedor(c1.getPropietario());
			
			ofertaCompraRepository.save(comp1);
			
			//Creamos Oferta
			
			OfertaCompra comp2 = new OfertaCompra("04/04/2019", 17.400);
			comp2.setMoto(c2);
			comp2.setComprador(u2);
			comp2.setVendedor(c2.getPropietario());
			
			ofertaCompraRepository.save(comp2);
			
			//Guardamos los anuncios p1,p2,p3
			repository.save(a1);
			repository.save(a2);
			
			
		}
		

		@RequestMapping("/")
		public String tablon(Model model, Pageable page, HttpServletRequest request) {

			//String nombre = request.getUserPrincipal().getName();
			model.addAttribute("anuncios", repository.findAll(page));
			model.addAttribute("usuarios", usuarioRepository.findAll(page));
			model.addAttribute("usuariosCounter", usuarioRepository.count());
			//model.addAttribute("usuarios",usuarioRepository.findByEmail(nombre));
			model.addAttribute("motos", motoRepository.count());
			model.addAttribute("ofertaCompra", ofertaCompraRepository.count());
			model.addAttribute("venta", ventaRepository.count());
			model.addAttribute("user", request.isUserInRole("USER"));
			model.addAttribute("admin", request.isUserInRole("ADMIN"));
			
			return "index";
		}
		
	  
		
		@RequestMapping("/ver_ofertas")
		public String tablon(Model model,HttpServletRequest request) {
			String nombre = request.getUserPrincipal().getName();
			User u = usuarioRepository.findByName(nombre);
			model.addAttribute("ofertas", ofertaCompraRepository.findByVendedor(u));
			
			return "ver_ofertas";
		}

		@RequestMapping("/anuncio/nuevo")
		public String nuevoAnuncio(Model model, @RequestParam String nombre, 
				@RequestParam String asunto,@RequestParam String matricula,@RequestParam String marca,
				@RequestParam String modelo,@RequestParam String color,	@RequestParam int cilindrada, @RequestParam double kilometros,@RequestParam int anioMatriculacion,
				@RequestParam double precio,HttpServletRequest request) {

			User u = usuarioRepository.findByName(request.getUserPrincipal().getName());
			
			
			
			Moto c = new Moto(matricula, marca,modelo,color,cilindrada,kilometros,anioMatriculacion,precio,u);
			motoRepository.save(c);
			
			Anuncio a = new Anuncio(nombre,asunto);
			
			a.setMoto(c);
			a.setUsuario(u);
			
			repository.save(a);
			
			return "anuncio_guardado";

		}
		
		@RequestMapping("/usuario/nuevo")
		public String nuevoUsuario(Model model,@RequestParam String name,@RequestParam String password, @RequestParam String nombre_completo, 
				@RequestParam String dni,@RequestParam String email,@RequestParam String telefono, HttpServletRequest request) {

			if(request.isUserInRole("ADMIN")){
				model.addAttribute("admin", request.isUserInRole("ADMIN"));
			User u = new User(name,password,nombre_completo,dni,email,telefono,"ROLE_USER"," ROLE_ADMIN");
			usuarioRepository.save(u);
			return "usuario_guardado";
					}
			
			else {
				User u = new User(name,password,nombre_completo,dni,email,telefono,"ROLE_USER");
				usuarioRepository.save(u);	
				return "usuario_guardado";
			}
			
	}		
		
		
		
		@RequestMapping("/anuncio/{id}")
		public String verAnuncio(Model model, @PathVariable long id) {
			
			Anuncio anuncio = repository.findById(id);
			Moto moto = anuncio.getMoto();
			model.addAttribute("anuncio", anuncio);
			model.addAttribute("moto", moto);
			return "ver_anuncio";
		}
		
		@RequestMapping("/usuario/{id}")
        public String verUsuario(Model model, @PathVariable long id) {
			
			//model.addAttribute("user", usuarioRepository.findAll(page));
			User usuario = usuarioRepository.findById(id);
			model.addAttribute("usuarios", usuario);
			
			return "ver_usuario";
		}
		
		@RequestMapping(value="/usuario/lista", method=RequestMethod.GET)
        public String listaUsuario(Model model, Pageable page, HttpServletRequest request) {
			
			model.addAttribute("usuarios", usuarioRepository.findAll(page));
			//model.addAttribute("usuarios", usuarioRepository.findAll(page));
			//User usuario = usuarioRepository.findById(id);
			//model.addAttribute("usuarios",usuario);
			return "listaUsuarios";
		}
		
		
		
		
		@RequestMapping("/vender")
		public String comprar(Model model,@RequestParam long idc,@RequestParam long ido) {
			
			OfertaCompra ofe = ofertaCompraRepository.findById(ido);
			Moto moto = motoRepository.findById(idc);
			List<OfertaCompra> lista = ofertaCompraRepository.findByMoto(moto);
			
			//Registramos venta
			Venta venta = new Venta(ofe.getFechaOferta(),ofe.getPrecioOferta());
			venta.setMoto(moto);
			venta.setComprador(ofe.getComprador());
			venta.setVendedor(moto.getPropietario());			
			ventaRepository.save(venta);
			
			//Borramos Anuncio

			Anuncio anu = repository.findByMoto(moto);
			repository.deleteById(anu.getId());
			
			//Borramos Ofertas
			for(OfertaCompra str : lista)
				{
					ofertaCompraRepository.delete(str);
				}
			
			return "venta_realizada";
		}
		
		
		@RequestMapping("/anuncio/oferta")
		public String nuevosAnuncio(Model model, @RequestParam String fechaOferta, @RequestParam double precioOferta, @RequestParam long id,HttpServletRequest request ) {

			OfertaCompra ofer = new OfertaCompra(fechaOferta, precioOferta);
			Moto moto3 = motoRepository.findById(id);
			
			ofer.setMoto(moto3);
			ofer.setComprador(usuarioRepository.findByName(request.getUserPrincipal().getName()));
			ofer.setVendedor(moto3.getPropietario());
			moto3.setPropietario(usuarioRepository.findByName(request.getUserPrincipal().getName()));
			moto3.setPrecio(precioOferta);
			ofertaCompraRepository.save(ofer);

			model.addAttribute("moto", moto3);
			
			return "oferta_realizada";

		}
		
		//he retocado para comprobar cambiando a dni, sigue retocado
		
		@RequestMapping(value="/eliminar/usuario", method=RequestMethod.POST)
		public String eliminarUsuario (Model model,  @RequestParam (value="dni") String dni, Pageable page)
		{
			List<User> user = usuarioRepository.findByDni(dni);
			model.addAttribute("usuarios", usuarioRepository.findAll(page));
			//User usuario = usuarioRepository.findById(id);
			model.addAttribute("user", user);
			
			//User usu = usuarioRepository.findByEmail(email);
		     
			usuarioRepository.deleteAll(user);
				
		//	}
			return "usuario_eliminado";
		}
		
			
		@RequestMapping(value="/borrar/anuncio", method=RequestMethod.POST)
		public String eliminaAnuncio(Model model, @RequestParam (value="matricula") String matricula)
		{
			Moto co = motoRepository.findByMatricula(matricula);
			//motoRepository.delete(co.getId());
			Anuncio anu = repository.findByMoto(co);
			//List <Anuncio> anu = (List<Anuncio>) repository.findByMoto(co))
			//Anuncio anu = repository.findByNombre(matricula);
			
			repository.deleteById(anu.getId());
			//motoRepository.deleteById(co.getId());
			//motoRepository.delete(co);
			//repository.deleteAll(anu);
			return "anuncio_eliminado";
			
		}
		
		
	
	}
	


