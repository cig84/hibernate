package mains;

import java.util.List;

import org.apache.log4j.Logger;

import clasesDAO.SessionManager;
import clasesServices.EmployeesServices;
import dataBaseHR.Employees;

public class MainEmplMaxPagadosDptId {
	
	private final static Logger log = Logger.getLogger("mylog");

	/**
	 * @param args
	 * Este programa hace una consulta en la base de datos para sacar la lista de empleados mejor pagados
	 * opr cada departamento y la mostra. Al final se cierra la factory utilizada para obtener conecciónes 
	 * a la base de datos.
	 */
	public static void main(String[] args) {
		
		log.info("Ejecución del programa");
		
		EmployeesServices empServ = new EmployeesServices();
		List<Employees> lista = empServ.obtenerEmpMejorPagados();
		empServ.mostrarEmpleados(lista);
		SessionManager.getSessionFactory().close();

	}

}
