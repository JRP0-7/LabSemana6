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
                gui.imprimirTexto("Comandos disponibles: Mkdir, Mfile, Rm, Find, Info, Cls, Help y Exit.");
                break;
            case "exit":
                gui.dispose();
                break;
            default:
                gui.imprimirTexto("Comando no reconocido. Escribe Help para ver los comandos disponibles.");
                break;
        }
    }
}
