package codi;

import java.net.URL;
import javax.swing.ImageIcon;

public class CrearImagen {
    public CrearImagen(){
    }
    protected ImageIcon Imagen(String ruta){
        URL imgURL = getClass().getResource(ruta);
        if(imgURL != null)
            return new ImageIcon(imgURL);
        else{
            new Bitacora("Errores.txt","Error, no se encontro la ruta: "+ruta,"Crear Tabla");
            return null;
        }
    }
}
