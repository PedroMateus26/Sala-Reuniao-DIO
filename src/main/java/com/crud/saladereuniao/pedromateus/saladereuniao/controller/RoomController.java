package com.crud.saladereuniao.pedromateus.saladereuniao.controller;

import com.crud.saladereuniao.pedromateus.saladereuniao.exception.ResourceNotFoundException;
import com.crud.saladereuniao.pedromateus.saladereuniao.model.Room;
import com.crud.saladereuniao.pedromateus.saladereuniao.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRespository;

    @GetMapping
    public ResponseEntity<List<Room>> findAll(){

        List list=List.of(roomRespository.findAll());
        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> findById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Room room = roomRespository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Room not found for this id: "+id));
        return ResponseEntity.ok().body(room);
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@Valid @RequestBody Room room){
        room=roomRespository.save(room);
        return ResponseEntity.ok().body(room);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@Valid @PathVariable(value = "id") Long id, @RequestBody Room room) throws ResourceNotFoundException {
        Room roomDataBase = roomRespository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Room not found for this id: "+id));
        roomDataBase=room;
        roomDataBase.setId(id);
        Room updateRoom=roomRespository.save(roomDataBase);
        return ResponseEntity.ok().body(room);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteRoom(@Valid @PathVariable(value = "id") Long id) throws ResourceNotFoundException {
            Room room = roomRespository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Room not found for this id: " + id));
            roomRespository.delete(room);
            Map<String, Boolean> response = new HashMap<>();
            response.put("delete", Boolean.TRUE);
            return response;

    }
}
