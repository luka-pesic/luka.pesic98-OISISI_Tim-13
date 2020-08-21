package apoteka.stranice.modelitabela;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import apoteka.Stanje;
import apoteka.logika.Recepti;
import apoteka.model.Recept;
import apoteka.model.RedIzvestaja;

public class IzvestajModelTabele extends AbstractTableModel {

	private static final long serialVersionUID = 2710362894062472488L;

	private List<String> kolone = new ArrayList<String>();
	private List<RedIzvestaja> izvestaj;

	public IzvestajModelTabele(List<RedIzvestaja> i) {
		kolone.add("Sifra");
		kolone.add("Ime");
		kolone.add("Proizvodjac");

		kolone.add("Kolicina");
		kolone.add("Profit");
		izvestaj = i;

	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public int getRowCount() {
		return izvestaj.size();
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
			return String.class;
		case 3:
			return Integer.class;
		case 4:
			return Float.class;

		default:
			return null;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		RedIzvestaja r = izvestaj.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return r.getSifraLeka();

		case 1:
			return r.getNazivLeka();

		case 2:
			return r.getProizvodjac();
		case 3:

			return r.getKolicina();

		case 4:
			return r.getProfit();

		}

		return null;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		super.setValueAt(aValue, rowIndex, columnIndex);
		if (columnIndex != 5) {
			return;
		}
		Stanje.getInstanca().getRecepti().get(rowIndex).setIzbrisan((boolean) aValue);
		;

	}

}