package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
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
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import java.awt.GridBagLayout;

public class frmTablero {

	private JFrame frame;
	private INonograma nonograma;
	private JPanel interactivePanel;
	private JPanel rowHintsPanel;
	private JPanel columnHintsPanel;
	
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
		
		interactivePanel = new JPanel();
		interactivePanel.setBounds(100, 108, 594, 460);
		interactivePanel.setBackground(new Color(240, 240, 240));
		frame.getContentPane().add(interactivePanel);
		interactivePanel.setLayout(new GridLayout(nonograma.getPlayboardSize(), nonograma.getPlayboardSize(), 0, 0)); 
		
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
		
		
		loadRowHintsPanel(rowHintsPanel);
		loadColumnHintsPanel(columnHintsPanel);	
		loadInteractivePanel(interactivePanel);
		
		JButton btnVerifySolution = new JButton("Verify");
		btnVerifySolution.setBounds(10, 17, 80, 35);
		frame.getContentPane().add(btnVerifySolution);
		suscribeVerifyButton(btnVerifySolution);
		
		JButton btnRestart = new JButton("Restart");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				nonograma.restartGame();

				emptyPanels();

				reloadPanels();

				refreshPanels();
				
			}
		});
		btnRestart.setBounds(10, 62, 80, 35);
		frame.getContentPane().add(btnRestart);
		
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
	
	private void reloadPanels() {
		
		interactivePanel.setLayout(new GridLayout(nonograma.getPlayboardSize(), nonograma.getPlayboardSize(), 0, 0));

		loadRowHintsPanel(rowHintsPanel);
		loadColumnHintsPanel(columnHintsPanel);
		loadInteractivePanel(interactivePanel);
		
	}
	
	private void loadMenuBar(JMenuBar bar) {

		ButtonGroup difficultiesButtons = new ButtonGroup();
		JMenu difficultiesMenu = new JMenu("Difficulty");
		
		for(DifficultyLevels difficulty : DifficultyLevels.values()) {
			
			JRadioButtonMenuItem radioButton = new JRadioButtonMenuItem("" + difficulty + "");
			radioButton.addActionListener( e -> {
				
				nonograma.changeDifficultyLevel(difficulty);
				emptyPanels();
				refreshPanels();
				reloadPanels();

			});
			
			difficultiesButtons.add(radioButton);
			
		}
		
		JRadioButtonMenuItem firstButton = (JRadioButtonMenuItem)difficultiesButtons.getElements().asIterator().next();
		firstButton.setSelected(true);
		
		Iterator a = difficultiesButtons.getElements().asIterator();
		
		while(a.hasNext()) {
			
			difficultiesMenu.add((JRadioButtonMenuItem) a.next());
			
		}
		
		bar.add(difficultiesMenu);
		
	}
	
	private void suscribeVerifyButton(JButton verifyButton) {
		
		verifyButton.addActionListener( e -> {
			
			verifySolution();
			
		});
		
	}
	
	private void verifySolution() {
		
		if(nonograma.askIfSolutionIsCorrect()) {
			
			System.out.print("Ganaste");
			
		} else {
			
			System.out.print("Perdiste");
		}
		
	}
	
	private void loadInteractivePanel(JPanel panel) {
		
		int playboardSize = nonograma.getPlayboardSize();
		
		for(int i = 0; i < playboardSize; i++) {
			for(int j = 0; j < playboardSize; j++) {

				JButton cell = new JButton();
				cell.setBackground(Color.WHITE);

				int row = i;
				int column = j;
				
				cell.addActionListener( e -> {
					
					updateButtonColor(cell, row, column);
					
				});
				
				panel.add(cell);
			}	
		}
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
	
	private void loadRowHintsPanel(JPanel rowHintsPanel) {
		
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
	
	private void loadColumnHintsPanel(JPanel columnHintsPanel) {
		
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
