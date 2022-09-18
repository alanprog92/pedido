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
@Table(name = "tabelaprodutodets")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
     allowGetters = true)
public class TabelaProdutoDet {
    @Id
    private Integer id;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "tabelaprodutoid")
    
    private TabelaProduto tabelaproduto;
    private Double qtdini;
    private Double qtdfim;
    private Double precounitario;
    
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

	public TabelaProduto getTabelaproduto() {
		return tabelaproduto;
	}

	public void setTabelaproduto(TabelaProduto tabelaproduto) {
		this.tabelaproduto = tabelaproduto;
	}

	public Double getQtdini() {
		return qtdini;
	}

	public void setQtdini(Double qtdini) {
		this.qtdini = qtdini;
	}

	public Double getQtdfim() {
		return qtdfim;
	}

	public void setQtdfim(Double qtdfim) {
		this.qtdfim = qtdfim;
	}

	public Double getPrecounitario() {
		return precounitario;
	}

	public void setPrecounitario(Double precounitario) {
		this.precounitario = precounitario;
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
		TabelaProdutoDet other = (TabelaProdutoDet) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "TabelaProdutoDet [id=" + id + ", tabelaproduto=" + tabelaproduto + ", qtdini=" + qtdini + ", qtdfim="
				+ qtdfim + ", precounitario=" + precounitario + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ "]";
	}



    
}
