package main;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //Debo crear un objeto
        //Añadir 3 reglas (no repetirse en bloque, fila, columna)
        //Empezar por función mostrar
        //Input y output como matriz 9x9
        //Identificar bloques/filas/columnas como tal
        //una casilla vacía se idenifica como 0

        Integer[][] start = {
                {4,0,1,7,9,0,5,0,0},
                {0,0,0,0,0,6,9,0,0},
                {0,9,0,0,3,1,0,0,8},
                {2,1,0,0,6,4,0,0,0},
                {0,0,0,1,0,2,4,0,3},
                {0,8,4,0,0,0,1,0,0},
                {1,3,2,8,5,9,0,0,0},
                {9,0,0,0,0,0,0,0,0},
                {8,0,0,0,0,0,0,5,9}};


        Sudoku sudoku = new Sudoku(start);
        sudoku.show();
        System.out.println(Arrays.toString(sudoku.block5[1]));
        System.out.println("------------");
        System.out.println(sudoku.isRepeated(1,1,7));

    }

}