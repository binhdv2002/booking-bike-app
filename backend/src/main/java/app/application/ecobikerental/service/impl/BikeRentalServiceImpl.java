package app.application.ecobikerental.service.impl;

import app.application.ecobikerental.entity.*;
import app.application.ecobikerental.repository.*;
import app.application.ecobikerental.service.BikeRentalService;
import app.application.ecobikerental.service.TransactionService;
import app.application.ecobikerental.service.dto.enums.BikeRentalStatus;
import app.application.ecobikerental.service.dto.request.RentalRequest;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class BikeRentalServiceImpl implements BikeRentalService {
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BikeRentalRepository bikeRentalRepository;
    @Autowired
    private BikeStationRepository bikeStationRepository;
    @Autowired
    private BikeRepository bikeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BarcodeGeneraterServiceImpl barcodeGeneraterServiceImpl;

    @Autowired
    private TransactionService transactionService;

    @Override
    public BikeRental rentalBike(RentalRequest rentalRequest) {

        Bike bike = bikeRepository
                .getBikeByBarcode(rentalRequest.getBarcode())
                .orElseThrow(()-> new IllegalArgumentException("Barcode is incorrect. This bike isn't exist "));
        if(bike.getBikeType().getId()==5){
            throw new IllegalArgumentException("Type of bike is invalid");
        }
        if(bike.getBikeStation().getTotalBikes()<=0){
            throw new IllegalArgumentException("Not enough number of bikes for you");
        }

        User user = userRepository
                .findById(rentalRequest.getIdUser())
                .orElseThrow(()-> new IllegalArgumentException("User is not exist"));

        CreditCard creditCard = creditCardRepository
                .findById(user.getCreditCard().getId())
                .orElseThrow(()-> new IllegalArgumentException("This credit card isn't exist"));

        if(transactionService.getListTransaction(user.getId())!=null){
            if(transactionService.checkTransactionNotReturn(user.getId())){
               throw new IllegalArgumentException("You have not done the procedure to return the bike ");
            }
        }
        Transaction transaction = Transaction.builder().transactionTime(LocalDateTime.now())
                .creditCard(creditCard)
                .description("Rent "+ bike.getBikeType().getTypeName())
                .user(user)
                .isReturn(false)
                .amount(bike.getBikeType()
                        .getDepositPrice()).build();
        transactionRepository.save(transaction);
        if(!(creditCard.getBalance() >transaction.getAmount())){
        throw new IllegalArgumentException("Blance into credit card not enough money");
    }
        creditCard.setBalance(creditCard.getBalance() - transaction.getAmount());
    CreditCard creditCardSave = creditCardRepository.save(creditCard);

        BikeRental bikeRental = BikeRental.builder().
                bikeRentalStatus(BikeRentalStatus.PENDING).
                bike(bike).
                startTime(LocalDateTime.now()).
                endTime(LocalDateTime.now().plusSeconds(1)).
                totalCost(0).
                bike(bike).
                user(user).
                build();
        BikeRental bikeRentalSave  = bikeRentalRepository.save(bikeRental);
        bike.setStatus(false);
        bike.getBikeStation().setTotalBikes(bike.getBikeStation().getTotalBikes()-1);
        bike.getBikeStation().setEmptySlots(bike.getBikeStation().getEmptySlots()+1);
        BikeStation bikeStation= bike.getBikeStation();
        bikeStationRepository.save(bikeStation);
        return bikeRentalSave!= null?bikeRentalSave:null;
    }

    @Override
    public BikeRental rentalBikeNew(RentalRequest rentalRequest) {
        Bike bike = bikeRepository
                .getBikeByBarcode(rentalRequest.getBarcode())
                .orElseThrow(()-> new IllegalArgumentException("Barcode is incorrect. This bike isn't exist "));
        if(bike.getBikeType().getId()!=5){
            throw new IllegalArgumentException("Type of bike is invalid");
        }
        if(bike.getBikeStation().getTotalBikes()<=0){
            throw new IllegalArgumentException("Not enough number of bikes for you");
        }
        User user = userRepository
                .findById(rentalRequest.getIdUser())
                .orElseThrow(()-> new IllegalArgumentException("User is not exist"));

        CreditCard creditCard = creditCardRepository
                .findById(user.getCreditCard().getId())
                .orElseThrow(()-> new IllegalArgumentException("This credit card isn't exist"));

        if(transactionService.getListTransaction(user.getId())!=null){
            if(transactionService.checkTransactionNotReturn(user.getId())){
                throw new IllegalArgumentException("You have not done the procedure to return the bike ");
            }
        }
        Transaction transaction = Transaction.builder().transactionTime(LocalDateTime.now())
                .creditCard(creditCard)
                .description("Rent "+ bike.getBikeType().getTypeName())
                .user(user)
                .isReturn(false)
                .amount(bike.getBikeType()
                        .getDepositPrice()).build();
        transactionRepository.save(transaction);
        if(!(creditCard.getBalance() >transaction.getAmount())){
            throw new IllegalArgumentException("Blance into credit card not enough money");
        }
        creditCard.setBalance(creditCard.getBalance() - 200000);
        CreditCard creditCardSave = creditCardRepository.save(creditCard);

        BikeRental bikeRental = BikeRental.builder().
                bikeRentalStatus(BikeRentalStatus.PENDING).
                bike(bike).
                startTime(LocalDateTime.now()).
                endTime(LocalDateTime.now().plusSeconds(1)).
                totalCost(0).
                bike(bike).
                user(user).
                build();
        BikeRental bikeRentalSave  = bikeRentalRepository.save(bikeRental);
        bike.setStatus(false);
        bike.getBikeStation().setTotalBikes(bike.getBikeStation().getTotalBikes()-1);
        bike.getBikeStation().setEmptySlots(bike.getBikeStation().getEmptySlots()+1);
        BikeStation bikeStation= bike.getBikeStation();
        bikeStationRepository.save(bikeStation);
        return bikeRentalSave!= null?bikeRentalSave:null;
    }

    @Override
    public byte[] generateBarcode(Long idBike) throws IOException, WriterException {
        Bike bike = bikeRepository.findById(idBike).orElseThrow(()-> new IllegalArgumentException("This bike isn't exist "));
        return barcodeGeneraterServiceImpl.generateBarcode(bike.getBarcode());
    }

    public String generateBarcodeValue(Long idBike) throws IOException, WriterException {
        Bike bike = bikeRepository.findById(idBike).orElseThrow(()-> new IllegalArgumentException("This bike isn't exist "));
        return bike.getBarcode();
    }

    @Override
    public BikeRental getRentalBikeByUser(Long idUser) {
        return null;
    }

    @Override
    public BikeRental findById(int id) {
        Optional<BikeRental> bikeRental = bikeRentalRepository.findById((long) id);
        return bikeRental.orElse(null);
    }
}
