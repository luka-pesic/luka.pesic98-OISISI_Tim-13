package apoteka.stranice.modelitabela;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import apoteka.Stanje;
import apoteka.model.Lek;

public class LekoviModelTabele extends AbstractTableModel {

	private static final long serialVersionUID = 2710362894062472488L;

	private List<String> kolone = new ArrayList<String>();

	public LekoviModelTabele() {
		kolone.add("Sifra");
		kolone.add("Naziv");
		kolone.add("Proizvodjac");
		kolone.add("Cena");
		kolone.add("Recept");
		if (Stanje.getInstanca().getUlogovan().getUloga().equals("Admin"))
			kolone.add("Izbrisan");
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return !Stanje.getInstanca().getUlogovan().getUloga().equals("Lekar") && columnIndex != 0;
	}

	@Override
	public int getRowCount() {
		return Stanje.getInstanca().getLekovi().size();
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
			return Float.class;
		case 4:
		case 5:
			return Boolean.class;
		default:
			return null;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Lek lek = Stanje.getInstanca().getLekovi().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return lek.getSifra();

		case 1:
			return lek.getIme();

		case 2:
			return lek.getProzvodjac();
		case 3:
			return lek.getCena();
		case 4:
			return lek.isNaRecept();
		case 5:
			return lek.isIzbrisan();

		}

		return null;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Lek lek = Stanje.getInstanca().getLekovi().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return;
		case 1:
			String v = (String) aValue;
			if (!v.equals(""))
				lek.setIme(v);
			break;
		case 2:
			String v1 = (String) aValue;
			lek.setProzvodjac(v1);
			break;
		case 3:
			Float price = (Float) aValue;
			if (price > 0)
				lek.setCena(price);
			break;

		case 4:
			lek.setNaRecept((boolean) aValue);
			break;

		case 5:
			lek.setIzbrisan(((boolean) aValue));
			break;

		}
	}

}