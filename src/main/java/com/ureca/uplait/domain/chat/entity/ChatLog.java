package com.ureca.uplait.domain.chat.entity;

import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chat_log")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatLog extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = true)
    private String log;

    @Column(nullable = true)
    private Long sequence;

    @Column(name = "is_chatbot", nullable = true)
    private Boolean isChatbot;
}
