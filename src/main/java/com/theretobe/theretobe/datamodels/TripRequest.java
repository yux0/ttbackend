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
@Entity(name = "trip_request")
public class TripRequest {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @NotNull
    private User reviewer;

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @NotNull
    private User requester;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @NotNull
    private Trip trip;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RequstStatus status;

    private String rejectReason;

    private String comment;

    @CreationTimestamp
    private OffsetDateTime creationTime;

    @UpdateTimestamp
    private OffsetDateTime lastUpdatedTime;
}
