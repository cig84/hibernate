package mains;

import org.apache.log4j.Logger;

import clasesDAO.SessionManager;
import clasesServices.EmployeesServices;

public class MainIncrementarSalarioTodos {
	
	private final static Logger log = Logger.getLogger("mylog");

	/**
	 * @param args
	 * Este programa hace una consulta en la base de datos para sacar la lista de empleados almacenados 
	 * en la tabla Employees. Al final se cierra la factory utilizada para obtener conecciónes 
	 * a la base de datos.
	 */
	public static void main(String[] args) {
		
		log.info("Ejecución del programa");
		
		EmployeesServices empServ = new EmployeesServices();
		empServ.incrementarSalario();
		SessionManager.getSessionFactory().close();

	}

}
