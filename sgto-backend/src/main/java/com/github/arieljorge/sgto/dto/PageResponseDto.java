package com.github.arieljorge.sgto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDto<T> {

    private List<T> content;
    private long totalElements;
    private int page;
    private int size;
    private int totalPages;
    private boolean last;

    public <G> PageResponseDto(Page<G> page, Function<G, T> mapper) {
        if (!page.getContent().isEmpty()) {
            this.content = page.getContent().stream().map(mapper).toList();
        }

        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.page = page.getNumber();
        this.size = page.getSize();
        this.last = page.isLast();
    }
}
