package obuda.nik.DoubleTranspositionCipher.domain.enitity;

import java.util.ArrayList;

public class Matrix {
    private ArrayList<char[]> columns;
    private int keyLength;

    public int getKeyLength() {
        return keyLength;
    }

    public void setKeyLength(int keyLength) {
        this.keyLength = keyLength;
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

    public Matrix(ArrayList<char[]> columns, int keyLength) {
        this.columns = columns;
        this.keyLength = keyLength;
    }
}
