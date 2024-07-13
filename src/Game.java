import java.util.Arrays;

public final class Game {
    private static final char EMPTY = ' ';
    private static final char[] SYMBOLS = { 'X', 'O' };

    private final char[][] board = {
            { EMPTY, EMPTY, EMPTY },
            { EMPTY, EMPTY, EMPTY },
            { EMPTY, EMPTY, EMPTY }
    };
    private final Player[] players = new Player[2];

    private int moveCount = 0;

    Game(Player player1, Player player2) {
        this.players[0] = player1;
        this.players[1] = player2;
    }

    private char getBoard(int[] move) {
        return board[move[0]][move[1]];
    }

    private boolean getBoardEmpty(int[] move) {
        return getBoard(move) == EMPTY;
    }

    private int getCountLine(int[][] line, char symbol) {
        int count = 0;
        for (int[] move : line) {
            if (getBoard(move) == symbol) {
                ++count;
            }
        }
        return count;
    }

    private int[][] getWinBlockMoves(char symbol) {
        int[][] moves = new int[9][];
        int index = 0;
        for (int[][] line : Util.getLines()) {
            if (getCountLine(line, symbol) == 2) {
                for (int[] move : line) {
                    if (getBoard(move) == EMPTY) {
                        moves[index++] = move;
                        break;
                    }
                }
            }
        }
        return Arrays.copyOfRange(moves, 0, index);
    }

    private int[][][] getWonLines(char symbol) {
        int[][][] lines = new int[8][][];
        int index = 0;
        for (int[][] line : Util.getLines()) {
            if (getCountLine(line, symbol) == 3) {
                lines[index++] = line;
            }
        }
        return Arrays.copyOfRange(lines, 0, index);
    }

    private boolean hasPlayerWon(Player player) {
        return getWonLines(getPlayerSymbol(player)).length != 0;
    }

    void setBoardEmpty(int[] move) {
        setBoard(move, EMPTY);
    }

    void setBoard(int[] move, char symbol) {
        board[move[0]][move[1]] = symbol;
    }

    int getPlayerIndex(Player player) {
        return player.hashCode() == players[0].hashCode() ? 0 : 1;
    }

    char getPlayerSymbol(Player player) {
        return SYMBOLS[getPlayerIndex(player)];
    }

    char getPlayerSymbolOther(Player player) {
        return SYMBOLS[1 - getPlayerIndex(player)];
    }

    int[][] getValidMoves() {
        int[][] moves = new int[9][];
        int index = 0;
        for (int[] move : Util.getMoves()) {
            if (getBoardEmpty(move)) {
                moves[index++] = move;
            }
        }
        return Arrays.copyOfRange(moves, 0, index);
    }

    boolean isOver() {
        return getValidMoves().length == 0 || getWonPlayer() != null;
    }

    int[][] getWinMoves(Player player) {
        return getWinBlockMoves(getPlayerSymbol(player));
    }

    int[][] getBlockMoves(Player player) {
        return getWinBlockMoves(getPlayerSymbolOther(player));
    }

    Player getWonPlayer() {
        for (Player player : players) {
            if (hasPlayerWon(player)) {
                return player;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String rowText = "#########################";
        String rowTextHollow = "#       #       #       #";

        StringBuilder str = new StringBuilder(rowText + "\n");
        for (char[] row : board) {
            str.append(rowTextHollow).append("\n#");
            for (char symbol : row) {
                str.append("   ").append(symbol).append("   #");
            }
            str.append("\n").append(rowTextHollow).append("\n").append(rowText).append("\n");
        }
        return str.toString();
    }

    public void move() {
        Player player = players[moveCount++ % 2];
        if (!(player instanceof UserPlayer)) {
            System.out.print(player.toString(this) + "\n"
                    + Arrays.toString(Util.movesToKeys(getValidMoves())) + " => ");
        }
        int[] move = player.getMove(this);
        if (!(player instanceof UserPlayer)) {
            System.out.println(Util.moveToKey(move));
        }
        setBoard(move, getPlayerSymbol(player));
    }

    public void play() {
        System.out.print(this);
        while (!isOver()) {
            move();
            System.out.print(this);
        }
        Player wonPlayer = getWonPlayer();
        if (wonPlayer == null) {
            System.out.println("Draw!");
        } else {
            System.out.println(wonPlayer.toString(this) + " wins!");
        }
    }
}
