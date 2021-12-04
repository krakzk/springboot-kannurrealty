package com.taintedmonk.kannurRealty.controller;

import com.taintedmonk.kannurRealty.entity.FileEntity;
import com.taintedmonk.kannurRealty.entity.LocationInfo;
import com.taintedmonk.kannurRealty.repository.FileRepository;
import com.taintedmonk.kannurRealty.repository.LocationInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class DataController {

    @Autowired
    LocationInfoRepository locationInfoRepository;

    @Autowired
    FileRepository fileRepository;

    @GetMapping("/testUrl")
    public String testUrl() {
        return "This is a test url. You have Success";
    }

    @GetMapping("/allLocations")
    public List<LocationInfo> getAllLocations(){
        return locationInfoRepository.findAll();
    }

    @GetMapping("/getLocationInfo/{id}")
    public ResponseEntity<LocationInfo> getLocationInfo(@PathVariable(value = "id") long id){
        Optional<LocationInfo> locationInfo = locationInfoRepository.findById(id);
        return  ResponseEntity.ok().body(locationInfo.get());
    }

    @PostMapping("/saveLocation")
    public LocationInfo saveLocation(@RequestBody LocationInfo locationInfo){
        return locationInfoRepository.save(locationInfo);
    }

    @PutMapping("/updateLocationInfo")
    public ResponseEntity<LocationInfo> updateLocationInfo(@RequestBody LocationInfo locationInfo){
        Optional<LocationInfo> locationInfo2 = locationInfoRepository.findById(locationInfo.getId());
        LocationInfo locationInfo1 = locationInfo2.get();
        locationInfo1.setHeading(locationInfo.getHeading());
        locationInfo1.setDescription(locationInfo.getHeading());
        return ResponseEntity.ok(locationInfoRepository.save(locationInfo1));


    }

    @DeleteMapping("/deleteLocation/{id}")
    public Map<String, Boolean> deleteLocation(@PathVariable(value = "id") long id){
        Optional<LocationInfo> locationInfo = locationInfoRepository
                .findById(id);
        locationInfoRepository.delete(locationInfo.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<String> saveFile(@RequestBody MultipartFile file) {
        try {
            FileEntity fileEntity = new FileEntity();
            fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
            fileEntity.setData(file.getBytes());
            fileEntity.setContenttype(file.getContentType());
            fileRepository.save(fileEntity);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }

}
