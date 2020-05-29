package Dao;


import entities.Alumno;
import util.Conexion;

public class alumnoDao extends Conexion<Alumno> implements GenericDao<Alumno> {
	public alumnoDao() {
		super (Alumno.class);
	}

}
