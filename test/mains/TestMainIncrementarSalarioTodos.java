package mains;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.junit.Test;

import clasesDAO.EmployeesDAO;
import clasesDAO.SessionManager;
import clasesServices.EmployeesServices;

import dataBaseHR.Employees;

/**
 * 
 * @author Francesco
 * Esta clase es un Test de la clase MainIncrementarSalarioTodos.
 * Se ejecutan 3 operaciones con la base de datos:
 * 1) recoger el empleado con "id" 150;
 * 2) incrementar el salario de todos los empleados;
 * 3) recoger el mismo empleado después de la actualización de los salarios.
 * Por cada operación hay que obtener una sesión y después cerrarla.
 * Una vez recogido los dos registros y almacenados en dos objetos de tipo Employees, se confronta el
 * valor del salario inicial multiplicado por el porcentaje de subida con del salario actualizado 
 * para comprobar que los dos valores coinciden.
 *
 */

public class TestMainIncrementarSalarioTodos {

	@Test
	public void testMainIncrementarSalarioTodos() {
		
		Session session = null;
		Session session2 = null;
		try {
			session = SessionManager.obtenerSession();
			EmployeesDAO eDAO = new EmployeesDAO();
			eDAO.setSession(session);
			
			Employees e1 = (Employees) eDAO.read(150);
			System.out.println(e1.toString());
			SessionManager.cerrar(session);
			
			EmployeesServices empServ = new EmployeesServices();
			empServ.incrementarSalario();
			
			session2 = SessionManager.obtenerSession();
			eDAO.setSession(session2);
			Employees e2 = (Employees) eDAO.read(150);
			System.out.println(e2.toString());
			
			assertEquals(e1.getSalary().multiply(new BigDecimal(1.2)).intValue(), e2.getSalary().intValue());
			assertTrue(e2.getSalary().intValue() == (e1.getSalary().multiply(new BigDecimal(1.2)).intValue()));
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		finally {
			SessionManager.getSessionFactory().close();
		}
		
	}

}
