package com.example.demo.controller;
import com.example.demo.model.Image;
import com.example.demo.model.Station;
import com.example.demo.service.StationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class RequestController {
    @Autowired
    private StationServices stationServices
    @GetMapping("/show")
    public ResponseEntity<?> getAllStation(){
        List<Station> response = stationServices.findAllCharginStations();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/show/{id}")
    public ResponseEntity<?> getStationInfoById(@PathVariable("id") Long id){
        Optional<Station> response = stationServices.getStationInfoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/add")
    public ResponseEntity<?> addStation(@RequestPart("station") Station station, @RequestPart("file")MultipartFile file) throws IOException {
         stationServices.save(station,file);
        return ResponseEntity.status(HttpStatus.OK).body("station added");
    }
    @PostMapping("/put/{id}")
    public ResponseEntity<?> putStation(@PathVariable("id") Long id,@RequestParam("stationName") String stationName,@RequestParam("stationAddress") String stationAddress,@RequestParam("stationPricing") String stationPricing,@RequestParam("file")MultipartFile file) throws IOException {
         String response = stationServices.put(id,stationName,stationAddress,stationPricing,file);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteStation(@PathVariable("id") Long id){
        stationServices.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("DELETED SUCCESSFULLY")
    }

}
