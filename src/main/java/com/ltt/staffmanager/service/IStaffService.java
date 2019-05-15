package com.ltt.staffmanager.service;

import com.ltt.staffmanager.model.StaffEntity;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IStaffService {
    List<StaffEntity> getAllStaff();

    List<StaffEntity> searchByProperties(@Param("name") String name,
                                         @Param("startDate") Date startDate,
                                         @Param("endDate") Date endDate,
                                         @Param("phonenumber") String phonenumber,
                                         @Param("address") String address);

    StaffEntity getStaffById(long id);

    List<StaffEntity> getStaffByName(String name);

    void updateStaff(StaffEntity staffEntity);

    void deleteStaff(long id);

    void saveStaff(StaffEntity staffEntity);
}
