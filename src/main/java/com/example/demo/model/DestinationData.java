package com.example.demo.model;

import org.apache.pulsar.shade.org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.pulsar.shade.org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Created by armen aslikyan on 10/11/2017.
 * This class is used to store destination data
 */
public class DestinationData {

    private String destinationField;

    public String getDestinationField() {
        return destinationField;
    }

    public void setDestinationField(String destinationField) {
        this.destinationField = destinationField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        DestinationData that = (DestinationData) o;

        return new EqualsBuilder()
                .append(destinationField, that.destinationField)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(destinationField)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "DestinationData{" +
                "destinationField='" + destinationField + '\'' +
                '}';
    }
}
