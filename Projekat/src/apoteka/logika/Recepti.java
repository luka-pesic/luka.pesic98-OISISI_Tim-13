package apoteka.logika;

import java.util.Date;
import java.util.Map;

import apoteka.Stanje;
import apoteka.model.Lek;
import apoteka.model.Recept;

public class Recepti {

	public static float ukupnaCena(Recept r) {
		Float ret = 0f;
		for (String s : r.getLekovi().keySet()) {
			Lek l = Lekovi.preuzmiPoSifri(s);
			ret += r.getLekovi().get(s) * l.getCena();
		}
		return ret;
	}

	public static void dodaj(Lek lek, int kolicina) {
		Map<String, Integer> lekovi = Stanje.getInstanca().getTrenutniRecept().getLekovi();
		Integer kol = lekovi.get(lek.getSifra());
		if (kol != null) {
			kolicina += kol;
		}
		lekovi.put(lek.getSifra(), kolicina);
	}

	public static void kreiraj(String text) {
		Recept r = Stanje.getInstanca().getTrenutniRecept();
		r.setDatum(new Date());
		r.setLekar(Stanje.getInstanca().getUlogovan().getKorisnickoIme());
		r.setSifra((Stanje.getInstanca().getRecepti().size() + 1));
		r.setCena((ukupnaCena((r))));
		Stanje.getInstanca().setTrenutniRecept((new Recept()));
		Stanje.getInstanca().getRecepti().add(r);
	}

	public static Recept pronadji(int sifra) {
		return Stanje.getInstanca().getRecepti().stream().filter(r -> !r.isIzbrisan() && r.getSifra() == sifra)
				.findFirst().orElse(null);
	}
}
