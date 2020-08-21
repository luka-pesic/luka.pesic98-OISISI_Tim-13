package apoteka.logika;

import java.util.Date;
import java.util.List;

import apoteka.Stanje;
import apoteka.model.Lek;
import apoteka.model.Racun;
import apoteka.model.Recept;
import apoteka.model.StavkaRacuna;

public class Prodaja {

	public static void dodajRecept(Recept r) {
		for (String sifra : r.getLekovi().keySet()) {
			Lek lek = Lekovi.preuzmiPoSifri(sifra);
			dodajLek(lek, r.getLekovi().get(sifra));
		}
	}

	public static int izracunajPopust(String name) {
		// popust se racuna tako sto se posmatra koliko je potrosio ovog meseca ako je
		// >5000->20%, >1000>-10, samo da ime onda 5%
		if (name == null || name.equals(""))
			return 0;
		int mesec = new Date().getMonth();
		int godina = new Date().getYear();
		double potroseno = Stanje.getInstanca().getRacuni().stream().filter(racun -> racun.getKupac().equals(name))
				.filter(racun -> racun.getDatum().getMonth() == mesec && racun.getDatum().getYear() == godina)
				.mapToDouble(racun -> racun.getPlaceno()).sum();
		if (potroseno >= 5000)
			return 20;
		if (potroseno >= 1000)
			return 10;
		return 5;
	}

	public static float izracunajUkupnuCenu() {
		float ukupno = 0;
		for (StavkaRacuna a : Stanje.getInstanca().getKorpa().getStavke()) {
			ukupno += a.getUkupnaCena();
		}
		return ukupno;
	}

	public static void dodajLek(Lek lek, int kolicina) {
		Racun korpa = Stanje.getInstanca().getKorpa();

		List<StavkaRacuna> stavke = korpa.getStavke();
		for (int i = 0; i < stavke.size(); i++) {
			StavkaRacuna stavka = stavke.get(i);
			if (stavka.getSifraLeka().equals(lek.getSifra())) {
				stavka.setKolicina(stavka.getKolicina() + kolicina);
				stavka.setUkupnaCena(stavka.getKolicina() * lek.getCena());
				return;
			}
		}
		StavkaRacuna stavka = new StavkaRacuna();
		stavka.setSifraLeka(lek.getSifra());
		stavka.setUkupnaCena(lek.getCena() * kolicina);
		stavka.setKolicina(kolicina);
		korpa.getStavke().add(stavka);
	}

	public static void kupi(String kupac) {
		Racun korpa = Stanje.getInstanca().getKorpa();
		korpa.setKupac(kupac);
		korpa.setProdavac(Stanje.getInstanca().getUlogovan().getKorisnickoIme());
		korpa.setDatum(new Date());
		korpa.setPopust(izracunajPopust(korpa.getKupac()));
		korpa.setPlaceno(izracunajUkupnuCenu());
		Stanje.getInstanca().getRacuni().add(korpa);
		Stanje.getInstanca().setKorpa(new Racun());
	}

}
