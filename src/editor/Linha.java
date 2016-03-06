package editor;
 
import java.awt.Color;
import java.awt.Graphics;
 
public class Linha extends Figura {
    Ponto p2;
   
    public Linha() {
        super();
        p2 = new Ponto (0,0);
    }
   
    public Linha(int x, int y, int l,int a, Color cor) {
        super(x, y, cor);
        p2 = new Ponto (l,a);
    }
   
    @Override
    public void desenha(Graphics g) {
        g.setColor(cor);
        g.drawLine(p.x, p.y, p2.x, p2.y);
    }
   
    @Override
    public void setCoordenadas(int x1, int y1, int x2, int y2) {
        p.x = x1;
        p.y = y1;
        p2.x = x2;
        p2.y = y2;
    }
    @Override
    public String mostrarInfo () {
    	return (" Ponto Inicial = " +p.x +"," +p.y +" Ponto final = " +p2.x +"," +p2.y );
    }
}