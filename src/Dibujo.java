
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ale
 */
public class Dibujo extends JPanel{
    
    //window
    private JFrame ventana;
    
    private Container contenedor;
    //figura en hexadecimal
    private final int [] FIGURA={ 

0x0FF07F8,
0x1FF8FFC,
0x1FF8FFC,
0x1FF8FFC,
0x1FF8FFC,
0x0FF07F8,
0x01800C0,
0x01800C0,
0x01800C0,
0x01800C0,
0x21800C0,
0x718F0C3,
0xFFFFFFFF,
0x1FE30FF8,
0x0000000,

        
    };
    
    //mascara
    private final int MASCARA=0x800000;
    
    // ancho de bits
    private final int ANCHO_BITS=32;
    
    //cordenadas
    private int coordenada_x, coordenada_y;
    
    //hilo
    private Thread hilo;
    
    public Dibujo(){
    
    //inicializar la ventana
    
    ventana = new JFrame("Dibujo");
    ventana.setSize(800,600);
    ventana.setVisible(true);
    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ventana.setResizable(false);
    
    contenedor=ventana.getContentPane();
    contenedor.setSize(800,600);
    
    //AGREGAR ALA VENTANA EL CONTENEDOR
    contenedor.add(this, BorderLayout.CENTER);
   
    
}

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics); //To change body of generated methods, choose Tools | Templates.
    int R = (int)(Math.random()*256); int G = (int)(Math.random()*256); int B= (int)(Math.random()*256);  
     graphics.setColor(new Color(R, G, B));
        
        for (int i = 0; i < this.FIGURA.length; i++) {
           //iterador para el ancho en bits de la figura
            for (int j = 0; j <this.ANCHO_BITS; j++) {
                int temp=this.FIGURA[i] & (this.MASCARA >>j);
                
                if(temp !=0){
                  graphics.drawLine(
                  this.coordenada_y+j,
                  this.coordenada_x+i,
                  this.coordenada_y+j,
                  this.coordenada_x+i);
                  
                    
                } 
            }
            
        }
        
       
    }

    public void dibujar(){
        int colision=0;
         for (int x = 0; x < 4; x++){
        this.coordenada_x=(int)(Math.random()*500);
        this.coordenada_y=(int)(Math.random()*500);
        int x1 = 1,x2=0;
        int y1 = 1,y2=1;
        
       
        
       
            //Colision en X++
            try {
                //for para dibujar 
                for (int j = 0; j < 120; j++) {
                    if (x1>=-250 & x1<470) {
                          x1=490-coordenada_x;
                    }
                
                this.hilo.sleep(30);
                
                if (x1<470) {
                    System.out.println("draw pixel: "+x1);
                   this.coordenada_x= coordenada_x-10;
                   this.coordenada_y= coordenada_y+5;
                  
                 
                   
                   paint(getGraphics()); 
                   
                }else  {
               
                 x1=500; 
                 
                
                }
                //dibujando x+
                if (x1>470) {
                    System.out.println("draw pixel: -"+x2);
                   x2=490+coordenada_x;
          
                    if (x2>=470 & x2<=1035) {
                   this.coordenada_x= coordenada_x+10;
                 
                 
                   
                   paint(getGraphics()); 
                    }else{
                      j=120;
                        System.out.println("Colision"); 
                        colision++;
                    }
                        

                    }
               
                }
                
                
                //cordenada en Y
                for (int j = 0; j < 120; j++) {
                    if (y1>-250 & y1<470) {
                          y1=490-coordenada_y;
                    }
                
                this.hilo.sleep(30);
                
                if (y1<470) {
                    System.out.println("draw pixel y1: -"+y1);
                   this.coordenada_y= coordenada_y-10;
                   this.coordenada_x= coordenada_x-5;
                  
                   paint(getGraphics()); 
                   
                }else  {
                 y1=500; 
                 

                }
                //dibujando y+
                if (y1>470) {
                    
                   y2=490+coordenada_y;
                    
                    if (y2>=470 & y2<=1250) {
                        System.out.println("draw pixel: "+y2);
                   this.coordenada_y= coordenada_y+10;
                   this.coordenada_x= coordenada_x-4;
                  
                   paint(getGraphics()); 
                    }else{
                      j=120;
                      colision++; 
                    }
                        

                    }
               
                }
                
              
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
  
        }
         JOptionPane.showMessageDialog(null, "!!terminaron las colisiones!!");
         System.exit(0);
             
    }
   
        
        
    }
    
    

