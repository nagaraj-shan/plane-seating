package com.squareshift.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareshift.dto.BookSeatDto;
import com.squareshift.dto.PlaneDto;
import com.squareshift.model.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class PlaneController {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    Map<Integer, Plane> planes = new HashMap<>();
    Map<Integer, PlaneDto> planeDtos = new HashMap<>();
    AtomicInteger idGen = new AtomicInteger(0);

    @Autowired
    public PlaneController() {
    }

    @GetMapping({"/index", "/"})
    public String index(Model model) {
        model.addAttribute("planes", planeDtos.values());
        return "index";
    }

    @GetMapping("/addplane")
    public String showAddPlane(PlaneDto planeDto) {
        return "add-plane";
    }

    @PostMapping("/addplane")
    public String addUser(@Valid PlaneDto planeDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-plane";
        }
        planeDto.setId(idGen.incrementAndGet());
        addPlane(planeDto, result);
        if (result.hasErrors()) {
            return "add-plane";
        }
        model.addAttribute("planes", planeDtos);
        return "redirect:/index";
    }


    private void addPlane(PlaneDto planeDto, BindingResult result) {
        try {
            int[][] seatBlocks = objectMapper.readValue(planeDto.getSeatBlocks(), int[][].class);
            Plane plane = Plane.newBuilder().withId(planeDto.getId()).withName(planeDto.getName())
                               .addSeatGroups(seatBlocks).build();
            planes.put(plane.getId(), plane);
            planeDtos.put(plane.getId(), planeDto);
        } catch (JsonProcessingException e) {
            result.rejectValue("seatBlocks", "InvalidSeatBlock", "Invalid Seat Blocks");
        }
    }


    @GetMapping("/view/{id}")
    public String viewPlane(@PathVariable("id") int id, Model model) {
        model.addAttribute("plane", planes.get(id));
        return "view-plane";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("planeDto", planeDtos.get(id));
        return "update-plane";
    }

    @PostMapping("/update/{id}")
    public String updatePlane(@PathVariable("id") int id, @Valid PlaneDto planeDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            planeDto.setId(id);
            return "update-plane";
        }
        model.addAttribute("planes", planeDtos);
        return "redirect:/index";
    }

    @PostMapping("/addPassenger/{id}")
    public String addPassenger(@PathVariable("id") int id, @Valid BookSeatDto bookSeatDto, BindingResult result, Model model) {
        Plane plane = planes.get(id);
        model.addAttribute("plane", plane);
        if (plane == null || result.hasErrors()) {
            result.rejectValue("planeId", "Invalid Plane");
            return "view-plane";
        }
        try {
            plane.bookSeat(bookSeatDto.getNumberOfSeats());
        } catch (Exception e){
            model.addAttribute("error", e.getMessage());
        }

        return "view-plane";
    }

    @GetMapping("/delete/{id}")
    public String deletePlane(@PathVariable("id") int id, Model model) {
        planeDtos.remove(id);
        planes.remove(id);
        model.addAttribute("planes", planeDtos);
        return "index";
    }
}
