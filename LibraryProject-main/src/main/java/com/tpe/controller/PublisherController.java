package com.tpe.controller;

import com.tpe.exception.ResourceNotFoundException;
import com.tpe.payload.mappers.PublisherMapper;
import com.tpe.payload.messages.ErrorMessages;
import com.tpe.payload.request.PublisherRequest;
import com.tpe.payload.response.PublisherResponse;
import com.tpe.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @PostMapping// http://localhost:8080/publishers
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<PublisherResponse> createPublisher(@Valid @RequestBody PublisherRequest publisherRequest){
        return publisherService.createPublisher(publisherRequest);
    }

    @GetMapping("/{id}")  //http://loclahost:8080/publishers/4
   // @PreAuthorize("hasAnyAuthority('ANONYMOUS')")
    public ResponseEntity<PublisherResponse> getPublisherById(@PathVariable("id") Long id){
        Optional<PublisherResponse> publisherResponse = publisherService.getPublisherById(id);
        if(publisherResponse.isPresent()){
            return new ResponseEntity<>(publisherResponse.get(), HttpStatus.OK);
        }else {
            throw new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_PUBLISHER_ID,id));
        }
    }

    @GetMapping // http://localhost:8080/publishers?page=size10&sort=name&type=asc
    public List<PublisherResponse> getAllPublishers(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "10") int size,
        @RequestParam(value = "sort", defaultValue = "name") String sort,
        @RequestParam(value = "type", defaultValue = "desc") String type) {

        Page<PublisherResponse> publisher = publisherService.getAllPublisher(page,size,sort,type);
        return (List<PublisherResponse>) new ResponseEntity<>(publisher,HttpStatus.OK);
    }

}
