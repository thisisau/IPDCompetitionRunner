package agents;

public class Detective implements Agent {
    @Override
    public boolean makeMove(boolean[] yourMoves, boolean[] opponentMoves) {
        int opType = 0;
        if(yourMoves.length == 0){return false;}
        if(yourMoves.length == 1){return false;}
        if(yourMoves.length == 2){return true;}
        if(yourMoves.length == 3){return true;}
        if(yourMoves.length == 4){
            if(opponentMoves[2] == false && opponentMoves[3] == true){
                opType = 1;
            }else if(opponentMoves[1] == true && opponentMoves[2] == false && opponentMoves[3] == true){
                opType = 2;
            }else if(opponentMoves[0] == false && opponentMoves[1] == false && opponentMoves[3] == false){
                opType = 3;
            }else if(opponentMoves[0] == true && opponentMoves[1] == true && opponentMoves[3] == true){
                opType = 4;
            }else if(opponentMoves[2] == opponentMoves[3] && opponentMoves[1] != opponentMoves[0]){
                opType = 5;
            }
        }
        if(yourMoves.length >= 4){
            if(opType == 1){
                return true;
            }else if(opType == 2){
                if((opponentMoves.length % 2) == 0){
                    return true;
                }else{
                    return false;
                }
            }else if(opType == 3){
                return false;
            }else if(opType == 4){
                return false;
            }else if(opType == 5){
                if(opponentMoves[(opponentMoves.length-1)] == false && yourMoves[(yourMoves.length-1)] == true){
                    return false;
                }else{
                    return true;
                }
            }
        }
        return opponentMoves[(opponentMoves.length-1)];
    }

    @Override
    public String getAgentName() {
        return "Detective";
    }
}
