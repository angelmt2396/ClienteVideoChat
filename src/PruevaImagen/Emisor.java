/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruevaImagen;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Angel
 */
public class Emisor {
    public static void main(String[] args) throws IOException {
        Socket socket  = new Socket("localhost",1234);
        
        JFrame frame = new JFrame("Prueba");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        
        ImageIcon image = new ImageIcon("C:\\Users\\Angel\\Videos\\Captures\\Settings 2_20_2021 11_40_27 PM.png");
        
        JLabel label = new JLabel(image);
        
        JPanel panelImg = new JPanel();
        panelImg.setSize(100, 100);
        JButton boton = new JButton("Enviar");
        
        panelImg.add(label, BorderLayout.CENTER);
        frame.add(panelImg, BorderLayout.CENTER);
        frame.add(boton, BorderLayout.SOUTH);
        
        frame.setVisible(true);
        
        boton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    OutputStream os = socket.getOutputStream();
                    BufferedOutputStream bos = new  BufferedOutputStream(os);
                    Image img = image.getImage();
                    BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
                    
                    
                    
                    Graphics g = bi.createGraphics();
                    
                    g.drawImage(img, 0, 0, null);
                    g.dispose();
                    
                    ImageIO.write(bi,"png",bos);
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        });
    }
}
