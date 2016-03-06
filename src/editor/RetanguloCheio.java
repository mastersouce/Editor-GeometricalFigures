package editor;

import java.awt.Graphics;

public class RetanguloCheio extends Retangulo {
    @Override
    public void desenha(Graphics g) {
        g.setColor(cor);
        g.fillRect(p.x, p.y, largura, altura);
    }
}
