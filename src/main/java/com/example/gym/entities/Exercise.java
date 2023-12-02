package com.example.gym.entities;


import jakarta.persistence.*;

@Table(name = "ejercicios")
@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "musculo")
    private String muscle;


    @Column(name = "ejercicio")
    private String exercise;


    @Column(name = "peso")
    private int weight;

    public Exercise() {
    }

    public Exercise(Long id, String muscle, String exercise, int weight) {
        this.id = id;
        this.muscle = muscle;
        this.exercise = exercise;
        this.weight = weight;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMuscle() {
        return muscle;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


}



