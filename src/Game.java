import com.position.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Game extends JFrame{

    Position position = new Position();
    JButton[] buttons = new JButton[Position.SIZE];

    public Game() {
        setLayout(new GridLayout(Position.DIM, Position.DIM));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        for (int i = 0; i < Position.SIZE; i++) {
           final JButton button = createButton();
            buttons[i] = button;
            final int idx = i;
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button.setText(Character.toString(position.turn));
                    button.setFocusPainted(false);
                    position.move(idx);
                    if (!position.isGameEnd()) {
                        int best = position.bestMove();
                        buttons[best].setText(Character.toString(position.turn));
                        position.move(best);
                    }
                    if (position.isGameEnd()) {
                        String message = position.isWinFor('x') ? "You win!" : position.isWinFor('o') ? "Computer win!" : "Draw";
                        JOptionPane.showMessageDialog(null, message);
                    }
                }
            });
        }
        pack();
        setVisible(true);
    }

    private JButton createButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(100, 100));
        button.setOpaque(true);
        button.setFont(new Font(null, Font.PLAIN, 50));
        add(button);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Game();
            }
        });
    }

}
