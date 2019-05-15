package com.ltt.staffmanager.repository;

import com.ltt.staffmanager.model.StaffEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface StaffRepository extends CrudRepository<StaffEntity, Long> {
    List<StaffEntity> findByName(String name);

    @Query(value = "select * from staff st where unaccent(st.name) ilike %:name% " +
            "and ( st.birthday >= :startDate) " +
            "and ( st.birthday <= :endDate) " +
            "and st.phonenumber like %:phonenumber% and unaccent(st.address) ilike %:address%", nativeQuery = true)
    List<StaffEntity> searchByProperties(@Param("name") String name,
                                   @Param("startDate") Date startDate,
                                   @Param("endDate") Date endDate,
                                   @Param("phonenumber") String phonenumber,
                                   @Param("address") String address);


}
