import java.util.Arrays;
import java.util.Scanner;

class Util {
    static boolean arrayContains(int[] arr, int obj) {
        for (int itm : arr) {
            if (itm == obj) {
                return true;
            }
        }
        return false;
    }

    static int[] numToMove(int num) {
        --num;
        return new int[] { num / 3, num % 3 };
    }

    static int moveToNum(int[] move) {
        return move[0] * 3 + move[1] + 1;
    }

    static int keyMap(int num) { // logical
        return new int[] {
                7, 8, 9,
                4, 5, 6,
                1, 2, 3
        }[num - 1];
    }

    static int[] keyToMove(int key) {
        return numToMove(keyMap(key));
    }

    static int moveToKey(int[] move) {
        return keyMap(moveToNum(move));
    }

    static int[] movesToKeys(int[][] moves) {
        int[] keys = new int[moves.length];
        for (int index = 0; index < moves.length; ++index) {
            keys[index] = moveToKey(moves[index]);
        }
        return keys;
    }

    static int[][] getMoves() {
        int[][] moves = new int[9][];
        int index = 0;
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 3; ++column) {
                moves[index++] = new int[] { row, column };
            }
        }
        return moves;
    }

    static int[][][] getLines() { // logical
        return new int[][][] {
                { { 0, 0 }, { 0, 1 }, { 0, 2 } }, { { 1, 0 }, { 1, 1 }, { 1, 2 } }, { { 2, 0 }, { 2, 1 }, { 2, 2 } },
                { { 0, 0 }, { 1, 0 }, { 2, 0 } }, { { 0, 1 }, { 1, 1 }, { 2, 1 } }, { { 0, 2 }, { 1, 2 }, { 2, 2 } },
                { { 0, 0 }, { 1, 1 }, { 2, 2 } }, { { 0, 2 }, { 1, 1 }, { 2, 0 } }
        };
    }

    static int getInput(String prompt, int[] inputs) {
        prompt += "\n" + Arrays.toString(inputs) + " => ";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            String scan = scanner.next();
            int input;
            try {
                input = Integer.parseInt(scan);
            } catch (NumberFormatException e) {
                continue;
            }
            if (arrayContains(inputs, input)) {
                return input;
            }
        }
    }
}
