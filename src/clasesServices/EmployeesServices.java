package clasesServices;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import clasesDAO.EmployeesDAO;
import clasesDAO.SessionManager;
import dataBaseHR.Employees;

/**
 * 
 * @author Francesco
 * En esta clase declaramos los métodos a los cuales el usuario puede acceder.
 * Esta clase inicializa un objeto EmployeesDAO que tiene acceso a la base de datos a través de los
 * métodos declarados en la misma clase.
 * Esta clase es la que se encarga de obtener una conección a la base de datos llamando el metodo
 * correspondiente de la clase SessionManager, de declarar el inicio de una transacción y setear
 * la sessión abierta para el objeto EmployeesDAO creado. También se encarga de hacer el commit o
 * el roolback de la transaccíon si hay algún fallo en la ejecución, así como de cerrar la sessión 
 * cuando el metodo llamado haya terminado su ejecución, con o sin fallos.
 *
 */
public class EmployeesServices {
	
	private static EmployeesDAO empDAO;
	private final static Logger log = Logger.getLogger("mylog");
	
	public EmployeesServices() {
		empDAO = new EmployeesDAO();
	}
	
	public Employees obtenerEmpleadoPorId(int id) {
		/*
		 * Este método devuelve unobjeto Employees que almacena lo que devuelve el método
		 * obtenerEmpleadoPorId() llamado por el objeto emp.DAO. El resultado será el empleado
		 * con el determinado id. El "id" del empleado será el parametro que pasaremos al método empleadosPorDept.
		 */
		Session session = null;
		Transaction trans = null;
		Employees emp = null;
		EmployeesDAO empDAO = new EmployeesDAO();
		try {
			session = SessionManager.obtenerSession();
			trans = session.beginTransaction();
			empDAO.setSession(session);
			emp = empDAO.obtenerEmpleadoPorId(id);
			trans.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			trans.rollback();
		}
		finally {
			SessionManager.cerrar(session);

		}
		return emp;
	}

	public boolean incrementarSalario(){
		/*
		 * Este método incrementa el salario de todos los dependientes en la tabla Employees
		 * en la base de datos. Para hacer esto después de haber configurado la connección
		 * con la base de datos, llama al metodo obtenerEmpleados() del objeto empDAO, almacena el
		 * resultado en una lista de empleados y actualiza el salario de los empleados llamando el 
		 * metodo actualizarSalario() de la propia clase.
		 */
		
		log.info("Ejecución del método 'incrementarSalario'");
		boolean ok = false;
		Session session = null;
		Transaction trans = null;
		List<Employees> listaEmp = null;
		try {
			session = SessionManager.obtenerSession();
			trans = session.beginTransaction();
			empDAO.setSession(session);
			listaEmp = empDAO.obtenerEmpleados();
			mostrarEmpleados(listaEmp);
			actualizarSalario(listaEmp);
			mostrarEmpleados(listaEmp);
			trans.commit();
			ok = true;
		}
		catch(Exception e) {
			log.error("Hay un error en la ejecución del método");
			e.printStackTrace();
			trans.rollback();
		}
		finally {
			SessionManager.cerrar(session);

		}
		return ok;
	}
	
	public List<Employees> obtenerEmpMejorPagados(){
		/*
		 * Este metodo devuelve una lista de empleados que almacena lo que devuelve el método 
		 * empleadosMejorPagados() llamado por el objeto empDAO. El resultado será la lista 
		 * de los empleados mejor pagados de cada departamento.
		 */
		log.info("Ejecución del método 'obtenerEmpMejorPagados'");
		Session session = null;
		Transaction trans = null;
		List<Employees> listaEmp = null;
		try {
			session = SessionManager.obtenerSession();
			trans = session.beginTransaction();
			empDAO.setSession(session);
			listaEmp = empDAO.empleadosMejorPagados();
			trans.commit();
		}
		catch(Exception e) {
			log.error("Hay un error en la ejecución del método");
			e.printStackTrace();
			trans.rollback();
		}
		finally {
			SessionManager.cerrar(session);

		}
		return listaEmp;
	}
	
	public List<Employees> obtenerEmplPorDept(int id) {
		/*
		 * Este método devuelve una lista de empleados que almacena lo que devuelve el método
		 * empleadosPorDept() llamado por el objeto emp.DAO. El resultado será la lista de los 
		 * empleados que pertenecen a un determinado departamento. El "id" del departamento será
		 * el parametro que pasaremos al método empleadosPorDept.
		 */
		log.info("Ejecución del método 'obtenerEmplPorDept'");
		Session session = null;
		Transaction trans = null;
		List<Employees> listaEmp = null;
		try {
			session = SessionManager.obtenerSession();
			trans = session.beginTransaction();
			empDAO.setSession(session);
			listaEmp = empDAO.empleadosPorDept(id);
			trans.commit();
		}
		catch(Exception e) {
			log.error("Hay un error en la ejecución del método");
			e.printStackTrace();
			trans.rollback();
		}
		finally {
			SessionManager.cerrar(session);
	
		}
		return listaEmp;
	}
	
	private void actualizarSalario(List<Employees> listaEmpleados) { // no devuelve nada porqué la lista se actualiza
		/*
		 * Este método recive la lista de empleados a los cuales hay que incrementar el salario.
		 * Dado que el salario almacenado en la base de datos es de tipo BigDecimal, para tener un valor
		 * mas preciso, se convierte en un int y se actualiza. Después de convertirlo otra vez en un
		 * BigDecimal, se actualiza el valor de cada objeto de la lista.
		 */
		log.info("Ejecución del método 'actualizarSalario'");
		for(Employees emp : listaEmpleados) {
			int salarioAct = emp.getSalary().multiply(new BigDecimal(1.2)).intValue();
			BigDecimal salarioBig = new BigDecimal(salarioAct);
			emp.setSalary(salarioBig);
			
		}
		
	}
	
	public void mostrarEmpleados(List<Employees> listaEmpleados) {
		/*
		 * Este método recorre una lista y imprime el valor de los objetos almacenados en la lista.
		 */
		log.info("Ejecución del método 'mostrarEmpleados'");
		for(Employees emp : listaEmpleados) {
			System.out.println(emp.toString());
		}
	}
	
	public void mostrarEmpleado(Employees empleado) {
		
		log.info("Ejecución del método 'mostrarEmpleados'");
		System.out.println(empleado.toString());
	}

}
