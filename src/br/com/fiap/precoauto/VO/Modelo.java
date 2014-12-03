package br.com.fiap.precoauto.VO;

import java.io.Serializable;

public class Modelo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3016630646467886762L;
	private String id;
	private String nome;
	private String marca;
	private String tipo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
}
