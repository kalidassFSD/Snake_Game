import javax.swing.*;
import java.awt.*;

public class snake {
    public static void main(String[] args) {
        int height=500;
        int width =500;

        JFrame frame = new JFrame("snake");
        JPanel panel = new JPanel();
        JLabel label = new JLabel();            //top label
        JLabel board = new JLabel();            //game area -bottom label

        frame.setVisible(true);
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        label.setText("Snake Game");
        label.setOpaque(true);
        label.setBackground(Color.gray);
        label.setForeground(Color.white);
        label.setFont(new Font("Times New Roman", Font.BOLD, 50));
        label.setHorizontalAlignment(JLabel.CENTER);


        panel.setLayout(new BorderLayout());
        panel.add(label);

        frame.add(panel, BorderLayout.NORTH);

        game Game = new game(width,height);
        frame.add(Game);


        frame.pack();
        Game.requestFocus();
    }
}
