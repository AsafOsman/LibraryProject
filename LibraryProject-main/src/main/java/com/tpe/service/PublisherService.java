package com.tpe.service;

import com.tpe.payload.mappers.PublisherMapper;
import com.tpe.payload.request.PublisherRequest;
import com.tpe.payload.response.PublisherResponse;
import com.tpe.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;
    private final MethodHelper  methodHelper;



    public ResponseEntity<PublisherResponse> createPublisher(PublisherRequest publisherRequest) {



    }

    public Optional<PublisherResponse> getPublisherById(Long id) {


    }


    public Page<PublisherResponse> getAllPublisher(int page, int size, String sort, String type) {
        Pageable pageable = methodHelper.getPageableWithProperties(page,size,sort,type);
        return publisherRepository.findAll(pageable).map(PublisherMapper::mapPublisherToPublisherResponse);
    }
}
