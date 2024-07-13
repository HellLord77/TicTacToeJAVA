import java.util.Random;

abstract class BotPlayer extends Player {
    private static final Random RANDOM = new Random();

    protected int[] getRandomMove(int[][] moves) {
        return moves[RANDOM.nextInt(moves.length)];
    }
}
