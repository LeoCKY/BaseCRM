package com.cky.controller;

import com.cky.base.controller.BaseController;
import com.cky.base.res.ResJSONBean;
import com.cky.service.PlaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@Slf4j
@RequestMapping("/place")
public class PlaceController extends BaseController {

    @Autowired
    private PlaceService placeService;


    @GetMapping(value = "/countries")
    public ResJSONBean getCountries() {
        ResJSONBean res = ResJSONBean.Success("");
        try {
            res.setData(placeService.selectCountriesAll());
        } catch (Exception ex) {
            log.error("getCountries error : ", ex.getMessage());
            res = ResJSONBean.error(ex.getMessage());
        }
        return res;
    }

    @GetMapping(value = "/states/{countriesId}")
    public ResJSONBean getStates(@PathVariable("countriesId") String countriesId) {
        ResJSONBean res = ResJSONBean.Success("");
        try {
            res.setData(placeService.selectStatesByParentId(countriesId));
        } catch (Exception ex) {
            log.error("getStates error : ", ex.getMessage());
            res = ResJSONBean.error(ex.getMessage());
        }
        return res;
    }

    @GetMapping(value = "/cities/{countriesId}/{statesId}")
    public ResJSONBean getCities(@PathVariable("countriesId") String countriesId, @PathVariable("statesId") String statesId) {
        ResJSONBean res = ResJSONBean.Success("");
        try {
            res.setData(placeService.selectCitiesByParentId(countriesId, statesId));
        } catch (Exception ex) {
            log.error("getCities error : ", ex.getMessage());
            res = ResJSONBean.error(ex.getMessage());
        }
        return res;
    }

}
