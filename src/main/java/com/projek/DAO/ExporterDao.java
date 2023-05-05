package com.projek.DAO;

import com.projek.Entity.Exporter;

import java.util.List;

public interface ExporterDao {
    int createCCA();
    int saveCCA();
    int tesInsert(String query);


    List<Exporter> findExporterbyStatus3();
    int saveExporter(Exporter data);
    Exporter findExporterById(Long id);
    int updateStatus(Integer id, Integer status);
    int updateDesc(Integer id, String desc);
}
