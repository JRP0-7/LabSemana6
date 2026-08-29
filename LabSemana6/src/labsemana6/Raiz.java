package labsemana6;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextArea;

public class Raiz {
    public static File root(){
        File raiz = new File(System.getProperty("user.dir"), "Sistema");
        if(!raiz.exists())
            raiz.mkdirs();

        return raiz;
    }
    
    public static void find(String nombreBuscado, JTextArea areaSalida){
    File carpetaActual = new File(System.getProperty("user.dir"));
    
    areaSalida.append("\n> Find "+nombreBuscado + "\n");
        buscarEnCarpeta(carpetaActual, nombreBuscado.toLowerCase(), areaSalida);
    }
    
    private static void buscarEnCarpeta(File carpeta, String nombreBuscado, JTextArea areaSalida) {

    File[] elementos = carpeta.listFiles();

    if (elementos == null) {
        return;
    }

    for (File elemento : elementos) {
        if (elemento.getName().toLowerCase().contains(nombreBuscado)) {
            String tipo = elemento.isDirectory() ? "[Carpeta] " : "[Archivo] ";
            areaSalida.append(tipo + elemento.getAbsolutePath() + "\n");
        }

        if (elemento.isDirectory()) {
            buscarEnCarpeta(elemento, nombreBuscado, areaSalida);
        }
    }
}
    
    public static void info(String nombre, JTextArea areaSalida){
    File carpetaActual = new File(System.getProperty("usar.dir"));
    File elemento = new File(carpetaActual, nombre);
    areaSalida.append("\n> Info "+nombre+"\n");
    
        if (!elemento.exists()) {
            areaSalida.append("No se encontro el archivo o carpeta.\n");
            areaSalida.setCaretPosition(areaSalida.getDocument().getLength());
            return;
        }
        
        String tipo = elemento.isDirectory() ? "Carpeta":"Archivo";
        long tamanio = elemento.length();
        Date fechaModificacion = new Date(elemento.lastModified());
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        areaSalida.append("Tipo: "+tipo+"\n");
        areaSalida.append("Ruta: "+elemento.getAbsolutePath()+"\n");
        areaSalida.append("Tamano: "+tamanio+"bytes\n");
        areaSalida.append("Ultima Modificacion: "+formato.format(fechaModificacion)+"\n");
        areaSalida.setCaretPosition(areaSalida.getDocument().getLength());
    
    
    }
}
