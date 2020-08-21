package apoteka;

import java.util.LinkedList;
import java.util.List;

import apoteka.model.Korisnik;

public class Stanje {
	private static Stanje instanca;
	private Korisnik ulogovan;
	private List<Korisnik> korisnici;

	private Stanje() {
		korisnici = (List<Korisnik>) ucitavanje("./korisnici.d");
		if (korisnici == null) {
			korisnici = new LinkedList<Korisnik>();
			Korisnik admin = new Korisnik();
			admin.setKorisnickoIme("admin");admin.setUloga("Admin");
			admin.setSifra("admin");
			korisnici.add(admin);
		}
	}

	public static Stanje getInstanca() {
		if (instanca == null)
			instanca = new Stanje();
		return instanca;
	}

	public Korisnik getUlogovan() {
		return ulogovan;
	}

	public void setUlogovan(Korisnik ulogovan) {
		this.ulogovan = ulogovan;
	}

	public List<Korisnik> getKorisnici() {
		return korisnici;
	}

	public void setKorisnici(List<Korisnik> korisnici) {
		this.korisnici = korisnici;
	}

	private Object ucitavanje(String fajl) {
		// TODO implement
		return null;
	}
}
