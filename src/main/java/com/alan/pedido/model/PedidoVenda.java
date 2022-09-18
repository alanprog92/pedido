package com.alan.pedido.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Alan.
 */
@Entity
@Table(name = "pedidovendas")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
     allowGetters = true)
public class PedidoVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "clienteid")    
    private Cliente cliente ;
    
    private LocalDate emissao;
    private String status;
    private Double totalpedido;
    private Double desconto;
    
    @OneToOne
    @JoinColumn(name = "condpgtoid")        
    private CondPgto condpgto;
    
    @OneToOne
    @JoinColumn(name = "formapgtoid")        
    private FormaPgto formapgto;    
    
    private Date prevchegada;
    private Double comissao;
    private Double percomissao;
    private Double totaldesc;
    private Date datavenc;
    
    @OneToOne
    @JoinColumn(name = "prazovencimentoid")        
    private PrazoVencimento prazovencimento;
        
    private String tipodesconto;
    
    @OneToOne
    @JoinColumn(name = "vendedorid")        
    private Vendedor vendedor;
    
    private String observacao;
    private String sincronizado;
    private String fase;
    
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getEmissao() {
		return emissao;
	}

	public void setEmissao(LocalDate emissao) {
		this.emissao = emissao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getTotalpedido() {
		return totalpedido;
	}

	public void setTotalpedido(Double totalpedido) {
		this.totalpedido = totalpedido;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public CondPgto getCondpgto() {
		return condpgto;
	}

	public void setCondpgto(CondPgto condpgto) {
		this.condpgto = condpgto;
	}

	public FormaPgto getFormapgto() {
		return formapgto;
	}

	public void setFormapgto(FormaPgto formapgto) {
		this.formapgto = formapgto;
	}

	public Date getPrevchegada() {
		return prevchegada;
	}

	public void setPrevchegada(Date prevchegada) {
		this.prevchegada = prevchegada;
	}

	public Double getComissao() {
		return comissao;
	}

	public void setComissao(Double comissao) {
		this.comissao = comissao;
	}

	public Double getPercomissao() {
		return percomissao;
	}

	public void setPercomissao(Double percomissao) {
		this.percomissao = percomissao;
	}

	public Double getTotaldesc() {
		return totaldesc;
	}

	public void setTotaldesc(Double totaldesc) {
		this.totaldesc = totaldesc;
	}

	public Date getDatavenc() {
		return datavenc;
	}

	public void setDatavenc(Date datavenc) {
		this.datavenc = datavenc;
	}

	public PrazoVencimento getPrazovencimento() {
		return prazovencimento;
	}

	public void setPrazovencimento(PrazoVencimento prazovencimento) {
		this.prazovencimento = prazovencimento;
	}

	public String getTipodesconto() {
		return tipodesconto;
	}

	public void setTipodesconto(String tipodesconto) {
		this.tipodesconto = tipodesconto;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getSincronizado() {
		return sincronizado;
	}

	public void setSincronizado(String sincronizado) {
		this.sincronizado = sincronizado;
	}

	public String getFase() {
		return fase;
	}

	public void setFase(String fase) {
		this.fase = fase;
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
		PedidoVenda other = (PedidoVenda) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "PedidoVenda [id=" + id + ", cliente=" + cliente + ", emissao=" + emissao + ", status=" + status
				+ ", totalpedido=" + totalpedido + ", desconto=" + desconto + ", condpgto=" + condpgto + ", formapgto="
				+ formapgto + ", prevchegada=" + prevchegada + ", comissao=" + comissao + ", percomissao=" + percomissao
				+ ", totaldesc=" + totaldesc + ", datavenc=" + datavenc + ", prazovencimento=" + prazovencimento
				+ ", tipodesconto=" + tipodesconto + ", vendedor=" + vendedor + ", observacao=" + observacao
				+ ", sincronizado=" + sincronizado + ", fase=" + fase + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}    

	
}
