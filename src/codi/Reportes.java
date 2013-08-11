package codi;

import java.awt.Desktop;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class Reportes{
    public Connection conexion;
    protected int tReporte;
    @SuppressWarnings("rawtypes")
	protected JComboBox Proveedor, Cedulas, Anyo;
    protected JFrame padre;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Reportes(Connection c, int i, JFrame p){
        conexion = c;
        tReporte = i;
        Proveedor = new JComboBox();
        Cedulas = new JComboBox();
        Anyo = new JComboBox();
        padre = p;
        Map param = new HashMap();
        param.put("logo", new CrearImagen().Imagen("images/logo.jpg"));
        p.setTitle("Sistema de Compras Directas CODI - Generando Reporte");
        if(i==0){
            try{
                String anyo = JOptionPane.showInputDialog(null,"Seleccione el año","Opciones",JOptionPane.QUESTION_MESSAGE,
                        null,LlenarAnyos(),Anyo.getItemAt(0).toString()).toString();
                param.put("anyo", anyo);
                param.put("monto_total",CalcularTotal(anyo));
                CrearReporte(param,"CODI-Anyo.jasper","CODI-"+anyo+".pdf");
            }
            catch(Exception e){
                new Bitacora("Errores.txt",e.toString(),"Reportes("+tReporte+")");
            }
        }
        else if(i==1){
            try{
                String proveedor = JOptionPane.showInputDialog(null,"Seleccione el proveedor","Opciones",JOptionPane.QUESTION_MESSAGE,
                        null,LlenarProveedores(),Proveedor.getItemAt(0).toString()).toString();
                for(int f=0; f<Proveedor.getItemCount(); f++){
                    if(Proveedor.getItemAt(f).toString().equals(proveedor)){
                        param.put("cedula", Cedulas.getItemAt(f));
                    }
                }
                CrearReporte(param,"CODI-CotratacionesProveedor.jasper","CODI-"+proveedor+".pdf");
            }
            catch(Exception e){
                new Bitacora("Errores.txt",e.toString(),"Reportes("+tReporte+")");
            }
        }
        else if(i==2){
            CrearReporte(param,"CODI-Proveedores.jasper","CODI-Proveedores.pdf");
        }
        p.setTitle("Sistema de Compras Directas CODI - Oficina de Suministros");
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void CrearReporte(Map param, String archivo, String nombrePDF){
        try{
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("reportes/"+archivo));
            JasperPrint print = JasperFillManager.fillReport(reporte, param, conexion);
            JasperExportManager.exportReportToPdfFile(print,nombrePDF);
            int r = 0;
                r=JOptionPane.showConfirmDialog(null,"Reporte creado correctamente\n¿Desea verlo?\n");
                if(r==0||r==-1){
                    try{
                        //JasperViewer.viewReport(print, false);
                        File file = new File(nombrePDF);
                        Desktop.getDesktop().open(file);
                    }
                    catch(Exception e){
                        new Bitacora("Errores.txt",e.toString(),"Reportes("+tReporte+")");
                    }
                }
        }
        catch(Exception e){
            new Bitacora("Errores.txt",e.toString(),"Reportes("+tReporte+")");
        }
    }
    
    private String CalcularTotal(String anyo){
        Statement statement;
        ResultSet rs;
        try {
            statement = conexion.createStatement();
            String consulta= "SELECT SUM(Bienes.Precio * Bienes.Cantidad) FROM ((Bienes "+
                    "INNER JOIN DetallesAdjudicaciones ON Bienes.Linea = DetallesAdjudicaciones.Bien) "+
                    "INNER JOIN Adjudicaciones ON Adjudicaciones.Adjudicacion = DetallesAdjudicaciones.Adjudicacion) "+
                    "INNER JOIN Contrataciones ON Adjudicaciones.Numero = Contrataciones.Numero "+
                    "WHERE Contrataciones.Anyo='"+anyo+"'";
            new Bitacora("Consultas.txt",consulta,"Reporte General");
            rs = statement.executeQuery(consulta);
            rs.next();
            return rs.getString(1);
        }
        catch(SQLException sqlex){
            new Bitacora("Errores.txt",sqlex.toString(),"Reporte General");
        }
        return "0";
    }
    
    @SuppressWarnings("unchecked")
	private Object[] LlenarProveedores(){
        ResultSet rs;
        Statement statement;
      	try {
            statement = conexion.createStatement();
            rs = statement.executeQuery("SELECT * FROM Proveedores");
            rs.next();
            Proveedor.removeAllItems();
            Cedulas.removeAllItems();
            do{
                Proveedor.addItem(rs.getString("RazonSocial"));
                Cedulas.addItem(rs.getString("Cedula"));
            }while(rs.next());
            statement.close();
            Object o[]= new Object[Proveedor.getItemCount()];
            for(int i=0;i<Proveedor.getItemCount(); i++)
                o[i]=Proveedor.getItemAt(i);
            return o;
      	}
      	catch(SQLException sqlex){
            new Bitacora("Errores.txt",sqlex.toString(),"Reportes");
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
	private Object[] LlenarAnyos(){
        ResultSet rs;
        Statement statement;
      	try {
            statement = conexion.createStatement();
            rs = statement.executeQuery("SELECT Anyo FROM Contrataciones GROUP BY Anyo");
            rs.next();
            Anyo.removeAllItems();
            do{
                Anyo.addItem(rs.getString("Anyo"));
            }while(rs.next());
            statement.close();
            Object o[]= new Object[Anyo.getItemCount()];
            for(int i=0;i<Anyo.getItemCount(); i++)
                o[i]=Anyo.getItemAt(i);
            return o;
      	}
      	catch(SQLException sqlex){
            new Bitacora("Errores.txt",sqlex.toString(),"Reportes");
        }
        return null;
    }
}