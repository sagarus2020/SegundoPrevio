package Dao;


import entities.Evento;
import util.Conexion;
import util.GenericDao;

public class eventoDao extends Conexion<Evento> implements GenericDao<Evento> {
	public eventoDao() {
		super (Evento.class);
	}

}