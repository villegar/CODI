package codi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Interfaz extends javax.swing.JFrame{
    protected Connection conexion;
    protected int formulario=1;
    protected fondo pnlFondo;
    protected int color=4;
    
    public Interfaz() {
        initComponents();
        pnlReportes.setVisible(false);
        try{
            String url = "jdbc:odbc:CODI";
            Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
            conexion = DriverManager.getConnection(url);
      	}
      	catch(ClassNotFoundException | SQLException ex){
            new Bitacora("Errores.txt",ex.toString(),"Interfaz");
            JOptionPane.showMessageDialog(null,"¡Ah ocurrido un error con la Base de Datos! \n"+
                    "Por seguridad la aplicación finalizara su ejecución","Estado",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
      	}
        pnlFondo = new fondo(titulo,this,true);
        pnlFondo.setBounds(0,0,700,330);
        add(pnlFondo);
        Thread hilo = new Thread(pnlFondo);
        hilo.start();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        pnlReportes = new fondo(titulo,this,false);
        txtBusqueda = new javax.swing.JTextField();
        titulo = new javax.swing.JLabel();
        Cerrar = new javax.swing.JButton();
        scroller = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        Menu = new javax.swing.JMenuBar();
        Editar = new javax.swing.JMenu();
        mCD = new javax.swing.JMenu();
        NuevasCD = new javax.swing.JMenuItem();
        ConsultarCD = new javax.swing.JMenuItem();
        mAdjudicaciones = new javax.swing.JMenu();
        NuevasAdjudicaciones = new javax.swing.JMenuItem();
        ConsultarAdjudicaciones = new javax.swing.JMenuItem();
        Proveedores = new javax.swing.JMenu();
        NuevosProveedores = new javax.swing.JMenuItem();
        ConsultarProveedores = new javax.swing.JMenuItem();
        mBienes = new javax.swing.JMenu();
        ConsultaBienes = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        Reportes = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        rCAnyo = new javax.swing.JMenuItem();
        rCProveedor = new javax.swing.JMenuItem();
        rProveedores = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Compras Directas CODI - Oficina de Suministros");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(new CrearImagen().Imagen("images/app.png").getImage());
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
        });

        jLayeredPane1.setBackground(null);

        pnlReportes.setBackground(null);
        pnlReportes.setOpaque(false);

        txtBusqueda.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtBusqueda.setToolTipText("Digite el Objeto de la Contratación que desea buscar");
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });

        titulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titulo.setText("Consulta y Modificación de Contratación Directa");

        Cerrar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codi/images/quit.png"))); // NOI18N
        Cerrar.setToolTipText("Cierra el formulario actual");
        Cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarActionPerformed(evt);
            }
        });

        scroller.setBackground(null);
        scroller.setOpaque(false);

        Tabla.setAutoCreateColumnsFromModel(false);
        Tabla.setAutoCreateRowSorter(true);
        Tabla.setModel(new ModeloTabla(null,null));
        Tabla.setOpaque(false);
        scroller.setViewportView(Tabla);

        javax.swing.GroupLayout pnlReportesLayout = new javax.swing.GroupLayout(pnlReportes);
        pnlReportes.setLayout(pnlReportesLayout);
        pnlReportesLayout.setHorizontalGroup(
            pnlReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlReportesLayout.createSequentialGroup()
                .addGroup(pnlReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlReportesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtBusqueda))
                    .addGroup(pnlReportesLayout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(titulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                        .addComponent(Cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlReportesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scroller)))
                .addContainerGap())
        );
        pnlReportesLayout.setVerticalGroup(
            pnlReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlReportesLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titulo)
                    .addComponent(Cerrar))
                .addGap(12, 12, 12)
                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scroller, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlReportes.setBounds(0, 0, 700, 330);
        jLayeredPane1.add(pnlReportes, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Editar.setText("Archivo");

        mCD.setText("Contrataciones Directas");

        NuevasCD.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        NuevasCD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codi/images/anyadir.png"))); // NOI18N
        NuevasCD.setText("Nueva");
        NuevasCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevasCDActionPerformed(evt);
            }
        });
        mCD.add(NuevasCD);

        ConsultarCD.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        ConsultarCD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codi/images/buscar.png"))); // NOI18N
        ConsultarCD.setText("Consultar y Modificar");
        ConsultarCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultarCDActionPerformed(evt);
            }
        });
        mCD.add(ConsultarCD);

        Editar.add(mCD);

        mAdjudicaciones.setText("Adjudicaciones");

        NuevasAdjudicaciones.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        NuevasAdjudicaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codi/images/anyadir.png"))); // NOI18N
        NuevasAdjudicaciones.setText("Nueva");
        NuevasAdjudicaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevasAdjudicacionesActionPerformed(evt);
            }
        });
        mAdjudicaciones.add(NuevasAdjudicaciones);

        ConsultarAdjudicaciones.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        ConsultarAdjudicaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codi/images/buscar.png"))); // NOI18N
        ConsultarAdjudicaciones.setText("Consultar y Modificar");
        ConsultarAdjudicaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultarAdjudicacionesActionPerformed(evt);
            }
        });
        mAdjudicaciones.add(ConsultarAdjudicaciones);

        Editar.add(mAdjudicaciones);

        Proveedores.setText("Proveedores");

        NuevosProveedores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        NuevosProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codi/images/anyadir.png"))); // NOI18N
        NuevosProveedores.setText("Nuevo");
        NuevosProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevosProveedoresActionPerformed(evt);
            }
        });
        Proveedores.add(NuevosProveedores);

        ConsultarProveedores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        ConsultarProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codi/images/buscar.png"))); // NOI18N
        ConsultarProveedores.setText("Consultar y Modificar");
        ConsultarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultarProveedoresActionPerformed(evt);
            }
        });
        Proveedores.add(ConsultarProveedores);

        Editar.add(Proveedores);

        mBienes.setText("Bienes");

        ConsultaBienes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        ConsultaBienes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codi/images/buscar.png"))); // NOI18N
        ConsultaBienes.setText("Consultar y Modificar");
        ConsultaBienes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultaBienesActionPerformed(evt);
            }
        });
        mBienes.add(ConsultaBienes);

        Editar.add(mBienes);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuItem1.setText("Cerrar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        Editar.add(jMenuItem1);

        Menu.add(Editar);

        Reportes.setText("Reportes");

        jMenu1.setText("Contrataciones");

        rCAnyo.setText("Por año");
        rCAnyo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rCAnyoActionPerformed(evt);
            }
        });
        jMenu1.add(rCAnyo);

        rCProveedor.setText("Por proveedor");
        rCProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rCProveedorActionPerformed(evt);
            }
        });
        jMenu1.add(rCProveedor);

        Reportes.add(jMenu1);

        rProveedores.setText("Proveedores");
        rProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rProveedoresActionPerformed(evt);
            }
        });
        Reportes.add(rProveedores);

        Menu.add(Reportes);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void NuevasCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevasCDActionPerformed
        new Contrataciones("Registro de nueva Contratación Directa", conexion).setVisible(true);
    }//GEN-LAST:event_NuevasCDActionPerformed

    private void ConsultarCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultarCDActionPerformed
        formulario=1;
        titulo.setText("Consulta y Modificación de Contrataciones Directas");
        Tabla.removeAll();
        pnlReportes.setVisible(true);
        ObtenerTabla(formulario);
        txtBusqueda.requestFocus();
        txtBusqueda.setToolTipText("Digite el objeto o el número de CD que desea buscar");
        pnlFondo.lblsgt.setVisible(false);
    }//GEN-LAST:event_ConsultarCDActionPerformed

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        int code = evt.getKeyCode();
    	if(code!=10){
            ObtenerTabla(formulario);
        }
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void CerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarActionPerformed
        pnlReportes.setVisible(false);
        pnlFondo.lblsgt.setVisible(true);
    }//GEN-LAST:event_CerrarActionPerformed

    private void ConsultarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultarProveedoresActionPerformed
        formulario=2;
        titulo.setText("            Consulta y Modificación de Proveedores");
        Tabla.removeAll();
        pnlReportes.setVisible(true);
        ObtenerTabla(formulario);
        txtBusqueda.requestFocus();
        txtBusqueda.setToolTipText("Digite el nombre del Proveedor que desea buscar");
        pnlFondo.lblsgt.setVisible(false);
    }//GEN-LAST:event_ConsultarProveedoresActionPerformed

    private void NuevosProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevosProveedoresActionPerformed
        new Proveedores("Registro de nuevo Proveedor",conexion).setVisible(true);
    }//GEN-LAST:event_NuevosProveedoresActionPerformed

    private void ConsultaBienesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultaBienesActionPerformed
        formulario=3;
        titulo.setText("                 Consulta y Modificación de Bienes");
        Tabla.removeAll();
        pnlReportes.setVisible(true);
        ObtenerTabla(formulario);
        txtBusqueda.requestFocus();
        txtBusqueda.setToolTipText("Digite la descripción del Bien que desea buscar");
        pnlFondo.lblsgt.setVisible(false);
    }//GEN-LAST:event_ConsultaBienesActionPerformed

    private void NuevasAdjudicacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevasAdjudicacionesActionPerformed
        new Adjudicaciones("Registro de nueva Adjudicación",conexion).setVisible(true);
    }//GEN-LAST:event_NuevasAdjudicacionesActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        if(pnlReportes.isVisible()){
            ObtenerTabla(formulario);
            txtBusqueda.requestFocus();
        }
    }//GEN-LAST:event_formWindowGainedFocus

    private void ConsultarAdjudicacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultarAdjudicacionesActionPerformed
        formulario=4;
        titulo.setText("         Consulta y Modificación de Adjudicaciones");
        Tabla.removeAll();
        pnlReportes.setVisible(true);
        ObtenerTabla(formulario);
        txtBusqueda.requestFocus();
        txtBusqueda.setToolTipText("Digite el nombre del Proveedor o el número de CD que desea buscar");
        pnlFondo.lblsgt.setVisible(false);
    }//GEN-LAST:event_ConsultarAdjudicacionesActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            conexion.close();
        } catch (SQLException ex){
        }
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void rProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rProveedoresActionPerformed
        new Reportes(conexion,2,this);
    }//GEN-LAST:event_rProveedoresActionPerformed

    private void rCAnyoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rCAnyoActionPerformed
        new Reportes(conexion,0,this);
    }//GEN-LAST:event_rCAnyoActionPerformed

    private void rCProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rCProveedorActionPerformed
        new Reportes(conexion,1,this);
    }//GEN-LAST:event_rCProveedorActionPerformed
    
    private void ObtenerTabla(int i){
      	String consulta ="";
        if(i==1){
            consulta = "SELECT Anyo AS [Año], Numero AS [Número], Objeto, Fecha, MontoEstimado AS [Monto Estimado], FechaDI AS [Fecha DI], Solicitudes FROM Contrataciones WHERE Objeto LIKE '%"+txtBusqueda.getText()+"%' "+
                    "OR Numero LIKE '%"+txtBusqueda.getText()+"%' ORDER BY Numero ASC";
            int[] a={40,36,56,222,70,106,70,80};
            EjecutarConsulta(a,consulta);
        }
        else if(i==2){
            consulta = "SELECT Cedula AS [Cédula Jurídica], RazonSocial AS [Razón Social] FROM Proveedores WHERE RazonSocial LIKE '%"+txtBusqueda.getText()+"%' ORDER BY RazonSocial ASC";
            int[] a={40,120,520};
            EjecutarConsulta(a,consulta);
        }
        else if(i==3){
            consulta = "SELECT Linea AS [#], Descripcion AS [Descripción], Cantidad, Precio FROM Bienes WHERE Descripcion LIKE '%"+txtBusqueda.getText()+"%' ORDER BY Descripcion ASC";
            int[] a={40,20,460,60,100};
            EjecutarConsulta(a,consulta);
        }
        else if(i==4){
            consulta="SELECT Adjudicaciones.Adjudicacion AS [#], Proveedores.RazonSocial AS [Proveedor], "+
                "Adjudicaciones.OrdenesCompra AS [Ordenes de Compra], Adjudicaciones.Observacion AS [Observación], "+
                "Adjudicaciones.Anyo+'SG-'+Adjudicaciones.Numero+'-CDSG' AS[Contratación Directa] FROM "+
                "(Adjudicaciones INNER JOIN Proveedores ON Adjudicaciones.CedulaProveedor = Proveedores.Cedula) "+
                "WHERE Proveedores.RazonSocial LIKE '%"+txtBusqueda.getText()+"%' OR Adjudicaciones.Numero LIKE '%"+txtBusqueda.getText()+"%' "+
                "ORDER BY Adjudicaciones.Adjudicacion ASC";
            int[] a={40,20,220,130,130,140};
            EjecutarConsulta(a,consulta);
        }
    }
    
    private void EjecutarConsulta(int[] anchos, String consulta){
        Statement statement;
      	ResultSet resultSet;
      	try {
            statement = conexion.createStatement();
            resultSet = statement.executeQuery(consulta);
            new CrearTabla(resultSet,Tabla,scroller,anchos,conexion,formulario);
            statement.close();
      	}
      	catch(SQLException sqlex){
            new Bitacora("Errores.txt",sqlex.toString(),"Interfaz");
        }
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cerrar;
    private javax.swing.JMenuItem ConsultaBienes;
    private javax.swing.JMenuItem ConsultarAdjudicaciones;
    private javax.swing.JMenuItem ConsultarCD;
    private javax.swing.JMenuItem ConsultarProveedores;
    private javax.swing.JMenu Editar;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JMenuItem NuevasAdjudicaciones;
    private javax.swing.JMenuItem NuevasCD;
    private javax.swing.JMenuItem NuevosProveedores;
    private javax.swing.JMenu Proveedores;
    private javax.swing.JMenu Reportes;
    private javax.swing.JTable Tabla;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu mAdjudicaciones;
    private javax.swing.JMenu mBienes;
    private javax.swing.JMenu mCD;
    private javax.swing.JPanel pnlReportes;
    private javax.swing.JMenuItem rCAnyo;
    private javax.swing.JMenuItem rCProveedor;
    private javax.swing.JMenuItem rProveedores;
    private javax.swing.JScrollPane scroller;
    private javax.swing.JLabel titulo;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}