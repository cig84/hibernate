package clasesDAO;

import dataBaseHR.Employees;

/**
 * 
 * @author Francesco
 * Esta clase es una Interfaz que implementa los metodos CRUD que todas las clases DAO necesitan para
 * ejecutar las operaciones de crear, leer, actualizar y borrar en la base de datos. 
 *
 */

public interface InterfazDAO {
	
	public Object create(Object obj);
	public Employees read(int id);
	public void update();
	public void delete();

}
