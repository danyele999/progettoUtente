package it.daniele.presentation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.daniele.business.Utente;
import it.daniele.login.LoginRequest;
import it.daniele.login.LoginResponse;
import it.daniele.login.UserDetailsImpl;
import it.daniele.persistence.UtenteService;
import it.daniele.security.JwtUtils;

@RestController
@RequestMapping("/api")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UtenteService utService;

	@Autowired
	JwtUtils jwtutils;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		authentication.getAuthorities();
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtutils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new LoginResponse(jwt, userDetails.getId(), userDetails.getUsername(),
				userDetails.getEmail(), roles, userDetails.getExpirationTime()));
	}

	@PostMapping("/registraUtente/{idRole}")
	public Utente creaUtente(@RequestBody Utente utaa, @PathVariable Long idRole) {
		Utente nuovo = utService.saveUtente(utaa.getNome(), utaa.getCognome(), utaa.getMail(), utaa.getNickname(),
				utaa.getPassword(), idRole);
		return nuovo;
	}

	@GetMapping("/aggiornamentoPassword/{id}")
	public String cambio(@PathVariable Long id) {
		Utente pass = utService.cambiaPassword(id);
		if (pass != null) {
			return "password Aggiornata";
		} else
			return "errore";
	}
}