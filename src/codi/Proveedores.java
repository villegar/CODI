package codi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Proveedores extends javax.swing.JFrame {
    protected Object[] datos;
    protected Connection conexion;
    
    public Proveedores(String s, Connection c){
        initComponents();
        conexion=c;
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
                    String consulta = "INSERT INTO Proveedores(Cedula,RazonSocial) VALUES('"+
                            Cedula.getText()+"', '"+ 
                            RazonSocial.getText()+"')";
                    int resultado = 0;
                    new Bitacora("Consultas.txt",consulta,"Proveedores");
                    try{
                        Statement statement = conexion.createStatement();
                        resultado = statement.executeUpdate(consulta);
                        statement.close();
                    }
                    catch(Exception ex){
                        new Bitacora("Errores.txt",ex.toString(),"Proveedores");
                    }
                    if(resultado == 1){
                            int r = 0;
                            r=JOptionPane.showConfirmDialog(null,"Registro ingresado correctamente\n¿Desea ingresar otro?\n");
                            if(r==0||r==-1)
                                LimpiarCampos();
                            else
                                dispose();
                    }
                    else
                            JOptionPane.showMessageDialog(null,"Registro NO ingresado");
                }
            }
        });
    }
    
    public Proveedores(String s, Connection c, Object[] d){
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
        Cedula.setToolTipText("Por seguridad este campo no puede ser modificado, si debe hacerlo contacte al Administrador");
        Guardar.setText("Actualizar");
        Guardar.setToolTipText("Guarda los cambios realizados");
        Guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(!ComprobarCampos()){
                    JOptionPane.showMessageDialog(null,"Ningún campo debe quedar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    String consulta = "UPDATE Proveedores SET RazonSocial ='"+RazonSocial.getText()+"'"+
                            " WHERE Cedula ='"+Cedula.getText()+"'";
                    new Bitacora("Consultas.txt",consulta,"Proveedores");
                    int resultado = 0;
                    try{
                        Statement statement = conexion.createStatement();
                        resultado = statement.executeUpdate(consulta);
                        statement.close();
                    }
                    catch(Exception ex){
                        new Bitacora("Errores.txt",ex.toString(),"Proveedores");
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
        Cedula.setEditable(false);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        Guardar = new javax.swing.JButton();
        Limpiar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Cedula = new javax.swing.JFormattedTextField();
        RazonSocial = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Registro de nuevo Proveedor");
        jLabel7.setToolTipText("");

        Guardar.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codi/images/anyadir.png"))); // NOI18N
        Guardar.setText("Guardar");
        Guardar.setToolTipText("Guardar los datos del Proveedor");

        Limpiar.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codi/images/limpiar.png"))); // NOI18N
        Limpiar.setText("Limpiar Campos");
        Limpiar.setToolTipText("Limpiar los campos del formulario");
        Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpiarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("Cédula Jurídica:");
        jLabel1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("Razón Social:");

        try {
            Cedula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("3-###-######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        Cedula.setToolTipText("Cédula Jurídica con el siguiente formato: 3-TTT-CCCCCC");
        Cedula.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        RazonSocial.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        RazonSocial.setToolTipText("Razón Social del Proveedor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Cedula, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                            .addComponent(RazonSocial)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(Guardar)
                        .addGap(28, 28, 28)
                        .addComponent(Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(126, 126, 126))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(RazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Guardar)
                    .addComponent(Limpiar))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        LimpiarCampos();
    }//GEN-LAST:event_LimpiarActionPerformed
    
    private void LimpiarCampos(){
        Cedula.setText(null);
        RazonSocial.setText(null);
    }
    
    private void LlenarCampos(){
        Cedula.setText(datos[1].toString());
        RazonSocial.setText(datos[2].toString());
    }
    
    private boolean ComprobarCampos(){
        if(Cedula.getText().equals("3-   -      "))
            return false;
        else if(RazonSocial.getText().equals(""))
            return false;
        return true;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField Cedula;
    private javax.swing.JButton Guardar;
    private javax.swing.JButton Limpiar;
    private javax.swing.JTextField RazonSocial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}