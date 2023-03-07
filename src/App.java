import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class App extends JPanel implements KeyListener {

    private static final int MOVE_AMOUNT = 1;
    private int speed = 1;
    private int lastKey = 0;
    private int score = 0;
    private int playerX = 400;
    private int playerY = 400;
    private int size = 50;
    private int foodX = (int) (Math.random() * (800 - size - 25) + 25);
    private int foodY = (int) (Math.random() * (800 - size - 25) + 25);

    public App() {
        setBackground(Color.WHITE);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new App());
        frame.setSize(800, 800);
        frame.setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(lastKey == keyCode) {
            speed++;
        } else {
            speed = 1;
        }

        switch (keyCode) {
            case KeyEvent.VK_W:
                playerY -= MOVE_AMOUNT * speed;
                break;
            case KeyEvent.VK_S:
                playerY += MOVE_AMOUNT * speed;
                break;
            case KeyEvent.VK_A:
                playerX -= MOVE_AMOUNT * speed;
                break;
            case KeyEvent.VK_D:
                playerX += MOVE_AMOUNT * speed;
                break;
        }
        lastKey = keyCode;

        if (playerX < foodX + size && playerX + size > foodX && playerY < foodY + size && playerY + size > foodY) {
            foodX = (int) (Math.random() * (800 - size - 2 * 25) + 25);
            foodY = (int) (Math.random() * (800 - size - 2 * 25) + 25);
            score++;
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(playerX, playerY, size, size);
        g.setColor(Color.GREEN);
        g.fillRect(foodX, foodY, size, size);
        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 10, 10);
    }
}
