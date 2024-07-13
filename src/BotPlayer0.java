class BotPlayer0 extends BotPlayer {
    @Override
    int[] getMove(Game game) {
        return getRandomMove(game.getValidMoves());
    }
}
