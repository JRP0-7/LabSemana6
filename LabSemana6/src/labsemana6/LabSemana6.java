/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package labsemana6;

import javax.swing.SwingUtilities;

/**
 *
 * @author josep
 */
public class LabSemana6 {

    /**
     * @param args the command line arguments
     */
    private static boolean modoEscritura = false;
    private static String archivoDestino = null;
    private static boolean modoAppend = false;
    private static StringBuilder buffer = new StringBuilder();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConsoleGUI gui = new ConsoleGUI();
            ManejoFile archivos = new ManejoFile();

            gui.setComandoListener(comando -> procesarComando(comando, gui, archivos));
            gui.setVisible(true);
        });

    }

    private static void procesarComando(String entrada, ConsoleGUI gui,
            ManejoFile archivos) {
        String comandoCompleto = entrada.trim();

        if (comandoCompleto.isEmpty()) {
            return;
        }

        if (modoEscritura) {
            if (comandoCompleto.equals("EXIT")) {
                String res = archivos.escribir(archivoDestino, buffer.toString(), modoAppend);
                if (res.isEmpty()) {
                    gui.imprimirTexto("Guardado en " + archivoDestino);
                } else {
                    gui.imprimirTexto(res);
                }
                modoEscritura = false;
                buffer.setLength(0);
            } else {
                buffer.append(entrada).append("\n");
            }
            return;
        }

        // El segundo valor conserva el texto completo del nombre indicado.
        String[] partes = comandoCompleto.split("\\s+", 2);
        String comando = partes[0].toLowerCase();
        String argumento = partes.length > 1 ? partes[1].trim() : "";

        switch (comando) {
            case "mkdir":
                gui.imprimirTexto(archivos.crearCarpeta(argumento));
                break;
            case "mfile":
                gui.imprimirTexto(archivos.crearArchivo(argumento));
                break;
            case "rm":
                gui.imprimirTexto(archivos.borrar(argumento));
                break;
            case "cd":
                gui.imprimirTexto(archivos.Mover(argumento));
                break;
            case "..":
                gui.imprimirTexto(archivos.Subir());
                break;
            case "dir":
                gui.imprimirTexto(archivos.listar());
                break;
            case "wr":
                if (argumento.isEmpty()) {
                    gui.imprimirTexto("Uso: Wr <archivo>");
                } else {
                    modoEscritura = true;
                    modoAppend = false;
                    archivoDestino = argumento;
                    buffer.setLength(0);
                    gui.imprimirTexto("Escribe el contenido. Termina con EXIT.");
                }
                break;
            case "rd":
                if (argumento.isEmpty()) {
                    gui.imprimirTexto("Uso: Rd <archivo>");
                } else {
                    gui.imprimirTexto(archivos.leer(argumento));
                }
                break;
            case "ap":
                if (argumento.isEmpty()) {
                    gui.imprimirTexto("Uso: Ap <archivo>");
                } else {
                    modoEscritura = true;
                    modoAppend = true;
                    archivoDestino = argumento;
                    buffer.setLength(0);
                    gui.imprimirTexto("Escribe el contenido. Termina con EXIT.");
                }
                break;
            case "ren":
                if (argumento.isEmpty()) {
                    gui.imprimirTexto("Uso: Ren <actual> <nuevo>");
                } else {
                    String[] d = argumento.split("\\s+", 2);
                    if (d.length == 2) {
                        gui.imprimirTexto(archivos.renombrar(d[0], d[1]));
                    } else {
                        gui.imprimirTexto("Uso: Ren <actual> <nuevo>");
                    }
                }
                break;
            case "copy":
                if (argumento.isEmpty()) {
                    gui.imprimirTexto("Uso: Copy <origen> <destino>");
                } else {
                    String[] d = argumento.split("\\s+", 2);
                    if (d.length == 2) {
                        gui.imprimirTexto(archivos.copiar(d[0], d[1]));
                    } else {
                        gui.imprimirTexto("Uso: Copy <origen> <destino>");
                    }
                }
                break;
            case "find":
                if (argumento.isEmpty()) {
                    gui.imprimirTexto("Uso: Find <nombre>");
                } else {
                    Raiz.find(argumento, gui);
                }
                break;
            case "info":
                if (argumento.isEmpty()) {
                    gui.imprimirTexto("Uso: Info <nombre>");
                } else {
                    Raiz.info(argumento, gui);
                }
                break;
            case "help":
                gui.imprimirTexto("Comandos disponibles: Mkdir, Mfile, Rm, Cd, Dir, Date, Time, Wr, Rd, Ap, Ren, Copy, Find, Info, Tree, Cls, Help y Exit.");
                break;
            case "exit":
                gui.dispose();
                break;
            default:
                gui.imprimirTexto("Comando no reconocido. Escribe Help para ver los comandos disponibles.");
                break;
        }
        gui.setRutaActual(archivos.ubicacionActual());
    }
}
