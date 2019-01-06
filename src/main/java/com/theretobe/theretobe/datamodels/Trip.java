package com.theretobe.theretobe.datamodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User owner;

    @NotNull
    @Column(nullable = false)
    private Integer capacity;

    @NotNull
    @Column(nullable = false)
    private Boolean allowJoinDailyTrip;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TripStatus status;

    @NotNull
    @Column(nullable = false)
    private String Thumbnail;

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

    @CreationTimestamp
    private OffsetDateTime creationTime;

    @UpdateTimestamp
    private OffsetDateTime lastUpdatedTime;
}
