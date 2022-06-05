package io.atlassian.micros.myservice.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    BookData bookRequestToBookData(BookRequest bookRequest);
    BookResponse bookDataToBookResponse(BookData bookData);
}
