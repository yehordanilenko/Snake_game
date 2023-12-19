import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HighscoreManager {
    public void saveScore(String playerName, int score) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO results (name, score) VALUES (?, ?)")) {
            preparedStatement.setString(1, playerName);
            preparedStatement.setInt(2, score);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void showHighscores(JFrame parentFrame) {
        List<String> highscoresList = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT name, score FROM results ORDER BY score DESC LIMIT 10");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String playerName = resultSet.getString("name");
                int score = resultSet.getInt("score");
                String highscoreEntry = "Player: " + playerName + ", Score: " + score;
                highscoresList.add(highscoreEntry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFrame highscoreFrame = new JFrame("Highscores");
        highscoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        highscoreFrame.setSize(300, 400);

        HighscorePanel highscorePanel = new HighscorePanel(highscoresList);
        highscoreFrame.add(highscorePanel);

        highscoreFrame.setVisible(true);
        highscoreFrame.setLocationRelativeTo(parentFrame);
    }


}
