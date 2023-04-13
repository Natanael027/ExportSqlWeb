package com.projek.DAO;

import com.projek.Entity.Data;

import java.util.List;

public interface DataDao {
    List<Data> findDatabyStatus3();
    int saveData(Data data);
    Data findDataById(Long id);
    int updateStatus(Integer id, Integer status);
    int updateDesc(Integer id, String desc);
}
