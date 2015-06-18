package mains;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.IsEqual.*;
import org.hibernate.Session;
import org.junit.Test;

import clasesDAO.EmployeesDAO;
import clasesDAO.SessionManager;
import dataBaseHR.Employees;

/**
 * 
 * @author Francesco
 * Esta clase es un Test de la clase MainEmplMaxPagadosDptId.
 * Se ejecutan 2 operaciones con la base de datos:
 * 1) recoger el empleado con el salario mas alto del "DepartmentID" = 110;
 * 3) recoger la lista de empleados mejor pagados por cada departamento.
 * Por cada operación hay que obtener una sesión y después cerrarla.
 * Una vez recogidos el empleado y la lista, esta se recorre hasta incontrar el empleado 
 * con valor "DepartmentID" = 110 y se comprueba que los dos empleados tienen el mismo salario.
 *
 */
public class TestMainEmplMaxPagadosDptId {

	@Test
	public void testMainEmplMaxPagadosDptId() {
		
		Session session = null;
		Session session2 = null;
		List<Employees> lista = null;
		int department_id = 110;
		
		try {
			session = SessionManager.obtenerSession();
			EmployeesDAO eDAO = new EmployeesDAO();
			eDAO.setSession(session);
			lista = eDAO.empleadosMejorPagados();
			
			session2 = SessionManager.obtenerSession();
			eDAO.setSession(session2);
			Employees e = (Employees) read(eDAO, department_id);
			System.out.println(e.toString());
			
			Iterator iterator = lista.iterator();
			Employees emp = new Employees();
			
			while(iterator.hasNext()){
				emp = (Employees) iterator.next();
				if(emp.getDepartments().getDepartmentId() == 110){
					assertEquals(e.getSalary(), emp.getSalary());
					assertThat(e.getSalary(), equalTo(emp.getSalary()));
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		finally {
			SessionManager.getSessionFactory().close();
		}
	}

	private Employees read(EmployeesDAO eDAO, int department_id) {
		
		Employees emp = (Employees) eDAO.getSession().createSQLQuery("SELECT * FROM employees WHERE department_id = " + department_id + " AND salary = (SELECT MAX(salary) FROM employees WHERE department_id = " + department_id + ")").addEntity(Employees.class).uniqueResult();
		return emp;
	}

}
