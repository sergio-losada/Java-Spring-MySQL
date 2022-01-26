package com.example.javaspringmysql.controller;

import java.util.ArrayList;

import com.example.javaspringmysql.dto.Profile;
import com.example.javaspringmysql.dto.Views;
import com.example.javaspringmysql.service.ProfileService;
import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class ProfileController {
    
    @Autowired
    private ProfileService profileService;

    /**
     * GET OPERATION 
     * @return all Profiles (JSON Array), 200 OK
     */
    @GetMapping(produces = "application/json")
    @JsonView(Views.ParsedProfile.class)
    public ResponseEntity<ArrayList<Profile>> get(){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.get());
    }

    /**
     * POST OPERATION
     * @param profile request body (Profile to insert)
     * @return inserted Profile (JSON), 201 CREATED or 409 CONFLICT
     */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Profile> post(@RequestBody Profile profile){ 
        if(profileService.getByKey(profile.getUserId()).isPresent())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); 
        else {
            this.profileService.post(profile);
            return ResponseEntity.status(HttpStatus.CREATED).body(profile);
        } 
    }

    /**
     * PUT OPERATION
     * @param profile request body (Profile to update)
     * @param userId path variable (userId of the profile to update)
     * @return updated Profile (JSON), 200 OK or 404 NOT FOUND
     */
    @PutMapping(value = "{userId}", consumes = "application/json")
    public ResponseEntity<Profile> put(@RequestBody Profile profile, @PathVariable("userId") String userId){
        if(!profileService.getByKey(userId).isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
        else {
            this.profileService.put(profile, userId);
            return ResponseEntity.status(HttpStatus.OK).body(profile); 
        }
    }

    /**
     * DELETE OPERATION
     * @param userId path variable (userId of the profile to delete)
     * @return empty response body, 204 NO CONTENT or 404 NOT FOUND
     */
    @DeleteMapping("{userId}")
    public ResponseEntity<Void> delete(@PathVariable("userId") String userId){
        if(!profileService.getByKey(userId).isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        else {
            this.profileService.delete(userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    /**
     * GET BY KEY OPERATION
     * @param userId path variable (userId of the profile to retrieved)
     * @return Profile that matchs the key (JSON), 200 OK or 404 NOT FOUND 
     */
    @GetMapping(value = "{userId}", produces = "application/json")
    public ResponseEntity<Profile> getByKey(@PathVariable("userId") String userId){
        if(!profileService.getByKey(userId).isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);    
        else 
            return ResponseEntity.status(HttpStatus.OK).body(profileService.getByKey(userId).get());
    }

}
