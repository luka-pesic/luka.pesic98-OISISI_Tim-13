package apoteka.logika;

import java.util.stream.Collectors;

import apoteka.Stanje;
import apoteka.model.Korisnik;

public class Korisnici {
	public static void registracija(Korisnik k) {
		Stanje.getInstanca().getKorisnici().add(k);
	}

	// true ako je slobodno, u suprotnom false
	public static boolean proveriKorisnickoIme(String ime) {
		return Stanje.getInstanca().getKorisnici().stream().filter(korisnik -> korisnik.getKorisnickoIme().equals(ime))
				.collect(Collectors.toList()).size() == 0;
	}

	public static Korisnik uloguj(String ime, String sifra) {
		return Stanje
				.getInstanca().getKorisnici().stream().filter(korisnik -> !korisnik.isIzbrisan()
						&& korisnik.getSifra().equals(sifra) && korisnik.getKorisnickoIme().equals(ime))
				.findFirst().orElse(null);
	}
}
