package mains;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import clasesDAO.EmployeesDAO;
import clasesDAO.SessionManager;
import clasesServices.EmployeesServices;
import dataBaseHR.Departments;
import dataBaseHR.Employees;

public class TestMainEmplMaxPagadosDptId {

	@Test
	public void testMain() {
		
		Session session = null;
		Session session2 = null;
		List<Employees> lista = null;
		int department_id = 110;
		
		try {
			session = SessionManager.obtenerSession();
			EmployeesDAO eDAO = new EmployeesDAO();
			eDAO.setSession(session);
			
			lista = eDAO.empleadosMejorPagados();
			Iterator iterator = lista.iterator();
			Employees emp = new Employees();
			
			while(iterator.hasNext() && emp.getDepartments().getDepartmentId()==100){
				emp = (Employees) iterator.next();	
			}
			
			session2 = SessionManager.obtenerSession();
			eDAO.setSession(session2);
			
			Employees e2 = (Employees) read(eDAO, department_id);
			System.out.println(e2.toString());
			
			assertEquals(e2.getSalary(), emp.getSalary());
			
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		finally {
			SessionManager.getSessionFactory().close();
		}
	}

	private Employees read(EmployeesDAO eDAO, int department_id) {
		
		Employees emp = (Employees) eDAO.getSession().createSQLQuery("SELECT * FROM employees WHERE " + department_id + "AND salary = (SELECT MAX(salary) FROM employees WHERE department_id=" + department_id).addEntity(Employees.class).uniqueResult();
		return emp;
	}

}
