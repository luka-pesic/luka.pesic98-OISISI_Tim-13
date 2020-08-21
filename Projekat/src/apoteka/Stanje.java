package apoteka;

import java.util.LinkedList;
import java.util.List;

import apoteka.model.Korisnik;
import apoteka.model.Lek;

public class Stanje {
	private static Stanje instanca;
	private Korisnik ulogovan;
	private List<Korisnik> korisnici;
	private List<Lek> lekovi;

	private Stanje() {
		korisnici = (List<Korisnik>) ucitavanje("./korisnici.d");
		if (korisnici == null) {
			korisnici = new LinkedList<Korisnik>();
			Korisnik admin = new Korisnik();
			admin.setKorisnickoIme("admin");
			admin.setUloga("Admin");
			admin.setSifra("admin");
			korisnici.add(admin);
		}
		lekovi = (List<Lek>) ucitavanje("./lekovi.d");
		if (lekovi == null)
			lekovi = new LinkedList<>();

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

	public List<Lek> getLekovi() {
		return lekovi;
	}

	public void setLekovi(List<Lek> lekovi) {
		this.lekovi = lekovi;
	}

	private Object ucitavanje(String fajl) {
		// TODO implement
		return null;
	}
}
