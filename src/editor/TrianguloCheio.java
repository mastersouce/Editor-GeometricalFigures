package editor;

import java.awt.Graphics;

class TrianguloCheio extends Triangulo {
	public TrianguloCheio() {
		super();
	}
	public void desenha(Graphics g) {
		if (count == 3) {
			g.setColor(cor);
			int xPoints[] = {p.x, p2.x, p3.x};
			int yPoints[] = {p.y, p2.y, p3.y};
			g.fillPolygon(xPoints, yPoints, 3);
		}
	}
}

