package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Bishop;
import model.King;
import model.Knight;
import model.ListPieces;
import model.Pawn;
import model.Piece;
import model.Queen;
import model.Rook;

public class ChessLaunch extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel bannerPanel = new JPanel();
	private JLabel bannerLabel = new JLabel("Bienvenue sur i-Chess !");

	private JPanel buttonsPanel = new JPanel();
	private JPanel nvChessPanel = new JPanel();
	private JPanel loadChessPanel = new JPanel();

	private Button start = new Button("Nouvelle partie", "icons/add.png", new Color(0x26b1aa));
	private Button upload = new Button("Continuer une partie", "/icons/upload-file.png", new Color(0xd67c54));

	private JFileChooser loadChess = new JFileChooser();
	private ListPieces uploadedListeBlanche = new ListPieces();
	private ListPieces uploadedListeNoir = new ListPieces();

	public ChessLaunch() {
		super("i-Chess");
		createBannerPanel();
		createButtonsPanel();
		setLayout(new BorderLayout());
		add(bannerPanel, BorderLayout.NORTH);
		add(buttonsPanel, BorderLayout.SOUTH);

		pack();
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void createBannerPanel() {
		Image banner = new ImageIcon("images/icons/banner.png").getImage().getScaledInstance(250, 250,
				Image.SCALE_SMOOTH);
		bannerLabel.setIcon(new ImageIcon(banner));
		bannerLabel.setFont(new Font("Evil Empire", Font.PLAIN, 30));
		bannerPanel.add(bannerLabel);
		bannerPanel.setPreferredSize(new Dimension(600, 250));
		bannerPanel.setBackground(new Color(210, 210, 210));
	}

	private void createButtonsPanel() {
		nvChessPanel.setLayout(new GridLayout(1, 1));
		nvChessPanel.setBorder(BorderFactory.createEmptyBorder(45, 50, 45, 25));
		nvChessPanel.add(start);

		loadChessPanel.setLayout(new GridLayout(1, 1));
		loadChessPanel.setBorder(BorderFactory.createEmptyBorder(45, 50, 45, 25));
		loadChessPanel.add(upload);

		buttonsPanel.setLayout(new GridLayout(1, 2));
		buttonsPanel.setPreferredSize(new Dimension(600, 150));
		buttonsPanel.add(nvChessPanel);
		buttonsPanel.add(loadChessPanel);
		
		loadChess.setFileFilter(new FileNameExtensionFilter("i-Chess file", "ichess"));

		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						long start = System.currentTimeMillis();
						new ChessGUI(null, null);
						long end = System.currentTimeMillis();
						System.out.println("Estimated time : " + (end - start));
					}
				});
				dispose();
			}
		});

		upload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int rVal = loadChess.showOpenDialog(ChessLaunch.this);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					try {
						ListPieces blanche = new ListPieces();
						ListPieces noir = new ListPieces();
						
						FileInputStream file = new FileInputStream(loadChess.getSelectedFile());
						ObjectInputStream obj = new ObjectInputStream(file);
						uploadedListeBlanche = (ListPieces) obj.readObject();
						uploadedListeNoir = (ListPieces) obj.readObject();
						Vector<Piece> allPieces = new Vector<Piece>();
						allPieces.addAll(uploadedListeBlanche.getPieces());
						allPieces.addAll(uploadedListeNoir.getPieces());
						
						for(Piece p : allPieces) {
							if(p instanceof Pawn) {
								if(p.isBlanche()) {
									blanche.addPiece(new Pawn(p.getX(), p.getY(), true, p.getIcon()));
								} else {
									noir.addPiece(new Pawn(p.getX(), p.getY(), false, p.getIcon()));
								}
							} else if (p instanceof Bishop) {
								if(p.isBlanche()) {
									blanche.addPiece(new Bishop(p.getX(), p.getY(), true, p.getIcon()));
								} else {
									noir.addPiece(new Bishop(p.getX(), p.getY(), false, p.getIcon()));
								}
							} else if (p instanceof Knight) {
								if(p.isBlanche()) {
									blanche.addPiece(new Knight(p.getX(), p.getY(), true, p.getIcon()));
								} else {
									noir.addPiece(new Knight(p.getX(), p.getY(), false, p.getIcon()));
								}
							} else if (p instanceof Rook) {
								if(p.isBlanche()) {
									blanche.addPiece(new Rook(p.getX(), p.getY(), true, p.getIcon()));
								} else {
									noir.addPiece(new Rook(p.getX(), p.getY(), false, p.getIcon()));
								}
							} else if (p instanceof Queen) {
								if(p.isBlanche()) {
									blanche.addPiece(new Queen(p.getX(), p.getY(), true, p.getIcon()));
								} else {
									noir.addPiece(new Queen(p.getX(), p.getY(), false, p.getIcon()));
								}
							} else if (p instanceof King) {
								if(p.isBlanche()) {
									blanche.addPiece(new King(p.getX(), p.getY(), true, p.getIcon()));
								} else {
									noir.addPiece(new King(p.getX(), p.getY(), false, p.getIcon()));
								}
							}
						}
						obj.close();
						file.close();
						new ChessGUI(blanche, noir);
					} catch (IOException | ClassNotFoundException cnfe) {
						// TODO Auto-generated catch block
						cnfe.printStackTrace();
					}
					uploadedListeBlanche.toString();
					dispose();
				}
				if (rVal == JFileChooser.CANCEL_OPTION) {
					System.out.println("CANCELED");
				}
				
			}
		});
	}

}
