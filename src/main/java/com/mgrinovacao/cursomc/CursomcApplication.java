package com.mgrinovacao.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mgrinovacao.cursomc.domain.CategoriaDomain;
import com.mgrinovacao.cursomc.domain.Cidade;
import com.mgrinovacao.cursomc.domain.Cliente;
import com.mgrinovacao.cursomc.domain.Endereco;
import com.mgrinovacao.cursomc.domain.Estado;
import com.mgrinovacao.cursomc.domain.Pagamento;
import com.mgrinovacao.cursomc.domain.PagamentoComBoleto;
import com.mgrinovacao.cursomc.domain.PagamentoComCartao;
import com.mgrinovacao.cursomc.domain.Pedido;
import com.mgrinovacao.cursomc.domain.Produto;
import com.mgrinovacao.cursomc.domain.enums.EstadoPagamento;
import com.mgrinovacao.cursomc.domain.enums.TipoCliente;
import com.mgrinovacao.cursomc.repositories.CategoriaRepository;
import com.mgrinovacao.cursomc.repositories.CidadeRepository;
import com.mgrinovacao.cursomc.repositories.ClienteRepository;
import com.mgrinovacao.cursomc.repositories.EnderecoRepository;
import com.mgrinovacao.cursomc.repositories.EstadoRepository;
import com.mgrinovacao.cursomc.repositories.PagamentoRepository;
import com.mgrinovacao.cursomc.repositories.PedidoRepository;
import com.mgrinovacao.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		CategoriaDomain cat1 = new CategoriaDomain(null, "Informática");
		CategoriaDomain cat2 = new CategoriaDomain(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Santa Catarina");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Itajai", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "mariasilva@gmail.com", "04522369877", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("4730456484", "4758226554"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "904", "esquina com a sete", "Sao Judas", "88348092", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Antonio Heil", "302", "apto 400", "Jardim America", "88456034", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/03/2020 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2019 11:40"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
	}

}
