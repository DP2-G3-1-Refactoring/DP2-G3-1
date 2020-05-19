
package org.springframework.samples.petclinic.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "authorities")
public class Authorities implements Serializable {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@Id
	@Column
	private String				username;

	@Column
	private String				authority;
}
