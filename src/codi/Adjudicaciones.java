package codi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Adjudicaciones extends javax.swing.JFrame {
    protected Object[] datos;
    protected Connection conexion;
    protected boolean guardado=false;
    protected int id;
    
    public Adjudicaciones(String s, Connection c) {
        initComponents();
        conexion=c;
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sistema de Compras Directas CODI - Oficina de Suministros");
        setIconImage(new CrearImagen().Imagen("images/app.png").getImage());
        jLabel7.setText(s);
        LlenarAnyos();
        LlenarProveedores();
        Adjudicar.setEnabled(false);
    }
    public Adjudicaciones(){
        initComponents();
    }
    public Adjudicaciones(String s, Connection c, Object[] d){
        initComponents();
        conexion=c;
        datos=d;
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sistema de Compras Directas CODI - Oficina de Suministros");
        setIconImage(new CrearImagen().Imagen("images/app.png").getImage());
        jLabel7.setText(s);
        Adjudicar.setEnabled(true);
        Guardar.setText("Actualizar");
        Guardar.setToolTipText("Guarda los cambios realizados");
        guardado=true;
        Anyo.setEnabled(false);
        Consecutivo.setEnabled(false);
        Proveedor.setEnabled(false);
        LlenarCampos();
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        Guardar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Anyo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        Consecutivo = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        Proveedor = new javax.swing.JComboBox();
        Cedulas = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        Ordenes = new javax.swing.JTextField();
        Scroll = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        Scroll1 = new javax.swing.JScrollPane();
        Tabla1 = new javax.swing.JTable();
        Adjudicar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        Observaciones = new javax.swing.JTextField();
        Monto = new javax.swing.JLabel();
        Id = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Registro de nueva Adjudicación");

        Guardar.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codi/images/anyadir.png"))); // NOI18N
        Guardar.setText("Guardar");
        Guardar.setToolTipText("Guardar los datos de la Adjudicación");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("Año:");

        Anyo.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Anyo.setToolTipText("Seleccione el año");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("Consecutivo:");

        Consecutivo.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Consecutivo.setToolTipText("Seleccione el consecutivo");
        Consecutivo.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel4.setText("Proveedor:");

        Proveedor.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Proveedor.setToolTipText("Seleccione el proveedor");

        Cedulas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Cedulas.setOpaque(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setText("Ordenes de Compra:");

        Ordenes.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Ordenes.setToolTipText("Digite las ordenes de compra, separadas por coma.");

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Scroll.setViewportView(Tabla);

        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Scroll1.setViewportView(Tabla1);

        Adjudicar.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Adjudicar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codi/images/cargar.png"))); // NOI18N
        Adjudicar.setText("Adjudicar");
        Adjudicar.setToolTipText("Adjudicar un nuevo bien");
        Adjudicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdjudicarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel6.setText("Observación:");

        Observaciones.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        Monto.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Monto.setText("Monto Total: ₵0,00");

        Id.setText("jTextField1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Cedulas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(203, 203, 203)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 848, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(202, 202, 202)
                                .addComponent(Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Adjudicar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Observaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(Monto, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Scroll, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Anyo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Consecutivo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Ordenes)))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(Anyo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Consecutivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(Ordenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Observaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Monto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cedulas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Guardar)
                    .addComponent(Adjudicar))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Anyo, Consecutivo, Ordenes, jLabel2, jLabel3, jLabel5});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 714, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        String consulta = "";
        if(guardado){
            consulta= "SELECT Bienes.Linea AS [#], Bienes.Descripcion AS [Descripción], Bienes.Cantidad, Bienes.Precio FROM "+
            "(Bienes INNER JOIN DetallesAdjudicaciones ON Bienes.Linea = DetallesAdjudicaciones.Bien) INNER JOIN "+
            "Adjudicaciones ON Adjudicaciones.Adjudicacion = DetallesAdjudicaciones.Adjudicacion "+
            "WHERE Adjudicaciones.Adjudicacion="+Id.getText()+" ORDER BY Descripcion ASC";
            if(ActualizarTabla(consulta))
                CalcularTotal();
        }
    }//GEN-LAST:event_formWindowGainedFocus

    private void AdjudicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdjudicarActionPerformed
        new Bienes("Registro de nuevo Bien",conexion,id).setVisible(true);
    }//GEN-LAST:event_AdjudicarActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        if(!guardado)
            GuardarAdjudicacion();
        else
            ActualizarAdjudicacion();
    }//GEN-LAST:event_GuardarActionPerformed
    
    @SuppressWarnings("unchecked")
	private void LlenarAnyos(){
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
            LlenarConsecutivos(Anyo.getSelectedItem().toString());
            Anyo.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    LlenarConsecutivos(Anyo.getSelectedItem().toString());
                }
            });
      	}
      	catch(SQLException sqlex){
            new Bitacora("Errores.txt",sqlex.toString(),"Adjudicaciones");
        }
    }
    
    @SuppressWarnings("unchecked")
	private void LlenarConsecutivos(String Anyo){
        Consecutivo.setEnabled(true);
        ResultSet rs;
        Statement statement;
      	try {
            statement = conexion.createStatement();
            rs = statement.executeQuery("SELECT Numero FROM Contrataciones WHERE Anyo = '"+Anyo+"'");
            rs.next();
            Consecutivo.removeAllItems();
            do{
                Consecutivo.addItem(rs.getString("Numero"));
            }while(rs.next());
            statement.close();
      	}
      	catch(SQLException sqlex){
            new Bitacora("Errores.txt",sqlex.toString(),"Adjudicaciones");
        }
    }
    
    @SuppressWarnings("unchecked")
	private void LlenarProveedores(){
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
      	}
      	catch(SQLException sqlex){
            new Bitacora("Errores.txt",sqlex.toString(),"Adjudicaciones");
        }
    }
    
    @SuppressWarnings("unchecked")
	private void LlenarCampos(){
        Id.setText(datos[1].toString());
        id=Integer.parseInt(datos[1].toString());
        //Proveedor.removeAllItems();
        Proveedor.addItem(datos[2].toString());
        Ordenes.setText(datos[3].toString());
        Observaciones.setText(datos[4].toString());
        //Anyo.removeAllItems();
        Anyo.addItem(datos[5].toString().substring(0, 4));
        //Consecutivo.removeAllItems();
        Consecutivo.addItem(datos[5].toString().substring(7, 14));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Adjudicar;
    @SuppressWarnings("rawtypes")
	private javax.swing.JComboBox Anyo;
    @SuppressWarnings("rawtypes")
	private javax.swing.JComboBox Cedulas;
    @SuppressWarnings("rawtypes")
	private javax.swing.JComboBox Consecutivo;
    private javax.swing.JButton Guardar;
    private javax.swing.JTextField Id;
    private javax.swing.JLabel Monto;
    private javax.swing.JTextField Observaciones;
    private javax.swing.JTextField Ordenes;
    @SuppressWarnings("rawtypes")
	private javax.swing.JComboBox Proveedor;
    private javax.swing.JScrollPane Scroll;
    private javax.swing.JScrollPane Scroll1;
    private javax.swing.JTable Tabla;
    private javax.swing.JTable Tabla1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    private void GuardarAdjudicacion(){
        if(!ComprobarCampos()){
            JOptionPane.showMessageDialog(null,"Ningún campo debe quedar vacío", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if(ValidarAdjudicacion()){
            String consulta = "INSERT INTO Adjudicaciones(Anyo,Numero,CedulaProveedor,OrdenesCompra,Observacion) VALUES('"+
                    Anyo.getSelectedItem().toString()+"', '"+
                    Consecutivo.getSelectedItem().toString()+"', '"+
                    Cedulas.getItemAt(Proveedor.getSelectedIndex()).toString()+"', '"+ 
                    Ordenes.getText()+"', '"+
                    Observaciones.getText()+"')";
            int resultado = 0;
            new Bitacora("Consultas.txt",consulta,"Adjudicaciones");
            try{
                Statement statement = conexion.createStatement();
                resultado = statement.executeUpdate(consulta);
                statement.close();
            }
            catch(Exception ex){
                new Bitacora("Errores.txt",ex.toString(),"Adjudicaciones");
            }
            if(resultado == 1){
                JOptionPane.showMessageDialog(null,"Registro ingresado correctamente");
                Adjudicar.setEnabled(true);
                Anyo.setEnabled(false);
                Consecutivo.setEnabled(false);
                Proveedor.setEnabled(false);
                ObtenerLineaAdjudicacion();
                guardado=true;
                Guardar.setText("Actualizar");
                Guardar.setToolTipText("Guarda los cambios realizados");
            }
            else
                JOptionPane.showMessageDialog(null,"Registro NO ingresado");
        }
        else{
            int r = 0;
            r=JOptionPane.showConfirmDialog(null,"La Adjudicación:\n"+
                    Anyo.getSelectedItem().toString()+"CD-"+Consecutivo.getSelectedItem().toString()+
                    "-CDSG para "+Proveedor.getSelectedItem().toString()+"\nYa existe\n\n¿Desea modificarla?","Error",JOptionPane.ERROR_MESSAGE);
            if(r==0||r==-1){
                Statement statement;
                String consulta="SELECT Adjudicaciones.Adjudicacion,Proveedores.RazonSocial, "+
                    "Adjudicaciones.OrdenesCompra, Adjudicaciones.Observacion, Adjudicaciones.Anyo+'SG-'+Adjudicaciones.Numero+'-CDSG' FROM "+
                    "(Adjudicaciones INNER JOIN Proveedores ON Adjudicaciones.CedulaProveedor = Proveedores.Cedula) "+
                    "WHERE Adjudicaciones.Anyo='"+Anyo.getSelectedItem().toString()+"' "+
                        "AND Adjudicaciones.Numero='"+Consecutivo.getSelectedItem().toString()+"' "+
                        "AND Adjudicaciones.CedulaProveedor='"+Cedulas.getItemAt(Proveedor.getSelectedIndex()).toString()+"'";
                try {
                    new Bitacora("Consultas.txt",consulta,"Adjudicaciones");
                    statement = conexion.createStatement();
                    ResultSet rs = statement.executeQuery(consulta);
                    rs.next();
                    Object[] d={"",rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)};
                    statement.close();
                    datos=d;
                    Adjudicar.setEnabled(true);
                    Guardar.setText("Actualizar");
                    Guardar.setToolTipText("Guarda los cambios realizados");
                    guardado=true;
                    Anyo.setEnabled(false);
                    Consecutivo.setEnabled(false);
                    Proveedor.setEnabled(false);
                    jLabel7.setText("Actualizar datos de Adjudicaciones");
                    LlenarCampos();
                } catch (SQLException ex) {
                    new Bitacora("Errores.txt",ex.toString(),"Adjudicaciones");
                }
            }
        }
    }

    private void ActualizarAdjudicacion() {
        if(!ComprobarCampos()){
            JOptionPane.showMessageDialog(null,"Ningún campo debe quedar vacío", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            String consulta = "UPDATE Adjudicaciones SET OrdenesCompra ='"+Ordenes.getText()+"', "+
                    "Observacion='"+Observaciones.getText()+"' WHERE Adjudicacion ="+Id.getText()+"";
            new Bitacora("Consultas.txt",consulta,"Adjudicaciones");
            int resultado = 0;
            try{
                Statement statement = conexion.createStatement();
                resultado = statement.executeUpdate(consulta);
                statement.close();
            }
            catch(Exception ex){
                new Bitacora("Errores.txt",ex.toString(),"Adjudicaciones");
            }
            if(resultado == 1){
                    JOptionPane.showMessageDialog(null,"Registro actualizado correctamente");
                    dispose();
            }
            else
                    JOptionPane.showMessageDialog(null,"Registro NO actualizado");
        }
    }

    private boolean ComprobarCampos(){
        if(Ordenes.getText().equals(""))
            return false;
        else if(Observaciones.getText().equals(""))
            return false;
        return true;
    }

    private boolean ActualizarTabla(String consulta) {
        int[] anchos={40,20,460,60,100};
        boolean resultado=false;
        Statement statement;
      	ResultSet resultSet;
      	try {
            statement = conexion.createStatement();
            resultSet = statement.executeQuery(consulta);
            new CrearTabla(resultSet,Tabla,Scroll,anchos,conexion,3);
            statement = conexion.createStatement();
            resultSet = statement.executeQuery(consulta);
            if(resultSet.next())
                resultado=true;
            statement.close();
      	}
      	catch(SQLException sqlex){
            new Bitacora("Errores.txt",sqlex.toString(),"Adjudicaciones");
        }
        return resultado;
    }

    private boolean ValidarAdjudicacion() {
        Statement statement;
        try {
            statement = conexion.createStatement();
            String consulta = "SELECT COUNT(Adjudicacion) FROM Adjudicaciones WHERE Anyo='"+Anyo.getSelectedItem().toString()+"' "+
                    "AND Numero='"+Consecutivo.getSelectedItem().toString()+"' "+
                    "AND CedulaProveedor='"+Cedulas.getItemAt(Proveedor.getSelectedIndex()).toString()+"'";
            ResultSet rs = statement.executeQuery(consulta);
            rs.next();
            new Bitacora("Consultas.txt",consulta,"Adjudicaciones");
            if(rs.getString(1).equals("1"))
                return false;
            statement.close();
        } catch (SQLException ex) {
            new Bitacora("Errores.txt",ex.toString(),"Adjudicaciones");
        }
        return true;
    }

    private void ObtenerLineaAdjudicacion() {
        Statement statement;
        ResultSet rs;
        try {
            statement = conexion.createStatement();
            rs = statement.executeQuery("SELECT Adjudicacion FROM Adjudicaciones ORDER BY Adjudicacion DESC");
            rs.next();
            id=Integer.parseInt(rs.getString(1));
            Id.setText(""+id);
            statement.close();
        }
        catch(SQLException sqlex){
            new Bitacora("Errores.txt",sqlex.toString(),"Adjudicaciones");
        }
    }
    
    private void CalcularTotal(){
        Statement statement;
        ResultSet rs;
        try {
            statement = conexion.createStatement();
            String consulta= "SELECT SUM(Bienes.Precio * Bienes.Cantidad) FROM "+
            "(Bienes INNER JOIN DetallesAdjudicaciones ON Bienes.Linea = DetallesAdjudicaciones.Bien) INNER JOIN "+
            "Adjudicaciones ON Adjudicaciones.Adjudicacion = DetallesAdjudicaciones.Adjudicacion "+
            "WHERE Adjudicaciones.Adjudicacion="+Id.getText();
            rs = statement.executeQuery(consulta);
            rs.next();
            DecimalFormat num = new DecimalFormat("#,###.00");
            Monto.setText("Monto Total: ₵"+num.format(Double.parseDouble(rs.getString(1))));
            statement.close();
        }
        catch(SQLException sqlex){
            new Bitacora("Errores.txt",sqlex.toString(),"Adjudicaciones");
        }
    }
}