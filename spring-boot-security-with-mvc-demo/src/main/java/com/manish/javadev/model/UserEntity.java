package com.manish.javadev.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "tbl_users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "STATUS")
	private boolean status;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "userEntity", cascade = CascadeType.ALL)
	private Set<RoleEntity> roles;

	public UserEntity() {

	}

	public UserEntity(String userName, String password, boolean status) {
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		this.userName = userName;
		this.password = password;
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Set<RoleEntity> getRoles() {
		if (roles == null) {
			roles = new HashSet<RoleEntity>();
		}
		return roles;
	}

}