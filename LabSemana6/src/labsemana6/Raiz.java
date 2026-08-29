package labsemana6;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Raiz {
    public static File root(){
        File raiz = new File(System.getProperty("user.dir"), "Sistema");
        if(!raiz.exists())
            raiz.mkdirs();

        return raiz;
    }
    
    public static void find(String nombreBuscado, ConsoleGUI gui){
    File carpetaActual = root();
    
    gui.imprimirTexto("\n> Find "+nombreBuscado + "\n");
        buscarEnCarpeta(carpetaActual, nombreBuscado.toLowerCase(), gui);
    }
    
    private static void buscarEnCarpeta(File carpeta, String nombreBuscado, ConsoleGUI gui) {

    File[] elementos = carpeta.listFiles();

    if (elementos == null) {
        return;
    }

    for (File elemento : elementos) {
        if (elemento.getName().toLowerCase().contains(nombreBuscado)) {
            String tipo = elemento.isDirectory() ? "[Carpeta] " : "[Archivo] ";
            gui.imprimirTexto(tipo + elemento.getAbsolutePath() + "\n");
        }

        if (elemento.isDirectory()) {
            buscarEnCarpeta(elemento, nombreBuscado, gui);
        }
    }
}
    
    public static void info(String nombre, ConsoleGUI gui){
    File carpetaActual = root();
    File elemento = new File(carpetaActual, nombre);
    gui.imprimirTexto("\n> Info "+nombre);
    
        if (!elemento.exists()) {
            gui.imprimirTexto("No se encontro el archivo o carpeta.");
            return;
        }
        
        String tipo = elemento.isDirectory() ? "Carpeta":"Archivo";
        long tamanio = elemento.length();
        Date fechaModificacion = new Date(elemento.lastModified());
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        gui.imprimirTexto("Tipo: "+tipo);
        gui.imprimirTexto("Ruta: "+elemento.getAbsolutePath());
        gui.imprimirTexto("Tamano: "+tamanio+" bytes");
        gui.imprimirTexto("Ultima Modificacion: "+formato.format(fechaModificacion));
    
    
    }
}
