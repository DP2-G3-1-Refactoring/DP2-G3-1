
package org.springframework.samples.petclinic.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.Enum.MatchStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "Matches")
public class Match extends BaseEntity {

	@Column(name = "title")
	private String			title;

	@Column(name = "match_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	@NotNull
	private Date			matchDate;

	@Column(name = "match_status")
	private MatchStatus		matchStatus;

	@Column(name = "stadium")
	@NotEmpty
	private String			stadium;

	@OneToOne(optional = true)
	@JoinColumn(name = "footballClub1", referencedColumnName = "name")
	private FootballClub	footballClub1;

	@OneToOne(optional = true)
	@JoinColumn(name = "footballClub2", referencedColumnName = "name")
	private FootballClub	footballClub2;

	@OneToOne(optional = true)
	@JoinColumn(name = "referee", referencedColumnName = "username")
	private Referee			referee;

	@OneToOne(optional = true)
	@JoinColumn(name = "matchRecord", referencedColumnName = "id")
	private MatchRecord		matchRecord;

	@Column(name = "creator")
	private String			creator; //Será el username

	//OPCIONAL - PARA LAS COMPETICIONES
	@ManyToOne(optional = true)
	@JoinColumn(name = "jornada_id")
	private Jornada			jornada;

	//OPCIONAL - PARA LAS COMPETICIONES
	@ManyToOne(optional = true)
	@JoinColumn(name = "round_id")
	private Round			round;

}
