package editor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;


public class PentagonoCheio extends Figura{
	int[] xPoints;
	int[] yPoints;
	int raio;
	
	public PentagonoCheio () {
		super();
		xPoints = new int [5];
		yPoints = new int [5];
		raio = 0;
		
	}
	public PentagonoCheio (int x, int y, int x2, int y2, int r, Color cor){
		super(x, y, cor);
		raio = r;
	}

	@Override
	public void setCoordenadas(int x1, int y1, int x2, int y2) {
		p.x = x1;
        p.y = y1;
        raio = (int)Point2D.distance(x1, y1, x2, y2);
        
        
        //ESTÁ CERTO 
        xPoints[0] = x1 + (int) (x2-x1);
        xPoints[1] = x1 + (int) ( ((x2-x1) * Math.cos(108) - ((y2-y1) * Math.sin(108) )));
        xPoints[2] = x1 + (int) ( ((x2-x1) * Math.cos(216) - ((y2-y1) * Math.sin(216) )));
        xPoints[3] = x1 + (int) ( ((x2-x1) * Math.cos(324) - ((y2-y1) * Math.sin(324) )));
        xPoints[4] = x1 + (int) ( ((x2-x1) * Math.cos(432) - ((y2-y1) * Math.sin(432) )));
        
        yPoints[0] = y1 + (int) (y2-y1);
        yPoints[1] = y1 + (int) ( ((x2-x1) * Math.sin(108) + ((y2-y1) * Math.cos(108) )));
        yPoints[2] = y1 + (int) ( ((x2-x1) * Math.sin(216) + ((y2-y1) * Math.cos(216) )));
        yPoints[3] = y1 + (int) ( ((x2-x1) * Math.sin(324) + ((y2-y1) * Math.cos(324) )));
        yPoints[4] = y1 + (int) ( ((x2-x1) * Math.sin(432) + ((y2-y1) * Math.cos(432) )));
        
	}
	public void desenha(Graphics g) {
		g.setColor(cor);
		g.fillPolygon(xPoints, yPoints, 5);
	}

}
