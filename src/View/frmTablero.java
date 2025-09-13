package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Model.ICell;
import Model.INonograma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.LayoutManager2;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.plaf.basic.BasicSplitPaneUI.BasicVerticalLayoutManager;

public class frmTablero {

	private JFrame frame;
	private INonograma nonograma;

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
		frame.setBounds(100, 100, 460, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		frame.getContentPane().setLayout(null);
		
		JPanel interactivePanel = new JPanel();
		interactivePanel.setBounds(100, 108, 320, 280);
		interactivePanel.setBackground(new Color(128, 128, 128));
		frame.getContentPane().add(interactivePanel);
		interactivePanel.setLayout(new GridLayout(5, 5, 0, 0));
		
		JPanel columnHintsPanel = new JPanel();
		columnHintsPanel.setBackground(new Color(128, 128, 128));
		columnHintsPanel.setBounds(100, 17, 320, 80);
		frame.getContentPane().add(columnHintsPanel);
		columnHintsPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel rowHintsPanel = new JPanel();
		rowHintsPanel.setBackground(new Color(128, 128, 128));
		rowHintsPanel.setBounds(10, 108, 80, 280);
		frame.getContentPane().add(rowHintsPanel);
		rowHintsPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		nonograma = INonograma.createNonograma(INonograma.DifficultyLevels.EASY);
		
		loadRowHintsPanel(rowHintsPanel);
		loadColumnHintsPanel(columnHintsPanel);	
		loadInteractivePanel(interactivePanel);
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
					
					System.out.print("Hubo un click en la celda de la fila: " + row + " columna: " + column + "\n");
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
