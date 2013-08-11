package codi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class fondo extends JPanel implements Runnable{
    protected String[] fondos={"fondo.jpg","fondo2.jpg","fondo3.jpg","fondo4.jpg","fondo5.jpg"};
    protected Color[] colores={new Color(255,255,255),new Color(255,255,255),new Color(255,255,255),new Color(0,0,0),new Color(0,0,0),new Color(196, 229, 255)};
    public int i=4;
    protected String fondo="fondo5.jpg";
    protected JLabel lblsgt, titulo;
    protected boolean v=false;
    protected JFrame cursor;
    
    public fondo(JLabel lbl, JFrame p, boolean visible){
    	setLayout(null);
        titulo=lbl;
        v=visible;
        cursor=p;
        lblsgt = new JLabel("Cambiar");
        lblsgt.setBounds(650,300,50,30);
        lblsgt.setForeground(colores[i]);
        lblsgt.setVisible(visible);
        add(lblsgt);
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me){
                if(me.getX()>=650&&me.getY()>=308&&me.getY()<=322)
                    cambiar();
            }
        });
        addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseMoved(MouseEvent me){
                if(v){
                    if(me.getX()>=650&&me.getY()>=308&&me.getY()<=322)
                        cursor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    else
                        cursor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                }
            }
        });
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        if(v){
            g.drawImage(new CrearImagen().Imagen("images/"+fondo).getImage(), 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paintComponent(g);
        }
    }
    
    protected void cambiar(){
        i++;
        if(i>4)
            i=0;
        fondo=fondos[i];
        titulo.setForeground(colores[i]);
        lblsgt.setForeground(colores[i]);
        repaint();
    }
    
    public void run(){//Inicio del Hilo
        while(true){
            try{
                Thread.sleep(5000*60);
            }catch (Exception e){}
            cambiar();
        }
    }
}