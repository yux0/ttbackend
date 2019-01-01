package com.theretobe.theretobe.datamodels;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "daily_trip")
//@EqualsAndHashCode(exclude={"creationTime", "lastUpdatedTime"})
//@ToString(exclude = {"creationTime", "lastUpdatedTime"})
public class DailyTrip {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Trip trip;

    @NotNull
    @Column(nullable = false)
    private OffsetDateTime startTime;

    @NotNull
    @Column(nullable = false)
    private OffsetDateTime endTime;

    @NotNull
    @Column(nullable = false)
    private String startLocation;

    @NotNull
    @Column(nullable = false)
    private String endLocation;

    @NotNull
    @Column(nullable = false)
    private String tripContent;

    @NotNull
    @Column(nullable = false)
    private String route;

    @OneToMany
    private List<User> attendants;

    @CreationTimestamp
    private OffsetDateTime creationTime;

    @UpdateTimestamp
    private OffsetDateTime lastUpdatedTime;
}
