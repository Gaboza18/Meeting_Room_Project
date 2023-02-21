package com.example.meeting.Room.domain;

import com.example.meeting.Report.domain.Report;
import com.example.meeting.User.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.grammars.hql.HqlParser;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid;

    @Column(name = "room_name")
    private String room_name;

    @Column(name = "interviewer")
    private String interviewer_email;

    @Column(name = "target_user")
    private String target_user_email;

    @Column(name = "start_time")
    private Timestamp start_time;

    @Column(name = "end_time")
    private Timestamp end_time;
    @Builder.Default
    @Column(name = "progress")
    @Enumerated(EnumType.ORDINAL)
    private Progress progress = Progress.BEFORE;

    @Column(name = "created_at")
    private Timestamp created_at;

    @Builder.Default
    @OneToMany(mappedBy = "room" , cascade = CascadeType.ALL , fetch = FetchType.LAZY , orphanRemoval = true)
    private ArrayList<Report> reports = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid")
    private User user;

}
