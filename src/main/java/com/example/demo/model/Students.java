package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Students implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_student;

  @Column(nullable = false)
  private String name;

  @ManyToOne
  @JoinColumn(name = "id_group")
  private Groups group;
}
