package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ChessController;
import model.ListPieces;
import workers.InitBlackWorker;
import workers.InitWhiteWorker;

public class ChessGUI extends JFrame {

	private JToolBar toolbar = new JToolBar();
	private JPanel headerPanel = new JPanel();
	private final JLabel headerMessage = new JLabel("Bienvenue sur i-Chess !");

	private Table tableChess;

	private JPanel rightPanel = new JPanel();
	private JPanel timerPanel = new JPanel();

	private TimerPanel timerBlanche = new TimerPanel("Blanc");
	private TimerPanel timerNoir = new TimerPanel("Noir");

	private Button start = new Button("Jouer", "icons/add.png", new Color(0x26b1aa));
	private Button saveJeu = new Button("Enregistrer", "icons/save.png", new Color(0xd47b53));
	private Button quit = new Button("Quitter", "icons/close.png", new Color(0xff605c));
	private JFileChooser saveGame = new JFileChooser();

	private ListPieces piecesBlanche = new ListPieces();
	private ListPieces piecesNoir = new ListPieces();

	private ChessController chessController;

	private TimerThread timerB;
	private TimerThread timerN;

	public ChessGUI(ListPieces blanche, ListPieces noir) {
		// TODO Auto-generated constructor stub
		if (blanche != null && noir != null) {
			this.piecesBlanche = blanche;
			this.piecesNoir = noir;
			System.out.println("Blanche Size GUI : " + piecesBlanche.size());
		}

		chessController = new ChessController(this, piecesBlanche, piecesNoir);
		timerN = new TimerThread(timerNoir, chessController);
		timerB = new TimerThread(timerBlanche, chessController);
		tableChess = new Table(chessController, piecesBlanche, piecesNoir, timerB, timerN);

		InitWhiteWorker blancheWorker = new InitWhiteWorker(chessController);
		InitBlackWorker noirWorker = new InitBlackWorker(chessController);
		blancheWorker.execute();
		noirWorker.execute();

		setLayout(new BorderLayout(15, 10));

		toolbar.setLayout(new BorderLayout());
		toolbar.setFloatable(false);
		toolbar.setMargin(new Insets(5, 10, 5, 5));
		start.setFont(new Font("Evil Empire", Font.PLAIN, 20));
		saveJeu.setFont(new Font("Evil Empire", Font.PLAIN, 20));
		quit.setFont(new Font("Evil Empire", Font.PLAIN, 20));

		headerMessage.setForeground(new Color(50, 111, 138));
		headerMessage.setFont(new Font("Evil Empire", Font.PLAIN, 24));

		headerPanel.setLayout(new FlowLayout());
		headerPanel.add(start);
		headerPanel.add(saveJeu);
		headerPanel.add(quit);
		
		saveGame.setFileFilter(new FileNameExtensionFilter("i-Chess file", "ichess"));

		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tableChess.initTable();
				timerB.execute();
			}
		});

		saveJeu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int rVal = saveGame.showSaveDialog(ChessGUI.this);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					File fileChess;
					if(saveGame.getSelectedFile().getAbsolutePath().contains(".ichess")) {
						fileChess = new File(saveGame.getSelectedFile().getAbsolutePath());
					} else {
						fileChess = new File(saveGame.getSelectedFile().getAbsolutePath() + ".ichess");
					}
					System.out.println(fileChess.getAbsolutePath());
					try {
						FileOutputStream file = new FileOutputStream(fileChess);
						ObjectOutputStream obj = new ObjectOutputStream(file);
						obj.writeObject(piecesBlanche);
						obj.writeObject(piecesNoir);
						obj.close();
						file.close();
					} catch (IOException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
				}
				if (rVal == JFileChooser.CANCEL_OPTION) {
					System.out.println("CANCELED");
				}
			}
		});

		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

		toolbar.add(headerMessage, BorderLayout.WEST);
		toolbar.add(headerPanel, BorderLayout.EAST);

		timerPanel.setLayout(new FlowLayout());
		timerPanel.add(timerBlanche);
		timerPanel.add(timerNoir);
		timerPanel.setPreferredSize(new Dimension(200, 200));

		rightPanel.setLayout(new BorderLayout());
		rightPanel.add(timerPanel, BorderLayout.NORTH);

		tableChess.setSize(tableChess.getPreferredSize());

		add(toolbar, BorderLayout.PAGE_START);
		add(rightPanel, BorderLayout.EAST);
		add(tableChess, BorderLayout.CENTER);

		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900, 800);
		setResizable(false);
		setLocationRelativeTo(null);
	}
}
