package codi;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bitacora {
    protected Date fecha;
    protected SimpleDateFormat formato;
    protected FileWriter log;
    protected PrintWriter pr;
    
    public Bitacora(String nombre, String error, String archivo){
        try{
            log = new FileWriter(nombre,true);
            pr = new PrintWriter(log);
            pr.println(new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date())+"\t"+archivo);
            pr.println(error);
            System.out.println(error);
            log.close();
        }catch(Exception e){}
    }
}