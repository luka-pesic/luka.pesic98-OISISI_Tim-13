package apoteka.stranice;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultRowSorter;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableCellRenderer;

import apoteka.Stanje;
import apoteka.model.Korisnik;
import apoteka.stranice.modelitabela.LekoviModelTabele;
import apoteka.stranice.modelitabela.ReceptiModelTabele;

public class Tabela extends JTable {

	private static final long serialVersionUID = 8900651367165240112L;

	public Tabela() {
		this.setRowSelectionAllowed(true);
		setAutoCreateRowSorter(true);
		this.setColumnSelectionAllowed(false);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setRowHeight(30);
		setShowGrid(false);
		setIntercellSpacing(new Dimension(0, 0));
		getTableHeader().setDefaultRenderer(new MyRenderer());
		setFillsViewportHeight(true);
		setAutoCreateRowSorter(true);
	}

	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		JComponent c = (JComponent) super.prepareRenderer(renderer, row, column);
		MatteBorder border = new MatteBorder(0, 0, 3, 0, new Color(135, 198, 236));

		c.setBorder(border);
		if (isRowSelected(row)) {
			c.setBackground(new Color(255, 255, 204));
		} else {
			c.setBackground(new Color(216, 236, 249));
		}

		return c;
	}

	private class MyRenderer implements TableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int col) {
			String v = (String) value;
			JLabel lab = new JLabel(v);
			JPanel pan = new JPanel();
			// pan.setBackground(new Color(182, 64, 14));
			pan.setBackground(new Color(135, 198, 236));
			pan.setBorder(new EmptyBorder(4, 4, 4, 4));
			pan.add(lab);
			return pan;

		}

	}

	public static RowFilter sakrijIzbrisane() {
		return new RowFilter<Object, Object>() {
			public boolean include(Entry<? extends Object, ? extends Object> entry) {
				Korisnik k = Stanje.getInstanca().getUlogovan();
				if (k.getUloga().equals("Admin"))
					return true;// admin vidi sve
				if (entry.getModel() instanceof LekoviModelTabele) {
					LekoviModelTabele t = (LekoviModelTabele) entry.getModel();
					return !Stanje.getInstanca().getLekovi().get((int) entry.getIdentifier()).isIzbrisan();
				} else if (entry.getModel() instanceof ReceptiModelTabele) {
					ReceptiModelTabele t = (ReceptiModelTabele) entry.getModel();
					return !Stanje.getInstanca().getRecepti().get((int) entry.getIdentifier()).isIzbrisan();
				}
				return true;
			}
		};
	}
}