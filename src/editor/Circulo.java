package editor;
 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
 
public class Circulo extends Figura {
    int raio;
   
    public Circulo() {
        super();
        raio =  0;
    }
   
    public Circulo(int x, int y, int l, int r, Color cor) {
        super(x, y, cor);
       raio = r;
    }
   
    @Override
    public void desenha(Graphics g) {
        g.setColor(cor);
        g.drawOval(p.x-raio, p.y-raio, 2*raio, 2*raio);
    }
   
    @Override
    public void setCoordenadas(int x1, int y1, int x2, int y2) {
        p.x = x1;
        p.y = y1;
        raio = (int)Point2D.distance(x1, y1, x2, y2);
    }
    public double Area() {
		return (Math.PI * (raio*raio));
	}
	public double Perimetro() {
		return Math.PI * 2 * raio;
	}
	public String mostrarInfo() {
    	return (" Area = " +Area() +" Perimetro = " +Perimetro()+
    			" Raio = " +raio +" Ponto Inicial = " +p.x +"," + p.y);
    }
	public boolean contains(int x, int y) {
        int distancia = (int)Point2D.distance(p.x, p.y, x, y);
        return distancia<=raio;
    }

}