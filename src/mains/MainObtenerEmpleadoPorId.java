package mains;

import org.apache.log4j.Logger;
import clasesDAO.SessionManager;
import clasesServices.EmployeesServices;
import dataBaseHR.Employees;

public class MainObtenerEmpleadoPorId {
	
	private final static Logger log = Logger.getLogger("mylog");
	
public static void main(String[] args) {
		
		log.info("Ejecución del programa");
		
		EmployeesServices empServ = new EmployeesServices();
		Employees emp = empServ.obtenerEmpleadoPorId(100);
		empServ.mostrarEmpleado(emp);
		SessionManager.getSessionFactory().close();

	}


}
