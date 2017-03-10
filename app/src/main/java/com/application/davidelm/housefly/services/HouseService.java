package com.application.davidelm.housefly.services;


import com.application.davidelm.housefly.model.House;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HouseService {
    @GET("repos/{owner}/{repo}/stargazers")
    Observable<ArrayList<House>> getStargazers(@Path("owner") String owner, @Path("repo") String repo);

}
