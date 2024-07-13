public class Main {
    public static void main(String[] args) {
        Player[] players = new Player[2];
        for (int index = 0; index < players.length; ++index) {
            int input = Util.getInput("[User, Bot0, Bot1, Bot2] Select Player "
                    + (index + 1), new int[] { 1, 2, 3, 4 });
            Player player = switch (input) {
                case 1 -> new UserPlayer();
                case 2 -> new BotPlayer0();
                case 3 -> new BotPlayer1();
                case 4 -> new BotPlayer2();
                default -> null;
            };
            players[index] = player;
        }
        new Game(players[0], players[1]).play();
    }
}
