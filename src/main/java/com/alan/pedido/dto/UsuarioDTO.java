package com.alan.pedido.dto;

public class UsuarioDTO {
	
    private Integer id;  
    private Integer vendedor;
    private String login;
    
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
    
}
