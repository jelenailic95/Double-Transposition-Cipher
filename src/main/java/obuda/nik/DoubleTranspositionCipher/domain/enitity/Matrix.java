package obuda.nik.DoubleTranspositionCipher.domain.enitity;

import java.util.ArrayList;

public class Matrix {
    private ArrayList<char[]> columns;
    private int rowNum;
    private int columnNum;
    private int cellNum;

    public int getCellNum() {
        return cellNum;
    }

    public void setCellNum(int cellNum) {
        this.cellNum = cellNum;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }

    public char[] getColumn(int index) {
        return this.columns.get(index);
    }

    public void setColumn(char[] column) {
        this.columns.add(column);
    }

    public void setColumnOnGivenPosition(int i, char[] column) {
        this.columns.set(i, column);
    }

    public Matrix() {
        this.columns = new ArrayList<>();
    }

    public Matrix(ArrayList<char[]> columns, int rowsNum, int columnsNum) {
        this.columns = columns;
        this.rowNum = rowsNum;
        this.columnNum = columnsNum;
    }
}
