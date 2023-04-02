package felipe.springframework.university.web.controller;

import felipe.springframework.university.common.dto.GroupDto;
import felipe.springframework.university.domain.services.GroupService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }


    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<GroupDto> createGroup(@Valid @RequestBody GroupDto groupDto){
        return new ResponseEntity<>(groupService.createGroup(groupDto), HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<GroupDto> updateGroup(@Valid @RequestBody GroupDto groupDto){
        return new ResponseEntity<>(groupService.updateGroup(groupDto), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<GroupDto> findGroupById(@PathVariable("id") Long id){
        return new ResponseEntity<>(groupService.findGroupById(id), HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<GroupDto>> findAllGroups(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize){
        return new ResponseEntity<>(groupService.findAllGroups(pageNumber, pageSize), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteGroupById(@PathVariable("id")Long id){
        groupService.deleteGroupById(id);
        return new ResponseEntity<>("Group deleted successfully", HttpStatus.OK);
    }

}
