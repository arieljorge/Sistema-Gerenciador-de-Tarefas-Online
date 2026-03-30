package com.github.arieljorge.sgto.enumerator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PlataformaExterna {
    PADRAO("PADRAO");

    private final String value;

    @JsonValue
    public String getValue() {
        return this.value;
    }

    @JsonCreator
    public static PlataformaExterna fromValue(String value) {
        if (!value.isBlank()) {
            for (PlataformaExterna plataformaExterna : PlataformaExterna.values()) {
                if (plataformaExterna.getValue().equalsIgnoreCase(value)) {
                    return plataformaExterna;
                }
            }
        }

        throw new IllegalArgumentException("Enumerator PlataformaExterna não encontrado para " + value);
    }
}
