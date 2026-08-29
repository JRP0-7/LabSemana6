package labsemana6;

import java.io.File;
import java.io.IOException;

public class ManejoFile {
    private File carpetaRaiz;
    private File carpetaActual;

    public ManejoFile() {
        carpetaRaiz = Raiz.root();
        carpetaActual = carpetaRaiz;
    }

    public File URLValido(String nombre) {
        if (nombre == null || nombre.trim().isEmpty())
            return null;

        File destino = new File(carpetaActual, nombre);

        String rutaDestino;
        String rutaRaiz;
        try {
            rutaDestino = destino.getCanonicalPath();
            rutaRaiz = carpetaRaiz.getCanonicalPath();
        } catch (IOException e) {
            return null;
        }

        if (rutaDestino.equals(rutaRaiz))
            return destino;
        if (rutaDestino.startsWith(rutaRaiz + File.separator))
            return destino;

        return null;
    }

    public String crearArchivo(String nombre) {
        File destino = URLValido(nombre);
        if (destino == null) {
            return "Ruta Invalida";
        }

        if (destino.exists()) {
            return "El archivo " + nombre + " ya existe";
        }

        try {
            if (destino.createNewFile()) {
                return "Archivo " + nombre + " creado";
            } else {
                return "No se pudo crear el archivo " + nombre;
            }
        } catch (IOException e) {
            return "Error " + e.getMessage();
        }
    }

    public String crearCarpeta(String nombre) {
        File destino = URLValido(nombre);
        if (destino == null) {
            return "Ruta Invalida";
        }

        if (destino.exists()) {
            return "La carpeta " + nombre + " ya existe";
        }

        if (destino.mkdir()) {
            return "Carpeta " + nombre + " creada";
        } else {
            return "No se pudo crear la carpeta " + nombre;
        }
    }

    public String borrar(String nombre) {
        File destino = URLValido(nombre);
        if (destino == null || !destino.exists()) {
            return "Ruta Invalida";
        }

        try {
            if(destino.getCanonicalPath().equals(carpetaRaiz.getCanonicalPath())){
                return "No se puede borrar la carpeta raiz";
            }
        } catch (IOException e) {
            return "Error " + e.getMessage();
        }

        if (borrarTodo(destino)) {
            return nombre + " eliminado correctamente";
        } else {
            return "No se pudo eliminar " + nombre;
        }
    }

    private boolean borrarTodo(File f) {
        if (f.isDirectory()) {
            File hijos[] = f.listFiles();
            if (hijos != null) {
                for (File child : hijos) {
                    borrarTodo(child);
                }
            }
        }
        return f.delete();
    }

}
