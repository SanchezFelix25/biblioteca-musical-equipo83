/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package itson.biblioteca.musical;

import itson.biblioteca.musical.gui.InsertarArtistasForm;
import itson.biblioteca.musical.gui.RegistroUsuarioForm;
import itson.biblioteca.musical.gui.BibliotecaPrincipalForm;
import itson.biblioteca.musical.gui.LoginForm;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Felix_isq
 */
public class BibliotecaMusical {
    
  
    
    public static void main(String[] args) {
      
        SwingUtilities.invokeLater(() -> {
            
            LoginForm form = new LoginForm();
            form.setVisible(true);
          //  RegistroUsuarioForm form = new RegistroUsuarioForm();
           // form.setVisible(true);
           // InsertarArtistasForm ventana = new InsertarArtistasForm();
           // ventana.setVisible(true);
           // BibliotecaPrincipalForm ventanaNueva = new BibliotecaPrincipalForm();
           // ventanaNueva.setVisible(true);
        });
    }

    
}
