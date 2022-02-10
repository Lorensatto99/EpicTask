package br.com.fiap.epictask.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@Entity(name = "TB_TASKS")
@Table(name = "TB_TASKS")
@SequenceGenerator(name = "task", sequenceName = "SQ_TB_TASK", allocationSize = 1)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{task.title.blank}") //validation
    private String title;


    @Size(min = 10, message = "{task.description.size}")
    private String description;

    @Min(value = 10, message = "{task.points.min}")
    @Max(value = 500, message = "{task.points.max}")
    private int points;

    @Min(value = 0, message = "{task.points.min}")
    @Max(value = 100, message = "{task.points.max}")
    private int status;

    @ManyToOne
    private User user;
}
