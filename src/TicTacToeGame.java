
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 *  The project is based on the tic-tac-toe game found here:
 *  https://www.codespeedy.com/tic-tac-toe-game-using-java-swing-in-java/
 * @author Yashika Jain (the game)
 * @author pvargovcik (the project)
 *
 */
public class TicTacToeGame implements ActionListener {
	public interface GameInterface {
		int moveMade(String[][] gameBoard, int player);
	}

	private JFrame frame = new JFrame();
	private JPanel t_panel = new JPanel();
	private JPanel bt_panel = new JPanel();
	private JLabel textfield = new JLabel();
	private JButton[] bton = new JButton[9];
	private int chance_flag = 0;
	private Random random = new Random();
	private boolean pl1_chance;
	private String studentName;
	private GameInterface gameInterface;

	public TicTacToeGame(String studentName) {
		this.studentName = studentName;

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.getContentPane().setBackground(new Color(50, 50, 50));
		frame.setTitle(studentName != null ? studentName : "Tic Tac Toe");
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);

		textfield.setBackground(new Color(120, 20, 124));
		textfield.setForeground(new Color(25, 255, 0));
		textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText(studentName != null ? studentName : "<studentID>");
		textfield.setOpaque(true);

		t_panel.setLayout(new BorderLayout());
		t_panel.setBounds(0, 0, 800, 100);

		bt_panel.setLayout(new GridLayout(3, 3));
		bt_panel.setBackground(new Color(150, 150, 150));

		for (int i = 0; i < 9; i++) {
			bton[i] = new JButton();
			bt_panel.add(bton[i]);
			bton[i].setFont(new Font("Ink Free", Font.BOLD, 120));
			bton[i].setFocusable(false);
			bton[i].addActionListener(this);
		}

		t_panel.add(textfield);
		frame.add(t_panel, BorderLayout.NORTH);
		frame.add(bt_panel);

		startGame();
	}

	public void startGame() {
		int chance = random.nextInt(100);

		if (chance % 2 == 0) {
			pl1_chance = true;
			textfield.setText("X turn");
		} else {
			pl1_chance = false;
			textfield.setText("O turn");
		}
	}

	public void gameOver(String s) {
		chance_flag = 0;
		Object[] option = { "Restart", "Exit" };
		int n = JOptionPane.showOptionDialog(frame, "Game Over\n" + s, "Game Over", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
		if (n == 0) {
			frame.dispose();
			TicTacToeGame game = new TicTacToeGame(studentName);
			if(gameInterface !=null) {
				game.setGameInterface(gameInterface);
			}
		} else {
			frame.dispose();
		}

	}

	public void matchCheck() {
		if (gameInterface != null) {
			String[][] gameBoard = { 
					{ bton[0].getText(), bton[1].getText(), bton[2].getText() },
					{ bton[3].getText(), bton[4].getText(), bton[5].getText() },
					{ bton[6].getText(), bton[7].getText(), bton[8].getText() } 
			};

			int moveResult = gameInterface.moveMade(gameBoard, !pl1_chance ? 1 : 2);
			
			if(moveResult == 1) {
				// X 1 win
				if(!checkXWins()) {
					textfield.setText("X did not won. Check your code");
					gameOver("ERR");
				}
			}else if(moveResult == 2) {
				// O 2 win
				if(!checkOWins()) {
					textfield.setText("X did not won. Check your code");
					gameOver("ERR");
				}
			}else if(moveResult == 0) {
				// Match Tie
				textfield.setText("Match Tie");
				gameOver("Match Tie");
			}else {
				// no win game continues
			}			
		}
	}
	
	private boolean checkXWins() {		
		if ((bton[0].getText() == "X") && (bton[1].getText() == "X") && (bton[2].getText() == "X")) {
			xWins(0, 1, 2);
		} else if ((bton[0].getText() == "X") && (bton[4].getText() == "X") && (bton[8].getText() == "X")) {
			xWins(0, 4, 8);
		} else if ((bton[0].getText() == "X") && (bton[3].getText() == "X") && (bton[6].getText() == "X")) {
			xWins(0, 3, 6);
		} else if ((bton[1].getText() == "X") && (bton[4].getText() == "X") && (bton[7].getText() == "X")) {
			xWins(1, 4, 7);
		} else if ((bton[2].getText() == "X") && (bton[4].getText() == "X") && (bton[6].getText() == "X")) {
			xWins(2, 4, 6);
		} else if ((bton[2].getText() == "X") && (bton[5].getText() == "X") && (bton[8].getText() == "X")) {
			xWins(2, 5, 8);
		} else if ((bton[3].getText() == "X") && (bton[4].getText() == "X") && (bton[5].getText() == "X")) {
			xWins(3, 4, 5);
		} else if ((bton[6].getText() == "X") && (bton[7].getText() == "X") && (bton[8].getText() == "X")) {
			xWins(6, 7, 8);
		}else {
			return false;
		}
		return true;
	}
	
	private boolean checkOWins() {
		if ((bton[0].getText() == "O") && (bton[1].getText() == "O") && (bton[2].getText() == "O")) {
			oWins(0, 1, 2);
		} else if ((bton[0].getText() == "O") && (bton[3].getText() == "O") && (bton[6].getText() == "O")) {
			oWins(0, 3, 6);
		} else if ((bton[0].getText() == "O") && (bton[4].getText() == "O") && (bton[8].getText() == "O")) {
			oWins(0, 4, 8);
		} else if ((bton[1].getText() == "O") && (bton[4].getText() == "O") && (bton[7].getText() == "O")) {
			oWins(1, 4, 7);
		} else if ((bton[2].getText() == "O") && (bton[4].getText() == "O") && (bton[6].getText() == "O")) {
			oWins(2, 4, 6);
		} else if ((bton[2].getText() == "O") && (bton[5].getText() == "O") && (bton[8].getText() == "O")) {
			oWins(2, 5, 8);
		} else if ((bton[3].getText() == "O") && (bton[4].getText() == "O") && (bton[5].getText() == "O")) {
			oWins(3, 4, 5);
		} else if ((bton[6].getText() == "O") && (bton[7].getText() == "O") && (bton[8].getText() == "O")) {
			oWins(6, 7, 8);
		}else {
			return false;
		}
		return true;
	}

	public void xWins(int x1, int x2, int x3) {
		bton[x1].setBackground(Color.RED);
		bton[x2].setBackground(Color.RED);
		bton[x3].setBackground(Color.RED);

		for (int i = 0; i < 9; i++) {
			bton[i].setEnabled(false);
		}
		textfield.setText("X wins");
		gameOver("X Wins");
	}

	public void oWins(int x1, int x2, int x3) {
		bton[x1].setBackground(Color.RED);
		bton[x2].setBackground(Color.RED);
		bton[x3].setBackground(Color.RED);

		for (int i = 0; i < 9; i++) {
			bton[i].setEnabled(false);
		}
		textfield.setText("O Wins");
		gameOver("O Wins");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(chance_flag >= 9) {
			textfield.setText("ERROR check code");
			gameOver("Game is finished, but no result. Check your code");
		}
		
		for (int i = 0; i < 9; i++) {
			if (e.getSource() == bton[i]) {
				if (pl1_chance) {
					if (bton[i].getText() == "") {
						bton[i].setForeground(new Color(255, 0, 0));
						bton[i].setText("X");
						pl1_chance = false;
						textfield.setText("O turn");
						chance_flag++;
						matchCheck();
					}
				} else {
					if (bton[i].getText() == "") {
						bton[i].setForeground(new Color(0, 0, 255));
						bton[i].setText("O");
						pl1_chance = true;
						textfield.setText("X turn");
						chance_flag++;
						matchCheck();
					}
				}
			}
		}
	}

	public void setGameInterface(GameInterface gameInterface) {
		this.gameInterface = gameInterface;
	}

}