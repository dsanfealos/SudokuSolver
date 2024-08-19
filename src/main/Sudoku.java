package main;

import java.util.Arrays;

public class Sudoku {

    public Integer[][] matrix = new Integer[9][9];
    public Integer[][][] notes = new Integer[9][9][9];
    public Integer[][] block1 = new Integer[3][3];
    public Integer[][] block2 = new Integer[3][3];
    public Integer[][] block3 = new Integer[3][3];
    public Integer[][] block4 = new Integer[3][3];
    public Integer[][] block5 = new Integer[3][3];
    public Integer[][] block6 = new Integer[3][3];
    public Integer[][] block7 = new Integer[3][3];
    public Integer[][] block8 = new Integer[3][3];
    public Integer[][] block9 = new Integer[3][3];


    public Sudoku(Integer[][] matrix) {
        if(matrix.length != 9 && matrix[0].length != 9){
            System.out.println("This sudoku has not the correct size");
            return;
        }
        this.matrix = matrix;
        setNotes();
        setBlocks();
    }

    public void show(){
        if(matrix.length != 9 && matrix[0].length != 9){
            System.out.println("This sudoku has not the correct size");
            return;
        }
        int countLine = 0;
        for (Integer[] line:matrix){
            int countNumber = 0;
            countLine++;
            if (countLine == 1 || countLine == 4 || countLine == 7){
                System.out.println("+----+----+----+");
            }
            for (Integer number : line) {
                countNumber++;
                switch (countNumber) {
                    case 1, 4, 7 -> System.out.print("|" + number);
                    case 3, 6, 9 -> System.out.print(number + "|");
                    default -> System.out.print(number);
                }
            }
            System.out.println(" ");
        }

    }

    public boolean isRepeated(Integer line, Integer row, Integer guess){
        return isInBlock(line, row, guess) || isInRow(row, guess) || isInLine(line, guess);
    }

    public boolean isInBlock(Integer line, Integer row, Integer guess){
        Integer[][] block = getBlock(line,row);
        for (Integer[] blockLine : block){
            for (Integer number : blockLine){
                if (number == guess) return true;
            }
        }
        return false;
    }

    public boolean isInRow(Integer row, Integer guess){
        for (Integer[] integers : matrix) {
            if (integers[row] == guess) return true;
        }
        return false;
    }

    public boolean isInLine(Integer line, Integer guess){
        for(Integer number:matrix[line]){
            if (number == guess) return true;
        }
        return false;
    }
    public void setBlocks(){
        int countLine = 0;
        for (Integer[] line:this.matrix){
            int countNumber = 0;
            switch (countLine){
                case 0,1,2 -> {
                    for (Integer number:line){
                        switch (countNumber){
                            case 0,1,2 -> this.block1[countLine][countNumber] = number;
                            case 3,4,5 -> this.block2[countLine][countNumber-3] = number;
                            default -> this.block3[countLine][countNumber-6] = number;
                        }
                        countNumber++;
                    }
                }
                case 3,4,5 -> {
                    for (Integer number:line){
                        switch (countNumber){
                            case 0,1,2 -> this.block4[countLine-3][countNumber] = number;
                            case 3,4,5 -> this.block5[countLine-3][countNumber-3] = number;
                            default -> this.block6[countLine-3][countNumber-6] = number;
                        }
                        countNumber++;
                    }
                }
                case 6,7,8 -> {
                    for (Integer number:line){
                        switch (countNumber){
                            case 0,1,2 -> this.block7[countLine-6][countNumber] = number;
                            case 3,4,5 -> this.block8[countLine-6][countNumber-3] = number;
                            default -> this.block9[countLine-6][countNumber-6] = number;
                        }
                        countNumber++;
                    }
                }
            }
            countLine++;
        }
    }

    public Integer[][] getBlock(Integer line, Integer row){
        Integer[][] block;
        switch(line){
            case 0,1,2 ->{
                switch (row){
                    case 0,1,2 -> block = this.block1;
                    case 3,4,5 -> block = this.block2;
                    default -> block = this.block3;
                }
            }
            case 3,4,5 -> {
                switch (row){
                    case 0,1,2 -> block = this.block4;
                    case 3,4,5 -> block = this.block5;
                    default -> block = this.block6;
                }
            }
            default -> {
                switch (row){
                    case 0,1,2 -> block = this.block7;
                    case 3,4,5 -> block = this.block8;
                    default -> block = this.block9;
                }
            }
        }
        return block;
    }

    public void setNotes(){
        int countLine = 0;
        for (Integer[] line: this.matrix){
            int countRow = 0;
            for (Integer number: line){
                notes[countLine][countRow][0] = number;
                for (int i = 0; i < 9; i++){
                    notes[countLine][countRow][i] = 0;
                }
                countRow++;
            }
            countLine++;
        }
    }

    public Integer[][] solveA(){
        int countLine = 0;
        for (Integer[] line : this.matrix) {
            int countRow = 0;
            for (Integer number : line) {
                if (number == 0) {
                    possibleIndividualSolutions(countLine, countRow);
                }
                countRow++;
            }
            countLine++;
        }
        return this.matrix;
    }

    public Integer[][] solveB(){
        //Equals Search
        for (int guess = 1; guess<10; guess++){
            Integer[] bannedLines = new Integer[9];
            Integer[] bannedRows = new Integer[9];
            int bannedCounter = 0;
            int countLineA = 0;
            for (Integer[] line: this.matrix){
                int countRowA = 0;
                for (Integer number: line){
                    if (number == guess){
                        bannedLines[bannedCounter] = countLineA;
                        bannedRows[bannedCounter] = countRowA;
                    }
                    if (number == 0) {
                        //Solve checking block, line, row
                        int finalCountLineA = countLineA;
                        int finalCountRowA = countRowA;
                        if (!isRepeated(countLineA, countRowA, guess) &&
                                Arrays.stream(bannedLines).noneMatch(x -> x == finalCountLineA) &&
                                Arrays.stream(bannedRows).noneMatch(x -> x == finalCountRowA)){


                        }
                        //Solve checking equals???

                    }
                    countRowA++;
                }
                countLineA++;
            }
        }

        //Repeated
        int countLine = 0;
        for (Integer[] line : this.matrix) {
            int countRow = 0;
            for (Integer number : line) {
                if (number == 0) {
                    //Solve checking block, line, row
                    for (int guess = 1; guess<10; guess++){
                        if (!isRepeated(countLine, countRow, guess)){

                        }
                        //Solve checking equals???


                    }
                }
                countRow++;
            }
            countLine++;
        }

        return this.matrix;
    }

    public Integer[][] solveC(){
        //Algorithm:
        //Equals -> Lines/Rows -> Blocks -> Crossed
        //If 60% tiles != 0 -> Notes
        //If blocked, restart

        return null;
    }

    public void possibleIndividualSolutions(Integer line, Integer row){
        for (int number = 1; number <= 9; number++){
            if (!isRepeated(line, row, number)){
                for (int i = 0; i < 9; i++){
                    if (notes[line][row][i] == 0){
                        this.notes[line][row][i] = number;
                        break;
                    }
                }
            }
        }
    }

    public boolean solveGuess(Integer line, Integer row){
        int counterOptions = 0;
        int solution = 0;
        boolean solved = false;
        for (Integer option:notes[line][row]){
            if (option != 0) {
                solution = option;
                counterOptions++;
            }
        }
        if (counterOptions == 1) {
            this.matrix[line][row] = solution;
            setBlocks();
            setNotes();
            solved = true;
        }
        return solved;
    }

}
