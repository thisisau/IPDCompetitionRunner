package agents;

public class XX implements Agent {
    @Override
    public boolean makeMove(boolean[] yourMoves, boolean[] opponentMoves) {
        // Add code here...
        if(yourMoves.length==0){
            return false;
        }
        if(yourMoves[yourMoves.length-1]==true&&opponentMoves[opponentMoves.length-1]==true){
            return true;
        }
        if(yourMoves[yourMoves.length-1]==false&&opponentMoves[opponentMoves.length-1]==false){
            return false;
        } // Cooperate
        if(yourMoves[yourMoves.length-1]==false&&opponentMoves[opponentMoves.length-1]==true){
            return true;
        }
        if(yourMoves[yourMoves.length-1]==true&&opponentMoves[opponentMoves.length-1]==false){
            return false;
        }
        return false; // I added this line so the code compiles
    }


    @Override
    public String getAgentName() {
        return "X_X";
    }
}
