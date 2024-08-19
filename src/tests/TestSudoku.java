import main.Sudoku;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestSudoku {
    Sudoku sudoku;

    @BeforeEach
    public void start(){
        sudoku = new Sudoku(new Integer[][]{
                {4,0,1,7,9,0,5,0,0},
                {0,0,0,0,0,6,9,0,0},
                {0,9,0,0,3,1,0,0,8},
                {2,1,0,0,6,4,0,0,0},
                {0,0,0,1,0,2,4,0,3},
                {0,8,4,0,0,0,1,0,0},
                {1,3,2,8,5,9,0,0,0},
                {9,0,0,0,0,0,0,0,0},
                {8,0,0,0,0,0,0,5,9}});

    }

    @Test
    public void test_isRepeated(){
        assertTrue(sudoku.isRepeated(3, 1, 6));
        assertFalse(sudoku.isRepeated(1, 1, 7));
    }

    @Test
    public void test_getBlock(){
        assertEquals(sudoku.block1, sudoku.getBlock(2,0));
        assertEquals(sudoku.block7, sudoku.getBlock(7, 2));
        assertEquals(sudoku.block8, sudoku.getBlock(6,3));
        assertNotEquals(sudoku.block5, sudoku.getBlock(8,8));
    }

    @Test
    public void test_possibleIndividualSolutions(){
        sudoku.possibleIndividualSolutions(6,8);
        assertEquals(4, sudoku.notes[6][8][0]);
        assertEquals(6, sudoku.notes[6][8][1]);
        assertEquals(7, sudoku.notes[6][8][2]);
    }

    @Test
    public void test_solveGuess(){
        Integer[][] solved = {
                {4,2,1,7,9,8,5,3,6},
                {3,7,8,5,2,6,9,1,4},
                {5,9,6,4,3,1,7,2,8},
                {2,1,3,9,6,4,8,7,5},
                {7,5,9,1,8,2,4,6,3},
                {6,8,4,3,7,5,1,9,2},
                {1,3,2,8,5,9,6,4,7},
                {9,6,5,2,4,7,3,8,1},
                {8,4,7,6,1,3,2,5,9}
        };
        Assertions.assertEquals(solved, sudoku.solveB());
    }
}
