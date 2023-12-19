import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 100;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts =2;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random = new Random();

    int[] rockX_1 = {25, 50, 200, 300, 200, 500, 400, 25, 50, 75, 125, 200, 200, 225, 250, 25, 25, 25, 425, 450, 500, 525, 550, 575}; // Example rock X positions
    int[] rockY_1 = {25, 25, 100, 100, 200, 600, 300, 25, 50, 50, 100, 125, 150, 175, 175, 300, 325, 350, 325, 350, 375, 400, 425, 450};
    int[] rockX_2 = {25, 50, 75, 125, 200, 200, 225, 250, 25, 25, 25, 425, 450, 500, 525, 550, 575}; // Example rock X positions
    int[] rockY_2 = {25, 50, 50, 100, 125, 150, 175, 175, 300, 325, 350, 325, 350, 375, 400, 425, 450}; // Example rock Y positions
    int[] rockX_3 = {125,125,125,125,125,125,150, 175, 200, 200, 225, 225,25, 50, 25, 50, 400, 400, 425, 425, 25, 25, 50, 50, 425, 450, 500, 525, 550, 575}; // Example rock X positions
    int[] rockY_3 = {25,50,75,100,125,150, 175, 150, 200, 225, 200, 225, 25, 50, 50, 25, 25, 50, 25, 50, 400, 425, 400, 425, 325, 350, 375, 400, 425, 450};
    int[] rockX_4 = {150, 150, 150, 150, 150,400, 425, 300, 300, 150, 175, 200, 225, 225, 250, 250, 50, 75, 50, 75, 425, 425, 450, 450, 50, 50, 75, 75, 450, 475, 525, 550, 575, 600}; // Updated rock X positions
    int[] rockY_4 = {450, 475, 400, 525, 550,400, 425, 500, 525, 575, 200, 175, 225, 250, 225, 250, 50, 75, 75, 50, 50, 75, 50, 75, 425, 450, 425, 450, 350, 375, 400, 425, 450, 475}; // Updated rock Y positions
    int[] rockX_5 = {50,75,100,125,150,175,200,225,250,275, 100,125,150,175,200,225,250,275,300,325,350,375,400,425,450, 500,500,500,500,500,500,500,500,500,500,500,500};
    int[] rockY_5 = {50,50,50,50,50,50,50,50,50,50, 425,425,425,425,425,425,425,425,425,425,425,425,425,425,425,75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350};
    int[] rockX_6 = {50,100,100,150,150,200,200,250,250,300, 100,100,150,150,200,200,250,250,300,300,350,350,400,400,450, 500,500,500,500,500,500,500,500,500,500,500,500};
    int[] rockY_6 = {50,50,75,50,75,50,75,50,75,50, 425,450,425,450,425,450,425,450,425,450,425,450,425,450,425,75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350};
    int[] rockX_7 = {50, 75, 100, 50, 75, 100, 50, 50, 50, 50, 50, 50, 350, 375, 400, 350, 375, 400, 350, 350, 350, 350, 350, 350};
    int[] rockY_7 = {50, 50, 50, 400, 400, 400, 325, 350, 375, 75, 100, 125, 150, 150, 150, 500, 500, 500, 425, 450, 475, 175, 200, 225};
    int[] rockX_8 = {50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500};
    int[] rockY_8 = {50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50,100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 475, 475, 475, 475, 475, 475, 475, 475, 475, 475, 475, 475, 475, 475, 475, 475, 475, 475, 475};
    int[] rockX_9 = {50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50,100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 400, 475, 475, 475, 475, 475, 475, 475, 475, 475, 475, 475, 475, 475, 475, 475, 475, 475, 475, 475};
    int[] rockY_9 = {50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500};

    int[] rockX_10 = {50, 75, 100, 125, 500, 525, 550, 475, 450, 425, 400, 400, 425, 450, 475, 500, 50, 75, 100, 125,150, 175};
    int[] rockY_10 = {50, 75, 100, 125, 500, 525, 550, 475, 450, 425, 400, 175, 150, 125, 100, 75, 525, 500, 475, 450, 425, 400};
    int[][][] levels = {{rockX_1, rockX_2, rockX_3, rockX_4, rockX_5,rockX_6,rockX_7,rockX_8,rockX_9,rockX_10}, {rockY_1, rockY_2, rockY_3, rockY_4,rockY_5, rockY_6,rockY_7,rockY_8,rockY_9,rockY_10}};

    int randomLevel = random.nextInt(10);
    int[] rockX = levels[0][randomLevel];
    int[] rockY = levels[1][randomLevel];
    int c = 0;
    private long startTime;
    private long elapsedTime;
    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();

    }
    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
        initGame();
        startTime = System.currentTimeMillis();
    }

    public void initGame() {
        // The center coordinates of the screen
        int centerX = SCREEN_WIDTH / 2;
        int centerY = SCREEN_HEIGHT / 2;
        randomLevel = random.nextInt(10);
        rockX = levels[0][randomLevel];
        rockY = levels[1][randomLevel];
        // Set the initial positions of the snake segments to form the snake in the center
        for (int i = 0; i < bodyParts; i++) {
            x[i] = centerX - i * UNIT_SIZE; // Adjust x position based on the unit size
            y[i] = centerY;
        }

        // Generate rock positions, ensuring they do not overlap with the snake's initial position
        boolean[] isRockAtInitialSnakePosition = new boolean[rockX.length];
        for (int i = 0; i < rockX.length; i++) {
            isRockAtInitialSnakePosition[i] = false;
        }

        for (int i = 0; i < rockX.length; i++) {
            if (rockX[i] == centerX && rockY[i] == centerY) {
                isRockAtInitialSnakePosition[i] = true;
            }
        }

        // Set new rock positions to avoid the snake's initial position
        for (int i = 0; i < rockX.length; i++) {
            if (isRockAtInitialSnakePosition[i]) {
                do {
                    rockX[i] = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
                    rockY[i] = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
                } while ((rockX[i] == centerX && rockY[i] == centerY) || isRockOverlapWithSnake(i));
            }
        }

        // Set a random initial direction for the snake
        int randomNumber = random.nextInt(4);
        switch (randomNumber) {
            case 0:
                direction = 'R';
                break;
            case 1:
                direction = 'L';
                break;
            case 2:
                direction = 'U';
                break;
            case 3:
                direction = 'D';
                break;
        }
    }


    // Helper method to check if a rock overlaps with the snake
    public boolean isRockOverlapWithSnake(int index) {
        for (int i = 0; i < bodyParts; i++) {
            if (x[i] == rockX[index] && y[i] == rockY[index]) {
                return true;
            }
        }
        return false;
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        if(running) {
            g.setColor(Color.gray); // Set color for rocks
            for (int i = 0; i < rockX.length; i++) {
                g.fillRect(rockX[i], rockY[i], UNIT_SIZE, UNIT_SIZE); // Draw rocks as rectangles (adjust as needed)
            }
//            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
//                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
//                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
//            }
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.yellow);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten))/2 , g.getFont().getSize());
            c = 0;
        }
        else {
            gameOver(g);
            c++;
        }
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("Time: " + elapsedTime + " sec", SCREEN_WIDTH - 100, SCREEN_HEIGHT - 10);
    }
    public void newApple() {
        boolean appleOnRock;
        do {
            appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
            appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;

            appleOnRock = false;
            for (int i = 0; i < rockX.length; i++) {
                if (appleX == rockX[i] && appleY == rockY[i]) {
                    appleOnRock = true;
                    break;
                }
            }
        } while (appleOnRock);
    }

    public void move(){
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];

        }
        switch (direction){
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }
    public void checkApple (){
        if((x[0] == appleX) && (y[0] == appleY)){
            bodyParts+=1;
            applesEaten++;
            newApple();
        }
    }
    public void checkCollisions(){
        // check if head collides body
        for (int i = bodyParts; i > 0 ; i--) {
            if((x[0] == x[i])&&(y[0] == y[i])){
                running = false;
            }
        }
        //check if head touches left border
        if(x[0] < 0){
            running = false;
        }
        //check if head touches right border
        if(x[0] >= SCREEN_WIDTH ){
            running = false;
        }
        // if head touches top border
        if(y[0] < 0){
            running = false;
        }
        // check ig head touches bottom border
        if(y[0] >= SCREEN_HEIGHT){
            running=false;
        }
        for (int i = 0; i < rockX.length; i++) {
            if (x[0] == rockX[i] && y[0] == rockY[i]) {
                running = false; // If collision occurs, end the game
            }
        }
        if(!running){
            timer.stop();
        }
    }
    public void showEndGameDialog() {
        String playerName = JOptionPane.showInputDialog(null, "Enter your name:", "Game Over - Enter Name", JOptionPane.PLAIN_MESSAGE);

        if (playerName != null && !playerName.trim().isEmpty()) {
            HighscoreManager highscoreManager = new HighscoreManager();
            highscoreManager.saveScore(playerName, applesEaten); // Save the score with the player's name
            // Notify the player that the score is saved
            JOptionPane.showMessageDialog(null, "Score saved successfully!", "Score Saved", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid name entered. Score not saved.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    public void gameOver(Graphics g){
        //Display score
        g.setColor(Color.yellow);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applesEaten))/2 , g.getFont().getSize());

        //Game over text
        g.setColor(Color.red);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over!", (SCREEN_WIDTH - metrics2.stringWidth("Game Over!"))/2 , SCREEN_HEIGHT/2);
        if(c==0){showEndGameDialog();}
    }

    public void restartGame() {
        running = false;
        bodyParts = 2;
        applesEaten = 0;
        direction = 'R';
        timer.stop();

        // Reinitialize the game
        startGame();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
            updateElapsedTime(); // Update the elapsed time
        }
        repaint();
    }
    private void updateElapsedTime() {
        long currentTime = System.currentTimeMillis();
        elapsedTime = (currentTime - startTime) / 1000; // Convert to seconds
    }
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){
                case KeyEvent.VK_A:
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_D:
                    if(direction != 'L'){
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_W:
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_S:
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
