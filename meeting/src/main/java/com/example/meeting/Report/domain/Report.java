package com.example.meeting.Report.domain;

import com.example.meeting.Result.domain.Result;
import com.example.meeting.Room.domain.Room;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Report {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2" , strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid;

    @Column(name = "report_name")
    private String report_name;

    @Column(name = "creator")
    private String creator;

    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "reason")
    private String reason;

    @Column(name = "evaluation")
    protected String evaluation;

    @Builder.Default
    @OneToMany(mappedBy = "report" , cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Result> results = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "room_uuid")
//    private Room room;

}
