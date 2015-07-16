package clasesDAO;

import java.util.List;

import org.apache.log4j.Logger;
import dataBaseHR.Employees;

/**
 * 
 * @author Francesco
 * En esta clase se definen los metodos para acceder a la base de datos. Esta clase implementa la
 * interfaz "interfazEmployeeDAO y extiende la super clase "SuperClaseDAO".
 *
 */

public class EmployeesDAO extends SuperClaseDAO implements InterfazEmployeeDAO {
	
	private final static Logger log = Logger.getLogger("mylog");
	
	public Employees obtenerEmpleadoPorId(int id) {
		
		log.info("Ejecución de la consulta 'obtenerEmpleadoPorId'");
		Employees emp = (Employees) getSession().createSQLQuery(Query.obtenerEmpleadoPorId + id).addEntity(Employees.class).uniqueResult();
		return emp;
	}
	public List<Employees> obtenerEmpleados() {
		
		log.info("Ejecución de la consulta 'obtenerEmpleados'");
		@SuppressWarnings("unchecked")
		List<Employees> list = getSession().createSQLQuery(Query.obtenerEmpleados).addEntity(Employees.class).list();
		return list;
	}
	
	public List<Employees> empleadosMejorPagados() {
		
		log.info("Ejecución de la consulta 'empleadosMejorPagados'");
		@SuppressWarnings("unchecked")
		List<Employees> list = getSession().createSQLQuery(Query.empleadosMejorPagados).addEntity(Employees.class).list();
		return list;
	}
	
	public List<Employees> empleadosPorDept(int id) {
		
		log.info("Ejecución de la consulta 'empleadosPorDept'");
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
		Employees emp = (Employees) getSession().createSQLQuery("SELECT * from HR.EMPLOYEES where EMPLOYEE_ID = " + id).addEntity(Employees.class).uniqueResult();
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
