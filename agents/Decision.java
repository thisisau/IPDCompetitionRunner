package agents;

public class Decision implements Agent {
    @Override
    public boolean makeMove(boolean[] yourMoves, boolean[] opponentMoves) {
        //counting co-ops and defects
        int oppDefects = 0, oppCoops = 0;
        for (int i = 0; i < opponentMoves.length; i++) {
            if (opponentMoves[i]) oppCoops++;
            else oppDefects++;
        }

        if (oppCoops > oppDefects) return true;
        else return false;
    }

    @Override
    public String getAgentName() {
        return "Prisoner's Decision";
    }
}
