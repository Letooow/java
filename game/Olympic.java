package game;

import java.util.LinkedHashMap;
import java.util.Random;

public class Olympic {
    private final Player[] players;
    private final boolean log;

    public Olympic(boolean log, Player[] players) {
        this.players = players;
        this.log = log;
    }

    public int play(Board board, boolean showStats) {
        LinkedHashMap<Integer, Boolean> playersInTournament = new LinkedHashMap<>();
        int circle = 0;
        int logarifm = ((int) Math.ceil(Math.log(players.length) / Math.log(2))) + 1;
        StringBuilder builder = new StringBuilder();
        int[] indexPlayers = new int[players.length];
        for (int i = 0; i < players.length; i++) indexPlayers[i] = i;
        Random random = new Random();

        while (indexPlayers.length > 1) {
            int[] winners = new int[(int) Math.ceil((double) indexPlayers.length / 2)];
            int indexOfWinners = 0;
            if (indexPlayers.length % 2 != 0) {
                int player = indexPlayers[random.nextInt(indexPlayers.length)];
                player = getPlayer(playersInTournament, player, indexPlayers, random);
                winners[indexOfWinners++] = player;
                playersInTournament.put(player, true);
            }
            while (indexOfWinners < (int) Math.ceil((double) indexPlayers.length / 2)) {

                int player1 = indexPlayers[random.nextInt(indexPlayers.length)];
                player1 = getPlayer(playersInTournament, player1, indexPlayers, random);

                int player2 = indexPlayers[random.nextInt(indexPlayers.length)];
                player2 = getPlayer(playersInTournament, player2, indexPlayers, random);

                Game game = new Game(false, players[player1], players[player2]);
                int result = 0;
                while (result == 0) {
                    board = board.reset();
                    result = game.play(board);
                    if (result == 1) {

                        win(player1, player2, logarifm, circle);

                        builder.append("Player ").append(player2 + 1);
                        builder.append(" || Position: ").append(logarifm - circle).append(System.lineSeparator());

                        winners[indexOfWinners++] = player1;
                        playersInTournament.put(player1, true);

                    } else if (result == 2) {

                        win(player2, player1, logarifm, circle);

                        builder.append("Player ").append(player1 + 1);
                        builder.append(" || Position: ").append(logarifm - circle).append(System.lineSeparator());

                        winners[indexOfWinners++] = player2;
                        playersInTournament.put(player2, true);

                    }
                }
            }
            builder.append("---------------------").append(System.lineSeparator());
            indexPlayers = winners;
            playersInTournament.clear();
            circle++;
        }
        log("Player " + (indexPlayers[0] + 1) + " win!");
        log("");
        builder.append("Player ").append(indexPlayers[0] + 1).append(" win!").append(System.lineSeparator());
        if (showStats) {
            System.out.println("-------RESULTS-------");
            System.out.println(builder);
        }
        return indexPlayers[0];
    }

    private int getPlayer(LinkedHashMap<Integer, Boolean> playersInTournament, int r1, int[] indexPLayers, Random random) {
        while (true) {
            if (playersInTournament.containsKey(r1)) r1 = indexPLayers[random.nextInt(indexPLayers.length)];
            else {
                playersInTournament.put(r1, false);
                break;
            }
        }
        return r1;
    }

    public void log(String massage) {
        if (log) {
            System.out.println(massage);
        }
    }
    private void win(int play1, int play2, int log, int circle) {
        log("Player " + (play1 + 1) + " win " + (play2 + 1) + " || Position: " + (log- circle));
    }
}