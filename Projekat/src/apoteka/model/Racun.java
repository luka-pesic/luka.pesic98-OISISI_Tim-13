package apoteka.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Racun {
	private String kupac, prodavacy;
	private Date datum;
	private int popust;
	private float placeno;
	private List<StavkaRacuna> stavke = new LinkedList<>();

	public String getKupac() {
		return kupac;
	}

	public void setKupac(String kupac) {
		this.kupac = kupac;
	}

	public String getProdavacy() {
		return prodavacy;
	}

	public void setProdavacy(String prodavacy) {
		this.prodavacy = prodavacy;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public int getPopust() {
		return popust;
	}

	public void setPopust(int popust) {
		this.popust = popust;
	}

	public float getPlaceno() {
		return placeno;
	}

	public void setPlaceno(float placeno) {
		this.placeno = placeno;
	}

	public List<StavkaRacuna> getStavke() {
		return stavke;
	}

	public void setStavke(List<StavkaRacuna> stavke) {
		this.stavke = stavke;
	}

}
