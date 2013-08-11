package codi;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ModeloTabla extends DefaultTableModel {
    @SuppressWarnings("rawtypes")
	public ModeloTabla(Vector fila, Vector col){
        super(fila, col);
    }
    @SuppressWarnings("rawtypes")
	public void AgregaFila(Vector fila){
        super.addRow(fila);
    }
    @SuppressWarnings("rawtypes")
	public void AgregarCol(Vector[] col){
        super.addColumn(col);
    }
    public boolean isCellEditable(int row, int column){
        return false;
    }
    @Override
    public Class<?> getColumnClass(int colIndex){
        Class<?> clazz = Object.class;
        Object aux = getValueAt(0, colIndex);
        if(aux!=null){
            clazz = aux.getClass();
        }
        return clazz;
    }
}