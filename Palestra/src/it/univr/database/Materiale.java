package it.univr.database;




/**
 * classe che rappresenta i Materiali a disposizione
 * @author Mago
 *
 */
public class Materiale {


    private int id;
	private String path;
	private String nome;
	private String tipo;
	private String formato;



	public Materiale() {


		this.id = 0;
		this.path = null;
		this.nome = null;
		this.tipo = null;
		this.formato = null;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		this.path = path;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public String getFormato() {
		return formato;
	}



	public void setFormato(String formato) {
		this.formato = formato;
	}










}
