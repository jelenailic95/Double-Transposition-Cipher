package obuda.nik.DoubleTranspositionCipher.domain.enitity;

import obuda.nik.DoubleTranspositionCipher.domain.enums.TranspositionType;

import java.util.List;

public class Message {
    private String message;
    private List<String> keys;
    private List<TranspositionType> transpositions;
    private String encodedMessage;

    public Message() {
    }

    public Message(String message, List<String> keys, List<TranspositionType> transpositions, String encodedMessage) {
        this.message = message;
        this.keys = keys;
        this.transpositions = transpositions;
        this.encodedMessage = encodedMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public List<TranspositionType> getTranspositions() {
        return transpositions;
    }

    public void setTranspositions(List<TranspositionType> transpositions) {
        this.transpositions = transpositions;
    }

    public String getEncodedMessage() {
        return encodedMessage;
    }

    public void setEncodedMessage(String encodedMessage) {
        this.encodedMessage = encodedMessage;
    }

    public String getKey(int i) {
        return keys.get(i);
    }

    public TranspositionType getTransposition(int i) {
        return transpositions.get(i);
    }

}
