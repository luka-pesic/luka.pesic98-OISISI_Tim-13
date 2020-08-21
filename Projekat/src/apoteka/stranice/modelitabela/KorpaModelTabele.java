package apoteka.stranice.modelitabela;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import apoteka.Stanje;
import apoteka.logika.Lekovi;
import apoteka.logika.Recepti;
import apoteka.model.Lek;
import apoteka.model.StavkaRacuna;

public class KorpaModelTabele extends AbstractTableModel {

	private static final long serialVersionUID = 2710362894062472488L;

	private List<String> kolone = new ArrayList<String>();

	public KorpaModelTabele() {
		kolone.add("Sifra leka");
		kolone.add("Cena leka");
		kolone.add("Kolicina");
		kolone.add("Ukupna cena");
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public int getRowCount() {
		return Stanje.getInstanca().getKorpa().getStavke().size();
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
			return String.class;
		case 1:
			return Float.class;
		case 2:
			return Integer.class;
		case 3:
			return Float.class;

		default:
			return null;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		StavkaRacuna r = Stanje.getInstanca().getKorpa().getStavke().get(rowIndex);
		Lek l = Lekovi.preuzmiPoSifri(r.getSifraLeka());
		switch (columnIndex) {
		case 0:
			return l.getSifra();

		case 1:
			return l.getCena();

		case 2:
			return r.getKolicina();
		case 3:

			return r.getUkupnaCena();
		}

		return null;
	}

}