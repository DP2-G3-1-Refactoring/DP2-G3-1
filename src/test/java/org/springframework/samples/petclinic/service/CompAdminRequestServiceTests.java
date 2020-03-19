
package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.CompAdminRequest;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.Enum.RequestStatus;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))

public class CompAdminRequestServiceTests {

	@Autowired
	protected CompAdminRequestService	compAdminRequestService;

	@Autowired
	protected CompetitionAdminService	competitionAdminService;

	@Autowired
	protected AuthoritiesService		authoritiesService;


	@Test //CASO POSITIVO
	void shouldCount() {
		int count = this.compAdminRequestService.count();
		Assertions.assertTrue(count == 1);
	}

	@Test //CASO POSITIVO
	void shouldFindCompAdminRequestById() {
		CompAdminRequest findById = null;
		findById = this.compAdminRequestService.findCompAdminRequestById(1);
		Assertions.assertTrue(findById != null);
	}

	@Test //CASO NEGATIVO
	void shouldNotFindCompAdminRequestById() {
		CompAdminRequest findById = null;
		findById = this.compAdminRequestService.findCompAdminRequestById(1000);
		Assertions.assertTrue(findById == null);
	}

	@Test //CASO POSITIVO
	void shouldFindCompAdminRequestByUsername() {
		CompAdminRequest findByUsername = null;
		findByUsername = this.compAdminRequestService.findCompAdminRequestByUsername("gonzalo");
		Assertions.assertTrue(findByUsername != null);
	}

	@Test //CASO NEGATIVO
	void shouldNotFindCompAdminRequestByUsername() {
		CompAdminRequest findByUsername = null;
		findByUsername = this.compAdminRequestService.findCompAdminRequestByUsername("pedroTest");
		Assertions.assertTrue(findByUsername == null);
	}

	@Test //CASO POSITIVO
	void shouldDeleteCompAdminRequest() {

		//Cogemos una ya existente y la intentamos borrar
		CompAdminRequest tbdeleted = this.compAdminRequestService.findCompAdminRequestByUsername("gonzalo");
		this.compAdminRequestService.deleteCompAdminRequest(tbdeleted);

		tbdeleted = this.compAdminRequestService.findCompAdminRequestByUsername("gonzalo");

		Assertions.assertFalse(tbdeleted != null);
	}

	@Test //CASO NEGATIVO
	void shouldNotDeleteCompAdminRequest() {

		CompAdminRequest tbdeleted = this.compAdminRequestService.findCompAdminRequestByUsername("gonzaloTest");

		Assertions.assertThrows(NullPointerException.class, () -> {
			this.compAdminRequestService.deleteCompAdminRequest(tbdeleted);
		});
	}

	@Test //CASO POSITIVO
	void shouldSaveCompAdminRequest() {

		User user = new User();
		user.setEnabled(true);
		user.setUsername("username");
		user.setPassword("username");

		// Creamos uno nuevo manualmente
		CompAdminRequest newComp = new CompAdminRequest();
		newComp.setId(1000);
		newComp.setTitle("JUnit testing title");
		newComp.setDescription("JUnit testing description");
		newComp.setStatus(RequestStatus.ON_HOLD);
		newComp.setUser(user);

		//Lo guardamos y contamos cuantas peticiones de Competition Admin hay
		this.compAdminRequestService.saveCompAdminRequest(newComp);
		int count = this.compAdminRequestService.count();

		// Debería haber 2, una que existía previamente y la nueva
		Assertions.assertTrue(count == 2);
	}

	@Test //CASO POSITIVO
	void shouldCountCompAdminRequestByUsername() {
		int countByUsername = this.compAdminRequestService.countCompAdminRequestByUsername("gonzalo");
		Assertions.assertTrue(countByUsername == 1);
	}

	@Test //CASO NEGATIVO
	void shouldNotCountCompAdminRequestByUsername() {
		Integer countByUsername = this.compAdminRequestService.countCompAdminRequestByUsername("gonzaloTest");
		Assertions.assertTrue(countByUsername == 0);
	}

	@Test //CASO POSITIVO
	void shouldFindCompAdminRequests() {
		Collection<CompAdminRequest> collection = null;
		collection = this.compAdminRequestService.findCompAdminRequests();
		Assertions.assertTrue(collection.size() == 1);
	}

}
