import java.util.Arrays;

class BotPlayer2 extends BotPlayer {
    private int getScore(Game game, int depth, boolean isMax) {
        if (game.isOver()) {
            Player wonPlayer = game.getWonPlayer();
            if (wonPlayer == null) {
                return 0;
            } else {
                return (this.hashCode() == wonPlayer.hashCode() ? 1
                        : -1) * 10 - depth;
            }
        } else {
            int score = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            char symbol = isMax ? game.getPlayerSymbol(this)
                    : game.getPlayerSymbolOther(this);
            for (int[] move : game.getValidMoves()) {
                game.setBoard(move, symbol);
                int nextScore = getScore(game, depth + 1, !isMax);
                game.setBoardEmpty(move);
                score = isMax ? Integer.max(score, nextScore)
                        : Integer.min(score, nextScore);
            }
            return score;
        }
    }

    @Override
    int[] getMove(Game game) {
        int[][] moves = new int[9][];
        int index = 0;
        int score = Integer.MIN_VALUE;
        char symbol = game.getPlayerSymbol(this);
        for (int[] move : game.getValidMoves()) {
            game.setBoard(move, symbol);
            int nextScore = getScore(game, 0, false);
            game.setBoardEmpty(move);
            if (nextScore > score) {
                score = nextScore;
                index = 0;
            }
            if (nextScore == score) {
                moves[index++] = move;
            }
        }
        return getRandomMove(Arrays.copyOfRange(moves, 0, index));
    }
}
