package com.projek.DAO;

import com.projek.Entity.Exporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExporterDaoImpl implements ExporterDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveExporter(Exporter exporter) {
        return 1;
     }

    @Override
    public Exporter findExporterById(Long id) {
        return new Exporter();
     }

    @Override
    public int updateStatus(Integer id, Integer status) {
        String sql = "UPDATE exporter SET status=? WHERE id = ? ";
        return jdbcTemplate.update(sql, status, id);
    }

    @Override
    public int updateDesc(Integer id, String description) {
        String sql = "UPDATE exporter SET description='"+description+"' WHERE id = ? ";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int createCCA() {
        String sql =
                """
  CREATE TABLE `messagemt_tes` (
  `countid` int(11) NOT NULL AUTO_INCREMENT,
  `messageid` varchar(50) NOT NULL,
  `campaignid` int(11) NOT NULL DEFAULT '0',
  `userid` varchar(200) NOT NULL DEFAULT ' ' COMMENT 'application_keys.app_key',
  `original` varchar(20) NOT NULL,
  `sendto` varchar(20) NOT NULL,
  `messagetype` int(11) NOT NULL DEFAULT '0' COMMENT '0/1-7bit, 2-flash, 4-wap, 8-8bit, 16-UCS2',
  `message` text NOT NULL,
  `mediaurl` text,
  `ston` int(11) NOT NULL DEFAULT '0',
  `snpi` int(11) NOT NULL DEFAULT '0',
  `dton` int(11) NOT NULL DEFAULT '0',
  `dnpi` int(11) NOT NULL DEFAULT '0',
  `dcs` int(11) NOT NULL DEFAULT '0',
  `udhl` int(11) NOT NULL DEFAULT '0',
  `destport` int(11) NOT NULL DEFAULT '0',
  `origport` int(11) NOT NULL DEFAULT '0',
  `refnum` int(11) NOT NULL DEFAULT '0',
  `segseq` int(11) NOT NULL DEFAULT '0',
  `totseg` int(11) NOT NULL DEFAULT '0',
  `ispush` bit(1) NOT NULL DEFAULT b'1',
  `replyto` text,
  `keyword` varchar(20) DEFAULT NULL,
  `receivedate` datetime NOT NULL,
  `sentdate` datetime DEFAULT NULL,
  `sentstatus` varchar(50) DEFAULT NULL,
  `delivereddate` datetime DEFAULT NULL,
  `deliveredstatus` varchar(50) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '00 = new , 10 = call ,12 = result',
  `priority` int(11) NOT NULL DEFAULT '5',
  `startdate` datetime DEFAULT NULL,
  `enddate` datetime DEFAULT NULL,
  `oprid` int(11) NOT NULL DEFAULT '0',
  `fromcp` tinyint(1) NOT NULL DEFAULT '0',
  `fromchannel` varchar(30) NOT NULL DEFAULT '*',
  `tochannel` varchar(30) NOT NULL DEFAULT '*',
  `thirdid` varchar(50) DEFAULT NULL,
  `jenis` tinyint(4) DEFAULT '0' COMMENT 'jenis sms. 0 regular, 1 premium',
  `smsprice` int(11) NOT NULL DEFAULT '0',
  `prefix_code` varchar(20) DEFAULT NULL,
  `date_10` datetime DEFAULT NULL,
  `date_20` datetime DEFAULT NULL,
  `date_30` datetime DEFAULT NULL,
  `date_90` datetime DEFAULT NULL,
                PRIMARY KEY (`countid`),
                KEY `thirdid` (`thirdid`),
        KEY `messageid` (`messageid`),
        KEY `status_fromchannel` (`status`,`fromchannel`),
        KEY `priority` (`priority`),
        KEY `startdate_enddate` (`startdate`,`enddate`),
        KEY `userid` (`userid`),
        KEY `sendto` (`sendto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
""";

        return jdbcTemplate.update(sql);
    }

    @Override
    public int saveCCA() {
        String sql =
              "INSERT INTO `messagemt_tes` (`messageid`, `campaignid`, `userid`, `original`, `sendto`, `messagetype`, `message`, `ston`, `snpi`, `dton`, `dnpi`, `dcs`, `udhl`, `destport`, `origport`, `refnum`, `segseq`, `totseg`, `ispush`, `replyto`, `keyword`, `receivedate`, `sentdate`, `sentstatus`, `delivereddate`, `deliveredstatus`, `status`, `priority`, `startdate`, `enddate`, `oprid`, `fromcp`, `fromchannel`, `tochannel`, `thirdid`, `jenis`, `smsprice`, `prefix_code`, `date_10`, `date_20`, `date_30`, `date_90`) VALUES \n"+
                "('BATCH-0205-_123455-null-cd0b19f6',	0,	'checkNumber',	'whatsapp',	'12345',	0,	'Selamat Siang',	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	1,	NULL,	NULL,	NOW(),	NULL,	NULL,	NULL,	NULL,	0,	5,	NOW(),	NOW(),	0,	0,	'',	'',	NULL,	4,	0,	'operator',	NULL,	NULL,	NULL,	NULL)";

        return jdbcTemplate.update(sql);
    }

    @Override
    public int tesInsert(String query) {
        return jdbcTemplate.update(query);
    }


    @Override
    public List<Exporter> findExporterbyStatus3() {
        return jdbcTemplate.query("SELECT * from exporter where status=3", BeanPropertyRowMapper.newInstance(Exporter.class));
    }

}
