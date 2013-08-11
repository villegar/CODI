package codi;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class CrearTabla extends JFrame{
    protected JTable tabla;
    protected Connection conexion;
    protected ResultSet rs;
    protected JScrollPane scroll;
    protected int[] anchos;
    protected int formulario;
    
    public CrearTabla(ResultSet resultset, JTable t, JScrollPane s, int a[], Connection c, int f) throws SQLException{
        tabla=t;
        conexion=c;
        rs=resultset;
        scroll=s;
        anchos=a;
        formulario=f;
        Actualizar();
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void Actualizar(){
      	try{
            boolean masRegistros = rs.next();
            if(!masRegistros){
                    return;
            }
            Vector encabezados = new Vector();
            Vector filas = new Vector();
            //obtener encabezados de columnas
            ResultSetMetaData rsmd = rs.getMetaData();
            encabezados.addElement("Editar");
            for(int i=1; i<=rsmd.getColumnCount();++i)
                encabezados.addElement(rsmd.getColumnName(i));

            //Obtener informacion de las filas
            do {
                filas.addElement(obtenerSiguienteFila(rs,rsmd));
            }while(rs.next());

            //Mostrar tabla con el contenido de ResultSet
            tabla = new JTable(filas, encabezados);
            tabla.setModel(new ModeloTabla(filas, encabezados));
            tabla.setRowHeight(25);
            tabla.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    if(tabla.getSelectedColumn()==0){
                        Object[]datos = new Object[tabla.getColumnCount()];
                        for(int c=0; c<tabla.getColumnCount();c++){
                            datos[c]=tabla.getValueAt(tabla.getSelectedRow(), c);
                        }
                        if(formulario==1)
                            new Contrataciones("Actualizar datos de Contratación Directa",conexion,datos).setVisible(true);
                        else if(formulario==2)
                            new Proveedores("Actualizar datos de Proveedores",conexion,datos).setVisible(true);
                        else if(formulario==3)
                            new Bienes("Actualizar datos de Bienes",conexion,datos).setVisible(true);
                        else if(formulario==4)
                            new Adjudicaciones("Actualizar datos de Adjudicaciones",conexion,datos).setVisible(true);
                    }
                }
            });
            tabla.addMouseMotionListener(new MouseAdapter(){
                    @Override
                    public void mouseMoved(MouseEvent me){
                        int a = tabla.columnAtPoint(me.getPoint());
                        if(a==0)
                            tabla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        else
                            tabla.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    }
            });
            scroll.setViewportView(tabla);
            for(int i=0; i<tabla.getColumnCount(); i++)
                tabla.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
      	}catch(SQLException sqlex){
            new Bitacora("Errores.txt",sqlex.toString(),"Crear Tabla("+formulario+")");
        }catch(Exception e){
            new Bitacora("Errores.txt",e.toString(),"Crear Tabla("+formulario+")");
        }
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private Vector obtenerSiguienteFila(ResultSet rs, ResultSetMetaData rsmd){
        try{
            Vector filaActual = new Vector();
            filaActual.addElement(new CrearImagen().Imagen("images/actualizar.png"));
            for(int i = 1; i <= rsmd.getColumnCount(); ++i)
                    switch(rsmd.getColumnType(i)){
                    case Types.VARCHAR:
                                    filaActual.addElement(rs.getString(i));
                                    break;
                    case Types.INTEGER:
                                    filaActual.addElement(new Long(rs.getLong(i)));
                                    break;
                    case Types.DOUBLE:
                                    filaActual.addElement(rs.getDouble(i));
                                    break;
                    default:
                                    filaActual.addElement(rs.getString(i));
                                    break;
             }
            return filaActual;
        }catch(SQLException sqlex){
            new Bitacora("Errores.txt",sqlex.toString(),"Crear Tabla");
        }catch(Exception e){
            new Bitacora("Errores.txt",e.toString(),"Crear Tabla");
        }
        return null;
    }
}