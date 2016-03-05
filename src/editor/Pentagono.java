package editor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;


public class Pentagono extends Figura{
	int[] xPoints;
	int[] yPoints;
	Ponto p2;
	Ponto p3;

	public Pentagono () {
		super();
		xPoints = new int [5];
		yPoints = new int [5];
		p2 = new Ponto();
		p3 = new Ponto();
	}
	public Pentagono (int x, int y, int x2, int y2, int r, Color cor){
		super(x, y, cor);
	}

	@Override
	public void setCoordenadas(int x1, int y1, int x2, int y2) {
		p.x = x1;
		p.y = y1;

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
		
		p2.x = xPoints[1];
		p2.y = yPoints[1];
		p3.x = xPoints[2];
		p3.y = yPoints[2];
	}
	public void desenha(Graphics g) {
		g.setColor(cor);
		g.drawPolygon(xPoints, yPoints, 5);
	}
	public double Area() {
		return ((5*((distancia(p2, p3))*(distancia(p2, p3))))*(4*(Math.tan((Math.PI*5)))));
	}
	public double Perimetro() {
		return ( 5 * (distancia(p2, p3)));
	}
	private double distancia(Ponto r, Ponto s) {
		return Math.sqrt(
				Math.pow(r.x-s.x, 2) + 
				Math.pow(r.y-s.y, 2)
				);
	}

}
