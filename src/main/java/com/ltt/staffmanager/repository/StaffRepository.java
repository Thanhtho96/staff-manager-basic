package com.ltt.staffmanager.repository;

import com.ltt.staffmanager.model.StaffEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface StaffRepository extends CrudRepository<StaffEntity, Long> {
    List<StaffEntity> findByName(String name);

    @Query("from StaffEntity st where st.name like %:name% " +
            "and ( st.birthday >= :startDate) " +
            "and ( st.birthday <= :endDate) " +
            "and st.phonenumber like %:phonenumber% " +
            "and st.address like %:address%")
//    @Query(value = "select * from staff where name like :name " +
//            "and ( birthday >= :startDate)" +
//            "and ( birthday <= :endDate)" +
//            "and phonenumber like :phonenumber and address like :address", nativeQuery = true)
    List<StaffEntity> searchByProperties(@Param("name") String name,
                                         @Param("startDate") Date startDate,
                                         @Param("endDate") Date endDate,
                                         @Param("phonenumber") String phonenumber,
                                         @Param("address") String address);


    @Query("from StaffEntity st where st.name like %:name% " +
            "and (concat(st.birthday, '') like %:birthday%) and st.phonenumber like %:phonenumber% and st.address like %:address%")
    List<StaffEntity> searchByProperties(@Param("name") String name,
                                         @Param("birthday") String birthday,
                                         @Param("phonenumber") String phonenumber,
                                         @Param("address") String address);

//    @Query("from StaffEntity st where st.name like %:name% " +
//            "and st.phonenumber like %:phonenumber% and st.address like %:address%")
//    List<StaffEntity> searchNotBirthday(@Param("name") String name,
//                                         @Param("phonenumber") String phonenumber,
//                                         @Param("address") String address);
}
