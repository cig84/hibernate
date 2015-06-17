package mains;

import java.util.List;

import clasesDAO.SessionManager;
import clasesServices.EmployeesServices;
import dataBaseHR.Employees;

public class MainObtenerEmpleadosPorDepartamento {

	/**
	 * @param args
	 * Este programa hace una consulta en la base de datos para sacar la lista de empleados por un 
	 * departamento dado y la mostra. El método obtenerEmplPorDept recive el "id" del departamento del
	 * cual se requiere la lista de empleados. Al final se cierra la factory utilizada para obtener conecciónes 
	 * a la base de datos.
	 */
	public static void main(String[] args) {
		
		EmployeesServices empServ = new EmployeesServices();
		List<Employees> lista = empServ.obtenerEmplPorDept(100);
		empServ.mostrarEmpleados(lista);
		SessionManager.getSessionFactory().close();

	}

}
