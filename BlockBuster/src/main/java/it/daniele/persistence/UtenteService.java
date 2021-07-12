package it.daniele.persistence;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.daniele.business.Ruolo;
import it.daniele.business.Utente;
import it.daniele.business.repository.RuoloRepo;
import it.daniele.business.repository.UtenteRepo;

@Service
public class UtenteService {

	@Autowired
	private UtenteRepo ut;
	@Autowired
	private RuoloRepo rr;
	@Autowired
	private PasswordEncoder encoder;

	public Utente crea(Utente utente) {
		return ut.save(utente);
	}

	public List<Utente> getAll() {
		List<Utente> lista = ut.findAll();
		if (lista.isEmpty()) {
			return null;
		} else {
			return lista;
		}
	}

	public Utente cercaId(Long id) {
		if (id == null) {
			return null;
		} else {
			return ut.findById(id).get();
		}
	}

	public Utente aggiorna(Utente utente) {
		if (utente.getCodice() == null) {
			return null;
		} else {
			return ut.save(utente);
		}
	}

	public String delete(Long id) {
		if (id == null) {
			return null;
		} else {
			ut.deleteById(id);
			return "eliminazione avvenuta con successo";
		}
	}

	public Utente trovaPerCognome(String cognome) {
		if (cognome == null) {
			return null;
		} else {
			return ut.findByCognome(cognome);
		}
	}

	public List<Utente> trovaPerDataDiNascita(Date data) {
		if (data == null) {
			return null;
		} else {
			return ut.findByDataDiNascita(data);
		}
	}

	public Utente saveUtente(String nome, String cognome, String mail, String nickname, String password, Long idRole) {
		Utente utente = ut.findBynome(nome).get();
		Ruolo ru = rr.findById(idRole).get();
		Set<Ruolo> set = new HashSet<>();
		set.add(ru);
		utente.setNome(nome);
		utente.setCognome(cognome);
		utente.setNickname(nickname);
		String hashedPassword = encoder.encode(password);
		utente.setPassword(hashedPassword);
		utente.setRoles(set);
		ut.save(utente);
		return utente;
	}

	public Utente cambiaPassword(Long ute) {
		Utente nuovo = ut.getById(ute);
		String newPassword = encoder.encode(nuovo.getPassword());
		nuovo.setPassword(newPassword);
		return ut.save(nuovo);
	}

}
