# API de cálculo de valor de frete e prazo de entrega usando o serviço VIACEP

#### Uso:
O frontend deve enviar uma requisição GET com os parâmetros peso do envio, CEP de origem,
CEP de destino e nome do destinatario. Por sua vez, o backend de posse destes parâmetros
calculará o valor do frete e o prazo de entrega, considerando as seguintes regras para
calcular o valor do frete retornado para o frontend:

- CEPs com DDDs iguais tem 50% de desconto no valor do frete e entrega prevista de 1 dia
- CEPs de estados iguais tem 75% de desconto no valor do frete e entrega prevista de 3 dias
- CEPs de estados diferentes não têm desconto no valor do frete e entrega prevista de 10 dias
- O valor do frete é cobrado pelo peso da encomenda, o valor para cada Kg é de R$ 1,00.

Por exemplo, caso o backend seja executando em sua máquina local na porta 8080 e a data de consulta foi 10/11/2021:

http://host:porta/peso/cep_de_origen/cep_de_destino/nome_do_destinatário

http://localhost:8080/12.1/94850230/95010003/Miguel

### a requisição acima retornará um JSON para o browser onde foi digitada:

{
    "Data prevista de entrega": "2021-11-13",
    "Valor do frete": "3.025",
    "Cep de origem": "94850-230",
    "Cep de destino": "95010-003"
}

### Em um banco de dados relacional no backend ficam gravados:

- peso
- CEP de origem
- CEP de destino
- nome do destinatario
- valor total do frete
- data prevista de entrega
- data da consulta

