
package org.springframework.samples.petclinic.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@Id
	String						username;

	String						password;

	boolean						enabled;
}
