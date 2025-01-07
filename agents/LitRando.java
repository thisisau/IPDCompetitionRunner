package agents;

public class LitRando implements Agent {
    @Override
    public boolean makeMove(boolean[] yourMoves, boolean[] opponentMoves) {
        // Add code here...
        return Math.round(Math.random()) == 0; // Cooperate
    }

    @Override
    public String getAgentName() {
        return "Literal Rando";
    }
}
