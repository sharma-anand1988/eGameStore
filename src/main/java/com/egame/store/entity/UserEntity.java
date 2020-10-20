package com.egame.store.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table
@Entity(name = "txn_users")
public class UserEntity extends BaseEntity {

	private static final long serialVersionUID = 3351707370492903497L;

	@Column(name = "name", length = 50)
	private String name;

	@Column(name = "email", length = 50)
	private String email;

	@Column(name = "password", length = 50)
	private String password;

}
