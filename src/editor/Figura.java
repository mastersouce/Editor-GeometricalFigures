package editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
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
    public void mostrarInfo() {}
    
}
