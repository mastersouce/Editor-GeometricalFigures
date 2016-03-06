package editor;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Figura implements Serializable {
    Ponto p;
    Color cor;
    double area;
    double perimetro;
    public Figura() {
        p = new Ponto();
        cor = Color.BLACK;  
    }
    
    public Figura(int x, int y, Color c) {
        p = new Ponto(x, y);
        cor = c;
    }
    
    public void setColor(Color c) {
    	cor = c;
    }
    
    public void desenha(Graphics g) {}
    
    public void setCoordenadas(int x1, int y1, int x2, int y2) {}
    
    
    public double Area () {
    	return 0.0;
    }
    public double Perimetro() {
    	return 0.0;
    }
    public String mostrarInfo() {
    	return "N/A";
    }
    public boolean contains(int x, int y) {
    	return false;
    }
    public void mover (int dx, int dy) {
    	p.x += dx;
    	p.y += dy;
    }
    
}
