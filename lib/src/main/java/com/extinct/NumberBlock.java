package com.extinct;

/**
 * @author Sudhakar K
 */

import static com.extinct.NumbersFrame.blocks;
import static com.extinct.NumbersFrame.emptySpace;
import static com.extinct.NumbersFrame.startTime;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class NumberBlock extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;

	private int position;
	private String text;

	public NumberBlock(String number, int x, int y, int width, int height, int position) {
		this.text = number;
		this.position = position;
		setText(number);
		setBounds(x, y, width, height);
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {

		int x = ((Component) e.getSource()).getX();
		int y = ((Component) e.getSource()).getY();

		if ((this.getBounds().getX() + this.getBounds().getWidth()) == emptySpace.getX()
				&& this.getBounds().getY() == emptySpace.getY()) {
			legalMove(x, y);
		} else if ((this.getBounds().getX() - this.getBounds().getWidth()) == emptySpace.getX()
				&& this.getBounds().getY() == emptySpace.getY()) {
			legalMove(x, y);
		} else if ((this.getBounds().getY() + this.getBounds().getHeight()) == emptySpace.getY()
				&& this.getBounds().getX() == emptySpace.getX()) {
			legalMove(x, y);
		} else if ((this.getBounds().getY() - this.getBounds().getHeight()) == emptySpace.getY()
				&& this.getBounds().getX() == emptySpace.getX()) {
			legalMove(x, y);
		}
	}

	private void legalMove(int x, int y) {
		this.setLocation(emptySpace.getX(), emptySpace.getY());
		this.setBackground(null);
		emptySpace.setLocation(x, y);
		swapPosition();
//		repaint();
		if (isPuzzleSolved()) {
			long timeDiff = (System.currentTimeMillis() - startTime) / 60000;
			JOptionPane.showConfirmDialog(null, "Puzzle Completed in " + timeDiff + " mins ", "Solved!",
					JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
	}

	private void swapPosition() {
		int temp = this.getPosition();

		this.setPosition(emptySpace.getPosition());

		emptySpace.setPosition(temp);
	}

	private boolean isPuzzleSolved() {
		boolean solved = false;
		int count = 0;
		for (NumberBlock block : blocks) {
			if (block.getPosition() == Integer.parseInt(block.getText())) {
				count++;
			} else {
				break;
			}
		}

//		System.out.println(blocks.size()+" count: "+count);
		if (blocks.size() == count) {
			solved = true;
		}
		return solved;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
