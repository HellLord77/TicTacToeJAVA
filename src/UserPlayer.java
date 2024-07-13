public class UserPlayer extends Player {
    @Override
    int[] getMove(Game game) {
        return Util.keyToMove(Util.getInput(toString(game),
                Util.movesToKeys(game.getValidMoves())));
    }
}
