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
public class Bienes extends javax.swing.JFrame {
    protected Object[] datos;
    protected Connection conexion;
    protected int adjudicacion;
    
    public Bienes(String s, Connection c, int a){
        initComponents();
        conexion=c;
        adjudicacion=a;
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sistema de Compras Directas CODI - Oficina de Suministros");
        setIconImage(new CrearImagen().Imagen("images/app.png").getImage());
        jLabel7.setText(s);
        Guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(!ComprobarCampos()){
                    JOptionPane.showMessageDialog(null,"Ningún campo debe quedar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    String consulta = "INSERT INTO Bienes(Descripcion,Precio,Cantidad) VALUES('"+
                            Descripcion.getText()+"', '"+ 
                            Precio.getText()+"', '"+
                            Cantidad.getText()+"')";
                    int resultado = 0;
                    new Bitacora("Consultas.txt",consulta,"Bienes");
                    try{
                        Statement statement = conexion.createStatement();
                        resultado = statement.executeUpdate(consulta);
                        statement.close();
                    }
                    catch(Exception ex){
                        new Bitacora("Errores.txt",ex.toString(),"Bienes");
                    }
                    if(resultado == 1){
                        Statement statement;
                        try {
                            statement = conexion.createStatement();
                            ResultSet rs = statement.executeQuery("SELECT Linea FROM Bienes ORDER BY Linea DESC");
                            rs.next();
                            int linea= Integer.parseInt(rs.getString("Linea"));
                            consulta = "INSERT INTO DetallesAdjudicaciones(Adjudicacion,Bien) VALUES('"+
                                adjudicacion+"', "+
                                linea+")";
                            new Bitacora("Consultas.txt",consulta,"Bienes");
                            statement.executeUpdate(consulta);
                            statement.close();
                            int r = 0;
                            r=JOptionPane.showConfirmDialog(null,"Registro ingresado correctamente\n¿Desea ingresar otro?\n");
                            if(r==0||r==-1)
                                LimpiarCampos();
                            else
                                dispose();
                        } catch (SQLException ex) {
                            new Bitacora("Errores.txt",ex.toString(),"Bienes");
                        }
                    }
                    else
                        JOptionPane.showMessageDialog(null,"Registro NO ingresado");
                }
            }
        });
    }
    
    public Bienes(String s, Connection c, Object[] d){
        initComponents();
        conexion=c;
        datos=d;
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sistema de Compras Directas CODI - Oficina de Suministros");
        setIconImage(new CrearImagen().Imagen("images/app.png").getImage());
        LlenarCampos();
        jLabel7.setText(s);
        Guardar.setText("Actualizar");
        Guardar.setToolTipText("Guarda los cambios realizados");
        Guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(!ComprobarCampos()){
                    JOptionPane.showMessageDialog(null,"Ningún campo debe quedar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    String consulta = "UPDATE Bienes SET Descripcion ='"+Descripcion.getText()+"', "+
                            "Precio='"+Precio.getText()+"', Cantidad='"+Cantidad.getText()+"' "+
                            " WHERE Linea ="+Consecutivo.getText()+"";
                    new Bitacora("Consultas.txt",consulta,"Bienes");
                    int resultado = 0;
                    try{
                        Statement statement = conexion.createStatement();
                        resultado = statement.executeUpdate(consulta);
                        statement.close();
                    }
                    catch(Exception ex){
                        new Bitacora("Errores.txt",ex.toString(),"Bienes");
                    }
                    if(resultado == 1){
                            JOptionPane.showMessageDialog(null,"Registro actualizado correctamente");
                            dispose();
                    }
                    else
                            JOptionPane.showMessageDialog(null,"Registro NO actualizado");
                }
            }
        });
        Limpiar.setEnabled(false);
        Consecutivo.setEditable(false);
    }
    private void LimpiarCampos(){
        Consecutivo.setText(null);
        Descripcion.setText(null);
        Cantidad.setText(null);
        Precio.setText(null);
    }
    
    private void LlenarCampos(){
        Consecutivo.setText(datos[1].toString());
        Descripcion.setText(datos[2].toString());
        Cantidad.setText(datos[3].toString());
        DecimalFormat num = new DecimalFormat("#,###.00");
        Precio.setText(num.format(Double.parseDouble(datos[4].toString())));
    }
    
    private boolean ComprobarCampos(){
        if(Descripcion.getText().equals(""))
            return false;
        else if(Cantidad.getText().equals(""))
            return false;
        else if(Precio.getText().equals(""))
            return false;
        return true;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        Limpiar = new javax.swing.JButton();
        Cantidad = new javax.swing.JTextField();
        Descripcion = new javax.swing.JTextField();
        Precio = new javax.swing.JFormattedTextField();
        Guardar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Consecutivo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel4.setText("Precio:       ₵");

        Limpiar.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codi/images/limpiar.png"))); // NOI18N
        Limpiar.setText("Limpiar Campos");
        Limpiar.setToolTipText("Limpiar los campos del formulario");
        Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpiarActionPerformed(evt);
            }
        });

        Cantidad.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Cantidad.setToolTipText("Cantidad a solicitar");

        Descripcion.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Descripcion.setToolTipText("Descripción breve del bien");

        Precio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        Precio.setToolTipText("Precio en colones");
        Precio.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        Guardar.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codi/images/anyadir.png"))); // NOI18N
        Guardar.setText("Guardar");
        Guardar.setToolTipText("Guardar los datos del Bien");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("Cantidad:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("Consecutivo:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("Descripción:");

        Consecutivo.setEditable(false);
        Consecutivo.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Consecutivo.setToolTipText("Será asignado automáticamente");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Registro de nuevo Bien");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Guardar)
                        .addGap(28, 28, 28)
                        .addComponent(Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 125, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(115, 115, 115))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Precio))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Descripcion)
                            .addComponent(Cantidad, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Consecutivo))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Consecutivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Guardar)
                    .addComponent(Limpiar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        LimpiarCampos();
    }//GEN-LAST:event_LimpiarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Cantidad;
    private javax.swing.JTextField Consecutivo;
    private javax.swing.JTextField Descripcion;
    private javax.swing.JButton Guardar;
    private javax.swing.JButton Limpiar;
    private javax.swing.JFormattedTextField Precio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}