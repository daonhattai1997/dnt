package dnt.service;

import dnt.repository.RoomTypeRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Setter(onMethod = @__(@Autowired))
public class RoomService {

    private RoomTypeRepository roomTpRes;
}
