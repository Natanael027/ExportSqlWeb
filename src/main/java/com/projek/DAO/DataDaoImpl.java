package com.projek.DAO;

import com.projek.Entity.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataDaoImpl implements DataDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveData(Data data) {
        boolean isUpdating = (data.getId() != null);
        System.out.println("id :"+data.getId()  + " isupdating :"+ isUpdating);
        if (isUpdating) {
            System.out.println(data.toString());
            String sql =
                    "UPDATE data SET nomor_tlp=?, status=?, path=?, description=?, updated_at=?, updated_by=? WHERE id=?";
            return jdbcTemplate.update(sql, new Object[]{
                    data.getNomor_tlp(),
                    data.getStatus(),
                    data.getPath(),
                    data.getDescription(),
                    data.getUpdated_at(),
                    data.getUpdated_by(),
                    data.getId()
            });
        }else {
            String sql =
                    "INSERT INTO `data` (`nomor_tlp`, `status`, `path`, `description`, `updated_at`,`updated_by`) VALUES(?,?,?,?,?,?)";
            return jdbcTemplate.update(sql, new Object[]{
                    data.getNomor_tlp(),
                    data.getStatus(),
                    data.getPath(),
                    data.getDescription(),
                    data.getUpdated_at(),
                    data.getUpdated_by()
            });
        }
    }

    @Override
    public Data findDataById(Long id) {
        try {
            Data data = jdbcTemplate.queryForObject("SELECT * FROM data WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Data.class), id);

            return data;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }    }

    @Override
    public int updateStatus(Integer id, Integer status) {
        String sql = "UPDATE data SET status=? WHERE id = ? ";
        return jdbcTemplate.update(sql, status, id);
    }

    @Override
    public int updateDesc(Integer id, String description) {
        String sql = "UPDATE data SET description='"+description+"' WHERE id = ? ";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Data> findDatabyStatus3() {
        return jdbcTemplate.query("SELECT * from data where status=3", BeanPropertyRowMapper.newInstance(Data.class));
    }

}
