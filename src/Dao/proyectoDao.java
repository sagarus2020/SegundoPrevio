package Dao;


import entities.Proyecto;
import util.Conexion;
import util.GenericDao;

public class proyectoDao extends Conexion<Proyecto> implements GenericDao<Proyecto> {
	public proyectoDao() {
		super (Proyecto.class);
	}

}