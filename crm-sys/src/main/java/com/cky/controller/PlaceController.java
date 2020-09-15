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

    @GetMapping(value = "/states/{countriesCode}")
    public ResJSONBean getStates(@PathVariable("countriesCode") String countriesCode) {
        ResJSONBean res = ResJSONBean.Success("");
        try {
            res.setData(placeService.selectStatesByParentCode(countriesCode));
        } catch (Exception ex) {
            log.error("getStates error : ", ex.getMessage());
            res = ResJSONBean.error(ex.getMessage());
        }
        return res;
    }

    @GetMapping(value = "/cities/{countriesCode}/{statesCode}")
    public ResJSONBean getCities(@PathVariable("countriesCode") String countriesCode, @PathVariable("statesCode") String statesCode) {
        ResJSONBean res = ResJSONBean.Success("");
        try {
            res.setData(placeService.selectCitiesByParentCode(countriesCode, statesCode));
        } catch (Exception ex) {
            log.error("getCities error : ", ex.getMessage());
            res = ResJSONBean.error(ex.getMessage());
        }
        return res;
    }

}
