package apoteka.model;

public class RedIzvestaja {
	private int kolicina;
	private float profit;
	private String nazivLeka, sifraLeka, proizvodjac;

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public float getProfit() {
		return profit;
	}

	public void setProfit(float cena) {
		this.profit = cena;
	}

	public String getNazivLeka() {
		return nazivLeka;
	}

	public void setNazivLeka(String nazivLeka) {
		this.nazivLeka = nazivLeka;
	}

	public String getSifraLeka() {
		return sifraLeka;
	}

	public void setSifraLeka(String sifraLeka) {
		this.sifraLeka = sifraLeka;
	}

	public String getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

}
