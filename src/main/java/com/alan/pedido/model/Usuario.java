package com.alan.pedido.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Alan.
 */
@Entity
@Table(name = "usuarios")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
     allowGetters = true)
public class Usuario  implements UserDetails {
	
    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  
    
    private Integer vendedor;
    
    @Column(unique = true)
    private String login;
    private String senha;    
    private String token;
    
    @Column( nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate     
    private Date createdAt;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate     
    private Date updatedAt;
    
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuarios_role", uniqueConstraints = @UniqueConstraint (
			         columnNames = {"usuario_id","role_id"}, name = "unique_role_user"), 
	joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id", table = "usuario", unique = false,
	foreignKey = @ForeignKey(name = "usuario_fk", value = ConstraintMode.CONSTRAINT)), 
	
	inverseJoinColumns = @JoinColumn (name = "role_id", referencedColumnName = "id", table = "role", unique = false, updatable = false,
	   foreignKey = @ForeignKey (name="role_fk", value = ConstraintMode.CONSTRAINT)))
	private List<Role> roles; /*Os papeis ou acessos*/
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVendedor() {
		return vendedor;
	}

	public void setVendedor(Integer vendedor) {
		this.vendedor = vendedor;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", vendedor=" + vendedor + ", login=" + login + ", senha=" + senha + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}

	/*São os acessos do usuário ROLE_ADMIN OU ROLE_VISITANTE*/
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return roles;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return this.senha;
	}

	@JsonIgnore
	@Override
	public String getUsername() {
		return this.login;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}



}
