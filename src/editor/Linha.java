package editor;
 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
 
public class Linha extends Figura {
    int xFinal,yFinal;
   
    public Linha() {
        super();
        xFinal=0;
        yFinal=0;
    }
   
    public Linha(int x, int y, int l,int a, Color cor) {
        super(x, y, cor);
        xFinal=l;
        yFinal=a;
    }
   
    @Override
    public void desenha(Graphics g) {
        g.setColor(cor);
        g.drawLine(p.x, p.y, xFinal, yFinal);
    }
   
    @Override
    public void setCoordenadas(int x1, int y1, int x2, int y2) {
        p.x = x1;
        p.y = y1;
        xFinal = x2;
        yFinal = y2;
    }
}