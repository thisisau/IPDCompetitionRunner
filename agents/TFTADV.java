package agents;

public class TFTADV implements Agent {
    @Override
    public boolean makeMove(boolean[] yourMoves, boolean[] opponentMoves) {
        int totalMoves = opponentMoves.length;

        // If it's the first few moves, cooperate to establish trust
        if (totalMoves < 5) {
            return true;
        }

        // Count opponent's defections and cooperations
        int defections = 0;
        for (boolean move : opponentMoves) {
            if (!move) defections++;
        }

        // Calculate the opponent's defection rate
        double defectionRate = (double) defections / totalMoves;

        // Identify if in the late game phase
        int remainingMoves = Math.max(200, 150) - totalMoves;
        boolean isLateGame = remainingMoves <= 20;

        // Tit-for-Tat: Mimic opponent's last move
        boolean lastOpponentMove = opponentMoves[totalMoves - 1];

        // Forgiveness strategy: Occasionally cooperate after defection
        boolean forgive = Math.random() < 0.1; // 10% chance to forgive

        // Decision-making process
        if (isLateGame) {
            // Late game: defect more aggressively if opponent defected often
            if (defectionRate > 0.4 || Math.random() < 0.2) {
                return false;
            }
        } else {
            // Early/Mid game strategy
            if (defectionRate > 0.6) {
                return false; // Defect against highly exploitative opponents
            }
            if (!lastOpponentMove && forgive) {
                return true; // Forgive occasional defections
            }
        }

        // Default: mimic opponent's last move
        return lastOpponentMove;
    }

    @Override
    public String getAgentName() {
        return "titForTat_ADVANCED";
    }
}
