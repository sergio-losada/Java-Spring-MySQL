package com.example.javaspringmysql.mock;

import java.util.Optional;

import com.example.javaspringmysql.dto.Profile;
import com.example.javaspringmysql.service.ProfileService;
import com.example.javaspringmysql.util.Status;

import java.util.ArrayList;
import java.util.Arrays;

import org.mockito.Mockito;

public class MockingProfile {
    public static void mockingApi(ProfileService profileService) {
        get(profileService);
        post(profileService);
        put(profileService);
        delete(profileService);
        getByKey(profileService);
    }

    public static void get(ProfileService profileService) {
        Profile p1 = new Profile("id1", "mockingGet1", Status.active);
        Profile p2 = new Profile("id2", "mockingGet2", Status.inactive);
        ArrayList<Profile> list = new ArrayList<>(Arrays.asList(p1, p2));
        // Return the list whenever GET is invoked
        Mockito.when(profileService.get()).thenReturn(list);
    }

    public static void post(ProfileService profileService) {
        Profile profile = new Profile("id3", "mockingPost3", Status.active);
        // Return the Api object whenever POST is invoked
        Mockito.when(profileService.post(Mockito.any(Profile.class))).thenReturn(profile);
    }

    public static void put(ProfileService profileService) {
        Profile profile = new Profile("id4", "mockingPut4", Status.inactive);
        // Return the Api object whenever PUT is invoked
        Mockito.when(profileService.put(Mockito.any(Profile.class), Mockito.anyString())).thenReturn(profile);
    }

    public static void delete(ProfileService profileService) {
        Mockito.doAnswer(invocation -> {
            return invocation;
        }).when(profileService).delete(Mockito.anyString());
    }

    public static void getByKey(ProfileService profileService) {
        Profile p1 = new Profile("id1", "mockingGetByKey", Status.away);
        // Return the Api object whenever GETBYKEY is invoked
        Mockito.when(profileService.getByKey(Mockito.anyString())).thenReturn(Optional.of(p1));
    }

}
