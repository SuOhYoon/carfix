package com.carfix.testapi;

import com.carfix.common.GeoData;
import com.carfix.testapi.service.TestApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestApiController {

    private final TestApiService testApiService;

    private static double deg2rad(double deg){
        return (deg * Math.PI/180.0);
    }
    //radian(라디안)을 10진수로 변환
    private static double rad2deg(double rad){
        return (rad * 180 / Math.PI);
    }
    @GetMapping("/navermap")
    public String naverMapTestApi(){
        return "test/map";
    }


    @GetMapping("/geotest")
    public String googleMapTestApi(){
        testApiService.extractCoordinatesFromAddress();
        return "test/map";
    }

    // 현재 위치 정보 서버에서 출력
    @PostMapping("")
    @ResponseBody
    public Map<String, Object> receiveLocation(@RequestBody GeoData geoData) {
        GeoData mylocation = testApiService.extractCoordinatesFromAddress();
        // 수신된 데이터 처리
        double lat1 = mylocation.getLat();
        double lat2 = geoData.getLat();
        double lng1 = mylocation.getLng();
        double lng2 = geoData.getLng();

        System.out.println("Company2 위도: " + mylocation.getLat());
        System.out.println("Company2 경도: " + mylocation.getLng());
        System.out.println("위도: " + geoData.getLat());
        System.out.println("경도: " + geoData.getLng());

        double distance = testApiService.calcDistance(lat1, lat2, lng1, lng2);
        System.out.println("거리: " + distance + "km");
        // 수신 확인 응답 또는 다른 처리를 반환할 수 있습니다.
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);

        return map;
    }
}
