package com.cb.travel.service.impl;


import com.cb.travel.dao.ProvinceDAO;
import com.cb.travel.entity.Province;
import com.cb.travel.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
    public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceDAO provinceDAO;

    @Override
    public List<Province> findByPage(Integer page, Integer rows) {
        int start = (page - 1) * rows;
        return provinceDAO.findByPage(start, rows);
    }

    @Override
    public Integer findTotals() {
        return provinceDAO.findTotals();
    }

    @Override
    public void save(Province province) {
        province.setPlacecounts(0);
        provinceDAO.save(province);
    }

    @Override
    public void delete(String id) {
        provinceDAO.delete(id);
    }

    @Override
    public Province findOne(String id) {
        return provinceDAO.findOne(id);
    }

    @Override
    public void update(Province province) {
        provinceDAO.update(province);
    }


}
