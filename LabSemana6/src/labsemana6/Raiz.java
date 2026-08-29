package labsemana6;

import java.io.File;
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
}
