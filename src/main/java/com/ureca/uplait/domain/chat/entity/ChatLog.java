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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = true)
    private String log;

    // TODO : ERD 상에서는 "채팅순서"라고 명시되어 있음. 정확한 의미를 모르겠음.
    @Column(nullable = true)
    private Long sequence;

    @Column(name = "is_chatbot", nullable = true)
    private Boolean isChatbot;
}
