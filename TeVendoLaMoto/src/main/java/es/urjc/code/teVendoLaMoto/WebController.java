package es.urjc.code.teVendoLaMoto;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController{
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

	
	

	@GetMapping("/login")
    public String login() {
    	return "login";
    }
	//No funciona este método
	/*@RequestMapping (value="/login", method={RequestMethod.GET})
	public String login(Model model, HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		return "login";
	}*/
	
	
	@GetMapping("/nuevoUsuario")
    public String getUsuario(Model model, HttpServletRequest request,Pageable page) {
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
    	return "nuevoUsuario";
    }
    
	
	@GetMapping("/nuevoAnuncio")
    public String getAnuncio(Model model, HttpServletRequest request, Pageable page) {
    	model.addAttribute("admin", request.isUserInRole("ADMIN"));
		return "nuevoAnuncio";
    }
	
	@GetMapping("/borrar_anuncio")
    public String borrarAnuncio(Model model, HttpServletRequest request, Pageable page) {
    	model.addAttribute("admin", request.isUserInRole("ADMIN"));
		return "borrar_anuncio";
    }
	
	@GetMapping("/eliminarUsuario")
    public String eliminarUsuario(Model model, HttpServletRequest request, Pageable page) {
    	model.addAttribute("admin", request.isUserInRole("ADMIN"));
    	model.addAttribute("usuarios", usuarioRepository.findAll(page));
		return "eliminarUsuario";
    }
	
	@GetMapping("/listaUsuarios")
    public String listaUsuarios(Model model, HttpServletRequest request, Pageable page) {
    	model.addAttribute("admin", request.isUserInRole("ADMIN"));
    	model.addAttribute("usuarios", usuarioRepository.findAll(page));
		return "listaUsuarios";
    }
	
	/*@GetMapping("user/listaUsuarios")
    public String listaUsuario(Model model, HttpServletRequest request, Pageable page) {
    	model.addAttribute("admin", request.isUserInRole("ADMIN"));
		return "user/listaUsuarios";
	}*/
    
		
	/*@GetMapping("/borrar_anuncio")
    public String borraranuncio(Model model, HttpServletRequest request, Pageable page) {
    	model.addAttribute("admin", request.isUserInRole("ADMIN"));
		return "borrar_anuncio";
    }*/
	
	//quitar si no funciona
	/*@GetMapping("/verofertas")
    public String verofertas(Model model, HttpServletRequest request, Pageable page) {
    	model.addAttribute("admin", request.isUserInRole("ADMIN"));
		return "ver_ofertas";
    }*/
	
    @GetMapping("/errorlogin")
    public String loginerror() {
    	return "errorlogin";
    }

    @GetMapping("/bienvenidalogin")
    public String bienvenidalogin() {
    	return "bienvenidalogin";
    }
    
    @GetMapping("/logout")
    public String logout() {
    	return "logout";
    }
    
    
    
    
    
    
    @GetMapping("/admin")
	public String index(Model model, HttpServletRequest request,Pageable page) { 
		
    	//String name = request.getUserPrincipal().getName();
    	model.addAttribute("admin", request.isUserInRole("ADMIN"));
    	model.addAttribute("anuncios", repository.findAll(page));
    	//model.addAttribute("usuarios", usuarioRepository.findAll(page));
		model.addAttribute("anunciosCount", repository.count());
		model.addAttribute("usuariosCount", usuarioRepository.count());
		//model.addAttribute("usuarios",usuarioRepository.findByEmail(name));
		model.addAttribute("motos", motoRepository.count());
		model.addAttribute("ofertaCompra", ofertaCompraRepository.count());
		model.addAttribute("venta", ventaRepository.count());
		return "admin";
	
	}
}
