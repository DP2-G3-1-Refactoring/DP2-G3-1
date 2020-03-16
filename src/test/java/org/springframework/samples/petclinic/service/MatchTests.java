
package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.FootballClub;
import org.springframework.samples.petclinic.model.Match;
import org.springframework.samples.petclinic.model.MatchRefereeRequest;
import org.springframework.samples.petclinic.model.MatchRequest;
import org.springframework.samples.petclinic.model.Referee;
import org.springframework.samples.petclinic.model.Enum.MatchStatus;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class MatchTests {

	@Autowired
	protected MatchService					matchService;

	@Autowired
	protected RefereeService				refereeService;

	@Autowired
	protected FootballClubService			footballClubService;

	@Autowired
	protected MatchRequestService			matchRequestService;

	@Autowired
	protected MatchRefereeRequestService	matchRefereeRequestService;


	@Test
	void shouldFindAllMatchRequests() {

		List<Match> ms = new ArrayList<>();

		ms.addAll(this.matchService.findAllMatchRequests());

		int count = ms.size();

		Assertions.assertTrue(count == 2);

	}

	@Test
	void shouldFindAllMatchRequestsByReferee() {

		List<Match> ms = new ArrayList<>();

		Referee ref = this.refereeService.findRefereeById(1);

		ms.addAll(this.matchService.findAllMatchRequestsByReferee(ref.getUser().getUsername()));

		int count = ms.size();

		Assertions.assertTrue(count == 0);

	}

	@Test
	void shouldFindMatchById() {

		Boolean res = true;

		Match m = this.matchService.findMatchById(1);

		if (m == null) {
			res = false;
		}

		Assertions.assertTrue(res == true);

	}

	@Test
	void shouldFindMatchByFootballClubName1() {

		Boolean res = true;

		FootballClub fc = this.footballClubService.findFootballClubById(1);

		Match m = this.matchService.findMatchByFootballClubName1(fc.getName());

		if (m == null) {
			res = false;
		}

		Assertions.assertTrue(res == true);

	}

	@Test
	void shouldFindMatchByFootballClubName2() {

		Boolean res = true;

		FootballClub fc = this.footballClubService.findFootballClubById(1);

		Match m = this.matchService.findMatchByFootballClubName2(fc.getName());

		if (m == null) {
			res = false;
		}

		Assertions.assertTrue(res == true);

	}

	@Test
	void shouldSaveMatch() {

		Match m = new Match();

		Calendar d = Calendar.getInstance();
		d.set(2025, 02, 02, 20, 20);
		Date date = d.getTime();

		FootballClub fc1 = this.footballClubService.findFootballClubById(1);
		FootballClub fc2 = this.footballClubService.findFootballClubById(2);

		Referee ref = this.refereeService.findRefereeById(1);

		m.setId(100);
		m.setTitle("JUnit test");
		m.setMatchDate(date);
		m.setMatchStatus(MatchStatus.FINISHED);
		m.setStadium("Stadium");
		m.setFootballClub1(fc1);
		m.setFootballClub2(fc2);
		m.setReferee(ref);

		this.matchService.saveMatch(m);

		int count = this.matchService.count();

		Assertions.assertTrue(count == 3);

	}

	@Test
	void shouldDeteleMatch() {

		// Todas las referencias donde esté el match se deben poner a null antes de borrarlo

		Match m = this.matchService.findMatchById(1);

		List<MatchRequest> mrs = new ArrayList<>();

		mrs.addAll(this.matchRequestService.findAllMatchRequests());

		for (MatchRequest mrr : mrs) {
			mrr.setFootballClub1(null);
			mrr.setFootballClub2(null);
			mrr.setReferee(null);
		}

		List<MatchRefereeRequest> mrrs = new ArrayList<>();

		mrrs.addAll(this.matchRefereeRequestService.findAllOnHoldMatchRefereeRequests());

		for (MatchRefereeRequest mrr : mrrs) {
			if (mrr.getMatch().getId() == m.getId()) {
				mrr.setMatch(null);
				mrr.setReferee(null);
			}

		}

		m.setFootballClub1(null);
		m.setFootballClub2(null);
		m.setReferee(null);

		this.matchService.deleteMatch(m);

		int count = this.matchService.count();

		Assertions.assertTrue(count == 1);

	}

}
