
package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Authenticated;
import org.springframework.samples.petclinic.model.Fan;
import org.springframework.samples.petclinic.model.FootballClub;
import org.springframework.samples.petclinic.service.AuthenticatedService;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.FanService;
import org.springframework.samples.petclinic.service.FootballClubService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.web.validators.CreditCardValidator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FanController {

	private static final String			VIEWS_FAN_CREATE_OR_UPDATE_FORM	= "fan/createOrUpdateFanForm";

	private final AuthenticatedService	authenticatedService;
	private final FootballClubService	footballClubService;
	private final FanService			fanService;


	@Autowired
	public FanController(final FootballClubService footballClubService, final AuthenticatedService authenticatedService, final FanService fanService, final UserService userService, final AuthoritiesService authoritiesService) {
		this.fanService = fanService;
		this.authenticatedService = authenticatedService;
		this.footballClubService = footballClubService;

	}

	@GetMapping(value = "/footballClub/{clubId}/fan/new")
	public String initCreationForm(@PathVariable final Integer clubId, final Map<String, Object> model) {
		model.put("isNew", true);

		//Obtenemos el username actual conectado
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		//Obtenemos el authenticated actual conectado
		Authenticated thisUser = this.authenticatedService.findAuthenticatedByUsername(currentPrincipalName);
		//Obtenemos club
		FootballClub thisClub = this.footballClubService.findFootballClubById(clubId);

		//Comprobamos que no es fan de otro equipo
		if (this.fanService.existFan(thisUser.getId())) {
			// si ya es fan vuelve a la vista de club con existFan igual a true
			model.put("existFan", true);
			model.put("footballClub", this.footballClubService.findFootballClubById(clubId));

			return "footballClubs/footballClubDetails";
		} else {
			//inicializamos el fan
			Fan f = new Fan();
			f.setUser(thisUser);
			f.setClub(thisClub);

			model.put("fan", f);

			return FanController.VIEWS_FAN_CREATE_OR_UPDATE_FORM;

		}

	}

	//NUEVO VIP

	@PostMapping(value = "/footballClub/{clubId}/fan/new")
	public String processCreationForm(@PathVariable final Integer clubId, @Valid final Fan f, final BindingResult result, final Map<String, Object> model) {

		//Validamos tarjeta
		CreditCardValidator ccValid = new CreditCardValidator();
		if (ccValid.supports(f.getCreditCard().getClass())) {
			ccValid.validate(f.getCreditCard(), result);
		}
		//Obtenemos el username actual conectado
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		//Obtenemos el authenticated actual conectado
		Authenticated thisUser = this.authenticatedService.findAuthenticatedByUsername(currentPrincipalName);
		//Obtenemos club
		FootballClub thisClub = this.footballClubService.findFootballClubById(clubId);
		//Creamos el fan

		f.setUser(thisUser);
		f.setClub(thisClub);

		if (result.hasErrors()) {
			model.put("isNew", true);
			model.put("fan", f);
			return FanController.VIEWS_FAN_CREATE_OR_UPDATE_FORM;
		} else {

			f.setVip(true);
			this.fanService.saveFan(f);

			return "redirect:/";
		}
	}

	//NUEVO NO VIP

	@GetMapping(value = "/footballClub/{clubId}/createFanNoVip")
	public String createFanNoVip(@PathVariable final Integer clubId) {

		//Obtenemos el username actual conectado
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		//Creamos el fan
		Fan f = new Fan();

		//Obtenemos el authenticated actual conectado
		Authenticated thisUser = this.authenticatedService.findAuthenticatedByUsername(currentPrincipalName);

		//Obtenemos el club del que quiere ser fan
		FootballClub club = this.footballClubService.findFootballClubById(clubId);

		//Añadimos los datos del user y el club al fan
		f.setClub(club);
		f.setUser(thisUser);

		//Guardamos en la db el nuevo presidente
		this.fanService.saveFan(f);

		//Redirigimos a la vista welcome
		return "redirect:/";
	}

	//EDIT TO VIP

	@GetMapping(value = "/footballClub/noVip")
	public String initUpdateFanForm(final Map<String, Object> model) {
		model.put("isNew", false);

		//Obtenemos el username actual conectado
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		//Obtenemos el authenticated actual conectado
		Authenticated thisUser = this.authenticatedService.findAuthenticatedByUsername(currentPrincipalName);

		//Buscamos el fan en la base de datos
		Fan f = this.fanService.findByUserId(thisUser.getId());

		//lo ponemos en model

		model.put("fan", f);

		//Seguimos en la pantalla de edición
		return FanController.VIEWS_FAN_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/footballClub/noVip")
	public String processUpdateFanForm(@Valid final Fan f, @PathParam("userId") final int userId, @PathParam("clubId") final int clubId, final BindingResult result, final Map<String, Object> model) {

		//Validamos tarjeta
		CreditCardValidator ccValid = new CreditCardValidator();
		if (ccValid.supports(f.getCreditCard().getClass())) {
			ccValid.validate(f.getCreditCard(), result);
		}
		//Obtenemos el authenticated actual conectado
		Authenticated thisUser = this.authenticatedService.findAuthenticatedById(userId);
		//Obtenemos el club del que quiere ser fan
		FootballClub club = this.footballClubService.findFootballClubById(clubId);
		f.setUser(thisUser);
		f.setClub(club);

		//Si hay errores en la vista seguimos en la pantalla de edición
		if (result.hasErrors()) {
			model.put("isNew", true);
			model.put("fan", f);
			return FanController.VIEWS_FAN_CREATE_OR_UPDATE_FORM;
		} else {

			//Si todo sale bien vamos a la vista de welcome
			f.setVip(true);
			this.fanService.saveFan(f);

			return "redirect:/";
		}
	}
	//Borrar Club
	@RequestMapping(value = "/footballClub/fan/delete")
	public String processDeleteForm() {

		//Obtenemos el username actual conectado
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		//Obtenemos el authenticated actual conectado
		Authenticated thisUser = this.authenticatedService.findAuthenticatedByUsername(currentPrincipalName);

		//Buscamos el fan en la base de datos
		Fan f = this.fanService.findByUserId(thisUser.getId());

		//Borramos el equipo en cuestión
		this.fanService.delete(f);

		//Volvemos a la vista de mi club, en este caso sería la de "club empty"
		return "redirect:/";
	}

}
