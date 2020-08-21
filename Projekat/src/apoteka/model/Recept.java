package apoteka.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;

public class Recept implements Serializable {

	private int sifra;
	private float cena;
	private String pacijent, lekar;
	private Date datum;
	private boolean izbrisan = false;
	private LinkedHashMap<String, Integer> lekovi = new LinkedHashMap<>();

	public int getSifra() {
		return sifra;
	}

	public void setSifra(int sifra) {
		this.sifra = sifra;
	}

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}

	public String getPacijent() {
		return pacijent;
	}

	public void setPacijent(String pacijent) {
		this.pacijent = pacijent;
	}

	public String getLekar() {
		return lekar;
	}

	public void setLekar(String lekar) {
		this.lekar = lekar;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public LinkedHashMap<String, Integer> getLekovi() {
		return lekovi;
	}

	public void setLekovi(LinkedHashMap<String, Integer> lekovi) {
		this.lekovi = lekovi;
	}

	public boolean isIzbrisan() {
		return izbrisan;
	}

	public void setIzbrisan(boolean izbrisan) {
		this.izbrisan = izbrisan;
	};

}
