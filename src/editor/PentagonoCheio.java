package editor;

import java.awt.*;

public class PentagonoCheio extends Pentagono {
	public void desenha(Graphics g) {
		g.setColor(cor);
		g.fillPolygon(xPoints, yPoints, 5);
	}
}

