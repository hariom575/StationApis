package com.example.demo.service;

import com.example.demo.model.Image;
import com.example.demo.model.Station;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.StationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class StationServices {
    @Autowired
    private ImageRepository imagemRepository;
    @Autowired // NEW
    private StationRepository stationRepository; // NEW

     // NEW
    @Transactional
    public Image save(Station station, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Image image = new Image(fileName, file.getContentType(), file.getBytes());

        stationRepository.save(station); // NEW
        image.setStation(station); // NEW

        return imagemRepository.save(image);
    }
    public List<Station> findAllCharginStations() {
        return  stationRepository.findAll();
    }

    public Optional<Station> getStationInfoById(Long id) {
        return stationRepository.findById(id.intValue());
    }

    public String put(Long id,String stationName, String stationAddress, String stationPricing, MultipartFile file) throws IOException {
        Optional<Station> station= stationRepository.findById(id.intValue());
        if(station.isEmpty()) return"station not found";
        Station station1 = station.get();
        if(!stationName.isEmpty()) station1.setStationName(stationName);
        if(!stationAddress.isEmpty()) station1.setStationAddress(stationAddress);
        if(!stationPricing.isEmpty()) station1.setStationPricing(stationPricing);
        stationRepository.save(station1);
        if(!file.isEmpty()){
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Image image = new Image(fileName, file.getContentType(), file.getBytes());
            image.setStation(station1);
            imagemRepository.save(image);
        }
        return "station data is changed";
    }

    public void delete(Long id) {
        stationRepository.deleteById(id.intValue());
    }
}
