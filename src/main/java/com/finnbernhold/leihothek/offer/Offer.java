package com.finnbernhold.leihothek.offer;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
@Table("offer")
public record Offer(
        @Id
        Integer id,
        String title,
        String description,
        Integer imageId,
        @CreatedBy
        String createdBy
){
}