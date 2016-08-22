package com.kp.service;

import com.kp.model.site.Page;

/**
 * Created by user on 8/12/16.
 */
public interface PageService {
    void deletePageById(long id);

    void savePage(Page page);
    Page findById(long id);

}
