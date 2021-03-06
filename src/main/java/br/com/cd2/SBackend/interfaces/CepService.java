package br.com.cd2.SBackend.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cd2.SBackend.model.Endereco;

@FeignClient(url= "https://viacep.com.br/ws/" , name = "viacep")
public interface CepService {
	@RequestMapping("{cep}/json")
    Endereco buscaEnderecoPorCep(@PathVariable("cep") String cep);
}
