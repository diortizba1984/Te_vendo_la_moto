package es.urjc.code.teVendoLaMoto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;

@Configuration
@ComponentScan()
@SpringBootApplication
@EnableHazelcastHttpSession
@EnableCaching

public class TeVendoLaMotoApplication {

	private static final Log LOG = LogFactory.getLog(TeVendoLaMotoApplication.class);	
	
	public static void main(String[] args) {
		SpringApplication.run(TeVendoLaMotoApplication.class, args);
	}
	/*Configuracion de la cache para reducir las consultas a la BBDD*/
	
	@Bean
		public CacheManager cacheManager() {
		LOG.info("Activating cache...");
		return new ConcurrentMapCacheManager("anuncios");
	}
	/*Configuracion de Hazelcast para que pueda conocer todas las instancias*/ 
	@Bean
	 public Config config() {
			Config config = new Config();
			JoinConfig joinConfig = config.getNetworkConfig().getJoin();
			joinConfig.getMulticastConfig().setEnabled(false);
			joinConfig.getTcpIpConfig().setEnabled(true).setMembers(Collections.singletonList("127.0.0.1"));
	 return config;
	}
}
