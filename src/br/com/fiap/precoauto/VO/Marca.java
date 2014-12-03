package br.com.fiap.precoauto.VO;

import java.io.Serializable;

public class Marca implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -645791542122053259L;
	private String id;
	private String nome;
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	

}
