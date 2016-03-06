package editor;

import java.awt.Graphics;

public class CirculoCheio extends Circulo {
	@Override
	public void desenha(Graphics g) {
		g.setColor(cor);
		g.fillOval(p.x-raio, p.y-raio, 2*raio, 2*raio);
	}
}
