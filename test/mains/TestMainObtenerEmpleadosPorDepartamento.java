package mains;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.Is.*;
import org.hibernate.Session;
import org.junit.Test;

import clasesDAO.EmployeesDAO;
import clasesDAO.SessionManager;

import dataBaseHR.Employees;

/**
 * 
 * @author Francesco
 * Esta clase es un Test de la clase MainObtenerEmpleadosPorDepartamento.
 * De primero se recoge la lista de empleados con "DepartmentID" igual a 110 y despues se recorre
 * la lista para comprobar que cada empleado en ella tiene 110 como valor de "DepartmentId".
 *
 */

public class TestMainObtenerEmpleadosPorDepartamento {

	@Test
	public void testMainObtenerEmpleadosPorDepartamento() {

		Session session = null;
		List<Employees> lista = null;
		short department_id = 110;
		
		try {
			session = SessionManager.obtenerSession();
			EmployeesDAO eDAO = new EmployeesDAO();
			eDAO.setSession(session);
			lista = eDAO.empleadosPorDept(department_id);
			SessionManager.cerrar(session);
			
			Iterator iterator = lista.iterator();
			Employees emp = new Employees();
			
			while(iterator.hasNext() ){
				emp = (Employees) iterator.next();
				assertEquals(department_id, emp.getDepartments().getDepartmentId());
				assertThat(emp.getDepartments().getDepartmentId(), is(department_id));
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		finally {
			SessionManager.getSessionFactory().close();
		}
	
	}
	
}
