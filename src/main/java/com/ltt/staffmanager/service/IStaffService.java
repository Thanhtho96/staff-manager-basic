package com.ltt.staffmanager.service;

import com.ltt.staffmanager.model.StaffEntity;

import java.util.Date;
import java.util.List;

public interface IStaffService {
    List<StaffEntity> getAllStaff();

    List<StaffEntity> searchByProperties(String name,
                                         Date startDate,
                                         Date endDate,
                                         String phonenumber,
                                         String address);

    StaffEntity getStaffById(long id);

    List<StaffEntity> getStaffByName(String name);

    void updateStaff(StaffEntity staffEntity);

    void deleteStaff(long id);

    void saveStaff(StaffEntity staffEntity);
}
