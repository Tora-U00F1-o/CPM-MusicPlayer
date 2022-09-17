package igu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import player.MusicPlayer;
import player.MyFile;
import utils.CodeUtil;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFileChooser fileChooser;
	private MusicPlayer player;
	private ProcessNext pn = new ProcessNext();
	
	private JPanel contentPane;
	
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnPlay;
	private JMenu mnOptions;
	private JMenu mnHelp;
	private JMenuItem mnItOpen;
	private JMenuItem mnItExit;
	private JMenuItem mnItNext;
	private JMenuItem mnItrandom;
	private JMenuItem mnItContents;
	private JMenuItem mnItAbout;
	private JSeparator separator;
	private JSeparator separator_1;
	
	private JTextField txtVolumen;

	private JLabel lbLibrary;
	private JPanel pnLibrary;
	private JList<MyFile> listLibrary;
	private DefaultListModel<MyFile> modelListLibrary;

	private JLabel lbPlay;
	private JPanel pnPlay;
	private JScrollPane scPlay;
	private JList<MyFile> listPlay;
	private DefaultListModel<MyFile> modelListPlay;
	
	private JSlider slVolumen;
	
	private JButton btnBack;
	private JButton btnPlay;
	
	private JPanel pnBtnPlay;
	private JButton btnStop;
	private JButton btnNext;
	private JButton btnDelete_1_3;
	private JPanel pnBtnLibrary;
	private JPanel panel;
	private JPanel pnNorte;
	private JPanel pnCentro;
	private JPanel pnSur;
	private JLabel lbLogo;
	private JButton btnDelete;
	private JButton btnAdd;
	

	private JLabel lblState;
	

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal(MusicPlayer player) {
		this.player = player;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/logoTitulo.png")));
		setTitle("JTH Music Player");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 934, 633);
		setMinimumSize(new Dimension(855, 408));
		setLocationRelativeTo(null);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		contentPane.add(getPnNorte(), BorderLayout.NORTH);
		contentPane.add(getPnCentro(), BorderLayout.CENTER);
		contentPane.add(getPnSur(), BorderLayout.SOUTH);

		cargaAyuda();
	}

	private void cargaAyuda() {
		URL hsURL;
		HelpSet hs;

		try {
			File fichero = new File("help/Ayuda.hs");
			hsURL = fichero.toURI().toURL();
			hs = new HelpSet(null, hsURL);
			
		} catch (Exception e) {
			System.out.println("Ayuda no encontrada");
			return;
		}

		HelpBroker hb = hs.createHelpBroker();

		hb.enableHelpKey(getRootPane(), "introduccion", hs);
		hb.enableHelpOnButton(mnItContents, "introduccion", hs);
		
		hb.enableHelp(btnAdd, "añadir", hs); //Ayuda contextual --> sensible al contexto
		hb.enableHelp(btnDelete, "eliminar", hs);
		hb.enableHelp(btnPlay, "reproducir", hs);
	}
	
	private JSlider getSlVolumen() {
		if(slVolumen == null) {
			slVolumen = new JSlider();
			slVolumen.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					int value = slVolumen.getValue();
					getTxtVolumen().setText(""+value);
					player.setVolume(value, slVolumen.getMaximum());
					
				}
			});
			slVolumen.setToolTipText("Volume Control");
			slVolumen.setFocusable(false);
			slVolumen.setBackground(Color.BLACK);
			slVolumen.setForeground(Color.WHITE);
			slVolumen.setPaintTicks(true);
			slVolumen.setPaintLabels(true);
			slVolumen.setMinorTickSpacing(10);
			slVolumen.setMajorTickSpacing(20);
		}
		return slVolumen;
	}
	
	private JPanel getPnNorte() {
		if(pnNorte == null) {
			pnNorte = new JPanel();
			pnNorte.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.BLACK));
			pnNorte.setBackground(Color.BLACK);
			pnNorte.setLayout(new GridLayout(0, 3, 0, 0));

			
			pnNorte.add(getLbLogo());
			pnNorte.add(getSlVolumen());
			pnNorte.add(getPanel());
		}
		return pnNorte;
	}
	
	private JLabel getLbLogo() {
		if(lbLogo == null) {
			lbLogo = new JLabel("");
			lbLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo.png")));
		}
		return lbLogo;
	}
	
	private JPanel getPnCentro() {
		if(pnCentro == null) {
			pnCentro = new JPanel();
			pnCentro.setBackground(Color.BLACK);
			pnCentro.setLayout(new GridLayout(0, 2, 4, 0));
			pnCentro.add(getPnLibrary());
			pnCentro.add(getPnPlay());
		}
		return pnCentro;
	}
	
	private JPanel getPnSur() {
		if(pnSur == null) {
			pnSur = new JPanel();
			pnSur.setBorder(new EmptyBorder(0, 7, 0, 7));
			pnSur.setLayout(new GridLayout(0, 2, 0, 0));
			pnSur.add(getLblState());
		}
		return pnSur;
	}
	
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnFile());
			menuBar.add(getMnPlay());
			menuBar.add(getMnOptions());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}

	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.setFont(new Font("Dialog", Font.PLAIN, 13));
			mnFile.setMnemonic('F');
			mnFile.add(getMnItOpen());
			mnFile.add(getSeparator_1());
			mnFile.add(getMnItExit());
		}
		return mnFile;
	}

	private JMenu getMnPlay() {
		if (mnPlay == null) {
			mnPlay = new JMenu("Play");
			mnPlay.setFont(new Font("Dialog", Font.PLAIN, 13));
			mnPlay.setMnemonic('P');
			mnPlay.add(getMnItNext());
		}
		return mnPlay;
	}

	private JMenu getMnOptions() {
		if (mnOptions == null) {
			mnOptions = new JMenu("Options");
			mnOptions.setFont(new Font("Dialog", Font.PLAIN, 13));
			mnOptions.setMnemonic('t');
			mnOptions.add(getMnItrandom());
		}
		return mnOptions;
	}

	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
			mnHelp.setFont(new Font("Dialog", Font.PLAIN, 13));
			mnHelp.setMnemonic('H');
			mnHelp.add(getMnItContents());
			mnHelp.add(getSeparator());
			mnHelp.add(getMnItAbout());
		}
		return mnHelp;
	}

	@SuppressWarnings("deprecation")
	private JMenuItem getMnItOpen() {
		if (mnItOpen == null) {
			mnItOpen = new JMenuItem("Open");
			mnItOpen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cargarFicheros();
				}
			});
			mnItOpen.setFont(new Font("Dialog", Font.PLAIN, 13));
			mnItOpen.setMnemonic('O');
			mnItOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		}
		return mnItOpen;
	}
	
	private void cargarFicheros() {
		if(getFileChooser().showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			for(File f : fileChooser.getSelectedFiles()) 
				modelListLibrary.addElement(new MyFile(f));
		}
	}

	private JFileChooser getFileChooser() {
		if(fileChooser == null) {
			fileChooser = new JFileChooser();
			fileChooser.setMultiSelectionEnabled(true);
			fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos MP3", "mp3"));
			String appPath = System.getProperty("user.dir"); //Devuelve desde donde se esta ejecutando la aplicacion (Carpeta Programa)
			fileChooser.setCurrentDirectory(new File(appPath+"/musica"));
			
			/*
			 * user.dir  ---> Carpeta Programa
			 * user.home ---> Carpeta principal de Usuario
			 */
		}
		
		return fileChooser;
		
	}
	
	private JMenuItem getMnItExit() {
		if (mnItExit == null) {
			mnItExit = new JMenuItem("Exit");
			mnItExit.setFont(new Font("Dialog", Font.PLAIN, 13));
			mnItExit.setMnemonic('E');
			mnItExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
		}
		return mnItExit;
	}

	private JMenuItem getMnItNext() {
		if (mnItNext == null) {
			mnItNext = new JMenuItem("Next");
			mnItNext.addActionListener(pn);
			mnItNext.setFont(new Font("Dialog", Font.PLAIN, 13));
			mnItNext.setMnemonic('N');
		}
		return mnItNext;
	}

	private JMenuItem getMnItrandom() {
		if (mnItrandom == null) {
			mnItrandom = new JMenuItem("Random");
			mnItrandom.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(modelListPlay.getSize() <= 0)
						return;
					
					player.stop();
					int random = CodeUtil.getRandomNumber(1, modelListPlay.getSize());
					listPlay.setSelectedIndex(random);
					
					play();
				}
			});
			mnItrandom.setFont(new Font("Dialog", Font.PLAIN, 13));
			mnItrandom.setMnemonic('R');
		}
		return mnItrandom;
	}

	private JMenuItem getMnItContents() {
		if (mnItContents == null) {
			mnItContents = new JMenuItem("Contents");
			mnItContents.setFont(new Font("Dialog", Font.PLAIN, 13));
			mnItContents.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
			mnItContents.setMnemonic('C');
		}
		return mnItContents;
	}

	private JMenuItem getMnItAbout() {
		if (mnItAbout == null) {
			mnItAbout = new JMenuItem("About");
			mnItAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showAboutContents();
				}
			});
			mnItAbout.setFont(new Font("Dialog", Font.PLAIN, 13));
			mnItAbout.setMnemonic('b');
		}
		return mnItAbout;
	}
	private void showAboutContents() {
		String msg = "Music Player made by Jorge Toraño\n"+"v1.0 Asturias\n";
		String title = "About of JTH Music Player";
		JOptionPane.showMessageDialog(this, msg, title, JOptionPane.INFORMATION_MESSAGE);
	}

	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}

	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}

	private JTextField getTxtVolumen() {
		if (txtVolumen == null) {
			txtVolumen = new JTextField();
			txtVolumen.setToolTipText("Volume");
			txtVolumen.setEditable(false);
			txtVolumen.setFocusable(false);
			txtVolumen.setHorizontalAlignment(SwingConstants.LEFT);
			txtVolumen.setBackground(Color.BLACK);
			txtVolumen.setFont(new Font("Dialog", Font.BOLD, 32));
			txtVolumen.setForeground(SystemColor.desktop);
			txtVolumen.setText("50");
			txtVolumen.setColumns(10);
		}
		return txtVolumen;
	}

	private JPanel getPnLibrary() {
		if (pnLibrary == null) {
			pnLibrary = new JPanel();
			pnLibrary.setBackground(Color.BLACK);
			pnLibrary.setLayout(new BorderLayout(0, 0));
			pnLibrary.add(getLbLibrary(), BorderLayout.NORTH);

			JScrollPane spLibrary = new JScrollPane();
			spLibrary.setBorder(new LineBorder(SystemColor.desktop, 4));
			pnLibrary.add(spLibrary, BorderLayout.CENTER);

			modelListLibrary = new DefaultListModel<MyFile>();
			listLibrary = new JList<MyFile>(modelListLibrary);
			listLibrary.setModel(modelListLibrary);
			getLbLibrary().setLabelFor(listLibrary);
			spLibrary.setViewportView(listLibrary);
			pnLibrary.add(getPnBtnLibrary(), BorderLayout.SOUTH);
		}
		return pnLibrary;
	}

	private JPanel getPnPlay() {
		if (pnPlay == null) {
			pnPlay = new JPanel();
			pnPlay.setBackground(Color.BLACK);
			pnPlay.setLayout(new BorderLayout(0, 0));
			pnPlay.add(getLbPlay(), BorderLayout.NORTH);
			pnPlay.add(getScPlay(), BorderLayout.CENTER);
			pnPlay.add(getPnBtnPlay(), BorderLayout.SOUTH);
		}
		return pnPlay;
	}

	private JLabel getLbLibrary() {
		if (lbLibrary == null) {
			lbLibrary = new JLabel("\u266A " + "Library:");
			lbLibrary.setDisplayedMnemonic('L');
			lbLibrary.setForeground(SystemColor.desktop);
			lbLibrary.setFont(new Font("Dialog", Font.BOLD, 18));
		}
		return lbLibrary;
	}

	private JLabel getLbPlay() {
		if (lbPlay == null) {
			lbPlay = new JLabel("\u266A Play:");
			lbPlay.setLabelFor(getListPlay());
			lbPlay.setDisplayedMnemonic('P');
			lbPlay.setForeground(SystemColor.desktop);
			lbPlay.setFont(new Font("Dialog", Font.BOLD, 18));
		}
		return lbPlay;
	}

	public JList<MyFile> getListPlay() {
		if (listPlay == null) {
			modelListPlay = new DefaultListModel<MyFile>();
			listPlay = new JList<MyFile>();
			listPlay.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listPlay.setModel(modelListPlay);
		}
		return listPlay;
	}

	private JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton("\u25C4\u25C4");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					player.stop();
					
					int index = listPlay.getSelectedIndex();
					index = (index <= 0) ? 0 : --index;
					
					listPlay.setSelectedIndex(index);
					
					play();
				}
			});
			btnBack.setFont(new Font("Dialog", Font.BOLD, 15));
			btnBack.setToolTipText("Previous");
			btnBack.setForeground(Color.WHITE);
		}
		return btnBack;
	}

	private JButton getBtnPlay() {
		if (btnPlay == null) {
			btnPlay = new JButton("\u25BA");
			btnPlay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if( listPlay.getSelectedIndex() == -1)
						listPlay.setSelectedIndex(0);
					
					play();	
				}
			});
			btnPlay.setFont(new Font("Dialog", Font.BOLD, 15));
			btnPlay.setToolTipText("Play");
			btnPlay.setForeground(Color.WHITE);
		}
		return btnPlay;
	}
	private void play() { //TODO
		try {
			File f = listPlay.getSelectedValue().getF();
			player.play(f);
			player.setVolume(slVolumen.getValue(), slVolumen.getMaximum());
			getLblState().setText("Playing: "+f.getName());
		} catch (Exception e) { }
	}

	private JScrollPane getScPlay() {
		if (scPlay == null) {
			scPlay = new JScrollPane();
			scPlay.setBorder(new LineBorder(SystemColor.desktop, 4));
			scPlay.setViewportView(getListPlay());
		}
		return scPlay;
	}

	private JPanel getPnBtnPlay() {
		if (pnBtnPlay == null) {
			pnBtnPlay = new JPanel();
			pnBtnPlay.setLayout(new GridLayout(0, 5, 0, 0));
			pnBtnPlay.add(getBtnBack());
			pnBtnPlay.add(getBtnPlay());
			pnBtnPlay.add(getBtnStop());
			pnBtnPlay.add(getBtnNext());
			pnBtnPlay.add(getBtnDelete_1_3());
		}
		return pnBtnPlay;
	}

	private JButton getBtnStop() {
		if (btnStop == null) {
			btnStop = new JButton("\u25A0");
			btnStop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					player.stop();
					getLblState().setText("Stopped");
				}
			});
			btnStop.setFont(new Font("Dialog", Font.BOLD, 15));
			btnStop.setToolTipText("Stop");
			btnStop.setForeground(Color.WHITE);
		}
		return btnStop;
	}

	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("\u25BA\u25BA");
			btnNext.setFont(new Font("Dialog", Font.BOLD, 15));
			btnNext.setToolTipText("Next");
			btnNext.setForeground(Color.WHITE);
			btnNext.addActionListener(pn);
		}
		return btnNext;
	}
	private class ProcessNext implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			player.stop();
			
			int index = getListPlay().getSelectedIndex()+1;
			index = (index == modelListPlay.getSize()) ? --index : index;
			
			getListPlay().setSelectedIndex(index);

			play();
		}
	};
	private JButton getBtnDelete_1_3() {
		if (btnDelete_1_3 == null) {
			btnDelete_1_3 = new JButton("Delete");
			btnDelete_1_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for(MyFile f: listPlay.getSelectedValuesList())
						modelListPlay.removeElement(f);
				}
			});
			btnDelete_1_3.setFont(new Font("Dialog", Font.BOLD, 15));
			btnDelete_1_3.setMnemonic('l');
			btnDelete_1_3.setForeground(Color.WHITE);
		}
		return btnDelete_1_3;
	}

	private JPanel getPnBtnLibrary() {
		if (pnBtnLibrary == null) {
			pnBtnLibrary = new JPanel();
			pnBtnLibrary.setLayout(new GridLayout(0, 2, 0, 0));

			pnBtnLibrary.add(getBtnAdd());
			pnBtnLibrary.add(getBtnDelete());
		}
		return pnBtnLibrary;
	}

	private JButton getBtnAdd() {
		if(btnAdd == null) {
			btnAdd = new JButton("Add To PlayList");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for(Integer index: listLibrary.getSelectedIndices())
						modelListPlay.addElement(modelListLibrary.get(index));
				}
			});
			btnAdd.setFont(new Font("Dialog", Font.BOLD, 15));
			btnAdd.setMnemonic('A');
			pnBtnLibrary.add(btnAdd);
			btnAdd.setForeground(Color.WHITE);
		} 
		return btnAdd;
	}
	
	private JButton getBtnDelete() {
		if(btnDelete == null) {
			btnDelete = new JButton("Delete");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for(MyFile f: listLibrary.getSelectedValuesList())
						modelListLibrary.removeElement(f);
				}
			});
			btnDelete.setFont(new Font("Dialog", Font.BOLD, 15));
			btnDelete.setMnemonic('D');
			btnDelete.setForeground(Color.WHITE);
		}
		return btnDelete;
	}
	
	private JLabel getLblState() {
		if (lblState == null) {
			lblState = new JLabel("Stopped");
		}
		return lblState;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.BLACK);
			panel.setLayout(new GridLayout(0, 2, 0, 0));

			JLabel lbVol = new JLabel("Vol:");
			lbVol.setFocusable(false);
			lbVol.setToolTipText("Volume");
			panel.add(lbVol);
			lbVol.setHorizontalAlignment(SwingConstants.RIGHT);
			lbVol.setFont(new Font("Dialog", Font.BOLD, 32));
			lbVol.setForeground(SystemColor.desktop);
			panel.add(getTxtVolumen());
		}
		return panel;
	}
	
	public MusicPlayer getMPlayer() {
		return this.player;
	}
}
