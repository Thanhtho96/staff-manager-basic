package com.ltt.staffmanager.controller;

import com.ltt.staffmanager.model.StaffEntity;
import com.ltt.staffmanager.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("/")
public class StaffController {
    @Autowired
    StaffService staffService;

    ArrayList<StaffEntity> listStaff;

    @RequestMapping({"/listStaff", "/"})
    public String listStudent(Model model) {
        listStaff = (ArrayList<StaffEntity>) staffService.getAllStaff();
        model.addAttribute("listStaff", listStaff);
        return "listStaff";
    }

    @PostMapping("/addStaff")
    public String showAddStaffForm() {
        return "addStaff";
    }

    @PostMapping("/saveStaff")
    public String saveStaff(StaffEntity staffEntity, Model model,
                            @RequestParam String newName,
                            @RequestParam String newBirthday,
                            @RequestParam String newPhoneNumber,
                            @RequestParam String newAddress) {
        staffEntity.setName(newName);
        try {
            staffEntity.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(newBirthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        staffEntity.setPhonenumber(newPhoneNumber);
        staffEntity.setAddress(newAddress);

        staffService.saveStaff(staffEntity);

        model.addAttribute("listStaff", staffService.getAllStaff());
        return "redirect:/listStaff";
    }


    @PostMapping("/editStaff/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        StaffEntity staff = staffService.getStaffById(id);
        model.addAttribute("staff", staff);
        return "updateStaff";
    }

    @PostMapping("/updateStaff/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid StaffEntity staffEntity,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            staffEntity.setId(id);
            return "updateStaff";
        }
        staffService.updateStaff(staffEntity);
        model.addAttribute("listStaff", staffService.getAllStaff());
        return "redirect:/listStaff";
    }

    @PostMapping("/deleteStaff/{id}")
    public String deleteStaff(@PathVariable("id") long id, Model model) {
        staffService.deleteStaff(id);
        model.addAttribute("listStaff", staffService.getAllStaff());
        return "redirect:/listStaff";
    }

    @PostMapping("/advanceSearch")
    public String getStudent(Model model,
                             @Param("name") String name,
                             @Param("startDate") String startDate,
                             @Param("endDate") String endDate,
                             @Param("phonenumber") String phonenumber,
                             @Param("address") String address) {
        Date startDateTemp = null, endDateTemp = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            if (startDate.equals("") && !endDate.equals("")) {
                startDateTemp = simpleDateFormat.parse("1970-01-01");
                endDateTemp = simpleDateFormat.parse(endDate);
            } else if (!startDate.equals("") && endDate.equals("")) {
                startDateTemp = simpleDateFormat.parse(startDate);
                Date date = new Date();
                endDateTemp = simpleDateFormat.parse(simpleDateFormat.format(date));
            } else if (startDate.equals("") && endDate.equals("")) {
                startDateTemp = simpleDateFormat.parse("1970-01-01");
                Date date = new Date();
                endDateTemp = simpleDateFormat.parse(simpleDateFormat.format(date));
            } else {
                endDateTemp = simpleDateFormat.parse(endDate);
                startDateTemp = simpleDateFormat.parse(startDate);
            }
        } catch (ParseException e) {
        }

        listStaff = (ArrayList<StaffEntity>) staffService.searchByProperties(
                name, startDateTemp, endDateTemp, phonenumber, address);

//      listStaff = (ArrayList<StaffEntity>) staffService.searchByProperties(name, birthday, phonenumber, address);
//        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createAccountModel", result);

        model.addAttribute("listStaff", listStaff);
        return "listStaff";
    }

}
