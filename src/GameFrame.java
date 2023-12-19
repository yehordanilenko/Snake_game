import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {

    GameFrame() {
        GamePanel panel = new GamePanel();
        this.add(panel, BorderLayout.CENTER); // Set the GamePanel to the center

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Game");
        JMenuItem restartItem = new JMenuItem("Restart");

        // Add action listener to the restart menu item
        restartItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.restartGame(); // Call the restartGame method in GamePanel
            }
        });

        menu.add(restartItem);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        // Inside your GameFrame class constructor
        JMenuItem highscoreItem = new JMenuItem("Highscores");
        highscoreItem.addActionListener(e -> {
            HighscoreManager highscoreManager = new HighscoreManager();
            highscoreManager.showHighscores(GameFrame.this); // Pass the frame reference
        });
        menu.add(highscoreItem);
    }
}
