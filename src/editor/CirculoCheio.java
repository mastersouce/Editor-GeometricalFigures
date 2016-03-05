package editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

public class CirculoCheio extends Figura {
	int raio;

	public CirculoCheio() {
		super();
		raio = 0;
	}

	public CirculoCheio(int x, int y, int r, Color cor) {
		super(x, y, cor);
		raio = r;
	}

	@Override
	public void desenha(Graphics g) {
		g.setColor(cor);
		g.fillOval(p.x-raio, p.y-raio, 2*raio, 2*raio);
	}

	@Override
	public void setCoordenadas(int x1, int y1, int x2, int y2) {
		p.x = x1;
		p.y = y1;
		raio = (int)Point2D.distance(x1, y1, x2, y2);
	}
	@Override 
	public double Area() {
		return (Math.PI * (raio*raio));
	}
	public double Perimetro() {
		return Math.PI * 2 * raio;
	}
}
