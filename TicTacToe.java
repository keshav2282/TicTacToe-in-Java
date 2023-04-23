

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {

    private JButton[][] buttons = new JButton[3][3];
    private boolean xTurn = true;
    private Color[] colors = {Color.BLACK, Color.BLACK, Color.BLACK};
    private Color xColor = Color.WHITE;
    private Color oColor = Color.WHITE;
    private Color backgroundColor = Color.BLACK;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        getContentPane().setBackground(backgroundColor);

        // Create and add the buttons to the frame
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton button = new JButton("");
                button.addActionListener(this);
                button.setBackground(colors[(row + col) % colors.length]);
                buttons[row][col] = button;
                add(button);
            }
        }

        setVisible(true);
    }

    // Handle button clicks
    public void actionPerformed(ActionEvent event) {
        JButton button = (JButton) event.getSource();

        if (button.getText().equals("")) {
            if (xTurn) {
                button.setText("X");
                button.setForeground(xColor);
            } else {
                button.setText("O");
                button.setForeground(oColor);
            }
            xTurn = !xTurn;
        }

        // Check for a win or draw
        if (checkWin("X")) {
            JOptionPane.showMessageDialog(this, "X wins!");
            reset();
        } else if (checkWin("O")) {
            JOptionPane.showMessageDialog(this, "O wins!");
            reset();
        } else if (checkDraw()) {
            JOptionPane.showMessageDialog(this, "Draw!");
            reset();
        }
    }

    // Check if a player has won
    private boolean checkWin(String player) {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(player)
                    && buttons[row][1].getText().equals(player)
                    && buttons[row][2].getText().equals(player)) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(player)
                    && buttons[1][col].getText().equals(player)
                    && buttons[2][col].getText().equals(player)) {
                return true;
            }
        }

        // Check diagonals
        if (buttons[0][0].getText().equals(player)
                && buttons[1][1].getText().equals(player)
                && buttons[2][2].getText().equals(player)) {
            return true;
        }
        if (buttons[0][2].getText().equals(player)
                && buttons[1][1].getText().equals(player)
                && buttons[2][0].getText().equals(player)) {
            return true;
        }

        return false;
    }

    // Check if the game is a draw
    private boolean checkDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    // Reset the game
    private void reset() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        xTurn = true;
    }
    
    public static void main(String[] args) {
        new TicTacToe();
    }
    
}