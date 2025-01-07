package agents;

public interface Agent {
    boolean makeMove(boolean[] yourMoves, boolean[] opponentMoves);
    String getAgentName();
}
