package clasesDAO;

import java.util.List;

import dataBaseHR.Employees;

/**
 * 
 * @author Francesco
 *Esta clase es una Interfaz que extende la InterfazDAO. Las clases que implementan esta Interfaz tienen
 *que implementar el método "obtenerEmpleados".
 *
 */

public interface InterfazEmployeeDAO extends InterfazDAO {
	
	public List<Employees> obtenerEmpleados();

}
