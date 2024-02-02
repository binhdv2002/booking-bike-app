package app.application.ecobikerental.controller;

import app.application.ecobikerental.entity.BikeType;
import app.application.ecobikerental.service.BikeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bike-type")
public class BikeTypeController {
    @Autowired
    private BikeTypeService bikeTypeService;
    @GetMapping("/detailBikeType/{id}")
    public ResponseEntity<?> detailBikeType(@PathVariable("id") Long id ){
        try{
            BikeType bikeType = bikeTypeService.detailBikeType(id);
            return ResponseEntity.status(200).body(bikeType);
        }catch (Exception e ){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/addBikeType")
    private ResponseEntity<?> addBikeType(@RequestBody BikeType bikeType){
        boolean isAdd = bikeTypeService.addType(bikeType);
        return isAdd ?
                ResponseEntity.status(200).body("Add bike type is successfully!") :
                ResponseEntity.status(400).body("Add bike type is failure!");
    }

    @PostMapping("/getListBikeType")
    private ResponseEntity<?> getListBikeType(){
        List<BikeType> bikeTypeList = bikeTypeService.getListBikeType();
        return bikeTypeList!=null ?
                ResponseEntity.status(200).body(bikeTypeList) :
                ResponseEntity.status(400).body("List of bike type is empty");
    }

    @PostMapping("/renderListBikeType")
    private ResponseEntity<?> renderListBikeType(){
        boolean result = bikeTypeService.renderBikeType();
        return result ?
                ResponseEntity.status(200).body("Render list bike type is successfully!") :
                ResponseEntity.status(400).body("Render list bike type is failure!");
    }
}
