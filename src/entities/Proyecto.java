package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the proyecto database table.
 * 
 */
@Entity
@NamedQuery(name="Proyecto.findAll", query="SELECT p FROM Proyecto p")
public class Proyecto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String nombre;

	@Lob
	private String resumen;

	private String video;

	//bi-directional many-to-many association to Alumno
	@ManyToMany
	@JoinTable(
		name="participa"
		, joinColumns={
			@JoinColumn(name="proyecto")
			}
		, inverseJoinColumns={
			@JoinColumn(name="alumno")
			}
		)
	private List<Alumno> alumnos;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="categoria")
	private Categoria categoriaBean;

	//bi-directional many-to-one association to Asignatura
	@ManyToOne
	@JoinColumn(name="asignatura")
	private Asignatura asignaturaBean;

	//bi-directional many-to-one association to Tipo
	@ManyToOne
	@JoinColumn(name="tipo")
	private Tipo tipoBean;

	//bi-directional many-to-one association to Evento
	@ManyToOne
	@JoinColumn(name="evento")
	private Evento eventoBean;

	public Proyecto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getResumen() {
		return this.resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public String getVideo() {
		return this.video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public List<Alumno> getAlumnos() {
		return this.alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public Categoria getCategoriaBean() {
		return this.categoriaBean;
	}

	public void setCategoriaBean(Categoria categoriaBean) {
		this.categoriaBean = categoriaBean;
	}

	public Asignatura getAsignaturaBean() {
		return this.asignaturaBean;
	}

	public void setAsignaturaBean(Asignatura asignaturaBean) {
		this.asignaturaBean = asignaturaBean;
	}

	public Tipo getTipoBean() {
		return this.tipoBean;
	}

	public void setTipoBean(Tipo tipoBean) {
		this.tipoBean = tipoBean;
	}

	public Evento getEventoBean() {
		return this.eventoBean;
	}

	public void setEventoBean(Evento eventoBean) {
		this.eventoBean = eventoBean;
	}

}