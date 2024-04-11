package com.cs489.Lab6.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Surgery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String surgeryId;
    private String name;
    private String phone;
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address surgeryAddress;

}
