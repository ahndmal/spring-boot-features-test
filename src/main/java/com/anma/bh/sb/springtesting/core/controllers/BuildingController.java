package com.anma.bh.sb.springtesting.core.controllers;

import com.anma.bh.sb.springtesting.core.models.Building;
import com.anma.bh.sb.springtesting.core.repositories.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class BuildingController {
    private final JdbcTemplate jdbcTemplate;
    private final BuildingRepository buildingRepository;

    @Autowired
    public BuildingController(JdbcTemplate jdbcTemplate, BuildingRepository buildingRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.buildingRepository = buildingRepository;
    }

    @GetMapping("/building")
    public String getBuildingsPage(Model model, @RequestParam(defaultValue = "2") int entrance) {

        Pageable buildingPageable = PageRequest.of(1,10);
        Page<Building> pagedBuildings = buildingRepository.findAll(buildingPageable);
        List<Building> buildingsNumber12 = buildingRepository.findAllByEntrance(entrance, buildingPageable);

        List<Map<String, Object>> buildings = jdbcTemplate.query("select * from buildings", new Building[] {}, new ColumnMapRowMapper());

        System.out.println("**** " + buildings);

        model.addAttribute("buildings", buildings);
        model.addAttribute("buildingsNumber12", buildingsNumber12);

        return "building";
    }


}
