package obuda.nik.DoubleTranspositionCipher.rest;

import obuda.nik.DoubleTranspositionCipher.domain.DTO.MessageDTO;
import obuda.nik.DoubleTranspositionCipher.domain.enitity.Message;
import obuda.nik.DoubleTranspositionCipher.service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/cipher")
public class MessageResource {
    @Autowired
    private MessageService messageService;

    private ModelMapper modelMapper = new ModelMapper();

    @PostMapping(
            value = "/encoder",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity encodeMessage(@RequestBody MessageDTO messageDTO) {
        Message message = messageService.encodeMessage(modelMapper.map(messageDTO, Message.class));
        messageDTO = modelMapper.map(message, MessageDTO.class);
        return new ResponseEntity<>(messageDTO, HttpStatus.OK);

    }

    @PostMapping(value = "/decoder")
    public ResponseEntity<MessageDTO> decodeMessage(@RequestBody MessageDTO messageDTO) {
        Message message = messageService.decodeMessage(modelMapper.map(messageDTO, Message.class));
        messageDTO = modelMapper.map(message, MessageDTO.class);
        return ResponseEntity.ok().body(messageDTO);

    }
}
