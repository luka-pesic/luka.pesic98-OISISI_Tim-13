package apoteka.stranice.modelitabela;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import apoteka.Stanje;
import apoteka.model.Korisnik;

public class KorisniciModelTabele extends AbstractTableModel {

	private static final long serialVersionUID = 2710362894062472488L;

	private List<String> kolone = new ArrayList<String>();

	public KorisniciModelTabele() {
		kolone.add("Korisnicko ime");
		kolone.add("Ime");
		kolone.add("Prezime");
		kolone.add("Uloga");
		kolone.add("Izbrisan");
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 4;
	}

	@Override
	public int getRowCount() {
		return Stanje.getInstanca().getKorisnici().size();
	}

	@Override
	public int getColumnCount() {
		return kolone.size();
	}

	@Override
	public String getColumnName(int column) {
		return kolone.get(column);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
		case 1:
		case 2:
		case 3:
			return String.class;
		case 4:
			return Boolean.class;
		default:
			return null;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Korisnik korisnik = Stanje.getInstanca().getKorisnici().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return korisnik.getKorisnickoIme();

		case 1:
			return korisnik.getIme();

		case 2:
			return korisnik.getPrezime();
		case 3:
			return korisnik.getUloga();
		case 4:
			return korisnik.isIzbrisan();

		}

		return null;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex != 4) {
			return;
		}
		Stanje.getInstanca().getKorisnici().get(rowIndex).setIzbrisan((boolean) aValue);
	}

}