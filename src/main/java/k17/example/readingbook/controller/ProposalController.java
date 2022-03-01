package k17.example.readingbook.controller;


import k17.example.readingbook.entity.Post;
import k17.example.readingbook.entity.Proposal;
import k17.example.readingbook.model.dto.PropPagingDto;
import k17.example.readingbook.model.request.ParamProp;
import k17.example.readingbook.model.request.ParamUpdatePost;
import k17.example.readingbook.service.PostService;
import k17.example.readingbook.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/")
public class ProposalController
{
    @Autowired
    private ProposalService proposalService;

    @GetMapping(value="props")
    public ResponseEntity<?> getListProps() {
        List<Proposal> proposals = proposalService.getAllProp();
        return ResponseEntity.ok(proposals);
    }

    @GetMapping(value="props/paging/{userId}/{pageNumber}")
    public ResponseEntity<?> getListPropsPaging(@PathVariable int userId,@PathVariable int pageNumber) {
        PropPagingDto propPagingDto = proposalService.getAllPropPaging(userId,pageNumber);
        return ResponseEntity.ok(propPagingDto);
    }

    @GetMapping(value="props/{id}")
    public ResponseEntity<?> getPropById(@PathVariable int id) {
        Proposal proposal =  proposalService.getPropById(id);
        return ResponseEntity.ok(proposal);
    }

    @DeleteMapping("props/{id}")
    public ResponseEntity<?> deleteProp(@PathVariable int id) {
        proposalService.deletePropById(id);
        return ResponseEntity.ok("delete success");
    }

    @PutMapping("props/{id}")
    public ResponseEntity<?> updatePropById(@Validated @RequestBody ParamProp req, @PathVariable int id) {
        Proposal proposal = proposalService.updatePropById(req,id);
        return ResponseEntity.ok(proposal);
    }

    @PostMapping("props/create")
    public ResponseEntity<?> createProp(@Validated @RequestBody ParamProp req) {
        Proposal proposal = proposalService.createProp(req);
        return ResponseEntity.ok(proposal);
    }

}
