package runner;

import agents.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Runner {
    private Agent[] agents;
    private ArrayList<Result> games = new ArrayList<>();
    final int GAME_LENGTH = 200;

    private boolean[][] simulateGame(int p1Index, int p2Index) {
        Agent p1 = agents[p1Index];
        Agent p2 = agents[p2Index];

        ArrayList<Boolean> p1Moves = new ArrayList<>();
        ArrayList<Boolean> p2Moves = new ArrayList<>();

        for (int i = 0; i < GAME_LENGTH; i++) {
            boolean[] p1MovesArray = new boolean[i];
            boolean[] p2MovesArray = new boolean[i];

            for (int j = 0; j < i; j++) {
                p1MovesArray[j] = p1Moves.get(j);
                p2MovesArray[j] = p2Moves.get(j);
            }

            p1Moves.add(p1.makeMove(p1MovesArray, p2MovesArray));
            p2Moves.add(p2.makeMove(p2MovesArray, p1MovesArray));
        }


        boolean[][] allMoves = new boolean[2][GAME_LENGTH];

        for (int i = 0; i < GAME_LENGTH; i++) {
            allMoves[0][i] = p1Moves.get(i);
            allMoves[1][i] = p2Moves.get(i);
        }

        return allMoves;
    }

    public void run() {
        for (int p1 = 0; p1 < agents.length; p1++) {
            for (int p2 = 0; p2 <= p1; p2++) {
                games.add(new Result(p1, p2, simulateGame(p1, p2)));
            }
        }

        try {
            PrintWriter writer = new PrintWriter("./results.csv", StandardCharsets.UTF_8);
            for (Result r : games) {
                StringBuilder gameText = new StringBuilder();
                var game = r.game();
                int[] score = calculateScore(game[0], game[1]);
                gameText.append("Player,Total Score,");
                for (int i = 0; i < GAME_LENGTH; i++) gameText.append(i+1).append(",");
                gameText.append("\n");
                gameText.append(agents[r.p1()].getAgentName()).append(",").append(score[0]).append(",");
                for (int i = 0; i < GAME_LENGTH; i++) gameText.append(game[0][i] ? "C" : "D").append(",");
                gameText.append("\n");
                gameText.append(agents[r.p2()].getAgentName()).append(",").append(score[1]).append(",");
                for (int i = 0; i < GAME_LENGTH; i++) gameText.append(game[1][i] ? "C" : "D").append(",");
                gameText.append("\n");
                writer.println(gameText);
            }
            writer.close();

            writer = new PrintWriter("./leaderboard.csv", StandardCharsets.UTF_8);
            writer.print(",Average,");
            for (Agent a : agents) {
                writer.print(a.getAgentName());
                writer.print(",");
            }
            writer.println();
            for (int i = 0; i < agents.length; i++) {
                StringBuilder gameText = new StringBuilder();
                gameText.append(agents[i].getAgentName()).append(",");
                int[] scores = new int[agents.length];
                for (int j = 0; j < agents.length; j++) {
                    for (Result r : games) {
                        if (r.p1() == i && r.p2() == j) {
                            scores[j] = calculateScore(r.game())[0];
                        }
                        else if (r.p2() == i && r.p1() == j) {
                            scores[j] = calculateScore(r.game())[1];
                        }
                    }
                }

                double averageScore = 0;
                for (int s : scores) averageScore += s;
                averageScore /= scores.length;
                gameText.append(averageScore).append(",");
                for (int s : scores) gameText.append(s).append(",");
                gameText.append("\n");
                writer.print(gameText);
            }
            writer.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] calculateScore(boolean[]... players) {
        return calculateScore(players[0], players[1]);
    }

    public static int[] calculateScore(boolean[] p1, boolean[] p2) {
        int[] points = {0, 0};
        for (int i = 0; i < p1.length; i++) {
            boolean p1Move = p1[i];
            boolean p2Move = p2[i];
            if (p1Move && p2Move) {
                points[0] += 3;
                points[1] += 3;
            }
            else if (p1Move) {
                points[1] += 5;
            }
            else if (p2Move) {
                points[0] += 5;
            }
            else {
                points[0] += 1;
                points[1] += 1;
            }
        }
        return points;
    }

    public Runner(Agent... agents) {
        this.agents = agents;
    }
}
