/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_bingo;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author David
 */
public class AplicarFondos extends JPanel {
    
    private Image imagenDeFondo;

    public AplicarFondos(String rutaImagen) {
        try {
            imagenDeFondo = ImageIO.read(new File(rutaImagen));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar la imagen: " + rutaImagen);
        }
    }

    // 2. Sobrescribe el método paintComponent para dibujar
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Llama al método del padre para el borrado
        
        // Verifica si la imagen se cargó correctamente
        if (imagenDeFondo != null) {
            // Dibuja la imagen para que ocupe todo el panel
            // Se usa this.getWidth() y this.getHeight() para que se escale con el panel
            g.drawImage(imagenDeFondo, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
    
    
}
