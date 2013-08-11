/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;

@SuppressWarnings("unused")
public class PruebaConsultas {
    protected Connection conexion;
    public PruebaConsultas(){
        try{
        	String url = "jdbc:odbc:CODI";
        	Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
        	conexion = DriverManager.getConnection(url);
      	}
      	catch(Exception ex){
                new Bitacora("Errores.txt",ex.toString(),"Interfaz");
         	JOptionPane.showMessageDialog(null,"¡Ah ocurrido un error con la Base de Datos! \n"+
                        "Por seguridad la aplicación finalizara su ejecución","Estado",
         		JOptionPane.ERROR_MESSAGE);
                System.exit(0);
      	}
    }
    
    private void ConsultaSelect(String Consulta){
        Statement statement;
      	ResultSet rs;
      	try {
            statement = conexion.createStatement();
            rs = statement.executeQuery(Consulta);
            ResultSetMetaData rsmd = rs.getMetaData();
            rs.next();
            do{
               for(int i = 1; i <= rsmd.getColumnCount(); ++i)
                   System.out.println(rs.getString(i));
            }while(rs.next());
            statement.close();
      	}
      	catch(SQLException sqlex){
            new Bitacora("Errores.txt",sqlex.toString(),"Interfaz");
        }
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String consulta="SELECT Adjudicaciones.Adjudicacion AS [#], Proveedores.RazonSocial AS [Proveedor], "+
                        "OrdenesCompra AS [OC], Observacion AS [Observación], "+
                        "DetallesContrataciones.Anyo+'SG-'+DetallesContrataciones.Contratacion+'-CDSG' AS[Contratación Directa] FROM "+
                        "(Adjudicaciones INNER JOIN DetallesContrataciones ON Adjudicaciones.Adjudicacion = DetallesContrataciones.Adjudicacion) "+
                        "INNER JOIN Proveedores ON Adjudicaciones.CedulaProveedor = Proveedores.Cedula";
                consulta = "SELECT COUNT(Adjudicacion) FROM Adjudicaciones WHERE Anyo='2014' "+
                    "AND Numero='000002' "+
                    "AND CedulaProveedor='3-101-123456'";
                consulta="SELECT Adjudicaciones.Adjudicacion AS [#], Proveedores.RazonSocial AS [Proveedor], "+
                "Adjudicaciones.OrdenesCompra AS [Ordenes de Compra], Adjudicaciones.Observacion AS [Observación], "+
                "Adjudicaciones.Anyo+'SG-'+Adjudicaciones.Numero+'-CDSG' AS[Contratación Directa] FROM "+
                "Adjudicaciones INNER JOIN Proveedores ON Adjudicaciones.CedulaProveedor = Proveedores.Cedula";
                consulta="SELECT Adjudicacion FROM Adjudicaciones ORDER BY Adjudicacion DESC";
                //consulta="SELECT "+
                //        "DetallesContrataciones.Anyo+'SG-'+DetallesContrataciones.Contratacion+'-CDSG' AS[Contratación Directa] FROM DetallesContrataciones ";
                consulta = "SELECT Bienes.Linea AS [#], Bienes.Descripcion AS [Descripción], Bienes.Cantidad, Bienes.Precio FROM "+
                        "(Bienes INNER JOIN DetallesAdjudicaciones ON Bienes.Linea = DetallesAdjudicaciones.Bien) INNER JOIN "+
                        "Adjudicaciones ON Adjudicaciones.Adjudicacion = DetallesAdjudicaciones.Adjudicacion "+
                        "ORDER BY Descripcion ASC";
                consulta="SELECT SUM(Adjudicacion) FROM Adjudicaciones";     
                consulta="SELECT Bienes.Descripcion, Adjudicaciones.Numero, Adjudicaciones.Anyo, Contrataciones.Fecha, Adjudicaciones.OrdenesCompra FROM ((Bienes INNER JOIN DetallesAdjudicaciones ON Bienes.Linea = DetallesAdjudicaciones.Bien) INNER JOIN Adjudicaciones ON Adjudicaciones.Adjudicacion = DetallesAdjudicaciones.Adjudicacion) INNER JOIN Contrataciones ON Adjudicaciones.Numero = Contrataciones.Numero AND Adjudicaciones.Anyo = Contrataciones.Anyo";
                //consulta="SELECT Bienes.Descripcion, Adjudicaciones.Numero, Adjudicaciones.Anyo, Adjudicaciones.OrdenesCompra FROM (Bienes INNER JOIN DetallesAdjudicaciones ON Bienes.Linea = DetallesAdjudicaciones.Bien) INNER JOIN Adjudicaciones ON Adjudicaciones.Adjudicacion = DetallesAdjudicaciones.Adjudicacion";
                consulta="SELECT SUM(Bienes.Precio * Bienes.Cantidad) AS Monto, Bienes.Descripcion, Adjudicaciones.Numero, Adjudicaciones.Observacion, Contrataciones.Fecha, Contrataciones.FechaDI, Contrataciones.Solicitudes, Adjudicaciones.OrdenesCompra FROM ((Bienes INNER JOIN DetallesAdjudicaciones ON Bienes.Linea = DetallesAdjudicaciones.Bien) INNER JOIN Adjudicaciones ON Adjudicaciones.Adjudicacion = DetallesAdjudicaciones.Adjudicacion) INNER JOIN Contrataciones ON Adjudicaciones.Numero = Contrataciones.Numero WHERE Contrataciones.Anyo = '2013' ORDER BY Adjudicaciones.Numero ASC";
                consulta= "SELECT SUM(Bienes.Precio * Bienes.Cantidad) FROM "+
            "(Bienes INNER JOIN DetallesAdjudicaciones ON Bienes.Linea = DetallesAdjudicaciones.Bien) INNER JOIN "+
            "Adjudicaciones ON Adjudicaciones.Adjudicacion = DetallesAdjudicaciones.Adjudicacion "+
            "WHERE Adjudicaciones.Anyo='2013'";
                new PruebaConsultas().ConsultaSelect(consulta);
            }
        });
    }
}
