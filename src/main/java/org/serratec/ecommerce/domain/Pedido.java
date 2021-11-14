package org.serratec.ecommerce.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    @ApiModelProperty(value = "Identificador único do pedido")
    private Long id;
    
    @Column(name = "data_pedido")
    @ApiModelProperty(value = "Data da efetuação do pedido", required = true)
    private LocalDateTime dataPedido;

    @ApiModelProperty(value = "Valor total dos itens do pedido", required = true)
    private BigDecimal valorTotal;

    @Column(name = "data_envio")
    @ApiModelProperty(value = "Data de envio do pedido", required = true)
    private LocalDateTime dataEnvio;

    @Column(name = "data_entrega")
    @ApiModelProperty(value = "Data da entrega do pedido ao cliente")
    private LocalDateTime dataEntrega;
    
    @ApiModelProperty(value = "Status do pedido", required = true)
    private boolean finalizado;

    public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	@ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    
    @ManyToMany
	@JoinTable(name = "produto_pedido", joinColumns = @JoinColumn(name = "pedido_id"), inverseJoinColumns = @JoinColumn(name = "produto_id"))
	private List<Produto> produtos;

    
    public Pedido() {
	}

	public Pedido(Produto produto, Cliente cliente) {
		this.produtos = new ArrayList<Produto>();
		this.produtos.add(produto);
		this.cliente = cliente;
		this.dataPedido = LocalDateTime.now();
		this.valorTotal = produto.getValor();
	}
	
	public Pedido(List<Produto> produtos, Cliente cliente) {
		this.produtos = produtos;
		this.cliente = cliente;
		this.dataPedido = LocalDateTime.now();
				
		for(Produto produto: produtos) {
			this.valorTotal = this.valorTotal.add(produto.getValor());
		}				 
	}
    public List<Produto> getProdutos(){
    	return this.produtos;
    }

    public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDateTime dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

   

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pedido other = (Pedido) obj;
        return Objects.equals(id, other.id);
    }
}
