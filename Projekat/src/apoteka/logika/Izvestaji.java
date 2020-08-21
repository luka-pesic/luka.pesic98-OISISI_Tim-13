package apoteka.logika;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import apoteka.Stanje;
import apoteka.model.Lek;
import apoteka.model.Racun;
import apoteka.model.RedIzvestaja;
import apoteka.model.StavkaRacuna;

public class Izvestaji {

	// ako se trazi po prizovodjacu psorledise null za prodavca i obrnuto
	// ako za sve lekove onda null,null
	public static List<RedIzvestaja> kreiraj(String apotekar, String proizvodjac) {
		LinkedHashMap<String, RedIzvestaja> ret = new LinkedHashMap<String, RedIzvestaja>();
		for (Racun r : Stanje.getInstanca().getRacuni()) {
			if (apotekar != null && !r.getProdavac().equals(apotekar))
				continue;

			for (StavkaRacuna stavka : r.getStavke()) {
				String sifraLeka = stavka.getSifraLeka();
				Lek lek = Lekovi.preuzmiPoSifri(sifraLeka);
				if (proizvodjac != null && !lek.getProzvodjac().equals(proizvodjac))
					continue;
				float zarada = (float) (stavka.getUkupnaCena() * (1.0 - r.getPopust() / 100f));
				if (ret.containsKey(sifraLeka)) {
					RedIzvestaja red = ret.get(sifraLeka);
					red.setKolicina(red.getKolicina() + stavka.getKolicina());
					red.setProfit(red.getProfit() + zarada);

				} else {

					RedIzvestaja rep = new RedIzvestaja();
					rep.setKolicina(stavka.getKolicina());
					rep.setNazivLeka(lek.getIme());
					rep.setProfit(stavka.getUkupnaCena());
					rep.setProizvodjac(lek.getProzvodjac());
					rep.setSifraLeka(lek.getSifra());
					ret.put(sifraLeka, rep);
				}
			}
		}

		return ret.values().stream().collect(Collectors.toList());
	}
}
