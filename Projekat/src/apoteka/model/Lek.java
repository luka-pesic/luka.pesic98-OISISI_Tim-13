package apoteka.model;

import java.io.Serializable;

public class Lek implements Serializable{
	private String sifra, ime, prozvodjac;
	private float cena;
	private boolean izbrisan=false,naRecept;

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getProzvodjac() {
		return prozvodjac;
	}

	public void setProzvodjac(String prozvodjac) {
		this.prozvodjac = prozvodjac;
	}

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}

	public boolean isIzbrisan() {
		return izbrisan;
	}

	public void setIzbrisan(boolean izbrisan) {
		this.izbrisan = izbrisan;
	}

	public boolean isNaRecept() {
		return naRecept;
	}

	public void setNaRecept(boolean naRecept) {
		this.naRecept = naRecept;
	}

}
