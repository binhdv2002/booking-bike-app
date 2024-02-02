package app.application.ecobikerental.controller;

import app.application.ecobikerental.entity.BikeStation;
import app.application.ecobikerental.service.BikeStationService;
import app.application.ecobikerental.service.impl.BikeStationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bike-station")
public class BikeStationController {
    @Autowired
    private BikeStationService bikeStationService;

    @GetMapping("/getList")
    private ResponseEntity<?> getListBikeStation(){
        List<BikeStation> list =  bikeStationService.getListBikeStation();
        return list!=null ? ResponseEntity.status(200).body(list): ResponseEntity.status(400).body("List is empty");
    }

    @PostMapping("/renderList")
    private ResponseEntity<?> renderListBikeStation(){
        boolean isRender =  bikeStationService.renderBikeStation();
        return isRender? ResponseEntity.status(200).body("Render true"): ResponseEntity.status(400).body("Render fail");
    }

    @GetMapping("/detail/{id}")
    private ResponseEntity<?> detailBikeStationBy(@PathVariable("id") Long id ){
        try{
            BikeStation bikeStation =  bikeStationService.detailBikeStation(id);
            return ResponseEntity.status(200).body(bikeStation);
        }
        catch (Exception e ){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping
    private ResponseEntity<?> searchByName(@RequestParam String search) {
        List<BikeStation> list = bikeStationService.searchByName(search);
        return list!=null ? ResponseEntity.status(200).body(list): ResponseEntity.status(400).body("List is empty");
    }


}
