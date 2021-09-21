package br.com.cd2.SBackend.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Consultas {//extends AbstractEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private double pesoEnvio;
	private double vlTotalFrete;
	private String name;
	private String cepOrigem;
	private String cepDestino;
	private LocalDate dataConsulta;
	private LocalDate dataPrevistaEntrega;
	
	public LocalDate getDataPrevistaEntrega() {
		return dataPrevistaEntrega;
	}

	public void setDataPrevistaEntrega(LocalDate dataPrevistaEntrega) {
		this.dataPrevistaEntrega = dataPrevistaEntrega;
	}

	public LocalDate getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(LocalDate dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public double getPesoEnvio() {
		return pesoEnvio;
	}

	public void setPesoEnvio(double pesoEnvio) {
		this.pesoEnvio = pesoEnvio;
	}
	
	public Long getId() {
	return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
	return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCepDestino() {
		return cepDestino;
	}
	
	public void setCepDestino(String cepDestino) {
		this.cepDestino = cepDestino;
	}
	
	public String getCepOrigem() {
		return cepOrigem;
	}
	
	public void setCepOrigem(String cepOrigem) {
		this.cepOrigem = cepOrigem;
	}
	
	public double getVlTotalFrete() {
		return vlTotalFrete;
	}
	
	public void setVlTotalFrete(double vlTotalFrete) {
		this.vlTotalFrete = vlTotalFrete;
	}
		
}
