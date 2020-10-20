package com.egame.store.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 3841429541941797582L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_id")
	private long pkId;
	
	/*
	 * @CreatedBy private long createdBy;
	 * 
	 * @CreatedDate private LocalDateTime createdDate;
	 */

}
