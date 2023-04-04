package com.example.demo.model;

import org.apache.pulsar.shade.org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.pulsar.shade.org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Created by armen aslikyan on 10/11/2017.
 * This class is used to store source data
 */
public class SourceData {

    private String sourceField;

    public String getSourceField() {
        return sourceField;
    }

    public void setSourceField(String sourceField) {
        this.sourceField = sourceField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SourceData that = (SourceData) o;

        return new EqualsBuilder()
                .append(sourceField, that.sourceField)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(sourceField).toHashCode();
    }

    @Override
    public String toString() {
        return "SourceData{" +
                "sourceField='" + sourceField + '\'' +
                '}';
    }
}
