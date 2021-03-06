
package org.springframework.samples.petclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.Enum.RequestStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "match_referee_request")
public class MatchRefereeRequest extends BaseEntity {

	@Column(name = "title")
	@NotEmpty
	private String			title;

	@Column(name = "status")
	@NotNull
	private RequestStatus	status;

	//
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username", referencedColumnName = "username")
	private Referee			referee;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "matchId", referencedColumnName = "id")
	private Match			match;

}
