package com.example.demo.mapper;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

/**
 * Created by armen aslikyan on 10/11/2017.
 * This class is used to convert new line character to \n and tab character to \t
 */
public class NewlineEscapeConverter extends BidirectionalConverter<String, String> {

    @Override
    public String convertTo(String source, Type<String> type, MappingContext mappingContext) {
        return source.replace("\n", "\\n").replace("\t", "\\t");
    }

    @Override
    public String convertFrom(String source, Type<String> type, MappingContext mappingContext) {
        return source.replace("\\n", "\n").replace("\\t", "\t");
    }
}
