package clasesDAO;

/**
 * 
 * @author Francesco
 *Esta clase contiene las consultas a la base de datos utilizadas por los métodos de la clase EmployeesDAO. 
 *
 */
public class Query {
	
	static String obtenerEmpleados = new String ("SELECT * FROM EMPLOYEES");
	static String empleadosMejorPagados = new String ("select * from EMPLOYEES where(DEPARTMENT_ID, SALARY) in (select department_id, MAX(salary) from EMPLOYEES group by DEPARTMENT_ID)");
	static String empleadosPorDept = new String ("SELECT * FROM EMPLOYEES where department_id = ");
	

}
