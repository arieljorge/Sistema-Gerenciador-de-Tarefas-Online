package com.github.arieljorge.sgto.config;

import com.github.arieljorge.sgto.enumerator.PlataformaExterna;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PlataformaExternaEnumConverter implements AttributeConverter<PlataformaExterna, String> {

    @Override
    public String convertToDatabaseColumn(PlataformaExterna plataformaExterna) {
        if (plataformaExterna == null) return null;
        return plataformaExterna.getValue();
    }

    @Override
    public PlataformaExterna convertToEntityAttribute(String value) {
        return PlataformaExterna.fromValue(value);
    }
}
