package br.com.fiap.precoauto.VO;
import java.util.Map;

public class Historico {
	private Modelo modelo;
	private Marca marca;
	private Map<String, String> historico;

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Map<String, String> getHistorico() {
		return historico;
	}

	public void setHistorico(Map<String, String> historico) {
		this.historico = historico;
	}

}
