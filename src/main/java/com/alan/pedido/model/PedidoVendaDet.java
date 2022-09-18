package com.alan.pedido.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Alan.
 */
@Entity
@Table(name = "pedidovendadets")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
     allowGetters = true)
public class PedidoVendaDet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @OneToOne
    @JoinColumn(name = "pedidovendaid")        
    private PedidoVenda pedidovenda;
    
    @OneToOne
    @JoinColumn(name = "produtoid")        
    private Produto produto;
    private String unidade;
    private Double quantidade;
    private Double unitario;
    private Double total;
    private String status;
    private Double percomissao;
    private Double comissao;
    private Double desconto;
    private Double totaldesc;
    private String tipovenda;
    private String tipodesconto;
    private Double descontoaux;
    
    @OneToOne
    @JoinColumn(name = "tabelaprodutoid", nullable = true)    
    private TabelaProduto tabelaproduto;
    
    @Column( nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate     
    private Date createdAt;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate     
    private Date updatedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PedidoVenda getPedidovenda() {
		return pedidovenda;
	}

	public void setPedidovenda(PedidoVenda pedidovenda) {
		this.pedidovenda = pedidovenda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getUnitario() {
		return unitario;
	}

	public void setUnitario(Double unitario) {
		this.unitario = unitario;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getPercomissao() {
		return percomissao;
	}

	public void setPercomissao(Double percomissao) {
		this.percomissao = percomissao;
	}

	public Double getComissao() {
		return comissao;
	}

	public void setComissao(Double comissao) {
		this.comissao = comissao;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Double getTotaldesc() {
		return totaldesc;
	}

	public void setTotaldesc(Double totaldesc) {
		this.totaldesc = totaldesc;
	}

	public String getTipovenda() {
		return tipovenda;
	}

	public void setTipovenda(String tipovenda) {
		this.tipovenda = tipovenda;
	}

	public String getTipodesconto() {
		return tipodesconto;
	}

	public void setTipodesconto(String tipodesconto) {
		this.tipodesconto = tipodesconto;
	}

	public Double getDescontoaux() {
		return descontoaux;
	}

	public void setDescontoaux(Double descontoaux) {
		this.descontoaux = descontoaux;
	}

	public TabelaProduto getTabelaproduto() {
		return tabelaproduto;
	}

	public void setTabelaproduto(TabelaProduto tabelaproduto) {
		this.tabelaproduto = tabelaproduto;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
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
		PedidoVendaDet other = (PedidoVendaDet) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "PedidoVendaDet [id=" + id + ", pedidovenda=" + pedidovenda + ", produto=" + produto + ", unidade="
				+ unidade + ", quantidade=" + quantidade + ", unitario=" + unitario + ", total=" + total + ", status="
				+ status + ", percomissao=" + percomissao + ", comissao=" + comissao + ", desconto=" + desconto
				+ ", totaldesc=" + totaldesc + ", tipovenda=" + tipovenda + ", tipodesconto=" + tipodesconto
				+ ", descontoaux=" + descontoaux + ", tabelaproduto=" + tabelaproduto + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

	
}
