package codi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Contrataciones extends javax.swing.JFrame {
    protected Object[] datos;
    protected Connection conexion;
    
    public Contrataciones(String s, Connection c){
        initComponents();
        conexion=c;
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sistema de Compras Directas CODI - Oficina de Suministros");
        setIconImage(new CrearImagen().Imagen("images/app.png").getImage());
        ActualizarAnyo();
        jLabel7.setText(s);
        Guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(!ComprobarCampos()){
                    JOptionPane.showMessageDialog(null,"Ningún campo debe quedar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    String consulta = "INSERT INTO Contrataciones(Anyo,Numero,Objeto,Fecha,MontoEstimado,FechaDI,Solicitudes) VALUES('"+
                            Anyo()+"', '"+
                            Numero.getText()+"', '"+ 
                            Objeto.getText()+"', '"+
                            Fecha.getText()+"', '"+
                            Monto.getText()+"', '"+
                            FechaDI.getText()+"', '"+
                            Solicitudes.getText()+"')";
                    int resultado = 0;
                    new Bitacora("Consultas.txt",consulta,"Contrataciones");
                    try{
                        Statement statement = conexion.createStatement();
                        resultado = statement.executeUpdate(consulta);
                        statement.close();
                    }
                    catch(Exception ex){
                        new Bitacora("Errores.txt",ex.toString(),"Contrataciones");
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
    
    public Contrataciones(String s, Connection c, Object[] d) {
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
                    String consulta = "UPDATE Contrataciones SET Objeto ='"+Objeto.getText()+"', "+
                            "Fecha='"+Fecha.getText()+"', MontoEstimado='"+Monto.getText()+
                            "', FechaDI='"+FechaDI.getText()+"', Solicitudes='"+Solicitudes.getText()+
                            "' WHERE Anyo ='"+AnyoReg()+"' AND Numero='"+Numero.getText()+"'";
                    new Bitacora("Consultas.txt",consulta,"Contrataciones");
                    int resultado = 0;
                    try{
                        Statement statement = conexion.createStatement();
                        resultado = statement.executeUpdate(consulta);
                        statement.close();
                    }
                    catch(Exception ex){
                        new Bitacora("Errores.txt",ex.toString(),"Contrataciones");
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
        Numero.setEditable(false);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContratacion = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Objeto = new javax.swing.JTextField();
        Guardar = new javax.swing.JButton();
        Limpiar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        Fecha = new javax.swing.JFormattedTextField();
        Monto = new javax.swing.JFormattedTextField();
        Numero = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        FechaDI = new javax.swing.JFormattedTextField();
        Solicitudes = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("Número de CD:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("Objeto:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("Fecha de DI:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel4.setText("Monto Estimado:         ₵");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setText("AnyoCD -");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel6.setText("- CDSG");

        Objeto.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Objeto.setToolTipText("Objeto/Descripción de la Contratación");

        Guardar.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codi/images/anyadir.png"))); // NOI18N
        Guardar.setText("Guardar");
        Guardar.setToolTipText("Guarda los datos de la contratación");

        Limpiar.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codi/images/limpiar.png"))); // NOI18N
        Limpiar.setText("Limpiar Campos");
        Limpiar.setToolTipText("Limpia los campos del formulario");
        Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpiarActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Registro de nueva Contratación Directa");

        try {
            Fecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        Fecha.setToolTipText("Fecha con el siguiente formato: dd/MM/yyyy");
        Fecha.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        Monto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        Monto.setToolTipText("Monto en colones");
        Monto.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        Numero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("000000"))));
        Numero.setToolTipText("Número de Contratación");
        Numero.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel8.setText("Fecha de CD:");

        try {
            FechaDI.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        FechaDI.setToolTipText("Fecha con el siguiente formato: dd/MM/yyyy");
        FechaDI.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        Solicitudes.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        Solicitudes.setToolTipText("Objeto/Descripción de la Contratación");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel9.setText("Solicitudes (GECO):");

        javax.swing.GroupLayout pnlContratacionLayout = new javax.swing.GroupLayout(pnlContratacion);
        pnlContratacion.setLayout(pnlContratacionLayout);
        pnlContratacionLayout.setHorizontalGroup(
            pnlContratacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContratacionLayout.createSequentialGroup()
                .addGroup(pnlContratacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContratacionLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlContratacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContratacionLayout.createSequentialGroup()
                        .addContainerGap(18, Short.MAX_VALUE)
                        .addGroup(pnlContratacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(pnlContratacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Monto, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlContratacionLayout.createSequentialGroup()
                        .addComponent(Guardar)
                        .addGap(36, 36, 36)
                        .addComponent(Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlContratacionLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Numero, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Objeto, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Fecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FechaDI, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Solicitudes))
                .addGap(18, 18, 18))
        );
        pnlContratacionLayout.setVerticalGroup(
            pnlContratacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContratacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(pnlContratacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(pnlContratacionLayout.createSequentialGroup()
                        .addGroup(pnlContratacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlContratacionLayout.createSequentialGroup()
                                .addGroup(pnlContratacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(Numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlContratacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Objeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)))
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlContratacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlContratacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Monto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContratacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FechaDI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContratacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Solicitudes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlContratacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Guardar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Limpiar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContratacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContratacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        ActualizarAnyo();
        LimpiarCampos();
    }//GEN-LAST:event_LimpiarActionPerformed
    
    private void LimpiarCampos(){
        Numero.setText(null);
        Objeto.setText(null);
        Fecha.setText(null);
        Monto.setText(null);
        FechaDI.setText(null);
        Solicitudes.setText(null);
    }
    
    private void ActualizarAnyo(){
        jLabel5.setText(Anyo()+"CD -");
    }
    
    private void ActualizarAnyo(String anyo){
        jLabel5.setText(anyo+"CD -");
    }
    
    private String Anyo(){
        return new SimpleDateFormat("yyyy").format(new Date());
    }
    
    private String AnyoReg(){
        return jLabel5.getText().substring(0, 4);
    }
    
    private void LlenarCampos(){
        ActualizarAnyo(datos[1].toString());
        DecimalFormat num = new DecimalFormat("#,###.00");
        Numero.setText(datos[2].toString());
        Objeto.setText(datos[3].toString());
        Fecha.setText(datos[4].toString());
        Monto.setText(num.format(Double.parseDouble(datos[5].toString())));
        FechaDI.setText(datos[6].toString());
        Solicitudes.setText(datos[7].toString());
    }
    
    private boolean ComprobarCampos(){
        if(Numero.getText().equals(""))
            return false;
        else if(Objeto.getText().equals(""))
            return false;
        else if(Fecha.getText().equals("  /  /    "))
            return false;
        else if(Monto.getText().equals(""))
            return false;
        else if(FechaDI.getText().equals("  /  /    "))
            return false;
        else if(Solicitudes.getText().equals(""))
            return false;
        return true;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField Fecha;
    private javax.swing.JFormattedTextField FechaDI;
    private javax.swing.JButton Guardar;
    private javax.swing.JButton Limpiar;
    private javax.swing.JFormattedTextField Monto;
    private javax.swing.JFormattedTextField Numero;
    private javax.swing.JTextField Objeto;
    private javax.swing.JTextField Solicitudes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel pnlContratacion;
    // End of variables declaration//GEN-END:variables
}