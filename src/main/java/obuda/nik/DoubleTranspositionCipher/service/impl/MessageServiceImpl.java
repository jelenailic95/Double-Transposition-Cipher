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
        List<Integer> positions1 = sortKeyAlphabetically(message.getKey(0));
        List<Integer> positions2 = sortKeyAlphabetically(message.getKey(1));
        String firstCipher;

        if (message.getTransposition(0) == TranspositionType.ROW) {
            firstCipher = encryptByRow(message.getMessage(), positions1);
        } else {
            firstCipher = encryptByColumn(message.getMessage(), positions1);
        }

        if (message.getTransposition(1) == TranspositionType.ROW) {
            message.setEncodedMessage(encryptByRow(firstCipher, positions2));
        } else {
            message.setEncodedMessage(encryptByColumn(firstCipher, positions2));
        }

        return message;
    }

    private List<Integer> sortKeyAlphabetically(String key) {
        char[] givenKey = key.toCharArray();
        char[] sortedLetters = key.toCharArray();

        Arrays.sort(sortedLetters);

        ArrayList<Character> stringList = new ArrayList<>();
        for (char letter : sortedLetters) {
            stringList.add(letter);
        }

        List<Integer> positions = new ArrayList<>();
        for (char letter : givenKey) {
            positions.add(stringList.indexOf(letter));
        }
        return positions;
    }

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

    private static char[] getColumn(char[][] array, int index, int size) {
        char[] column = new char[size];
        for (int i = 0; i < column.length; i++) {
            column[i] = array[i][index];
        }
        return column;
    }

    private Matrix rearange(Matrix m, List<Integer> positions, Matrix nova) {
        for (int i = 0; i < m.getKeyLength(); i++) {
            int position = positions.get(i);
            char[] col = m.getColumn(i);
            nova.setColumnOnGivenPosition(position, col);
        }
        return nova;
    }

    private String encryptByColumn(String message, List<Integer> positions) {
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

    private String encryptByRow(String message, List<Integer> positions) {
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

    @Override
    public Message decodeMessage(Message message) {
        return message;
    }
}
