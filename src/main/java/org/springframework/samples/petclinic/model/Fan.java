
package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.datatypes.CreditCard;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Fan extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "club_id", referencedColumnName = "id")
	@Valid
	private FootballClub	club;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@Valid
	private Authenticated	user;

	@NotNull
	private boolean			vip;

	private CreditCard		creditCard;

}
