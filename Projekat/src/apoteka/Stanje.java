package apoteka;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import apoteka.model.Korisnik;
import apoteka.model.Lek;
import apoteka.model.Racun;
import apoteka.model.Recept;

public class Stanje {
	private static Stanje instanca;
	private Korisnik ulogovan;
	private List<Korisnik> korisnici;
	private List<Lek> lekovi;
	private List<Recept> recepti;
	private List<Racun> racuni;
	private Racun korpa;

	private Recept trenutniRecept = new Recept();// recept koji se renutno kreira

	private Stanje() {
		korpa = new Racun();
		korisnici = (List<Korisnik>) ucitavanje("./korisnici.d");
		if (korisnici == null) {
			korisnici = new LinkedList<Korisnik>();
			Korisnik admin = new Korisnik();
			admin.setKorisnickoIme("admin");
			admin.setUloga("Admin");
			admin.setSifra("admin");
			admin.setIme("admin");
			admin.setPrezime("admin");
			korisnici.add(admin);
		}
		lekovi = (List<Lek>) ucitavanje("./lekovi.d");
		if (lekovi == null)
			lekovi = new LinkedList<>();
		recepti = (List<Recept>) ucitavanje("./recepti.d");
		if (recepti == null)
			recepti = new LinkedList<>();

		racuni = (List<Racun>) ucitavanje("./racuni.d");
		if (racuni == null)
			racuni = new LinkedList<>();

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

	public List<Recept> getRecepti() {
		return recepti;
	}

	public void setRecepti(List<Recept> recepti) {
		this.recepti = recepti;
	}

	private Object ucitavanje(String fajl) {
		File f = new File(fajl);
		ObjectInputStream objInStream = null;
		Object ret = null;
		try {
			objInStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
			ret = objInStream.readObject();
		} catch (Exception e) {
			System.out.println();
		} finally {
			try {
				if (objInStream != null)
					objInStream.close();
			} catch (Exception e) {
			}
		}
		return ret;
	}

	public Recept getTrenutniRecept() {
		return trenutniRecept;
	}

	public void setTrenutniRecept(Recept trenutniRecept) {
		this.trenutniRecept = trenutniRecept;
	}

	public List<Racun> getRacuni() {
		return racuni;
	}

	public void setRacuni(List<Racun> racuni) {
		this.racuni = racuni;
	}

	public Racun getKorpa() {
		return korpa;
	}

	public void setKorpa(Racun korpa) {
		this.korpa = korpa;
	}

	public void sacuvajSve() {
		sacuvaj(korisnici, "./korisnici.d");
		sacuvaj(lekovi, "./lekovi.d");
		sacuvaj(recepti, "./recepti.d");
		sacuvaj(racuni, "./racuni.d");
	}

	private void sacuvaj(Object o, String putanja) {
		File f = new File(putanja);
		ObjectOutputStream objOutStream = null;
		try {
			objOutStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
			objOutStream.writeObject(o);
		} catch (IOException e1) {
		} finally {

			try {
				if (objOutStream != null)
					objOutStream.close();
			} catch (Exception e) {
			}
		}
	}

}
