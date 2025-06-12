package com.ureca.uplait.domain.allowword;

import com.ureca.uplait.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "allow_word")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllowWord extends BaseEntity {

    @Column(name = "allow_word", nullable = false, length = 20)
    private String allowWord;
}
