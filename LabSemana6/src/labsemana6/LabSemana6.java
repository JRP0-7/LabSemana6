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
            gui.setVisible(true);
        });
    
}
}