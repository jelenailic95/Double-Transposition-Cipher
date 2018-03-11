package obuda.nik.DoubleTranspositionCipher.service.impl;

import obuda.nik.DoubleTranspositionCipher.domain.enitity.Message;
import obuda.nik.DoubleTranspositionCipher.service.MessageService;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Override
    public Message encodeMessage(Message message) {
        return message;
    }

    @Override
    public Message decodeMessage(Message message) {
        return message;
    }
}
