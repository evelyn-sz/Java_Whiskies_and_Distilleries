package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> getWhiskiesByYear(
            @RequestParam(name="year", required = false) Integer year,
            @RequestParam(name="distillery", required = false) String distilleryName,
            @RequestParam(name="age", required = false) Integer age){
    if (year != null) {
        return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
    } if (distilleryName != null){
            if (age != null) {
                return new ResponseEntity<>(whiskyRepository.findByDistilleryNameAndAge(distilleryName, age), HttpStatus.OK);
            }
            return new ResponseEntity<>(whiskyRepository.findByDistilleryName(distilleryName), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

//        if (year != null) {
//        return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
//    } else if (distilleryName != null){
//        if (age != null) {
//            return new ResponseEntity<>(whiskyRepository.findByDistilleryNameAndAge(distilleryName, age), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(whiskyRepository.findByDistilleryName(distilleryName), HttpStatus.OK);
//    }
//        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
//}


    @GetMapping(value="/whiskies/{id}")
    public ResponseEntity getWhiskyById(@PathVariable Long id){
        return new ResponseEntity(whiskyRepository.findById(id), HttpStatus.OK);
    }
}
