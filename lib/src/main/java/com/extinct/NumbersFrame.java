package com.extinct;

/**
 * @author Sudhakar K
 */
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class NumbersFrame {

	JFrame mainFrame;

	private static final int x = 10;
	private static final int y = 10;
	private static final int width = 600;
	private static final int height = 600;
	private static int matrix = 4;
	public static final List<NumberBlock> blocks = new ArrayList<NumberBlock>();
	public static long startTime;
	public static NumberBlock emptySpace = null;

	public NumbersFrame() {

//		super();
		mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String gridSize = JOptionPane.showInputDialog(null, "Enter a level between 4 and 8", "Choose Level",
				JOptionPane.OK_CANCEL_OPTION);

		if (gridSize == null || !Pattern.matches("^\\d*$", gridSize)) {
			JOptionPane.showConfirmDialog(null, "Invalid level entered", "Error", JOptionPane.PLAIN_MESSAGE);
			System.exit(1);
		} else {
			matrix = Integer.parseInt(gridSize);
			if (matrix < 4 || matrix > 8) {
				JOptionPane.showConfirmDialog(null, "Invalid level entered", "Error", JOptionPane.PLAIN_MESSAGE);
				System.exit(0);
			}
		}

		mainFrame.setBounds(x, y, width, height);
		GridLayout gridLayout = new GridLayout(matrix, matrix);
		mainFrame.setLayout(gridLayout);
		NumberBlock block = null;
		int blockHeight = height / matrix;
		int blockWidth = width / matrix;
		int positionX = x;
		int positionY = y;

		List<Integer> numbers = new ArrayList<Integer>(matrix * matrix);

		for (int i = 1; i < (matrix * matrix); i++) {
			numbers.add(i);
		}

		Collections.shuffle(numbers);

		for (int i = 1; i < (matrix * matrix); i++) {
			block = new NumberBlock(numbers.get(i - 1).toString(), positionX, positionY, blockWidth, blockHeight, i);
			positionX = positionX + blockWidth;
			blocks.add(block);
			mainFrame.add(block);
			if (i % matrix == 0) {
				positionX = x;
				positionY = positionY + blockHeight;
			}
		}

		emptySpace = new NumberBlock("", positionX, positionY, blockWidth, blockHeight, matrix * matrix);
		mainFrame.add(emptySpace);

//		this.validate();

		mainFrame.setTitle("Author - sudhu72@gmail.com");
//		mainFrame.pack();
//		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowDeactivated(WindowEvent we) {
				// ((JFrame)we.getSource()).setState(JFrame.ICONIFIED);
				System.out.println("windowDeactivated " + emptySpace.getX() + " - " + emptySpace.getY());
			}

			@Override
			public void windowActivated(WindowEvent we) {
				// TODO Auto-generated method stub
				// ((JFrame)we.getSource()).setState(JFrame.NORMAL);
//				mainFrame.invalidate();
//			    mainFrame.validate();
//			    mainFrame.repaint();
				System.out.println("windowActivated " + emptySpace.getX() + " - " + emptySpace.getY());
			}

			@Override
			public void windowGainedFocus(WindowEvent e) {
				System.out.println("windowGainedFocus");
			}

			public void windowClosing(WindowEvent we) {
				((JFrame) we.getSource()).setVisible(false);
				System.exit(0);
			}
		});

//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		mainFrame.setVisible(true);

		startTime = System.currentTimeMillis();
	}

	public static void main(String[] args) {
		new NumbersFrame();
	}

}
