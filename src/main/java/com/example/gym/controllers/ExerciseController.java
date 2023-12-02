package com.example.gym.controllers;

import com.example.gym.entities.Exercise;
import com.example.gym.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ExerciseController {


    private final ExerciseRepository exerciseRepository;


    @Autowired
    public ExerciseController(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @GetMapping("/chest")
    public List<Exercise> findChest() {

        List<Exercise> chest = exerciseRepository.findAll();

        return chest.stream().filter(exercise -> "Pecho".equals(exercise.getMuscle())).collect(Collectors.toList());

    }

    @GetMapping("/back")
    public List<Exercise> findBack() {

        List<Exercise> back = exerciseRepository.findAll();

        return back.stream().filter(exercise -> "Espalda".equals(exercise.getMuscle())).collect(Collectors.toList());

    }


    @GetMapping("/legs")
    public List<Exercise> findLegs() {

        List<Exercise> legs = exerciseRepository.findAll();

        return legs.stream().filter(exercise -> "Pierna".equals(exercise.getMuscle())).collect(Collectors.toList());

    }

    @GetMapping("/arms")
    public List<Exercise> findArms() {

        List<Exercise> arms = exerciseRepository.findAll();

        return arms.stream().filter(exercise -> "Brazo".equals(exercise.getMuscle())).collect(Collectors.toList());

    }

    @GetMapping("/exercises/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long id) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(id);

        if (exerciseOptional.isPresent()) {
            Exercise exercise = exerciseOptional.get();
            return new ResponseEntity<>(exercise, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/add")
    public ResponseEntity<Exercise> addExercise(@RequestBody Exercise exercise) {
        try {
            Exercise savedExercise = exerciseRepository.save(exercise);
            return new ResponseEntity<>(savedExercise, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExercise(@PathVariable Long id) {
        if (exerciseRepository.existsById(id)) {
            exerciseRepository.deleteById(id);
            return new ResponseEntity<>("Borrado", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontro un ejercicio con el id:" + id, HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/update/{id}")
    public Exercise update(@RequestBody Exercise editedExercise, @PathVariable Long id) {
        Optional<Exercise> optionalExercise = exerciseRepository.findById(id);

        if (optionalExercise.isPresent()) {
            Exercise exercise = optionalExercise.get();

            exercise.setMuscle(editedExercise.getMuscle());
            exercise.setExercise(editedExercise.getExercise());
            exercise.setWeight(editedExercise.getWeight());

            return exerciseRepository.save(exercise);
        } else {
            throw new NoSuchElementException("No se encontro el id: " + id);
        }
    }}