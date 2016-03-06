package editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Editor extends JFrame implements MouseListener, MouseMotionListener, Ficheiro {
	JPanel pFundo, pBotoes,pCoord;
	static Figura r;
	MeuPainel pEdicao, pInfo;
	int x1, y1;   // Coordenadas do ponto onde o rato foi premido
	int x2, y2;   // Coordenadas do ponto onde o rato foi solto
	Color cor;
	JTextArea info;
	JButton bAzul, bVermelho, bPreto, bRosa, bCiano, bMagenta, 
	bChocolate,  bSgreen, bMBlue, bPurple, bVerde, bLaranja, 
	bBranco, bCinzento, bAmarelo, bMaroon;
	JButton bRetanguloCheio, bCirculoCheio,bCirculo, 
	bRetangulo, bLinha, bPentagono, bTriangulo, bTrianguloCheio, bPentagonoCheio, bCasota, 
	bSelecionar, bApagar, bMover;
	ArrayList<Figura> lista;
	int ferramenta;

	public Editor() {
		ferramenta = 0;
		setSize(800, 800);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		lista = new ArrayList<Figura>();
		pFundo = new JPanel(new BorderLayout());
		pBotoes = new JPanel();
		pCoord = new JPanel();

		setTitle("EDITOR GRÁFICO");
		info = new JTextArea();
		textoSul();
		cor = Color.black;

		Color Maroon = Color.decode("#800000");
		Color Verde = Color.decode("0x008000");
		Color Purple = Color.decode("#800080");
		Color Chocolate = Color.decode("#D2691E");
		// Spring Green
		Color Sgreen = Color.decode("#00FF7F");
		// Midnight Blue
		Color MBlue = Color.decode("#191970");

		pBotoes.setBackground(Color.WHITE);
		pBotoes.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		add(pFundo);
		pFundo.add(pBotoes, BorderLayout.WEST);
		pBotoes.setPreferredSize(new Dimension(200,500));
		pEdicao = new MeuPainel();
		pEdicao.setBackground(Color.WHITE);
		pFundo.add(pEdicao, BorderLayout.CENTER);
		pEdicao.addMouseListener(this);
		pEdicao.addMouseMotionListener(this);

		pFundo.add(pCoord, BorderLayout.SOUTH);
		pCoord.setLayout(new BorderLayout());
		pCoord.add(info, BorderLayout.CENTER);
		pCoord.setBackground(Color.WHITE);
		pCoord.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		pCoord.setPreferredSize(new Dimension(20,20));
		
		r = null;

		// CORES
		bAzul = new JButton("   ");
		bAzul.setBackground(Color.BLUE);
		pBotoes.add(bAzul);
		ActionListener alAzul = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cor = Color.BLUE;
				if(r != null && ferramenta <= 1) {
					r.setColor(cor);
					repaint();
				}
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
				if(r != null && ferramenta <= 1) r.setColor(cor);
				ferramenta = 0;
				repaint();
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
				if(r != null && ferramenta <= 1) r.setColor(cor);
				repaint();
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
				if(r != null && ferramenta <= 1) r.setColor(cor);
				repaint();
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
				if(r != null && ferramenta <= 1) r.setColor(cor);
				repaint();
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
				if(r != null && ferramenta <= 1) r.setColor(cor);
				repaint();
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
				if(r != null && ferramenta <= 1) r.setColor(cor);
				repaint();
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
				if(r != null && ferramenta <= 1) r.setColor(cor);
				repaint();
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
				if(r != null && ferramenta <= 1) r.setColor(cor);
				repaint();
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
				if(r != null && ferramenta <= 1) r.setColor(cor);
				repaint();
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
				if(r != null && ferramenta <= 1) r.setColor(cor);
				repaint();
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
				if(r != null && ferramenta <= 1)  r.setColor(cor);
				repaint();
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
				if(r != null && ferramenta <= 1) r.setColor(cor);
				repaint();
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
				if(r != null && ferramenta <= 1) r.setColor(cor);
				repaint();
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
				if(r != null && ferramenta <= 1) r.setColor(cor);
				repaint();
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
				if(r != null && ferramenta <= 1) r.setColor(cor);
				repaint();
			}
		};
		bVermelho.addActionListener(alVermelho);


		//FORMAS
		bCasota = new JButton ("");
		pBotoes.add(bCasota);
		ActionListener acCasota = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				r = new CasotaDoCão();
				r.setColor(cor);
				ferramenta = 0;
			}
		};
		bCasota.addActionListener(acCasota);
		bCasota.setBorderPainted(false);
		bCasota.setBorder(null);
		bCasota.setMargin(new Insets(0, 0, 0, 0));
		bCasota.setContentAreaFilled(false);
		try {
			Image img = ImageIO.read(new File("Images/snoopy.png"));
			bCasota.setIcon(new ImageIcon(img));
			Image img1 = ImageIO.read(new File("Images/snoopy1.png"));
			bCasota.setPressedIcon(new ImageIcon(img1));
		} catch (IOException ex) {
		}

		bTriangulo = new JButton ("");
		pBotoes.add(bTriangulo);
		ActionListener acTriangulo = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				r = new Triangulo();
				r.setColor(cor);
				ferramenta = 0;
			}
		};
		bTriangulo.addActionListener(acTriangulo);
		bTriangulo.setBorderPainted(false);
		bTriangulo.setBorder(null);
		bTriangulo.setMargin(new Insets(0, 0, 0, 0));
		bTriangulo.setContentAreaFilled(false);
		try {
			Image img = ImageIO.read(new File("Images/Triangle1.png"));
			bTriangulo.setIcon(new ImageIcon(img));
			Image img1 = ImageIO.read(new File("Images/Triangle.png"));
			bTriangulo.setPressedIcon(new ImageIcon(img1));
		} catch (IOException ex) {
		}


		bTrianguloCheio = new JButton ("");
		pBotoes.add(bTrianguloCheio);
		ActionListener acTrianguloCheio = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				r = new TrianguloCheio();
				r.setColor(cor);
				ferramenta = 0;
			}
		};
		bTrianguloCheio.addActionListener(acTrianguloCheio);
		bTrianguloCheio.setBorderPainted(false);
		bTrianguloCheio.setBorder(null);
		bTrianguloCheio.setMargin(new Insets(0, 0, 0, 0));
		bTrianguloCheio.setContentAreaFilled(false);
		try {
			Image img = ImageIO.read(new File("Images/Triangle1full.png"));
			bTrianguloCheio.setIcon(new ImageIcon(img));
			Image img1 = ImageIO.read(new File("Images/Trianglefull.png"));
			bTrianguloCheio.setPressedIcon(new ImageIcon(img1));
		} catch (IOException ex) {
		}

		bPentagono = new JButton ("");
		pBotoes.add(bPentagono);
		ActionListener acPentagono = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				r = new Pentagono();
				r.setColor(cor);
				ferramenta = 0;
			}
		};
		bPentagono.addActionListener(acPentagono);
		bPentagono.setBorderPainted(false);
		bPentagono.setBorder(null);
		bPentagono.setMargin(new Insets(0, 0, 0, 0));
		bPentagono.setContentAreaFilled(false);
		try {
			Image img = ImageIO.read(new File("Images/pentagon1.png"));
			bPentagono.setIcon(new ImageIcon(img));
			Image img1 = ImageIO.read(new File("Images/pentagon.png"));
			bPentagono.setPressedIcon(new ImageIcon(img1));
		} catch (IOException ex) {
		}

		bPentagonoCheio = new JButton ("");
		pBotoes.add(bPentagonoCheio);
		ActionListener acPentagonoCheio = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				r = new PentagonoCheio();
				r.setColor(cor);
				ferramenta = 0;
			}
		};
		bPentagonoCheio.addActionListener(acPentagonoCheio);
		bPentagonoCheio.setBorderPainted(false);
		bPentagonoCheio.setBorder(null);
		bPentagonoCheio.setMargin(new Insets(0, 0, 0, 0));
		bPentagonoCheio.setContentAreaFilled(false);
		try {
			Image img = ImageIO.read(new File("Images/pentagonfull.png"));
			bPentagonoCheio.setIcon(new ImageIcon(img));
			Image img1 = ImageIO.read(new File("Images/pentagon1full.png"));
			bPentagonoCheio.setPressedIcon(new ImageIcon(img1));
		} catch (IOException ex) {
		}

		bRetangulo = new JButton ("");
		pBotoes.add(bRetangulo);
		ActionListener acRetangulo = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				r = new Retangulo();
				r.setColor(cor);
				ferramenta = 0;
			}
		};
		bRetangulo.addActionListener(acRetangulo);
		bRetangulo.setBorderPainted(false);
		bRetangulo.setBorder(null);
		bRetangulo.setMargin(new Insets(0, 0, 0, 0));
		bRetangulo.setContentAreaFilled(false);
		try {
			Image img = ImageIO.read(new File("Images/Retangulo1.png"));
			bRetangulo.setIcon(new ImageIcon(img));
			Image img1 = ImageIO.read(new File("Images/Retangulo.png"));
			bRetangulo.setPressedIcon(new ImageIcon(img1));
		} catch (IOException ex) {
		}

		bRetanguloCheio = new JButton ("");
		pBotoes.add(bRetanguloCheio);
		ActionListener acRetanguloCheio = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				r = new RetanguloCheio();
				r.setColor(cor);
				ferramenta = 0;
			}
		};
		bRetanguloCheio.addActionListener(acRetanguloCheio);
		bRetanguloCheio.setBorderPainted(false);
		bRetanguloCheio.setBorder(null);
		bRetanguloCheio.setMargin(new Insets(0, 0, 0, 0));
		bRetanguloCheio.setContentAreaFilled(false);
		try {
			Image img = ImageIO.read(new File("Images/Retangulo1full.png"));
			bRetanguloCheio.setIcon(new ImageIcon(img));
			Image img1 = ImageIO.read(new File("Images/Retangulofull.png"));
			bRetanguloCheio.setPressedIcon(new ImageIcon(img1));
		} catch (IOException ex) {
		}


		bCirculo = new JButton ("");
		pBotoes.add(bCirculo);
		ActionListener acCirculo = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				r = new Circulo();
				r.setColor(cor);
				ferramenta = 0;
			}
		};
		bCirculo.addActionListener(acCirculo);
		bCirculo.setBorderPainted(false);
		bCirculo.setBorder(null);
		bCirculo.setMargin(new Insets(0, 0, 0, 0));
		bCirculo.setContentAreaFilled(false);
		try {
			Image img = ImageIO.read(new File("Images/Circle1.png"));
			bCirculo.setIcon(new ImageIcon(img));
			Image img1 = ImageIO.read(new File("Images/Circle.png"));
			bCirculo.setPressedIcon(new ImageIcon(img1));
		} catch (IOException ex) {
		}

		bCirculoCheio = new JButton ("");
		pBotoes.add(bCirculoCheio);
		ActionListener acCirculoCheio = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				r = new CirculoCheio();
				r.setColor(cor);
				ferramenta = 0;
			}
		};

		bCirculoCheio.addActionListener(acCirculoCheio);
		bCirculoCheio.setBorderPainted(false);
		bCirculoCheio.setBorder(null);
		bCirculoCheio.setMargin(new Insets(0, 0, 0, 0));
		bCirculoCheio.setContentAreaFilled(false);
		try {
			Image img = ImageIO.read(new File("Images/Circle1full.png"));
			bCirculoCheio.setIcon(new ImageIcon(img));
			Image img1 = ImageIO.read(new File("Images/Circlefull.png"));
			bCirculoCheio.setPressedIcon(new ImageIcon(img1));
		} catch (IOException ex) {
		}

		bLinha = new JButton ("");
		pBotoes.add(bLinha);
		ActionListener acLinha = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				r = new Linha();
				r.setColor(cor);
				ferramenta = 0;
			}
		};
		bLinha.addActionListener(acLinha);
		bLinha.setBorderPainted(false);
		bLinha.setBorder(null);
		bLinha.setMargin(new Insets(0, 0, 0, 0));
		bLinha.setContentAreaFilled(false);
		try {
			Image img = ImageIO.read(new File("Images/linha1.png"));
			bLinha.setIcon(new ImageIcon(img));
			Image img1 = ImageIO.read(new File("Images/linha.png"));
			bLinha.setPressedIcon(new ImageIcon(img1));
		} catch (IOException ex) {
		}

		// Ferramentas
		bSelecionar = new JButton ("");
		pBotoes.add(bSelecionar);
		ActionListener acSelecionar = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				r = null;
				ferramenta = 1;
			}
		};
		bSelecionar.addActionListener(acSelecionar);
		bSelecionar.setBorderPainted(false);
		bSelecionar.setBorder(null);
		bSelecionar.setMargin(new Insets(0, 0, 0, 0));
		bSelecionar.setContentAreaFilled(false);
		try {
			Image img = ImageIO.read(new File("Images/select.png"));
			bSelecionar.setIcon(new ImageIcon(img));
			Image img1 = ImageIO.read(new File("Images/select1.png"));
			bSelecionar.setPressedIcon(new ImageIcon(img1));
		} catch (IOException ex) {
		}

		bMover = new JButton ("");
		pBotoes.add(bMover);
		ActionListener acMover = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				r = null;
				ferramenta = 2;
			}
		};
		bMover.addActionListener(acMover);
		bMover.setBorderPainted(false);
		bMover.setBorder(null);
		bMover.setMargin(new Insets(0, 0, 0, 0));
		bMover.setContentAreaFilled(false);
		try {
			Image img = ImageIO.read(new File("Images/mover.png"));
			bMover.setIcon(new ImageIcon(img));
			Image img1 = ImageIO.read(new File("Images/mover1.png"));
			bMover.setPressedIcon(new ImageIcon(img1));
		} catch (IOException ex) {
		}

		bApagar = new JButton ("");
		pBotoes.add(bApagar);
		ActionListener acApagar = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				r = null;
				ferramenta = 3;
			}
		};
		bApagar.addActionListener(acApagar);
		bApagar.setBorderPainted(false);
		bApagar.setBorder(null);
		bApagar.setMargin(new Insets(0, 0, 0, 0));
		bApagar.setContentAreaFilled(false);
		try {
			Image img = ImageIO.read(new File("Images/cut-icon.png"));
			bApagar.setIcon(new ImageIcon(img));
			Image img1 = ImageIO.read(new File("Images/cut-icon1.png"));
			bApagar.setPressedIcon(new ImageIcon(img1));
		} catch (IOException ex) {
		}

		//Criar ActionListeners para o Menu
		ActionListener acSair = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				sair();
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

		//gravar e abrir
		ActionListener acGravar = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				gravar();
			}
		};

		ActionListener acAbrir = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				abrir();
			}
		};

		JMenuItem abrir = new JMenuItem("Abrir");
		fich.add(abrir);
		abrir.addActionListener(acAbrir);
		JMenuItem gravar = new JMenuItem("Gravar");
		fich.add(gravar);
		gravar.addActionListener(acGravar);

		JMenuItem sair = new JMenuItem("Sair");
		fich.add(sair);
		sair.addActionListener(acSair);
		JMenu fich2 = new JMenu("Acerca");
		menu.add(fich2);
		JMenuItem info2 = new JMenuItem("Sobre");
		fich2.add(info2);
		info2.addActionListener(acInfo2);
	}

	public void mostraInfo2(){
		final JDialog dialogo2 = new JDialog(this, "Sobre", true);
		JPanel pDialogo2 = new JPanel();
		Label info2 = new Label("André Cerveira, Nº21402048 " +"João Batista, Nº21404080 " + "Miguel Soeiro, Nº21502788");
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
		e.setVisible(true);
		e.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override 
	public void mouseClicked(MouseEvent mouse){
		if (ferramenta > 0) {
			for (int i=lista.size()-1; i>=0; i--) {
				Figura a = lista.get(i);
				if (a.contains(mouse.getX(), mouse.getY())) {
					if (ferramenta == 1) {
						r = a;
					} 
					if (ferramenta == 3) {
						int p = JOptionPane.showConfirmDialog(pEdicao, "Quer remover esta figura?","Remover", JOptionPane.YES_NO_OPTION);
						if (p == 0){
							lista.remove(a);
							r = null;
							repaint ();
						}
					}
					break;
				}
			}
		}

		else if (r != null) {
			if(r.getClass() == Triangulo.class) { 
				Triangulo tri = (Triangulo) r;
				int g = tri.getCount();
				tri.setCount(g+1);
				g++;
				if(g==1){
					tri.p.x=mouse.getX();
					tri.p.y=mouse.getY();
				}

				if(g==2){
					tri.setP2(new Ponto(mouse.getX(), mouse.getY()));
				}
				if(g==3){
					tri.setP3(new Ponto(mouse.getX(), mouse.getY()));
					addFiguraLista();
					System.out.println(lista.size());
				}
				repaint();
			}
			else if(r.getClass() == TrianguloCheio.class) { 
				TrianguloCheio tri = (TrianguloCheio) r;
				int g = tri.getCount();
				tri.setCount(g+1);
				g++;
				if(g==1){
					tri.p.x=mouse.getX();
					tri.p.y=mouse.getY();
				}

				if(g==2){
					tri.setP2(new Ponto(mouse.getX(), mouse.getY()));
				}
				if(g==3){
					tri.setP3(new Ponto(mouse.getX(), mouse.getY()));
					addFiguraLista();
					System.out.println(lista.size());
				}
				repaint();
			}
		}
	}

	@Override 
	public void mouseReleased(MouseEvent e) {
		if(r != null && ferramenta == 0) {
			if ( (r.getClass() != Triangulo.class && r.getClass() != TrianguloCheio.class)) {
				addFiguraLista();
				System.out.println(lista.size());
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		if (ferramenta == 2) {
			for (int i=lista.size()-1; i>=0; i--) {
				Figura a = lista.get(i);
				if (a.contains(x1, y1)) {
					r = a;
					break;
				}
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		if(r != null && ferramenta == 0) {
			r.setCoordenadas(x1, y1, x2, y2);
			pEdicao.repaint();
		}
		else if (ferramenta == 2) {
			int dx = x2 - x1;
			int dy = y2 - y1;
			r.mover(dx, dy);
			x1 = x2;
			y1 = y2;
			repaint();
		}
	}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	@Override public void mouseMoved(MouseEvent e) {}

	class MeuPainel extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for(Figura f: lista)
				f.desenha(g);
			if (r != null){
				r.desenha(g);
			}
		}
	}
	public void addFiguraLista() {
		Class c = r.getClass();
		lista.add(r);

		Object param[] = new Object[] {};

		try {
			Constructor cons = c.getConstructor();
			r = (Figura) (cons.newInstance(param));
			r.setColor(cor);
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		}
	}

	public void clearLista () {
		lista.clear();
		pEdicao.removeAll();
		repaint();
	}

	public void textoSul () {
		Thread t = new Thread(new Runnable(){
			@Override
			public void run() {
				while (true) {
					info.setText("(x,y) =("+MouseInfo.getPointerInfo().getLocation().x+", "+MouseInfo.getPointerInfo().getLocation().y+")");
					if(r != null && r.p.x != 0) {
						info.append (r.mostrarInfo());
					}
					else if (lista.size()>0) {
						info.append(lista.get(lista.size()-1).mostrarInfo());
					}
					try
					{
						Thread.sleep(100);
						info.setText("");
					}
					catch (InterruptedException e3)
					{
						e3.printStackTrace();
					}
				}	
			}

		});
		t.start();
	}
	
	@Override
	public void gravar() {
		String name = JOptionPane.showInputDialog(pFundo,"Nome do ficheiro", "Gravar" ,JOptionPane.PLAIN_MESSAGE);
		if (name != "") {
			try {
				FileOutputStream writer;
				writer = new FileOutputStream(name+".eg");
				ObjectOutputStream wwriter = new ObjectOutputStream(writer);
				wwriter.writeObject(lista);
				wwriter.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		else {
			JOptionPane.showMessageDialog(null,"Não é possivel criar ficheiro sem nome", "ERRO!" ,JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void abrir() {
		try {
			String nome = null;
			JFileChooser fc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Editor gráfico", "eg");
			fc.addChoosableFileFilter(filter);
			fc.setAcceptAllFileFilterUsed(false);
			fc.setDialogTitle("Abrir");
			int returnValue = fc.showOpenDialog(pFundo);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File ficheiro = fc.getSelectedFile();
				nome = ficheiro.getAbsolutePath();
			}
			if (nome != null && nome != "") {
				FileInputStream fis = new FileInputStream(nome);
				ObjectInputStream ois;
				ois = new ObjectInputStream(fis);
				ArrayList <Figura> listaFile = (ArrayList<Figura>) ois.readObject();
				ois.close();
				clearLista();
				for(Figura a: listaFile) {
					r = a;
					addFiguraLista();
				}
			}
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null,"O sistema não conseguiu localizar o ficheiro especificado", "ERRO!" ,JOptionPane.ERROR_MESSAGE);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void sair() {
		System.exit(0);
	}
}

