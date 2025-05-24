
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

public class TicTacWidget extends JComponent {

    private TicTacModel plan;
    private double xOffset = 0;

    public TicTacModel getModel() {
        return plan;
    }

    public void setModel(TicTacModel model) {
        this.plan = model;
        this.repaint();
    }

    private int getScaling() {
        int w = this.getWidth() / plan.getWidth();
        int h = this.getHeight() / plan.getHeight();
        if (h < w) {
            xOffset = (1 - (double) this.getHeight() / this.getWidth()) * this.getWidth() / 2;
        }
        return w < h ? w : h;
    }

    public int getModelCoordsX(int x) {
        return (x - (int) xOffset) / this.getScaling();
    }

    public int getModelCoordsY(int y) {
        return y / this.getScaling();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (plan == null) {
            g.setColor(Color.red);
            g.drawString("No model", 10, 10);
            return;
        }
        int s = this.getScaling();
        int offset = 3;
        for (int x = 0; x < plan.getWidth(); x++) {
            for (int y = 0; y < plan.getHeight(); y++) {
                g.setColor(Color.black);
                g.drawRect(x * s + (int) xOffset, y * s, s, s);
                switch (plan.getMarkAt(x, y)) {

                    case 'x' -> {
                        g.setColor(Color.blue);
                        g.drawLine(x * s + offset + (int) xOffset, y * s + offset, (x + 1) * s - offset + (int) xOffset, (y + 1) * s - offset);
                        g.drawLine(x * s + offset + (int) xOffset, (y + 1) * s - offset, (x + 1) * s - offset + (int) xOffset, y * s + offset);
                    }
                    case 'o' -> {
                        g.setColor(Color.red);
                        g.drawOval(x * s + offset + (int) xOffset, y * s + offset, s - offset * 2, s - offset * 2);
                    }
                }
            }
        }
    }
}
