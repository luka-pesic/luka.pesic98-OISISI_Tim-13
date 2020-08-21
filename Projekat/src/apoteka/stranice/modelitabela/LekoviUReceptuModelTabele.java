package apoteka.stranice.modelitabela;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import apoteka.logika.Lekovi;
import apoteka.model.Lek;
import apoteka.model.Recept;

public class LekoviUReceptuModelTabele extends AbstractTableModel {

	private static final long serialVersionUID = 2710362894062472488L;
	private List<String> kolone = new ArrayList<String>();
	private Recept recept;

	public LekoviUReceptuModelTabele(Recept r) {
		kolone.add("Sifra");
		kolone.add("Naziv ");
		kolone.add("Kolicina");
		kolone.add("Cena");
		recept = r;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public int getRowCount() {
		return recept.getLekovi().size();
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
			return String.class;
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
		int i = 0;

		for (String id : recept.getLekovi().keySet()) {
			if (i == rowIndex) {
				Integer kolicina = recept.getLekovi().get(id);
				Lek lek = Lekovi.preuzmiPoSifri(id);
				switch (columnIndex) {
				case 0:
					return lek.getSifra();
				case 1:
					return lek.getIme();
				case 2:
					return kolicina;
				case 3:
					return kolicina * lek.getCena();
				}

			}
			i++;
		}

		return null;
	}

}
