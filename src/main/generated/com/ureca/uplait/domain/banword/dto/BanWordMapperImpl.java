package com.ureca.uplait.domain.banword.dto;

import com.ureca.uplait.domain.banword.dto.request.BanWordRequest;
import com.ureca.uplait.domain.banword.dto.response.BanWordResponse;
import com.ureca.uplait.domain.banword.entity.BanWord;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-10T13:15:32+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class BanWordMapperImpl implements BanWordMapper {

    @Override
    public BanWord toEntity(BanWordRequest request) {
        if ( request == null ) {
            return null;
        }

        BanWord.BanWordBuilder banWord = BanWord.builder();

        banWord.banWord( request.banWord() );

        return banWord.build();
    }

    @Override
    public BanWordResponse toDto(BanWord banWord) {
        if ( banWord == null ) {
            return null;
        }

        BanWordResponse banWordResponse = new BanWordResponse();

        banWordResponse.setId( banWord.getId() );
        banWordResponse.setBanWord( banWord.getBanWord() );
        banWordResponse.setCreatedAt( banWord.getCreatedAt() );

        return banWordResponse;
    }

    @Override
    public List<BanWordResponse> toDtoList(List<BanWord> banWords) {
        if ( banWords == null ) {
            return null;
        }

        List<BanWordResponse> list = new ArrayList<BanWordResponse>( banWords.size() );
        for ( BanWord banWord : banWords ) {
            list.add( toDto( banWord ) );
        }

        return list;
    }
}
