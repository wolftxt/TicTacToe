
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TicTacWindow extends javax.swing.JFrame {

    private boolean gameOn = true;
    
    private void save() {
        try {
            FileOutputStream fileOutput = new FileOutputStream("tttgame");
            ObjectOutputStream output = new ObjectOutputStream(fileOutput);
            output.writeObject(ticTacWidget1.getModel());
            output.close();
            fileOutput.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("FileNotFoundException");
        } catch (IOException ex) {
            throw new RuntimeException("IOException");
        }
    }

    private void newGame() {
        game = new TicTacGame();
        ticTacWidget1.setModel(game.getModel());
        jLabel1.setText("It's " + game.getCurrentPlayer() + "'s turn.");
        ticTacWidget1.repaint();
    }
    private TicTacGame game;

    public TicTacWindow() {
        initComponents();
        game = new TicTacGame();
        ticTacWidget1.setModel(game.getModel());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ticTacWidget1 = new TicTacWidget();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("It's x's turn");
        jLabel1.setToolTipText("");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        ticTacWidget1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ticTacWidget1MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(ticTacWidget1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ticTacWidget1, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
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

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        save();
    }//GEN-LAST:event_jLabel1MouseClicked

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
                new TicTacWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private TicTacWidget ticTacWidget1;
    // End of variables declaration//GEN-END:variables
}
