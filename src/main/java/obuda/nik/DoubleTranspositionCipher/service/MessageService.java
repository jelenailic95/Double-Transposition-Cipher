package obuda.nik.DoubleTranspositionCipher.service;

import obuda.nik.DoubleTranspositionCipher.domain.enitity.Message;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {
    Message encodeMessage(Message message);
    Message decodeMessage(Message message);

}
