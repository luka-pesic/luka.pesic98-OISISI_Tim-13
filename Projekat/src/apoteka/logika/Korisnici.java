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

	public static String proveri(Korisnik u) {
		String message = "";
		if (u.getKorisnickoIme().equals(""))
			message += "Korisnièko ime nije uneto\r\n";
		if (u.getSifra().equals(""))
			message += "Lozinka nije uneta\r\n";
		if (u.getIme().equals(""))
			message += "Ime nije uneto\r\n";
		if (u.getPrezime().equals(""))
			message += "Prezime nije uneto\r\n";
		if (!proveriKorisnickoIme(u.getKorisnickoIme()))
			message += "Korisnièko ime vec posotji\r\n";
		return message;
	}

	public static String[] apotekari() {
		return (String[]) Stanje.getInstanca().getKorisnici().stream()
				.filter(k -> !k.isIzbrisan() && k.getUloga().equals("Apotekar")).map(k -> k.getKorisnickoIme())
				.distinct().collect(Collectors.toList()).toArray(new String[0]);
	}
}
