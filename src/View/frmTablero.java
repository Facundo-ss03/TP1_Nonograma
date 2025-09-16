package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

import Model.ICell;
import Model.INonograma;
import Model.INonograma.DifficultyLevels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import javax.swing.JMenu;

public class frmTablero {

	private JFrame frame;
	private INonograma nonograma;
	private JPanel interactivePanel;
	private JPanel rowHintsPanel;
	private JPanel columnHintsPanel;
	private JButton[][] interactivePanelButtons;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmTablero window = new frmTablero();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmTablero() {
		
		initialize();
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 720, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		nonograma = INonograma.createNonograma(INonograma.DifficultyLevels.EASY);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		frame.getContentPane().setLayout(null);
		loadMenuBar(menuBar);

		//skins menu start
		JMenu mnNewMenu = new JMenu("Skin");
		menuBar.add(mnNewMenu);

		
		JRadioButtonMenuItem rdbtnMetal = new JRadioButtonMenuItem("Metal");
		JRadioButtonMenuItem rdbtnNimbus = new JRadioButtonMenuItem("Nimbus");
		JRadioButtonMenuItem rdbtnMotif = new JRadioButtonMenuItem("Motif");

		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnMetal);
		group.add(rdbtnNimbus);
		group.add(rdbtnMotif);

		
		mnNewMenu.add(rdbtnMetal);
		mnNewMenu.add(rdbtnNimbus);
		mnNewMenu.add(rdbtnMotif);
		

		JButton pistaButton = new JButton("Pedir Pista");
			menuBar.add(pistaButton);
			pistaButton.addActionListener(e -> {
			    if(nonograma.askRemaningHintsAvaiables() > 0) {
			    	
				    int[] pista = nonograma.askCorrectHint();
			        int row = pista[0];
			        int col = pista[1];
	
			        
			        nonograma.setCellAsBlack(row, col); 
	
			        
			        JButton button = interactivePanelButtons[row][col]; 
			        button.setBackground(Color.BLACK);
			    } else {
			        JOptionPane.showMessageDialog(frame, "No hay más pistas disponibles", "Pista", JOptionPane.INFORMATION_MESSAGE);
			    }
			});
		
		
		rdbtnMetal.addActionListener(e -> {
		    try {
		        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		        SwingUtilities.updateComponentTreeUI(frame);
		        
		        interactivePanel.invalidate();
		        interactivePanel.validate();
		       
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		});

		rdbtnNimbus.addActionListener(e -> {
		    try {
		        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		        SwingUtilities.updateComponentTreeUI(frame);
		        
		        interactivePanel.invalidate();
		        interactivePanel.validate();
		        
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		});

		rdbtnMotif.addActionListener(e -> {
		    try {
		    	UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		        SwingUtilities.updateComponentTreeUI(frame);
		        
		        interactivePanel.invalidate();
		        interactivePanel.validate();
		        
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		});
		
		interactivePanel = new JPanel();
		interactivePanel.setBounds(100, 108, 594, 460);
		interactivePanel.setBackground(new Color(240, 240, 240));
		frame.getContentPane().add(interactivePanel);
		
		columnHintsPanel = new JPanel();
		columnHintsPanel.setBackground(new Color(240, 240, 240));
		columnHintsPanel.setBounds(100, 17, 594, 80);
		frame.getContentPane().add(columnHintsPanel);
		columnHintsPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		rowHintsPanel = new JPanel();
		rowHintsPanel.setBackground(new Color(240, 240, 240));
		rowHintsPanel.setBounds(10, 108, 80, 460);
		frame.getContentPane().add(rowHintsPanel);
		rowHintsPanel.setLayout(new GridLayout(0, 1, 0, 0));

		restartPanels();
		
		JButton btnVerifySolution = new JButton("Verify");
		btnVerifySolution.setBounds(10, 17, 80, 35);
		frame.getContentPane().add(btnVerifySolution);
		suscribeButtonToVerifyWin(btnVerifySolution);
		
		JButton btnRestart = new JButton("Restart");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				nonograma.restartGame();

				restartPanels();

			}
		});
		btnRestart.setBounds(10, 62, 80, 35);
		frame.getContentPane().add(btnRestart);
		
	}
	

	public void suscribeButtonToVerifyWin(JButton button) {
		
		button.addActionListener( e -> {
			
			verifySolution();
			
		});
		
	}
	
	private void verifySolution() {
		
		boolean result = nonograma.askIfSolutionIsCorrect();
		
	    if (result)
	        JOptionPane.showMessageDialog(frame, "¡Ganaste!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
	    
	    if(!result && nonograma.askRemainingAttempts() > 0)
	        JOptionPane.showMessageDialog(frame, "¡Todavía no es correcto! te quedan " + nonograma.askRemainingAttempts() + " intentos.", "Resultado", JOptionPane.WARNING_MESSAGE); 
	    
	    if(!result && nonograma.askRemainingAttempts() == 0) {
	    	
	    	JOptionPane.showMessageDialog(frame, "¡Perdiste! La solución era la siguiente.", "Resultado", JOptionPane.ERROR_MESSAGE); 
	    	showSolution();
	    }
	    	    
	}
	
	public void showSolution() {
		
		for(int row = 0; row < interactivePanelButtons.length; row++) {
			for(int column = 0; column < interactivePanelButtons.length; column++) {
				
				ICell cell = nonograma.getCellOfSolution(row, column);

				if(cell.isBlack())
				interactivePanelButtons[row][column].setBackground(Color.BLACK);
				if(cell.isBlank() || cell.isFlagged())
				interactivePanelButtons[row][column].setBackground(Color.RED);
				
				interactivePanelButtons[row][column].setEnabled(false);
			}	
		}
	}

	private void restartPanels() {
		
		emptyPanels();
		refreshPanels();
		loadPanels();
		
	}
	
	private void emptyPanels() {

		interactivePanel.removeAll();
		rowHintsPanel.removeAll();
		columnHintsPanel.removeAll();
		
	}
	
	private void refreshPanels() {

		interactivePanel.revalidate();
		interactivePanel.repaint();
		rowHintsPanel.revalidate();
		rowHintsPanel.repaint();
		columnHintsPanel.revalidate();
		columnHintsPanel.repaint();
	}
	
	private void loadPanels() {
		
		interactivePanel.setLayout(new GridLayout(nonograma.getPlayboardSize(), nonograma.getPlayboardSize(), 0, 0));

		loadRowHintsPanel();
		loadColumnHintsPanel();
		loadInteractivePanel();
		
	}
	
	private void loadMenuBar(JMenuBar bar) {

		ButtonGroup difficultiesButtons = new ButtonGroup();
		JMenu difficultiesMenu = new JMenu("Difficulty");
		
		for(DifficultyLevels difficulty : DifficultyLevels.values()) {
			
			JRadioButtonMenuItem radioButton = new JRadioButtonMenuItem("" + difficulty + "");
			radioButton.addActionListener( e -> {
				
				nonograma.changeDifficultyLevel(difficulty);
				
				restartPanels();

			});
			
			difficultiesButtons.add(radioButton);
			
		}
		
		JRadioButtonMenuItem firstButton = (JRadioButtonMenuItem)difficultiesButtons.getElements().asIterator().next();
		firstButton.setSelected(true);
		
		Iterator radioButtons = difficultiesButtons.getElements().asIterator();
		
		while(radioButtons.hasNext()) {
			
			difficultiesMenu.add((JRadioButtonMenuItem) radioButtons.next());
			
		}
		
		bar.add(difficultiesMenu);
		
	}
	
	private void loadInteractivePanel() {
		
		int playboardSize = nonograma.getPlayboardSize();
		interactivePanelButtons = new JButton[playboardSize][playboardSize];
		
		for(int rowOfButtons = 0; rowOfButtons < playboardSize; rowOfButtons++) {
			
			loadRow(rowOfButtons, playboardSize);
			
		}
	}
	
	private void loadRow(int row, int size) {
		
		for(int columnButton = 0; columnButton < size; columnButton++) {
			
			JButton cell = createCellButton(row, columnButton);
			
			interactivePanel.add(cell);
			interactivePanelButtons[row][columnButton] = cell;
		}	
		
	} 

	private JButton createCellButton(int buttonRow, int buttonColumn){

		JButton cell = new JButton();
		cell.setBackground(Color.WHITE);  

		int row = buttonRow;
		int column = buttonColumn;
		
		cell.addActionListener( e -> {
			
			updateButtonColor(cell, row, column);
			
		});
		
		return cell;
		
	}
	

	private void updateButtonColor(JButton button, int row, int column) {

		ICell cell = nonograma.getCellOfPlayerPlayboard(row, column);

		if(cell.getCurrentState() == ICell.CellStates.BLANK) {

			nonograma.setCellAsBlack(row, column);
			button.setBackground(Color.BLACK);
			return;
		}
		
		if(cell.getCurrentState() == ICell.CellStates.PAINTED) {

			nonograma.setCellAsFlagged(row, column);
			button.setBackground(Color.RED);
			return;
		}
		
		if(cell.getCurrentState() == ICell.CellStates.FLAGGED) {

			nonograma.setCellAsBlank(row, column);
			button.setBackground(Color.WHITE);
			return;
		}
	}
	
	
	private void loadRowHintsPanel() {
		
		for(int i = 0; i < nonograma.getPlayboardSize(); i++) {
			
			JTextField newHintsField = new JTextField(printBlackCellsListInHorizontal(i));
			newHintsField.setHorizontalAlignment(SwingConstants.CENTER);
			newHintsField.setEditable(false);
			newHintsField.setFocusable(false);
			newHintsField.setBackground(Color.LIGHT_GRAY);
			newHintsField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
			rowHintsPanel.add(newHintsField);
			
		}
		
	}
	
	private String printBlackCellsListInHorizontal(int row) {
		
		StringBuilder sb = new StringBuilder();

		for(Integer hint : nonograma.getBlackChainsLengthsInRow(row)) {
			
			sb.append(" " + hint + " ");
			
		}
		
		return sb.toString();
		
	}
	
	private void loadColumnHintsPanel() {
		
		for(int i = 0; i < nonograma.getPlayboardSize(); i++) {
			
			JTextPane newHintsField = new JTextPane();
			newHintsField.setText(printBlackCellsListInVertical(i));
			newHintsField.setEditable(false);
			newHintsField.setFocusable(false);
			newHintsField.setBackground(Color.LIGHT_GRAY);
			newHintsField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
			columnHintsPanel.add(newHintsField);
			
		}
		
	}
	
	private String printBlackCellsListInVertical(int column) {
		
		StringBuilder sb = new StringBuilder();

		for(Integer hint : nonograma.getBlackChainsLengthsInColumn(column)) {
			
			sb.append(hint + "\n");
			
		}
		
		return sb.toString();
		
	}
}
