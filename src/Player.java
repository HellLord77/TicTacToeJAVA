abstract class Player {
    abstract int[] getMove(Game game);

    String toString(Game game) {
        return "[" + game.getPlayerSymbol(this) + "@" +
                getClass().getSimpleName() + "] Player " + (game.getPlayerIndex(this) + 1);
    }
}
