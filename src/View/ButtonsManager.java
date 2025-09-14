package View;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Model.ICell;
import Model.INonograma;

public class ButtonsManager {
	
	private final JFrame frame;
	private final INonograma currentNonograma;
	public JButton[][] interactivePanelButtons;
	
	public ButtonsManager(JFrame frame, INonograma nonograma) {
		
		this.frame = frame;
		this.currentNonograma = nonograma;
		
	}
	
	public void loadInteractiveButtons() {

		int size = currentNonograma.getPlayboardSize();
		this.interactivePanelButtons = new JButton[size][size];
		for(int rowOfButtons = 0; rowOfButtons < interactivePanelButtons.length; rowOfButtons++) {
			
			loadRowOfButtons(rowOfButtons, interactivePanelButtons.length);
			
		}
		
	}
	
	private void loadRowOfButtons(int row, int size) {
		
		for(int columnButton = 0; columnButton < size; columnButton++) {
			
			JButton cell = createCellButton(row, columnButton);
			
			interactivePanelButtons[row][columnButton] = cell;
		}	
		
	} 
	
	
	public void suscribeButtonToRevelateHint(JButton button) {
		
		button.addActionListener(e -> {
		    int[] pista = currentNonograma.askCorrectHint();
		    if (pista != null) {
		    	
		        int row = pista[0];
		        int column = pista[1];
		        
		        currentNonograma.setCellAsBlack(row, column); 

		        setButtonBlack(row, column);
		        
		    } else {
		        JOptionPane.showMessageDialog(frame, "No hay más pistas disponibles", "Pista", JOptionPane.INFORMATION_MESSAGE);
		    }
		});
	}
	
	public void setButtonBlack(int row, int column) {
		
		interactivePanelButtons[row][column].setBackground(Color.BLACK);
		
	}

	public void updateButtonColor(JButton button, int row, int column) {

		ICell cell = currentNonograma.getCellOfPlayerPlayboard(row, column);

		if(cell.getCurrentState() == ICell.CellStates.BLANK) {

			currentNonograma.setCellAsBlack(row, column);
			button.setBackground(Color.BLACK);
			return;
		}
		
		if(cell.getCurrentState() == ICell.CellStates.PAINTED) {

			currentNonograma.setCellAsFlagged(row, column);
			button.setBackground(Color.RED);
			return;
		}
		
		if(cell.getCurrentState() == ICell.CellStates.FLAGGED) {

			currentNonograma.setCellAsBlank(row, column);
			button.setBackground(Color.WHITE);
			return;
		}
	}
	
	public JButton createCellButton(int buttonRow, int buttonColumn){

		JButton cell = new JButton();
		cell.setBackground(Color.WHITE);  

		int row = buttonRow;
		int column = buttonColumn;
		
		cell.addActionListener( e -> {
			
			updateButtonColor(cell, row, column);
			
		});
		
		return cell;
		
	}
	
	public void suscribeButtonToVerifyWin(JButton button) {
		
		button.addActionListener( e -> {
			
			verifySolution();
			
		});
		
	}
	
	private void verifySolution() {
	    if (currentNonograma.askIfSolutionIsCorrect()) {
	        JOptionPane.showMessageDialog(frame, "¡Ganaste!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
	    } else {
	        JOptionPane.showMessageDialog(frame, "¡Todavía no es correcto!", "Resultado", JOptionPane.WARNING_MESSAGE); 
	    }
	}
}
