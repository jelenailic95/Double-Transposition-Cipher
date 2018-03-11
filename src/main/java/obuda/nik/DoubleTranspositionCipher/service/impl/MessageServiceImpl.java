package obuda.nik.DoubleTranspositionCipher.service.impl;

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
            firstCipher = encryptByColumn(message.getMessage(), positions1);
        } else {
            firstCipher = encryptByRow(message.getMessage(), positions1);
        }

        if (message.getTransposition(1) == TranspositionType.ROW) {
            message.setEncodedMessage(encryptByColumn(firstCipher, positions2));
        } else {
            message.setEncodedMessage(encryptByColumn(firstCipher, positions2));
        }

        return message;
    }

    private List<Integer> sortKeyAlphabetically(String key) {
        char[] sortmap = key.toCharArray();
        char[] wordmap = key.toCharArray();

        Arrays.sort(sortmap);

        ArrayList<Character> stringList = new ArrayList<>();
        for (char letter : sortmap) {
            stringList.add(letter);
        }

        List<Integer> positions = new ArrayList<>();
        for (char letter : wordmap) {
            positions.add(stringList.indexOf(letter));
        }
        return positions;
    }

    private String encryptByColumn(String message, List<Integer> positions) {
        return "";
    }

    private String encryptByRow(String message, List<Integer> positions) {
        return "";
    }

    @Override
    public Message decodeMessage(Message message) {
        return message;
    }
}
