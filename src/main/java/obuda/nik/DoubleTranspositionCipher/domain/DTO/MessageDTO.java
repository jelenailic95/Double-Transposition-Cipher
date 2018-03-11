package obuda.nik.DoubleTranspositionCipher.domain.DTO;

import obuda.nik.DoubleTranspositionCipher.domain.enums.TranspositionType;

import javax.validation.constraints.NotNull;
import java.util.List;

public class MessageDTO {
    @NotNull
    private String message;
    @NotNull
    private List<String> keys;
    @NotNull
    private List<TranspositionType> transpositions;
    private String encodedMessage;

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
}
