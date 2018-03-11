package obuda.nik.DoubleTranspositionCipher.domain.DTO;

import obuda.nik.DoubleTranspositionCipher.domain.enums.TranspositionType;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class MessageDTO {
    @NotNull
    private String message;
    @NotNull
    private Set<String> keys;
    @NotNull
    private Set<TranspositionType> transpositions;
    private String encodedMessage;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<String> getKeys() {
        return keys;
    }

    public void setKeys(Set<String> keys) {
        this.keys = keys;
    }

    public Set<TranspositionType> getTranspositions() {
        return transpositions;
    }

    public void setTranspositions(Set<TranspositionType> transpositions) {
        this.transpositions = transpositions;
    }

    public String getEncodedMessage() {
        return encodedMessage;
    }

    public void setEncodedMessage(String encodedMessage) {
        this.encodedMessage = encodedMessage;
    }
}
