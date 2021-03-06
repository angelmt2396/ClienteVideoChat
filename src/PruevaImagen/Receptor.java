/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruevaImagen;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Angel
 */
public class Receptor {
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Prueba");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        
        
        JLabel label = new JLabel("Esperando imagen");
        
        frame.add(label, BorderLayout.SOUTH);
        
        
        frame.setVisible(true);
        
        ServerSocket ss = new ServerSocket(1234);
        
        Socket socket = ss.accept();
        
        InputStream is = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        
        BufferedImage bi = ImageIO.read(bis);
        
        bis.close();
        socket.close();
        
        JLabel jPic = new JLabel(new ImageIcon(bi));
        label.setText("imagen recivida");
        frame.add(jPic, BorderLayout.CENTER);
    }   
}
        
