package apoteka.stranice.modelitabela;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import apoteka.Stanje;
import apoteka.logika.Recepti;
import apoteka.model.Recept;

public class ReceptiModelTabele extends AbstractTableModel {

	private static final long serialVersionUID = 2710362894062472488L;

	private List<String> kolone = new ArrayList<String>();

	public ReceptiModelTabele() {
		kolone.add("Sifra");
		kolone.add("Lekar");
		kolone.add("Pacijent");
		kolone.add("Datum");
		kolone.add("Cena");
		if (Stanje.getInstanca().getUlogovan().getUloga().equals("Admin"))
			kolone.add("Izbrisan");
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 5;
	}

	@Override
	public int getRowCount() {
		return Stanje.getInstanca().getRecepti().size();
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
			return String.class;
		case 4:
			return Float.class;
		case 5:
			return Boolean.class;
		default:
			return null;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Recept r = Stanje.getInstanca().getRecepti().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return r.getSifra();

		case 1:
			return r.getLekar();

		case 2:
			return r.getPacijent();
		case 3:
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm");

			return f.format(r.getDatum());

		case 4:
			return Recepti.ukupnaCena(r);
		case 5:
			return r.isIzbrisan();

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