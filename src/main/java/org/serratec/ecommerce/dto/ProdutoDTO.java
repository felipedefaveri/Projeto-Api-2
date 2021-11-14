package org.serratec.ecommerce.dto;

import java.math.BigDecimal;

import org.serratec.ecommerce.domain.Produto;

public class ProdutoDTO {
	private String nome;
	private String descricao;
	private BigDecimal valor;
	private CategoriaDTO categoriaDTO;
	
	public ProdutoDTO() {
		
	}

	public ProdutoDTO(String nome, String descricao, BigDecimal valor, CategoriaDTO categoriaDTO) {
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.categoriaDTO = categoriaDTO;
	}
	
	public ProdutoDTO(Produto produto) {
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.valor = produto.getValor();
		this.categoriaDTO = new CategoriaDTO(produto.getCategoria());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public CategoriaDTO getCategoriaDTO() {
		return categoriaDTO;
	}

	public void setCategoriaDTO(CategoriaDTO categoriaDTO) {
		this.categoriaDTO = categoriaDTO;
	}
}
