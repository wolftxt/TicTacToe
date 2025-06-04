
public class TicTacGame {

    private TicTacModel plan;

    public TicTacGame() {
        this(25, 25);
    }

    public TicTacGame(int width, int height) {
        this.plan = new TicTacModel(width, height);
    }

    public TicTacModel getPlan() {
        return plan;
    }

    public void setPlan(TicTacModel plan) {
        this.plan = plan;
    }

    // Different methods to check for a win
    /* public char getWinner() {
        int spaceCount = 0;
        int xCount = 0;
        int oCount = 0;
        for (int i = 0; i < plan.getWidth(); i++) { // check for column win
            for (int j = 0; j < plan.getHeight(); j++) {
                if (plan.getMarkAt(i, j) == 'x') {
                    xCount++;
                }
                if (plan.getMarkAt(i, j) == 'o') {
                    oCount++;
                }
            }
            if (xCount > 4 || oCount > 4) {
                return plan.getMarkAt(i, plan.getHeight() - 1);
            }
            xCount = 0;
            oCount = 0;
        }
        for (int i = 0; i < plan.getHeight(); i++) { // check for row win
            for (int j = 0; j < plan.getWidth(); j++) {
                if (plan.getMarkAt(j, i) == 'x') {
                    xCount++;
                }
                if (plan.getMarkAt(j, i) == 'o') {
                    oCount++;
                }
            }
            if (xCount > 4 || oCount > 4) {
                return plan.getMarkAt(plan.getWidth() - 1, i);
            }
            xCount = 0;
            oCount = 0;
        }
        for (int i = 0; i < plan.getHeight(); i++) { // Can also be .getWidth()
            if (plan.getMarkAt(i, i) == 'x') {
                xCount++;
            }
            if (plan.getMarkAt(i, i) == 'o') {
                oCount++;
            }
            if (xCount > 4 || oCount > 4) {
                return plan.getMarkAt(i, i);
            }
        }
        xCount = 0;
        oCount = 0;
        for (int i = 4; i > -1; i--) {
            if (plan.getMarkAt(i, 4-i) == 'x') {
                xCount++;
            }
            if (plan.getMarkAt(i, 4-i) == 'o') {
                oCount++;
            }
            if (xCount > 4 || oCount > 4) {
                return plan.getMarkAt(i, 4 - i);
            }
        }
        xCount = 0;
        oCount = 0;
                for (int i = 0; i < plan.getWidth(); i++) {
            for (int j = 0; j < plan.getHeight(); j++) {
                if (plan.getMarkAt(i, j) == ' ') {
                    spaceCount++;
                }
            }
        }
        if (spaceCount == 0) {
            return '!';
        }
        return '?';
    } */
 /* public char getWinner() {
        int pointsToWin = 3;
        int spaceCount = 0;
        for (int i = 0; i < plan.getWidth() - pointsToWin + 1; i++) {
            for (int j = 0; j < plan.getHeight() - pointsToWin + 1; j++) {
                int c1 = 0;
                int c2 = 0;
                int c3 = 0;
                int c4 = 0;
                for (int k = 1; k < pointsToWin; k++) {
                    if (plan.getMarkAt(i, j) == 'x' || plan.getMarkAt(i, j) == 'o') {
                        if (plan.getMarkAt(i, j) == plan.getMarkAt(i + k, j)) {
                            c1++;
                        }
                        if (plan.getMarkAt(i, j) == plan.getMarkAt(i, j + k)) {
                            c2++;
                        }
                        if (plan.getMarkAt(i, j) == plan.getMarkAt(i + k, j + k)) {
                            c3++;
                        }
                    }
                    if (plan.getMarkAt(i + pointsToWin - 1, j) == 'x' || plan.getMarkAt(i + pointsToWin - 1, j) == 'o') {
                        if (plan.getMarkAt(i + pointsToWin - 1, j) == plan.getMarkAt(i + pointsToWin - 1 - k, j + k)) {
                            c4++;
                        }
                    }
                }
                if (c1 == pointsToWin - 1 || c2 == pointsToWin - 1 || c3 == pointsToWin - 1) {
                    return plan.getMarkAt(i, j);
                }
                if (c4 == pointsToWin - 1) {
                    return plan.getMarkAt(i + pointsToWin - 1, j);
                }
            }
        }
        for (int i = 0; i < plan.getWidth() - pointsToWin + 1; i++) {
            for (int j = plan.getHeight() - pointsToWin + 1; j < plan.getHeight(); j++) {
                int c1 = 0;
                for (int k = 1; k < pointsToWin; k++) {
                    if (plan.getMarkAt(i, j) == 'x' || plan.getMarkAt(i, j) == 'o') {
                        if (plan.getMarkAt(i, j) == plan.getMarkAt(i + k, j)) {
                            c1++;
                        }
                    }
                }
                if (c1 == pointsToWin - 1) {
                    return plan.getMarkAt(i, j);
                }
            }
        }
        for (int i = 0; i < plan.getHeight() - pointsToWin + 1; i++) {
            for (int j = plan.getWidth() - pointsToWin + 1; j < plan.getWidth(); j++) {
                int c1 = 0;
                for (int k = 1; k < pointsToWin; k++) {
                    if (plan.getMarkAt(j, i) == 'x' || plan.getMarkAt(j, i) == 'o') {
                        if (plan.getMarkAt(j, i) == plan.getMarkAt(j, i + k)) {
                            c1++;
                        }
                    }
                }
                if (c1 == pointsToWin - 1) {
                    return plan.getMarkAt(j, i);
                }
            }
        }
        for (int i = 0; i < plan.getWidth(); i++) {
            for (int j = 0; j < plan.getHeight(); j++) {
                if (plan.getMarkAt(i, j) == ' ') {
                    spaceCount++;
                }
            }
        }
        if (spaceCount == 0) {
            return '!';
        }
        return '?';
    } */
    public char getWinner() {
        int pointsToWin = 5;
        int spaceCount = 0;
        class Checks {

            private char checkWinRow(int x, int y) {
                int count = 0;
                for (int i = 1; i < pointsToWin; i++) {
                    if (x < plan.getWidth() - pointsToWin + 1) {
                        if ((plan.getMarkAt(x, y) == 'x' || plan.getMarkAt(x, y) == 'o') && (plan.getMarkAt(x, y) == plan.getMarkAt(x + i, y))) {
                            count++;
                        }
                    }
                }
                if (count == pointsToWin - 1) {
                    return plan.getMarkAt(x, y);
                }
                return '?';
            }

            private char checkWinColumn(int x, int y) {
                int count = 0;
                for (int i = 1; i < pointsToWin; i++) {
                    if (y < plan.getHeight() - pointsToWin + 1) {
                        if ((plan.getMarkAt(x, y) == 'x' || plan.getMarkAt(x, y) == 'o') && (plan.getMarkAt(x, y) == plan.getMarkAt(x, y + i))) {
                            count++;
                        }
                    }
                }
                if (count == pointsToWin - 1) {
                    return plan.getMarkAt(x, y);
                }
                return '?';
            }

            private char checkWinDiagonal(int x, int y) {
                int count = 0;
                for (int i = 1; i < pointsToWin; i++) {
                    if ((x < plan.getWidth() - pointsToWin + 1) && (y < plan.getHeight() - pointsToWin + 1)) {
                        if ((plan.getMarkAt(x, y) == 'x' || plan.getMarkAt(x, y) == 'o') && (plan.getMarkAt(x, y) == plan.getMarkAt(x + i, y + i))) {
                            count++;
                        }
                    }
                }
                if (count == pointsToWin - 1) {
                    return plan.getMarkAt(x, y);
                }
                return '?';
            }

            private char checkWinAntiDiagonal(int x, int y) {
                int count = 0;
                for (int i = 1; i < pointsToWin; i++) {
                    if ((x - pointsToWin + 1 >= 0) && (y < plan.getHeight() - pointsToWin + 1)) {
                        if ((plan.getMarkAt(x, y) == 'x' || plan.getMarkAt(x, y) == 'o') && (plan.getMarkAt(x, y) == plan.getMarkAt(x - i, y + i))) {
                            count++;
                        }
                    }
                }
                if (count == pointsToWin - 1) {
                    return plan.getMarkAt(x, y);
                }
                return '?';
            }
        }
        Checks ch = new Checks();
        for (int i = 0; i < plan.getWidth(); i++) {
            for (int j = 0; j < plan.getHeight(); j++) {
                if (ch.checkWinRow(i, j) == 'x' || ch.checkWinRow(i, j) == 'o') {
                    return ch.checkWinRow(i, j);
                }
                if (ch.checkWinColumn(i, j) == 'x' || ch.checkWinColumn(i, j) == 'o') {
                    return ch.checkWinColumn(i, j);
                }
                if (ch.checkWinDiagonal(i, j) == 'x' || ch.checkWinDiagonal(i, j) == 'o') {
                    return ch.checkWinDiagonal(i, j);
                }
                if (ch.checkWinAntiDiagonal(i, j) == 'x' || ch.checkWinAntiDiagonal(i, j) == 'o') {
                    return ch.checkWinAntiDiagonal(i, j);
                }
            }
        }
        for (int i = 0; i < plan.getWidth(); i++) {
            for (int j = 0; j < plan.getHeight(); j++) {
                if (plan.getMarkAt(i, j) == ' ') {
                    spaceCount++;
                }
            }
        }
        if (spaceCount == 0) {
            return '!';
        }
        return '?';
    }

    public boolean turn(int x, int y) {
        plan.setMarkAt(x, y, getCurrentPlayer());
        return true;
    }

    public char getCurrentPlayer() {
        int counter = 0;
        for (int i = 0; i < plan.getWidth(); i++) {
            for (int j = 0; j < plan.getHeight(); j++) {
                if (plan.getMarkAt(i, j) == 'x' || plan.getMarkAt(i, j) == 'o') {
                    counter++;
                }
            }
        }
        if (counter % 2 == 0) {
            return 'x';
        }
        return 'o';
    }
}
