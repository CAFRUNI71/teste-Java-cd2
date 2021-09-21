package br.com.cd2.SBackend.rest;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.cd2.SBackend.interfaces.CepService;
import br.com.cd2.SBackend.model.Consultas;
import br.com.cd2.SBackend.model.Endereco;
import br.com.cd2.SBackend.repository.ConsultasRepository;

@RestController
public class CepRestService {
	@Autowired 
	private ConsultasRepository consultasRepository;
	
	@Autowired
	private CepService cepService;
	   
	@GetMapping(path="/{peso}/{cepOrigem}/{cepDestino}/{nomeDestinatario}")
	public String getCep(@PathVariable double peso, @PathVariable String cepOrigem, @PathVariable String cepDestino, @PathVariable String nomeDestinatario) {

		String endOrigem;
		String endDestino;
		String dddOrigem;
		String dddDestino;
		String estadoOrigem;
		String estadoDestino;
		Endereco endereco_Origem;
		Endereco endereco_Destino;
		double pesoTotal = 0.00;
		double vlTotalFrete = 0.00;
		double desc_50 = 0.50;
		double desc_75 = 0.25;	

		endereco_Origem = cepService.buscaEnderecoPorCep(cepOrigem);

		endereco_Destino = cepService.buscaEnderecoPorCep(cepDestino);

	    Consultas registro = new Consultas();
		
		endOrigem = endereco_Origem.getLogradouro();
		endDestino = endereco_Destino.getLogradouro();
		dddOrigem = endereco_Origem.getDdd();
		dddDestino = endereco_Destino.getDdd();
		estadoOrigem = endereco_Origem.getUf();
		estadoDestino = endereco_Destino.getUf();
		
		if (endOrigem.equals(null))
		{
			throw new NullPointerException(); // Endereço de origem não existe 
		}
		else if (endDestino.equals(null))
		{
			throw new NullPointerException(); // Endereço de destino não existe.
		}
		else
		{
			System.out.println("Calculando valor do frete...\n");
		}
		
		pesoTotal = peso;
		
		if (dddOrigem.equals(dddDestino))
		{//DDD iguais
			vlTotalFrete = pesoTotal*desc_50;
			registro.setPesoEnvio(pesoTotal);
			registro.setDataConsulta(LocalDate.now());
			registro.setDataPrevistaEntrega(LocalDate.now().plusDays(1));
			registro.setName(nomeDestinatario); 
			registro.setCepDestino(cepDestino);
			registro.setCepOrigem(cepOrigem); 
			registro.setVlTotalFrete(vlTotalFrete);
			consultasRepository.save(registro);
			return "Valor do frete : " + vlTotalFrete + "\nData prevista de entrega: " + LocalDate.now().plusDays(1) + "\nCep de origem: " + endereco_Origem.getCep() + "\nCep de destino: " + endereco_Destino.getCep();
		}
		else if (estadoOrigem.equals(estadoDestino))
		{//Mesmo Estado mas DDDs diferentes
			vlTotalFrete = pesoTotal*desc_75;
			registro.setPesoEnvio(pesoTotal);
			registro.setDataConsulta(LocalDate.now());
			registro.setDataPrevistaEntrega(LocalDate.now().plusDays(3));			
			registro.setName(nomeDestinatario); 
			registro.setCepDestino(cepDestino);
			registro.setCepOrigem(cepOrigem); 
			registro.setVlTotalFrete(vlTotalFrete);
			consultasRepository.save(registro);
			return "Valor do frete : " + vlTotalFrete + "\nData prevista de entrega: " + LocalDate.now().plusDays(3) + "\nCep de origem: " + endereco_Origem.getCep() + "\nCep de destino: " + endereco_Destino.getCep();
		}
		else
		{//Estados diferentes
			vlTotalFrete = pesoTotal;
			registro.setPesoEnvio(pesoTotal);
			registro.setDataConsulta(LocalDate.now());
			registro.setDataPrevistaEntrega(LocalDate.now().plusDays(10));
			registro.setName(nomeDestinatario); 
			registro.setCepDestino(cepDestino);
			registro.setCepOrigem(cepOrigem); 
			registro.setVlTotalFrete(vlTotalFrete);
			consultasRepository.save(registro);
			return "Valor total do frete: " + vlTotalFrete + "\nData prevista de entrega: " + LocalDate.now().plusDays(10) + "\nCep de origem: " + endereco_Origem.getCep() + "\nCep de destino: " + endereco_Destino.getCep();
		}
	
	}

}
