package org.but.feec.bds.services;

import org.but.feec.bds.api.SqlInjectionStaffView;
import org.but.feec.bds.data.SqlInjectionStaffRepository;

import java.util.List;

public class SqlInjectionStaffService {
    private SqlInjectionStaffRepository sqlInjectionStaffRepository;

    public SqlInjectionStaffService(SqlInjectionStaffRepository sqlInjectionStaffRepository) {
        this.sqlInjectionStaffRepository = sqlInjectionStaffRepository;
    }

    public List<SqlInjectionStaffView> getSqlInjectionStaffView(String username, String password) {
       return sqlInjectionStaffRepository.getSqlInjectionStaffView(username, password);
    }
}
