package obuda.nik.DoubleTranspositionCipher.service;

import obuda.nik.DoubleTranspositionCipher.domain.enitity.Message;
import org.springframework.stereotype.Service;

/**
 * <h1>Message Interface</h1>
 * Message Interface has a method that encrypts given message
 * and method that decrypts given message with the double
 * transposition cipher.
 * <p>
 *
 * @author Marija Kovacevic
 * @version 1.0
 */
@Service
public interface MessageService {
    /**
     * This method encrypts given message.
     *
     * @param message Contains message, keys and type of transpositions.
     * @return Message with encrypted message.
     */
    Message encodeMessage(Message message);

    /**
     * This method decrypts given message.
     *
     * @param message Contains message, keys and type of transpositions.
     * @return Message with decrypted message.
     */
    Message decodeMessage(Message message);

}
