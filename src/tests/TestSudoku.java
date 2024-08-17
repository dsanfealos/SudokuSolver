import main.Sudoku;
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

    }
}
