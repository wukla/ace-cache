package com.ace.cache.rest;

import com.ace.cache.service.ICacheManager;
import com.ace.cache.utils.TreeUtils;
import com.ace.cache.vo.CacheTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("cache")
public class CacheRest {
    @Autowired
    private ICacheManager cacheManager;

    @RequestMapping("/list")
    @ResponseBody
    public List<CacheTree> listAll() {
        return TreeUtils.buildTree(cacheManager.getAll());
    }

    @RequestMapping(path = "/pre/{pre:.*}", method = RequestMethod.GET)
    @ResponseBody
    public List<CacheTree> listPre(@PathVariable("pre") String pre) {
        return TreeUtils.buildTree(cacheManager.getByPre(pre));
    }

    @RequestMapping(path = "/{key:.*}", method = RequestMethod.GET)
    @ResponseBody
    public String get(@PathVariable("key") String key) {
        return cacheManager.get(key);
    }

    @RequestMapping(path = "/remove", method = {RequestMethod.DELETE})
    @ResponseBody
    public void removeAll() {
        cacheManager.removeAll();
    }

    @RequestMapping(path = "/pre/{pre:.*}", method = {RequestMethod.DELETE})
    @ResponseBody
    public void removePre(@PathVariable("pre") String pre) {
        cacheManager.removeByPre(pre);
    }

    @RequestMapping(path = "/{key:.*}", method = RequestMethod.DELETE)
    @ResponseBody
    public void removeKey(@PathVariable("key") String key) {
        cacheManager.remove(key);
    }

    @RequestMapping(path = "/{key:.*}", method = RequestMethod.PUT)
    @ResponseBody
    public void updateTime(@PathVariable("key") String key, int hour) {
        cacheManager.update(key, hour);
    }

}
