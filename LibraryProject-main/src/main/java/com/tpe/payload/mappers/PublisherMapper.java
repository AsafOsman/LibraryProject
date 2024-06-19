package com.tpe.payload.mappers;


import com.tpe.entity.concretes.business.Publisher;
import com.tpe.payload.request.PublisherRequest;
import com.tpe.payload.response.PublisherResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Data
@Component
public class PublisherMapper {

    // POJO --> DTO
    public static PublisherResponse mapPublisherToPublisherResponse(Publisher publisher) {
        return PublisherResponse.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .builtIn(publisher.getBuiltIn())
//                .books(publisher.getBooks().stream()
//                        .map(BookMapper::)
//                        .collect(Collectors.toList()))
                .build();
    }

    // DTO --> POJO
    public static Publisher mapPublisherRequestToPublisher(PublisherRequest publisherRequest) {
        return  Publisher.builder()
                .name(publisherRequest.getName())
                .builtIn(publisherRequest.getBuiltIn())
//                .books(publisherRequest.getBooks().stream()
//                        .map(BookMapper::)
//                        .collect(Collectors.toList()))
                .build();
    }

}
