package com.theretobe.theretobe.datamodels;

import com.google.gson.annotations.SerializedName;
import com.theretobe.theretobe.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "mockUser")
        //indexes = {@Index(name="fbIndex", columnList="fbUserId", unique = true)})
public class MockUser {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @SerializedName(Constants.USER_EMAIL)
    @Column(nullable = false)
    private String email;

    @SerializedName(Constants.USER_OAUTH)
    @Column(nullable = false)
    private String fbUserId;
}
