package editor;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;



public class Editor extends JFrame implements MouseListener, MouseMotionListener {
	JPanel pFundo, pBotoes, pBotoesFormas, pCoord;
	static Figura r;
	MeuPainel pEdicao, pInfo;
	int x1, y1;   // Coordenadas do ponto onde o rato foi premido
	int x2, y2;   // Coordenadas do ponto onde o rato foi solto
	Color cor;
	JButton bAzul, bVermelho, bPreto, bRosa, bCiano, bMagenta, 
	bChocolate,  bSgreen, bMBlue, bPurple, bVerde, bLaranja, 
	bBranco, bCinzento, bAmarelo, bMaroon;
	JButton bRetanguloCheio, bCirculoCheio,bCirculo, 
	bRetangulo, bLinha, bPentagono, bTriangulo, bTrianguloCheio, bPentagonoCheio;
	ArrayList<Figura> lista;
	boolean mousePressionado = false;


	public Editor() {
		setSize(800,800);
		lista = new ArrayList<Figura>();
		pFundo = new JPanel(new BorderLayout());
		pBotoes = new JPanel();
		pCoord = new JPanel();
		new Mover().start();

		cor = null;

		Color Maroon = Color.decode("#800000");
		Color Verde = Color.decode("0x008000");
		Color Purple = Color.decode("#800080");
		Color Chocolate = Color.decode("#D2691E");
		// Spring Green
		Color Sgreen = Color.decode("#00FF7F");
		// Midnight Blue
		Color MBlue = Color.decode("#191970");

		/*pBotoesFormas = new JPanel(); 
        pFundo.add(pBotoesFormas, BorderLayout.CENTER); 
		 */
		pBotoes.setBackground(Color.BLACK);
		add(pFundo);
		pFundo.add(pBotoes, BorderLayout.WEST);
		pBotoes.setPreferredSize(new Dimension(120,500));
		pEdicao = new MeuPainel();
		pFundo.add(pEdicao, BorderLayout.CENTER);
		pEdicao.addMouseListener(this);
		pEdicao.addMouseMotionListener(this);

		pFundo.add(pCoord, BorderLayout.SOUTH);
		pCoord.setBackground(Color.WHITE);
		pCoord.setPreferredSize(new Dimension(50,30));
		/*pInfo = new MeuPainel();
		pInfo.addMouseMotionListener(this);
		pCoord.add(pInfo);
		 */
		r = null;


		// CORES


		bAzul = new JButton("   ");
		bAzul.setBackground(Color.BLUE);
		pBotoes.add(bAzul);
		ActionListener alAzul = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cor = Color.BLUE;
				if(r != null) r.setColor(cor);
			}
		};
		bAzul.addActionListener(alAzul);



		bCinzento = new JButton("   ");
		bCinzento.setBackground(Color.lightGray);
		pBotoes.add(bCinzento);
		ActionListener alCinzento = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cor = Color.lightGray;
				if(r != null) r.setColor(cor);
			}
		};
		bCinzento.addActionListener(alCinzento);


		bRosa = new JButton("   ");
		bRosa.setBackground(Color.pink);
		pBotoes.add(bRosa);
		ActionListener alRosa = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cor = Color.pink;
				if(r != null) r.setColor(cor);
			}
		};
		bRosa.addActionListener(alRosa);


		bPurple = new JButton("   ");
		bPurple.setBackground(Purple);
		pBotoes.add(bPurple);
		ActionListener alPurple = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cor = Color.decode("#800080");
				if(r != null) r.setColor(cor);
			}
		};
		bPurple.addActionListener(alPurple);


		bMBlue = new JButton("   ");
		bMBlue.setBackground(MBlue);
		pBotoes.add(bMBlue);
		ActionListener alMBlue = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cor = Color.decode("#191970");
				if(r != null) r.setColor(cor);
			}
		};
		bMBlue.addActionListener(alMBlue);


		bSgreen = new JButton("   ");
		bSgreen.setBackground(Sgreen);
		pBotoes.add(bSgreen);
		ActionListener alSgreen = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cor = Color.decode("#00FF7F");
				if(r != null) r.setColor(cor);
			}
		};
		bSgreen.addActionListener(alSgreen);

		bChocolate = new JButton("   ");
		bChocolate.setBackground(Chocolate);
		pBotoes.add(bChocolate);
		ActionListener alChocolate = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cor = Color.decode("#D2691E");
				if(r != null) r.setColor(cor);
			}
		};
		bChocolate.addActionListener(alChocolate);


		bVerde = new JButton("   ");
		bVerde.setBackground(Verde);
		pBotoes.add(bVerde);
		ActionListener alVerde = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cor = Color.decode("0x008000");
				if(r != null) r.setColor(cor);
			}
		};
		bVerde.addActionListener(alVerde);


		bMaroon = new JButton("   ");
		bMaroon.setBackground(Maroon);
		pBotoes.add(bMaroon);
		ActionListener alMaroon = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cor = Color.decode("#800000");
				if(r != null) r.setColor(cor);
			}
		};
		bMaroon.addActionListener(alMaroon);


		bLaranja = new JButton("   ");
		bLaranja.setBackground(Color.orange);
		pBotoes.add(bLaranja);
		ActionListener alLaranja = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cor = Color.orange;
				if(r != null) r.setColor(cor);
			}
		};
		bLaranja.addActionListener(alLaranja);


		bBranco = new JButton("   ");
		bBranco.setBackground(Color.white);
		pBotoes.add(bBranco);
		ActionListener alBranco = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cor = Color.white;
				if(r != null) r.setColor(cor);
			}
		};
		bBranco.addActionListener(alBranco);

		bPreto = new JButton("   ");
		bPreto.setBackground(Color.black);
		pBotoes.add(bPreto);
		ActionListener alPreto = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cor = Color.black;
				if(r != null)  r.setColor(cor);
			}
		};
		bPreto.addActionListener(alPreto);


		bAmarelo = new JButton("   ");
		bAmarelo.setBackground(Color.yellow);
		pBotoes.add(bAmarelo);
		ActionListener alAmarelo = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cor = Color.yellow;
				if(r != null) r.setColor(cor);
			}
		};
		bAmarelo.addActionListener(alAmarelo);


		bMagenta = new JButton("   ");
		bMagenta.setBackground(Color.magenta);
		pBotoes.add(bMagenta);
		ActionListener alMagenta = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cor = Color.magenta;
				if(r != null) r.setColor(cor);
			}
		};
		bMagenta.addActionListener(alMagenta);


		bCiano = new JButton("   ");
		bCiano.setBackground(Color.cyan);
		pBotoes.add(bCiano);
		ActionListener alCiano = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cor = Color.cyan;
				if(r != null) r.setColor(cor);
			}
		};
		bCiano.addActionListener(alCiano);


		bVermelho = new JButton("   ");
		bVermelho.setBackground(Color.RED);
		pBotoes.add(bVermelho);
		ActionListener alVermelho = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cor = Color.RED;
				if(r != null) r.setColor(cor);
			}
		};
		bVermelho.addActionListener(alVermelho);


		//FORMAS


		bTriangulo = new JButton ("Triangulo");
		pBotoes.add(bTriangulo);
		ActionListener acTriangulo = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				r = new Triangulo();
				r.setColor(cor);
			}
		};
		bTriangulo.addActionListener(acTriangulo);

		bTrianguloCheio = new JButton ("TrianguloCheio");
		pBotoes.add(bTrianguloCheio);
		ActionListener acTrianguloCheio = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				r = new TrianguloCheio();
				r.setColor(cor);
			}
		};
		bTrianguloCheio.addActionListener(acTrianguloCheio);




		bPentagono = new JButton ("Pentagono");
		pBotoes.add(bPentagono);
		ActionListener acPentagono = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				r = new Pentagono();
				r.setColor(cor);
			}
		};
		bPentagono.addActionListener(acPentagono);

		bPentagonoCheio = new JButton ("PentagonoCheio");
		pBotoes.add(bPentagonoCheio);
		ActionListener acPentagonoCheio = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				r = new PentagonoCheio();
				r.setColor(cor);
			}
		};
		bPentagonoCheio.addActionListener(acPentagonoCheio);

		bRetanguloCheio = new JButton ("RetânguloCheio");

		pBotoes.add(bRetanguloCheio);

		ActionListener acRetanguloCheio = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				r = new RetanguloCheio();
				r.setColor(cor);
			}
		};
		bRetanguloCheio.addActionListener(acRetanguloCheio);

		bCirculoCheio = new JButton ("CirculoCheio");

		pBotoes.add(bCirculoCheio);

		ActionListener acCirculoCheio = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				r = new CirculoCheio();
				r.setColor(cor);
			}
		};

		bCirculoCheio.addActionListener(acCirculoCheio);

		bCirculo = new JButton ("Circulo");

		pBotoes.add(bCirculo);

		ActionListener acCirculo = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				r = new Circulo();
				r.setColor(cor);
			}
		};
		bCirculo.addActionListener(acCirculo);

		bRetangulo = new JButton ("Retangulo");

		pBotoes.add(bRetangulo);

		ActionListener acRetangulo = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				r = new Retangulo();
				r.setColor(cor);
			}
		};
		bRetangulo.addActionListener(acRetangulo);

		bLinha = new JButton ("Linha");

		pBotoes.add(bLinha);


		ActionListener acLinha = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				r = new Linha();
				r.setColor(cor);
			}
		};

		bLinha.addActionListener(acLinha);

		// GRAVAR E ABRIR


		//Criar ActionListeners para o Menu

		ActionListener acSair = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				System.exit(0);

			}
		};


		ActionListener acInfo = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				mostraInfo();

			}
		};

		ActionListener acInfo2 = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				mostraInfo2();

			}
		};

		// Criar Menu
		JMenuBar menu = new JMenuBar();
		this.setJMenuBar(menu);
		JMenu fich = new JMenu("Ficheiro");
		menu.add(fich);
		JMenuItem info = new JMenuItem("Informação");
		fich.add(info);
		info.addActionListener(acInfo);

		//gravar e abrir
		
		
		ActionListener acGravar = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				try {
					FileOutputStream writer;
					writer = new FileOutputStream("try.txt");
					ObjectOutputStream wwriter = new ObjectOutputStream(writer);
			        wwriter.writeObject(lista);
			        wwriter.close();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		};
		
		ActionListener acAbrir = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				/*FileInputStream fis = new FileInputStream("try.tmp");
				ObjectInputStream ois = new ObjectInputStream(fis);
				lista = (List<Figura>) ois.readObject();
				ois.close();*/
			}
		};
		
		
		JMenuItem gravar = new JMenuItem("Gravar");
		fich.add(gravar);
		gravar.addActionListener(acGravar);
		JMenuItem abrir = new JMenuItem("Abrir");
		fich.add(abrir);
		abrir.addActionListener(acAbrir);
		
		
		
		
		JMenuItem sair = new JMenuItem("Sair");
		fich.add(sair);
		sair.addActionListener(acSair);
		JMenu fich2 = new JMenu("Acerca");
		menu.add(fich2);
		JMenuItem info2 = new JMenuItem("Sobre");
		fich2.add(info2);
		info2.addActionListener(acInfo2);
	}

	public void mostraInfo(){
		final JDialog dialogo = new JDialog(this, "Título", true);
		JPanel pDialogo = new JPanel();
		Label info = new Label("Editor Gráfico");
		pDialogo.add(info);
		JButton bOK = new JButton("OK");
		pDialogo.add(bOK);

		ActionListener acOK = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				dialogo.dispose();
			}
		};
		bOK.addActionListener(acOK);

		dialogo.getContentPane().add(pDialogo);
		dialogo.pack();
		dialogo.setVisible(true);
	}

	public void mostraInfo2(){
		final JDialog dialogo2 = new JDialog(this, "Sobre", true);
		JPanel pDialogo2 = new JPanel();
		Label info2 = new Label("André Cerveira, Nº21402048 " + "João Batista, Nº21404080 " + "Miguel Soeiro, Nº21502788");
		pDialogo2.add(info2);
		JButton bOK = new JButton("OK");
		pDialogo2.add(bOK);

		ActionListener acOK = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dialogo2.dispose();

			}

		};
		bOK.addActionListener(acOK);

		dialogo2.getContentPane().add(pDialogo2);
		dialogo2.pack();
		dialogo2.setVisible(true);
	}

	public static void main(String[] args) {
		Editor e = new Editor();
		JTextArea jTextArea = new JTextArea();
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		int x = (int) b.getX();
		int y = (int) b.getY();
		e.add(jTextArea, BorderLayout.SOUTH );
		e.setVisible(true);
		e.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//jTextArea.append("(x,y) = ("+MouseInfo.getPointerInfo().getLocation().x+", "+MouseInfo.getPointerInfo().getLocation().y+")");
		while (true) {
			jTextArea.append("(x,y) =("+MouseInfo.getPointerInfo().getLocation().x+", "+MouseInfo.getPointerInfo().getLocation().y+")");
			if(r != null) jTextArea.append(" area = "+ r.Area());
			if(r != null) jTextArea.append(" Perimetro = " + r.Perimetro());
			try
			{
				Thread.sleep(30);
				jTextArea.setText("");
			}
			catch (InterruptedException e3)
			{
				e3.printStackTrace();
			}
		}

	}


	@Override 
	public void mouseClicked(MouseEvent mouse){
		if (r != null) {
			if(r.getClass() == Triangulo.class) { 
				Triangulo tri = (Triangulo) r;
				//System.out.println("count =" +tri.getCount());
				int g = tri.getCount();
				tri.setCount(g+1);
				g++;
				//System.out.println("mouseClicked" );

				if(g==1){
					tri.p.x=mouse.getX();
					tri.p.y=mouse.getY();
				}

				if(g==2){
					//tri.getP2().x = mouse.getX();
					//tri.getP2().y = mouse.getY();
					tri.setP2(new Ponto(mouse.getX(), mouse.getY()));
				}
				if(g==3){
					tri.setP3(new Ponto(mouse.getX(), mouse.getY()));
					//Inicializar class para adicionar à lista
					addFiguraLista();
					System.out.println(lista.size());
				}
				repaint();
			}
			if(r.getClass() == TrianguloCheio.class) { 
				TrianguloCheio tri = (TrianguloCheio) r;
				//System.out.println("count =" +tri.getCount());
				int g = tri.getCount();
				tri.setCount(g+1);
				g++;
				//System.out.println("mouseClicked" );

				if(g==1){
					tri.p.x=mouse.getX();
					tri.p.y=mouse.getY();
				}

				if(g==2){
					//tri.getP2().x = mouse.getX();
					//tri.getP2().y = mouse.getY();
					tri.setP2(new Ponto(mouse.getX(), mouse.getY()));
				}
				if(g==3){
					tri.setP3(new Ponto(mouse.getX(), mouse.getY()));
					//Inicializar class para adicionar à lista
					addFiguraLista();
					System.out.println(lista.size());
				}
				repaint();
			}
		}
	}

	// TRATAR DE EXCEÇÃO P TRICHEIO 
	@Override 
	public void mouseReleased(MouseEvent e) {
		//System.out.println("mouseReleased" );
		if(r != null) {
			//System.out.println(((Triangulo)r).getCount());
			if ( (r.getClass() != Triangulo.class && r.getClass() != TrianguloCheio.class)) {
				addFiguraLista();
				System.out.println(lista.size());
			}
			//
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		mousePressionado = true;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(r != null) {
			x2 = e.getX();
			y2 = e.getY();
			r.setCoordenadas(x1, y1, x2, y2);
			pEdicao.repaint();
		}
	}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	@Override public void mouseMoved(MouseEvent e) {}
	public class Mover extends Thread{
		public void run() {
			while(true) {
				try {
					sleep(1000);
				}
				catch (Exception erro) {}
				if (mousePressionado) {
					Point ponto = getMousePosition();
					//pEdicao.setBounds(ponto.x, ponto.y, 250, 150);
				}
			}
		}

	}


	class MeuPainel extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (r == null){

			}
			else{ 
				for(Figura f: lista)
					f.desenha(g);
				r.desenha(g);
			}
		}
	}
	public void addFiguraLista() {
		Class c = r.getClass();
		lista.add(r);
		mousePressionado = false;

		Object param[] = new Object[] {};

		try {
			Constructor cons = c.getConstructor();
			r = (Figura) (cons.newInstance(param));
			r.setColor(cor);
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	/*public static void info() {
        int mouseY = MouseInfo.getPointerInfo().getLocation().y;
        int mouseX = MouseInfo.getPointerInfo().getLocation().x;
        System.out.println("x:" + mouseX + "y:" + mouseY);
    }*/

}

