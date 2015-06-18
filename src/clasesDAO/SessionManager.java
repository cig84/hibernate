package clasesDAO;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * 
 * @author Francesco
 * Esta clase permite crear un pool de conesiones con la base de datos. Cada vez que un servicio necesita
 * de una conección la pide a esta clase llamando al metodo obtener sesión. Al final se puede cerrar la 
 * sesión con el método cerrar. 
 * El pool de conecciones se pon een un bloque static como si fuera un Patron Singleton, 
 * así que se puede pedir sólo una SessionFactory a la vez con el método getSessionFactory().
 *
 */
public class SessionManager {
	
	private final static Logger log = Logger.getLogger("mylog");
	
	static {
		try {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		factory = configuration.buildSessionFactory(builder.build());
		}
		catch(Exception e){
			log.error("Error al instanciar la SessionFactory");
			e.printStackTrace();
		}
	}
	
	private static SessionFactory factory;
	
	private SessionManager() {
		
	}
	
	public static SessionFactory getSessionFactory() {
		log.info("Instancia de una SessionFactory");
		return factory;
	}
	
	public static Session obtenerSession() {
		log.info("Instancia de una sessión");
		return factory.openSession();
	}
	
	public static void cerrar(Session session) {
		log.info("Cierre de la sessión");
		session.close();
	}

}
