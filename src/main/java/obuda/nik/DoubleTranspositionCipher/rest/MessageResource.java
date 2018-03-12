package obuda.nik.DoubleTranspositionCipher.rest;

import obuda.nik.DoubleTranspositionCipher.domain.DTO.MessageDTO;
import obuda.nik.DoubleTranspositionCipher.domain.enitity.Message;
import obuda.nik.DoubleTranspositionCipher.service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>Message Resource</h1>
 * REST controller for the message.
 * Root url: /api/cipher
 * <p>
 *
 * @author Marija Kovacevic
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/cipher")
public class MessageResource {
    @Autowired
    private MessageService messageService;

    private ModelMapper modelMapper = new ModelMapper();

    /**
     * POST : Encode given message with double transposition cipher.
     *
     * @param messageDTO Object that contains message, keys and type of transpositions.
     * @return ResponseEntity with status 202 ACCEPTED and with the messageDTO that contains encrypted message.
     */
    @PostMapping(
            value = "/encoder",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity encodeMessage(@RequestBody MessageDTO messageDTO) {
        Message message = messageService.encodeMessage(modelMapper.map(messageDTO, Message.class));
        messageDTO = modelMapper.map(message, MessageDTO.class);
        return ResponseEntity.ok().body(messageDTO);

    }

    /**
     * POST : Decode given message with double transposition cipher.
     *
     * @param messageDTO Object that contains message, keys and type of transpositions.
     * @return ResponseEntity with status 202 ACCEPTED and with the messageDTO that contains decrypted message.
     */
    @PostMapping(value = "/decoder")
    public ResponseEntity<MessageDTO> decodeMessage(@RequestBody MessageDTO messageDTO) {
        Message message = messageService.decodeMessage(modelMapper.map(messageDTO, Message.class));
        messageDTO = modelMapper.map(message, MessageDTO.class);
        return ResponseEntity.ok().body(messageDTO);

    }
}
