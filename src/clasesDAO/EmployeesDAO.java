package clasesDAO;

import java.util.List;

import dataBaseHR.Employees;

/**
 * 
 * @author Francesco
 * En esta clase se definen los metodos para acceder a la base de datos. Esta clase implementa la
 * interfaz "interfazEmployeeDAO y extiende la super clase "SuperClaseDAO".
 *
 */

public class EmployeesDAO extends SuperClaseDAO implements InterfazEmployeeDAO {
	
	public List<Employees> obtenerEmpleados() {
		
		@SuppressWarnings("unchecked")
		List<Employees> list = getSession().createSQLQuery(Query.obtenerEmpleados).addEntity(Employees.class).list();
		return list;
	}
	
	public List<Employees> empleadosMejorPagados() {
		
		@SuppressWarnings("unchecked")
		List<Employees> list = getSession().createSQLQuery(Query.empleadosMejorPagados).addEntity(Employees.class).list();
		return list;
	}
	
	public List<Employees> empleadosPorDept(int id) {
		
		@SuppressWarnings("unchecked")
		List<Employees> list = getSession().createSQLQuery(Query.empleadosPorDept + id).addEntity(Employees.class).list();
		return list;
	}

	@Override
	public Object create(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employees read(int id) {
		Employees emp = (Employees) getSession().createSQLQuery("SELECT * from EMPLOYEES where EMPLOYEE_ID = " + id).addEntity(Employees.class).uniqueResult();
		return emp;
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

}
