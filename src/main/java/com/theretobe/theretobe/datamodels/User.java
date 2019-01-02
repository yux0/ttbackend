package com.theretobe.theretobe.datamodels;

import com.google.gson.annotations.SerializedName;
import com.theretobe.theretobe.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @SerializedName(Constants.USER_FIRSTNAME)
    @Column(nullable = false)
    private String firstName;

    private String middleName;

    @SerializedName(Constants.USER_LASTNAME)
    @Column(nullable = false)
    private String lastName;

    private String nickName;

    @SerializedName(Constants.USER_EMAIL)
    @Column(nullable = false)
    private String email;

    @SerializedName(Constants.USER_PHONE)
    @Column(nullable = false)
    private String phone;

    private String photo;

    private String footprint;

    @OneToMany
    private List<Trip> savedTrip;

    @OneToMany
    private List<Trip> joinedTrip;

    private String city;
    private String state;
    private String country;
    private String gender;
    private String dob;
    private String interest;
    private String description;


    @CreationTimestamp
    private OffsetDateTime creationDate;

    @UpdateTimestamp
    private OffsetDateTime lastUpdatedTime;
}
