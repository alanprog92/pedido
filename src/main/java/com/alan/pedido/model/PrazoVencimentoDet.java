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
@Table(name = "prazovencimentodets")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
     allowGetters = true)
public class PrazoVencimentoDet {
    @Id   
    private Integer id;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "prazovencimento")
    private PrazoVencimento prazovencimento;
    private Integer codigo;
    private Integer qtddias;    
    
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

	public PrazoVencimento getPrazovencimento() {
		return prazovencimento;
	}

	public void setPrazovencimento(PrazoVencimento prazovencimento) {
		this.prazovencimento = prazovencimento;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getQtddias() {
		return qtddias;
	}

	public void setQtddias(Integer qtddias) {
		this.qtddias = qtddias;
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
		PrazoVencimentoDet other = (PrazoVencimentoDet) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "PrazoVencimentoDet [id=" + id + ", prazovencimento=" + prazovencimento + ", codigo=" + codigo
				+ ", qtddias=" + qtddias + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
        
    

}
