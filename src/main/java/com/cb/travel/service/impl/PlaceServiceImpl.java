package com.cb.travel.service.impl;

import com.cb.travel.dao.PlaceDAO;
import com.cb.travel.dao.ProvinceDAO;
import com.cb.travel.entity.Place;
import com.cb.travel.entity.Province;
import com.cb.travel.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Controller
@Transactional
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceDAO placeDao;
    @Autowired
    private ProvinceDAO provinceDAO;

    @Override
    public List<Place> findByProvinceIdPage(Integer page, Integer rows, String provinceId) {
        int start = (page - 1) * rows;
        return placeDao.findByProvinceIdPage(start, rows, provinceId);
    }

    @Override
    public Integer findByProvinceIdCounts(String provinceId) {
        return placeDao.findByProvinceIdCounts(provinceId);
    }

    @Override
    public void save(Place place) {
        // 保存景点信息后, 要更新对应省份的景点个数 +1
        placeDao.save(place);
        // 查询原始省份信息
        Province province = provinceDAO.findOne(place.getProvinceid());
        // 更新省份信息的景点个数
        province.setPlacecounts(province.getPlacecounts() + 1);
        provinceDAO.update(province);
    }

    @Override
    public void delete(String id) {
        // 不能直接删除景点, 要先让省份中的景点个数 -1, 再删除景点
        Place place = placeDao.findOne(id);
        Province province = provinceDAO.findOne(place.getProvinceid());
        province.setPlacecounts(province.getPlacecounts() - 1); // 让省份的景点个数 -1
        provinceDAO.update(province);
        // 删除景点信息
        placeDao.delete(id);
    }

    @Override
    public Place findOne(String id) {
        return placeDao.findOne(id);
    }

    @Override
    public void update(Place place) {
        placeDao.update(place);
    }
}
