package com.sudokusolver;

public class Main {


    private static final int gridSize = 9;
    public static void main(String[] args) {
        int[][] board = {{0,0,3,5,6,0,0,0,7},
                {0,0,7,0,4,2,0,0,5},
                {0,0,4,0,9,7,6,0,3},
                {4,0,0,0,0,5,1,0,2},
                {0,6,0,0,3,4,0,0,0},
                {0,3,8,2,1,0,0,0,4},
                {0,0,0,9,0,6,8,7,0},
                {7,0,6,0,0,1,0,4,0},
                {0,9,1,4,7,0,0,5,0}};

        System.out.println("******Before Solving******");
        printBoard(board);

        if(solveBoard(board)){
            System.out.println();
            System.out.println("Solved Sucessfully");
            System.out.println();
            System.out.println("******After Solving******");
        }else{
            System.out.println("Sudoku not solvable");
        }

        printBoard(board);

        }

    static void printBoard(int[][] board){
        for (int i=0; i<gridSize; i++){
            if(i%3==0 && i!=0){
                System.out.println("--------------");
            }
            for (int j=0; j<gridSize; j++){
                if(j%3==0 && j!=0){
                    System.out.print("|");
                }
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    static boolean numberinRow(int[][] board, int number, int row){
        for (int i=0; i<gridSize; i++){
            if(board[row][i]== number){
                return true;
            }
        }
        return false;
    }

    static boolean numberinCol(int[][] board, int number, int col){
        for (int i=0; i<gridSize; i++){
            if(board[i][col]== number){
                return true;
            }
        }
        return false;
    }

    static boolean numberinBox(int[][] board, int number, int row, int col){
        int localRow = row - row % 3;
        int localCol = col - col % 3;
        for (int i=localRow; i<localRow+3; i++){
            for (int j=localCol; j<localCol+3; j++){
                if (board[i][j] == number){
                    return true;
                }
            }
        }
        return false;
    }

    static boolean validPlacement(int[][] board, int number, int row, int col){
        return (!numberinRow(board,number,row)&&
            !numberinCol(board,number,col)&&
            !numberinBox(board,number,row,col));
    }

    static boolean solveBoard(int[][] board){
        for (int row=0; row<gridSize; row++){
            for (int col=0; col<gridSize; col++){
                if(board[row][col]==0){
                    for (int numtoTry=1; numtoTry<=gridSize; numtoTry++){
                        if(validPlacement(board, numtoTry, row, col)){
                            board[row][col] = numtoTry;
                            if(solveBoard(board)){
                                return true;
                            }else{
                                board[row][col]=0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
