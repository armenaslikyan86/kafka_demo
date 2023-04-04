package com.example.demo.mapper;

import com.example.demo.model.DestinationData;
import com.example.demo.model.SourceData;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

/**
 * Created by armen aslikyan on 10/11/2017.
 * This class is used to map source data to destination data
 * It uses Orika library to map the data
 */
public class BeanMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        factory.getConverterFactory().registerConverter(new NewlineEscapeConverter());
        factory.classMap(SourceData.class, DestinationData.class)
                .field("sourceField", "destinationField")
                .byDefault().register();
    }

//    public static void main(String[] args) {
//        final BeanMapper beanMapper = new BeanMapper();
//        beanMapper.init();
//        final SourceData sourceData = new SourceData();
//        sourceData.setSourceField("Here is the data with new line character \n and tab character \t");
//        final DestinationData destinationData = beanMapper.map(sourceData, DestinationData.class);
//        System.out.println(destinationData.getDestinationField());
//    }
}
