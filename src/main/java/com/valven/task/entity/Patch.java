package com.valven.task.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Patch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(columnDefinition = "TEXT")
    private String patchContent;

    @ManyToOne
    @JsonBackReference
    private Commit commit;
}
