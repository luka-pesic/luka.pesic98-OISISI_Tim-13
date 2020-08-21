package apoteka.logika;

import java.util.List;
import java.util.stream.Collectors;

import apoteka.Stanje;
import apoteka.model.Lek;

public class Lekovi {
	public static String dodaj(Lek lek) {
		String greska = proveri(lek);
		if (!greska.equals(""))
			return greska;

		Stanje.getInstanca().getLekovi().add(lek);
		return "";
	}

	// true ako je slobodna
	private static boolean proveriSifru(String sifra) {
		return Stanje.getInstanca().getLekovi().stream().filter(l -> l.getSifra() == sifra).collect(Collectors.toList())
				.size() == 0;
	}

	public static String proveri(Lek lek) {
		String message = "";
		if (lek.getIme().equals(""))
			message += "Ime nije uneto\r\n";
		if (lek.getSifra().equals(""))
			message += "Šifra nije uneta\r\n";
		if (lek.getProzvodjac().equals(""))
			message += "Proizvoðaè nije unet\r\n";
		if (lek.getCena() <= 0)
			message += "Cena nije validna\r\n";
		if (!proveriSifru(lek.getSifra()))
			message += "Sifra veæ posotji\r\n";
		return message;
	}

	public static List<Lek> preuzmiSve() {
		return Stanje.getInstanca().getLekovi().stream().filter(lek -> !lek.isIzbrisan()).collect(Collectors.toList());
	}

	public static Lek preuzmiPoSifri(String id) {
		return Stanje.getInstanca().getLekovi().stream().filter(lek -> lek.getSifra().equals(id)).findFirst()
				.orElse(null);
	}

	public static Lek[] preuzmiBezRecepta() {
		return (Lek[]) Stanje.getInstanca().getLekovi().stream().filter(lek -> !lek.isIzbrisan() && !lek.isNaRecept())
				.collect(Collectors.toList()).toArray(new Lek[0]);
	}

	public static String[] proizvodjaci() {
		return (String[]) Stanje.getInstanca().getLekovi().stream().filter(lek -> !lek.isIzbrisan())
				.map(lek -> lek.getProzvodjac()).distinct().collect(Collectors.toList()).toArray(new String[0]);
	}

}
