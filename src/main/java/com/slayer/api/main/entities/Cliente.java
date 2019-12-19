package com.slayer.api.main.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@NotNull
	@Column(length = 50, nullable = false)
	private String nombres;
	
	@NotEmpty
	@NotNull
	@Column(length = 50, nullable = false)
	private String apellidos;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date creado;
	
	@NotEmpty
	@NotNull
	@Email
	@Column(length = 100, nullable = false, unique = true)
	private String email;

	public Cliente() {
		
	}

	public Cliente(Long id, String nombres, String apellidos, Date creado, String email) {
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.creado = creado;
		this.email = email;
	}
	
	@PrePersist
	public void prePersist() {
		creado = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getCreado() {
		return creado;
	}

	public void setCreado(Date creado) {
		this.creado = creado;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", creado=" + creado + ", email="
				+ email + "]";
	}

}
