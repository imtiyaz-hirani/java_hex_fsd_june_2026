package com.springboot.ecom.model;

import com.springboot.ecom.enums.JobTitle;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Executive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private JobTitle jobTitle;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
