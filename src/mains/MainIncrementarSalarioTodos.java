package mains;

import clasesDAO.SessionManager;
import clasesServices.EmployeesServices;

public class MainIncrementarSalarioTodos {

	/**
	 * @param args
	 * Este programa hace una consulta en la base de datos para sacar la lista de empleados almacenados 
	 * en la tabla Employees. Al final se cierra la factory utilizada para obtener conecciónes 
	 * a la base de datos.
	 */
	public static void main(String[] args) {
		
		EmployeesServices empServ = new EmployeesServices();
		empServ.incrementarSalario();
		SessionManager.getSessionFactory().close();

	}

}
