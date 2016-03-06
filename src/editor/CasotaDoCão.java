/*
 * Pentágono (quase) certo aka SnoopyHouse
 */
package editor;
import java.awt.*;


public class CasotaDoCão extends Figura{
	int[] xPoints;
	int[] yPoints;

	public CasotaDoCão () {
		super();
		xPoints = new int [5];
		yPoints = new int [5];
	}
	public CasotaDoCão (int x, int y, int x2, int y2, int r, Color cor){
		super(x, y, cor);
	}

	@Override
	public void setCoordenadas(int x1, int y1, int x2, int y2) {
		p.x = x1;
		p.y = y1;
		
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
		g.drawPolygon(xPoints, yPoints, 5);
	}
	public double Area() {
		Ponto p2 = new Ponto(xPoints[1], yPoints[1]);
		Ponto p3 = new Ponto(xPoints[2], yPoints[2]);
		double d = distancia(p2, p3);
		return( ((5*(d*d)) * Math.tan(54))/4);
	}
	public double Perimetro() {
		Ponto p2 = new Ponto(xPoints[1], yPoints[1]);
		Ponto p3 = new Ponto(xPoints[2], yPoints[2]);
		return ( 5 * (distancia(p2, p3)));
	}
	private double distancia(Ponto r, Ponto s) {
		return Math.sqrt(
				Math.pow(r.x-s.x, 2) + 
				Math.pow(r.y-s.y, 2)
				);
	}
	public String mostrarInfo() {
		Ponto p = new Ponto(xPoints[0], yPoints[0]);
    	return (" Area = " +Area() +" Perimetro = " +Perimetro()+
    			" Ponto Inicial = " +p.x +"," + p.y);
    }
	public boolean contains(int x, int y) {
		Polygon p = new Polygon(xPoints, yPoints, 5);
		if (p.contains(x, y)) {
			return true;
		}
		return false;
	}
	public void mover (int dx, int dy) {
    	for(int i = 0; i < xPoints.length; i++) {
    		xPoints[i] += dx;
    		yPoints[i] += dy;
    	}
    }

}
