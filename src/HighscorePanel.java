import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HighscorePanel extends JPanel {

    public HighscorePanel(List<String> highscores) {
        setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Populate text area with highscores
        for (String score : highscores) {
            textArea.append(score + "\n");
        }
    }
}
