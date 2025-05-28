
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TicTacWindow extends javax.swing.JFrame {

    private boolean gameOn = true;

    private void save() {
        JTextField input = new JTextField(10);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Name your pattern, name must be at least 3 characters long:"));
        panel.add(input);

        int result = JOptionPane.showConfirmDialog(null, panel, "Save Pattern", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result != JOptionPane.OK_OPTION || input.getText().length() < 3) {
            return;
        }
        try {
            String basePath = Paths.get(TicTacWindow.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent().toAbsolutePath().toString();
            FileOutputStream fileOutput = new FileOutputStream(basePath + '/' + input.getText());
            ObjectOutputStream output = new ObjectOutputStream(fileOutput);
            output.writeObject(ticTacWidget1.getModel());
            output.close();
            fileOutput.close();
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
    }

    private void load() {
        JFileChooser jfc = new JFileChooser();
        if (jfc.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        try {
            FileInputStream fileIn = new FileInputStream(jfc.getSelectedFile());
            ObjectInputStream in = new ObjectInputStream(fileIn);
            TicTacModel plan = (TicTacModel) in.readObject();
            ticTacWidget1.setModel(plan);
            game.setPlan(plan);
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private void newGame() {
        game = new TicTacGame();
        ticTacWidget1.setModel(game.getPlan());
        jLabel1.setText("It's " + game.getCurrentPlayer() + "'s turn.");
        ticTacWidget1.repaint();
    }
    private TicTacGame game;

    public TicTacWindow() {
        initComponents();
        game = new TicTacGame();
        ticTacWidget1.setModel(game.getPlan());
        this.addWindowFocusListener(new WindowAdapter() {
            public void windowGainedFocus(WindowEvent e) {
                ticTacWidget1.requestFocusInWindow();
            }
        });
    }

    private void showHelp() {
        JOptionPane.showMessageDialog(this, "Welcome to TicTacToe!\nKeybinds:\nl - load game\ns - save game");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ticTacWidget1 = new TicTacWidget();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("It's x's turn");
        jLabel1.setToolTipText("");

        ticTacWidget1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ticTacWidget1MouseReleased(evt);
            }
        });
        ticTacWidget1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ticTacWidget1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE)
            .addComponent(ticTacWidget1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ticTacWidget1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ticTacWidget1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ticTacWidget1MouseReleased
        if (!gameOn) {
            this.newGame();
            gameOn = true;
            return;
        }
        game.turn(ticTacWidget1.getModelCoordsX(evt.getX()), ticTacWidget1.getModelCoordsY(evt.getY()));
        ticTacWidget1.repaint();
        switch (game.getWinner()) {
            case 'x', 'o' -> {
                jLabel1.setText("Player " + game.getWinner() + " won!");
                gameOn = false;
            }
            case '!' -> {
                jLabel1.setText("It's a draw!");
                gameOn = false;
            }
            default ->
                jLabel1.setText("It's " + game.getCurrentPlayer() + "'s turn");
        }
    }//GEN-LAST:event_ticTacWidget1MouseReleased

    private void ticTacWidget1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ticTacWidget1KeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_S -> {
                save();
            }
            case KeyEvent.VK_L -> {
                load();
            }
        }
    }//GEN-LAST:event_ticTacWidget1KeyReleased

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TicTacWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TicTacWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TicTacWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TicTacWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TicTacWindow window = new TicTacWindow();
                window.setVisible(true);
                window.showHelp();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private TicTacWidget ticTacWidget1;
    // End of variables declaration//GEN-END:variables
}
