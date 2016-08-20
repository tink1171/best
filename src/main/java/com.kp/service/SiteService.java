package com.kp.service;

import com.kp.model.site.Site;

import java.util.List;

/**
 * Created by user on 8/20/16.
 */
public interface SiteService {
    Site findById(long id);
    Site findBySitename(String sitename);
    Site findByTags(List<String> tags);

    void saveSite(Site site);
    void updateSite(Site site);
    void deleteSiteById(long id);


    List<Site> findAllSites();
    void deleteAllSites();

    boolean isSiteExist(Site site);


}