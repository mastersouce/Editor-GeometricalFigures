package editor;

import java.awt.Color;
import java.awt.Graphics;

public class RetanguloCheio extends Figura {
    int largura, altura;
    
    public RetanguloCheio() {
        super();
        largura = altura = 0;
    }
    
    public RetanguloCheio(int x, int y, int l, int a, Color cor) {
        super(x, y, cor);
        largura = l;
        altura = a;
    }
    
    @Override
    public void desenha(Graphics g) {
        g.setColor(cor);
        g.fillRect(p.x, p.y, largura, altura);
    }
    
    @Override
    public void setCoordenadas(int x1, int y1, int x2, int y2) {
        p.x = Math.min(x1, x2);
        p.y = Math.min(y1, y2);
        largura = Math.abs(x1-x2);
        altura = Math.abs(y1-y2);
    }
    public double Area() {
    	return altura * largura;
    }
    public double Perimetro() {
    	return (2 * altura) + (2 * largura);
    }
}
