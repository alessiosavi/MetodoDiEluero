

package metododieulero;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Grafico extends JPanel {
    
    double a,b;
    int n;
    int funzione;
    double scalaX,scalaY;
    double maxY,minY;
    ArrayList<Double> array = new ArrayList<Double>();
    ArrayList<Double> arrayx = new ArrayList<Double>();
    
    public Grafico(){
        this.setVisible(true);
        this.setBackground(Color.white);
    }
    
    @Override
    public void paint(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.black);
        g.drawLine(10,this.getHeight()-10-(int)((0-minY)*scalaY), this.getWidth()-10,this.getHeight()-10-(int)((0-minY)*scalaY));
        g.drawLine(10+(int)((0-a)*scalaX), 10, 10+(int)((0-a)*scalaX), this.getHeight()-10);
        g.setColor(Color.blue);
        for(int i=0;i<array.size()-1;i++){
            g.drawLine(10+(int)((arrayx.get(i)-a)*scalaX),this.getHeight()-10-(int)((0-minY)*scalaY)-(int)((array.get(i))*scalaY) ,10+(int)((arrayx.get(i+1)-a)*scalaX), this.getHeight()-10-(int)((0-minY)*scalaY)-(int)((array.get(i+1))*scalaY));
        }
        
        double passo=(double)((b-a)/n);
        double i=a;
        
        double y0=0;
         
        if(funzione==0){
           if(Math.cos(i)==0){
               y0=(double)(1/0.001);
           }else{
               y0=(double)(1/(double)(Math.cos(i)));
           }
        }
        if(funzione==1){
           y0=((double)Math.pow(Math.abs((i*i)-1),(double)(1/2)));
        }
        if(funzione==2){
           y0=((double)Math.pow(i,i));
        }
        if(funzione==3){
           y0=((double)((double)Math.log10(i)));
        }
        if(funzione==4){
           y0=(double)((double)Math.pow(Math.E,(double)Math.pow(-i,4)));
        }
        
        double y=0;
        
        while(i<=b){
            if(funzione==0){
                if(y0==0){
                    y=y0+(passo*((double)Math.tan(i)/0.001));
                }else{
                    y=y0+(passo*((double)Math.tan(i)/y0));
                }
            }
            if(funzione==1){
                if(i>-1.001&&i<-0.999){
                    i=-0.999;
                }
                if(i<1.001&&i>0.999){
                    i=0.999;
                }else{
                    y=y0+(passo*((i*y0)/(i*i-1)));
                }
            }
            if(funzione==2){
                if(i<=0){
                    y=y0+(passo*(y0*(1+(double)Math.log10(0.001))));
                }else{
                    y=y0+(passo*(y0*(1+(double)Math.log10(i))));
                }
            }
            if(funzione==3){
                if(i<=0){
                    y=y0+(passo*(y0/0.001));
                }else{
                    y=y0+(passo*(y0/(i*(double)Math.log10(i))));
                }
            }
            if(funzione==4){
                y=y0+(passo*(-4*(double)Math.pow(i,3)*y0));
            }
            
            g.setColor(Color.RED);
            System.out.println(i+" "+y);
            g.drawLine((10+(int)(((i-passo)-a)*scalaX)),this.getHeight()-10-(int)((y0-minY)*scalaY),10+(int)((i-a)*scalaX),this.getHeight()-10-(int)((y-minY)*scalaY));
            y0=y;
            i+=passo;
        }
    
        
    }
    
}
