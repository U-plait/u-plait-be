package com.ureca.uplait.domain.banword.entity;

import com.ureca.uplait.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ban_word")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BanWord extends BaseEntity {

    @Column(name = "ban_word", nullable = false, length = 20)
    private String banWord;
}
