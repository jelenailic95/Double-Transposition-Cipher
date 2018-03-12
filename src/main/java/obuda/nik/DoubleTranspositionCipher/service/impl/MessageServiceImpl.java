package obuda.nik.DoubleTranspositionCipher.service.impl;

import obuda.nik.DoubleTranspositionCipher.domain.enitity.Matrix;
import obuda.nik.DoubleTranspositionCipher.domain.enitity.Message;
import obuda.nik.DoubleTranspositionCipher.domain.enums.TranspositionType;
import obuda.nik.DoubleTranspositionCipher.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Override
    public Message encodeMessage(Message message) {

        // sort keys and get indexes for rearanging rows/columns
        List<Integer> positions1 = getEncryptKeyPositions(message.getKey(0));
        List<Integer> positions2 = getEncryptKeyPositions(message.getKey(1));

        String firstCipher;   // result of the first transposition

        // first transposition
        if (message.getTransposition(0) == TranspositionType.ROW) {
            firstCipher = transpositionByRow(message.getMessage(), positions1);
        } else {
            firstCipher = transpositionByColumn(message.getMessage(), positions1);
        }

        // second transposition
        if (message.getTransposition(1) == TranspositionType.ROW) {
            message.setEncodedMessage(transpositionByRow(firstCipher, positions2));
        } else {
            message.setEncodedMessage(transpositionByColumn(firstCipher, positions2));
        }

        return message;
    }


    @Override
    public Message decodeMessage(Message message) {
        List<Integer> positions1 = getDecryptKeyPositions(message.getKey(0));
        List<Integer> positions2 = getDecryptKeyPositions(message.getKey(1));
        String firstCipher;

        if (message.getTransposition(0) == TranspositionType.ROW) {
            firstCipher = transpositionByRow(message.getMessage(), positions1);
        } else {
            firstCipher = transpositionByColumn(message.getMessage(), positions1);
        }

        if (message.getTransposition(1) == TranspositionType.ROW) {
            message.setEncodedMessage(transpositionByRow(firstCipher, positions2));
        } else {
            message.setEncodedMessage(transpositionByColumn(firstCipher, positions2));
        }

        return message;
    }

    /**
     * This method sorts letters alphabetically and returns list of the letters positions in the sorted list.
     * This method is used for encrypting.
     *
     * @param key Given key.
     * @return List with positions of the letters in the sorted list.
     */
    private List<Integer> getEncryptKeyPositions(String key) {
        char[] givenKey = key.toCharArray();
        char[] sortedLetters = key.toCharArray();

        Arrays.sort(sortedLetters);    // sort letters alphabetically
        return getPositions(sortedLetters, givenKey);
    }

    private List<Integer> getPositions(char[] first, char[] sec) {
        ArrayList<Character> sortedList = new ArrayList<>();
        List<Integer> positions = new ArrayList<>();

        // convert [] into array list, because it will be possible to use indexOf
        for (char letter : first) {
            sortedList.add(letter);
        }

        // get positions of the letters in the sorted list
        for (char letter : sec) {
            positions.add(sortedList.indexOf(letter));
        }
        return positions;
    }


    /**
     * This method returns list of the letters positions before sorting them alphabetically.
     * This method is used for decrypting.
     *
     * @param key Given key.
     * @return List with positions of the letters before sorting them alphabetically.
     */
    private List<Integer> getDecryptKeyPositions(String key) {
        char[] givenKey = key.toCharArray();
        char[] sortedLetters = key.toCharArray();

        Arrays.sort(sortedLetters);     // sort letters alphabetically
        return getPositions(givenKey, sortedLetters);
    }

    /**
     * This method adds char 'X' at the end of the given message.
     * This method is called when there are missing letters for the regular matrix.
     *
     * @param mess Given message.
     * @param n    Number of letters that we need to add.
     * @return String with added 'X' at the end.
     */
    private String repeatNTimes(String mess, int n) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(mess);
        for (int i = 0; i < n; i++) {
            buffer.append('X');
        }
        return buffer.toString();
    }

    private static char[][] createFirstMatrix(String message, int rows, int cols) {
        char[][] matrica = new char[rows][cols];
        int k = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrica[i][j] = message.charAt(k);
                System.out.print(matrica[i][j]);
                k++;
            }
            System.out.println('\n');
        }
        return matrica;
    }

    /**
     * This method adds char 'X' at the end of the given message.
     * This method used for column transposition.
     *
     * @param array Initial matrix.
     * @param index Position of the column in the matrix.
     * @param size  Size of the matrix.
     * @return column from the matrix.
     */
    private static char[] getColumn(char[][] array, int index, int size) {
        char[] column = new char[size];     // create empty column
        for (int i = 0; i < column.length; i++) {
            column[i] = array[i][index];
        }
        return column;
    }

    /**
     * This method adds char 'X' at the end of the given message.
     * This method used for column transposition.
     *
     * @param m         Initial matrix.
     * @param positions The new order of the columns/rows.
     * @param newMatrix New matrix with rearanged positions of the columns/rows.
     * @return New matrix with the rearanged positions of the columns/rows.
     */
    private Matrix rearange(Matrix m, List<Integer> positions, Matrix newMatrix) {
        for (int i = 0; i < m.getKeyLength(); i++) {
            int position = positions.get(i);
            char[] col = m.getColumn(i);
            newMatrix.setColumnOnGivenPosition(position, col);
        }
        return newMatrix;
    }

    /**
     * This method does transposition by columns.
     *
     * @param message   Given message for encrypting/decrypting.
     * @param positions The new order of the columns/rows based on the key.
     * @return Encrypted/decrypted message.
     */
    private String transpositionByColumn(String message, List<Integer> positions) {
        Matrix m = new Matrix();
        Matrix nova = new Matrix();

        int keyLength = positions.size();

        int rows = (int) Math.ceil((double) message.length() / (double) keyLength);

        m.setKeyLength(keyLength);

        int diff = keyLength * rows - message.length();
        if (diff > 0) {
            message = repeatNTimes(message, diff);
        }

        char[][] matrica = createFirstMatrix(message, rows, keyLength);

        // set columns
        for (int i = 0; i < m.getKeyLength(); i++) {
            char[] col = getColumn(matrica, i, rows);
            m.setColumn(col);
            nova.setColumn(col);
        }

        nova = rearange(m, positions, nova);

        StringBuilder output1 = new StringBuilder();
        // print final
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < m.getKeyLength(); j++) {
                System.out.print(nova.getColumn(j)[i]);
                output1.append(nova.getColumn(j)[i]);

            }
            System.out.println("\n");
        }
        System.out.println(output1.toString());
        return output1.toString().toUpperCase();

    }

    /**
     * This method does transposition by rows.
     *
     * @param message   Given message for encrypting/decrypting.
     * @param positions The new order of the columns/rows based on the key.
     * @return Encrypted/decrypted message.
     */
    private String transpositionByRow(String message, List<Integer> positions) {
        Matrix m = new Matrix();
        Matrix nova = new Matrix();

        int keyLength = positions.size();
        int cols = (int) Math.ceil((double) message.length() / (double) positions.size());

        m.setKeyLength(keyLength);

        int diff = positions.size() * cols - message.length();
        if (diff > 0) {
            message = repeatNTimes(message, diff);
        }

        char[][] matrica = createFirstMatrix(message, keyLength, cols);   /// proveri kako popunjavanje ide
        // set columns
        for (int i = 0; i < keyLength; i++) {
            m.setColumn(matrica[i]);
            nova.setColumn(matrica[i]);
        }

        nova = rearange(m, positions, nova);

        StringBuilder output = new StringBuilder();
        // print final
        for (int i = 0; i < keyLength; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(nova.getColumn(i)[j]);
                output.append(nova.getColumn(i)[j]);
            }
            System.out.println("\n");
        }
        System.out.println(output.toString());
        return output.toString().toUpperCase();
    }
}
