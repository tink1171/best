package com.kp.service;

import com.kp.model.site.Site;
import com.kp.repository.SiteRepository;
import com.kp.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 8/12/16.
 */
@Service
@Transactional
public class SiteServiceImpl implements SiteService {

    private SiteRepository repository;

    public Site findById(long id) {
        return repository.findById(id);
    }

    public Site findBySitename(String sitename) {
        return repository.findBySitename(sitename);
    }

    public Site findByTags(List<String> tags) {
        return null;
    }

    public void saveSite(Site site) {
        repository.save(site);
    }

    public void updateSite(Site site) {
        repository.save(site);
    }

    public void deleteSiteById(long id) {
        repository.delete(id);
    }

    public List<Site> findAllSites() {
        return repository.findAll();
    }

    public void deleteAllSites() {
        repository.deleteAll();
    }

    public boolean isSiteExist(Site sitename) {
        return (repository.findBySitename(sitename.getSitename())!=null);
    }

    
}
