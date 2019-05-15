package com.ltt.staffmanager.service;

import com.ltt.staffmanager.model.StaffEntity;
import com.ltt.staffmanager.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StaffService implements IStaffService {
    @Autowired
    private StaffRepository staffRepository;

    @Override
    public List<StaffEntity> getAllStaff() {
        return (ArrayList<StaffEntity>) staffRepository.findAll();
    }

    @Override
    public List<StaffEntity> searchByProperties(String name, Date startDate, Date endDate, String phonenumber, String address) {
        return staffRepository.searchByProperties(name, startDate, endDate, phonenumber, address);
    }

    @Override
    public List<StaffEntity> searchByProperties(String name, String birthday, String phonenumber, String address) {
        return staffRepository.searchByProperties(name, birthday, phonenumber, address);
    }

    @Override
    public StaffEntity getStaffById(long id) {
        StaffEntity obj = staffRepository.findById(id).get();
        return obj;
    }

    @Override
    public List<StaffEntity> getStaffByName(String name) {
        ArrayList<StaffEntity> listStaff = new ArrayList<>();
        staffRepository.findByName(name).forEach(e -> listStaff.add(e));
        return listStaff;
    }

    @Override
    public void updateStaff(StaffEntity staffEntity) {
        staffRepository.save(staffEntity);
    }

    @Override
    public void deleteStaff(long id) {
        staffRepository.delete(getStaffById(id));
    }

    @Override
    public void saveStaff(StaffEntity staffEntity) {
        staffRepository.save(staffEntity);
    }

}
