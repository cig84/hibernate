package clasesDAO;

/**
 * 
 * @author Francesco
 *Esta clase contiene las consultas a la base de datos utilizadas por los métodos de la clase EmployeesDAO. 
 *
 */
public class Query {
	
	static String obtenerEmpleados = new String ("SELECT * FROM HR.EMPLOYEES");
	static String obtenerEmpleadoPorId = new String ("SELECT * from EMPLOYEES where EMPLOYEE_ID = ");
	static String empleadosMejorPagados = new String ("select * from HR.EMPLOYEES where(DEPARTMENT_ID, SALARY) in (select department_id, MAX(salary) from HR.EMPLOYEES group by DEPARTMENT_ID)");
	static String empleadosPorDept = new String ("SELECT * FROM HR.EMPLOYEES where department_id = ");
	

}
