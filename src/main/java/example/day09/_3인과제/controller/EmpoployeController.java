package example.day09._3인과제.controller;

import example.day09._3인과제.dao.EmpoployeDao;
import example.day09._3인과제.dto.EmpoployeDto;
import example.day09._3인과제.dto.PointDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmpoployeController {
    @Autowired
    EmpoployeDao empoployeDao;

    @PostMapping("/create")
    public boolean ecreate(EmpoployeDto empoployeDto){
        System.out.println("EmpoployeController.ecreate");

        boolean result = empoployeDao.ecreate(empoployeDto);

        return result;
    }

    @GetMapping("/read")
    public ArrayList<EmpoployeDto> eread(){
        System.out.println("EmpoployeController.eread");

        ArrayList<EmpoployeDto> result = empoployeDao.eread();

        return result;
    }

    @PostMapping("/update")
    public boolean eupdate(EmpoployeDto empoployeDto ){
        System.out.println("EmpoployeController.eupdate");

        boolean result = empoployeDao.eupdate( empoployeDto);

        return result;
    }

    @GetMapping("/delete/{eno}")
    public boolean edelete(@PathVariable int eno){
        System.out.println("EmpoployeController.edelete");

        boolean result = empoployeDao.edelete(eno);

        return result;
    }

    @PostMapping("/point")
    public List<PointDto> pointRead(PointDto pointDto){
        System.out.println("EmpoployeController.pointread");

        List<PointDto> result = empoployeDao.pointRead(pointDto);

        return result;
    }

    @PostMapping("/point/create")
    public boolean pointCreate(PointDto pointDto){
        System.out.println("EmpoployeController.pointCreate");

        boolean result = empoployeDao.pointCreate(pointDto);

        return result;
    }

    @PostMapping("/point/read")
    public String findName(PointDto pointDto){
        System.out.println("EmpoployeController.findName");

        String result = empoployeDao.findName(pointDto);

        return result;
    }
}
