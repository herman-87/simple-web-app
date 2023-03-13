package com.herman87.simplewebapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "t_department")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_id", nullable = false)
    private Long id;
    @NotBlank(message = "Please enter the department name")
//    @Size(max = 4, min = 2)
    @Column(name = "c_name")
    private String name;
    @Column(name = "c_address")
    private String address;
    @Column(name = "c_code")
    private String code;

}
