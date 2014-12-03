package br.com.fiap.precoauto.VO;

import java.io.Serializable;

public class Versao implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7448160214211036240L;
	private String id;
	private String nome;
	private String modelo;
	private String valor;
	private String tipo;
	private String ref;
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
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	
	

}
