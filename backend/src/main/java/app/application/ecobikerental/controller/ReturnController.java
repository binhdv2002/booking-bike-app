package app.application.ecobikerental.controller;

import app.application.ecobikerental.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/return-bike")
public class ReturnController {
    @Autowired
    private ReturnService returnService;

    @GetMapping("/caculateTotalCost/{idRentBike}")
    public ResponseEntity<?> caculateTotalCost(@PathVariable("idRentBike") Long idRentBy){
        try {
            boolean isCaculated = returnService.totalCostNormal(idRentBy);
            return isCaculated? ResponseEntity.status(200).body("Return bike for bike station is successfully!"):
                    ResponseEntity.status(400).body("Return bike for bike station is failure!");

        }catch (Exception e ){
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }
    @GetMapping("/caculateTotalCostNew/{idRentBike}")
    public ResponseEntity<?> caculateTotalCostNew(@PathVariable("idRentBike") Long idRentBy){
        try {
            boolean isCaculated = returnService.totalCostNormalNew(idRentBy);
            return isCaculated ? ResponseEntity.status(200).body("Return bike for bike station is successfully!"):
                    ResponseEntity.status(400).body("Return bike for bike station is failure!");
        } catch (Exception e ){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
