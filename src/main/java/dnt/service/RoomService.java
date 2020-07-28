package dnt.service;

import dnt.entity.RoomType;
import dnt.repository.RoomTypeRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter(onMethod = @__(@Autowired))
public class RoomService {

    private RoomTypeRepository roomTpRes;

    public List<RoomType> findAll() {
        return roomTpRes.findAll();
    }
}
