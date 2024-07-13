class BotPlayer1 extends BotPlayer {
    @Override
    int[] getMove(Game game) {
        int[][] moves = game.getWinMoves(this);
        if (moves.length == 0) {
            moves = game.getBlockMoves(this);
        }
        if (moves.length == 0) {
            moves = game.getValidMoves();
        }
        return getRandomMove(moves);
    }
}
