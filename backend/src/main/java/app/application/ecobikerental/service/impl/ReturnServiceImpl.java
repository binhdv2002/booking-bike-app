package app.application.ecobikerental.service.impl;

import app.application.ecobikerental.entity.*;
import app.application.ecobikerental.repository.*;
import app.application.ecobikerental.service.BikeRentalService;
import app.application.ecobikerental.service.ReturnService;
import app.application.ecobikerental.service.dto.enums.BikeRentalStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReturnServiceImpl implements ReturnService {
    @Autowired
    private BikeRentalRepository bikeRentalRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BikeRepository bikeRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BikeStationRepository bikeStationRepository;
    @Override
    public boolean totalCostNormal(Long idRental) {
        LocalDateTime now = LocalDateTime.now();
        float money = 0;
        BikeRental bikeRental = bikeRentalRepository.findById(idRental).orElseThrow(()->new IllegalArgumentException("Now, you don't have bike rental"));
        Bike bike = bikeRepository.findById(bikeRental.getBike().getId()).orElseThrow(() -> new IllegalArgumentException("Bike not found into database"));
        if(bike.getBikeType().getId()==5){
            throw new IllegalArgumentException("Type of bike is invalid");
        }
        if(bikeRental.getBikeRentalStatus() == BikeRentalStatus.PAID){
            throw new IllegalArgumentException(" Bike Rental is paid ");
        }

        List<Transaction> listTransaction = transactionRepository.getListTransactionIsReturn(bikeRental.getUser().getId());
        if(listTransaction.size()>0){
            CreditCard creditCard = userRepository.getCreditFromUser(bikeRental.getUser().getId()).orElseThrow(()-> new IllegalArgumentException("Credit card not found into database"));
            creditCard.setBalance(creditCard.getBalance() + listTransaction.get(0).getAmount());
            Transaction transaction = listTransaction.get(0);
            transaction.setReturn(true);
            transactionRepository.save(transaction);
            long rental_during_temp = Duration.between( bikeRental.getStartTime(), now).toMinutes();
            long rental_during =rental_during_temp + bikeRental.getRental_duration();

            BikeStation bikeStation = bikeStationRepository.findById(bike.getBikeStation().getId()).orElseThrow(()-> new IllegalArgumentException("Bike station is not exist "));
            if(bikeStation.getEmptySlots()<=0 ){
                throw new IllegalArgumentException("Bike station isn't enough slot ");
            }

            bikeStation.setTotalBikes(bikeStation.getTotalBikes()+1);
            bikeStation.setEmptySlots(bikeStation.getEmptySlots()-1);
            bikeStationRepository.save(bikeStation);
            if(rental_during<=10){
                money = 0;
            }
            else if(rental_during> 10 && rental_during<30)
            {
                money =10000;
            }
            else{
                money = 10000 + Math.round((float) (rental_during - 30) /15) * 3000;
            }
            if(bike.getBikeType().getId() == 4 ||bike.getBikeType().getId() == 3){
                money = (float) (money * 1.5);
            }
            bikeRental.setEndTime(now);
            bikeRental.setBikeRentalStatus(BikeRentalStatus.PAID);
            bikeRental.setTotalCost(money);
            bikeRentalRepository.save(bikeRental);
            creditCard.setBalance(creditCard.getBalance()-money);
            creditCardRepository.save(creditCard);
            bike.setStatus(true);
            bikeRepository.save(bike);
        }
        return true;
    }

    @Override
    public boolean totalCostNormalNew(Long idRental) {
        LocalDateTime now = LocalDateTime.now();
        float money = 0;
        BikeRental bikeRental = bikeRentalRepository.findById(idRental).orElseThrow(()->new IllegalArgumentException("Now, you don't have bike rental"));
        Bike bike = bikeRepository.findById(bikeRental.getBike().getId()).orElseThrow(() -> new IllegalArgumentException("Bike not found into database"));
        if(bike.getBikeType().getId()!=5){
            throw new IllegalArgumentException("Type of bike is invalid");
        }
        if(bikeRental.getBikeRentalStatus() == BikeRentalStatus.PAID){
            throw new IllegalArgumentException(" Bike Rental is paid ");
        }
        List<Transaction> listTransaction = transactionRepository.getListTransactionIsReturn(bikeRental.getUser().getId());
        if(listTransaction.size()>0){
            CreditCard creditCard = userRepository.getCreditFromUser(bikeRental.getUser().getId()).orElseThrow(()-> new IllegalArgumentException("Credit card not found into database"));
            Transaction transaction = listTransaction.get(0);
            transaction.setReturn(true);
            transactionRepository.save(transaction);
            long rental_during_temp = Duration.between( bikeRental.getStartTime(), now).toMinutes();
            long rental_during =rental_during_temp + bikeRental.getRental_duration();

            BikeStation bikeStation = bikeStationRepository.findById(bike.getBikeStation().getId()).orElseThrow(()-> new IllegalArgumentException("Bike station is not exist "));
            if(bikeStation.getEmptySlots()<=0 ){
                throw new IllegalArgumentException("Bike station isn't enough slot ");
            }
            bikeStation.setTotalBikes(bikeStation.getTotalBikes()+1);
            bikeStation.setEmptySlots(bikeStation.getEmptySlots()-1);
            bikeStationRepository.save(bikeStation);
            if((int)(rental_during/60)<12){
                money = 200000- (12-(int)(rental_during/60))*10000;
            }
            else if((int)(rental_during/60)<24 && (int)(rental_during/60)>=12)
            {
                money =0;
            }
            else{
                money = -(int)(((int)(rental_during/60)  - 24*60)/15)*2000;
            }
            bikeRental.setEndTime(now);
            bikeRental.setBikeRentalStatus(BikeRentalStatus.PAID);
            bikeRental.setTotalCost(money);
            bikeRentalRepository.save(bikeRental);
            creditCard.setBalance(creditCard.getBalance()+money);
            creditCardRepository.save(creditCard);
            bike.setStatus(true);
            bikeRepository.save(bike);
        }
        return true;
    }
}
