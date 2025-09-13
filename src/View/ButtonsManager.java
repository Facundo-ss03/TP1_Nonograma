package View;

import java.awt.Color;

import javax.swing.JButton;

import Model.ICell;
import Model.INonograma;

public class ButtonsManager {
	
	INonograma currentNonograma;
	
	public ButtonsManager(INonograma nonograma) {
		
		this.currentNonograma = nonograma;
		
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
	
}
