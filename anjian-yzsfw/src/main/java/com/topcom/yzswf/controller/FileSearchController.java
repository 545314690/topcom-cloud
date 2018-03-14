package com.topcom.yzswf.controller;

import com.topcom.yzswf.service.EsFileService;
import com.topcom.yzswf.util.Page;
import com.topcom.yzswf.vo.FileQueryVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by lism on 17-10-31.
 *
 * @author lism
 */
@RestController
@RequestMapping("fileSearch")
public class FileSearchController {

    @Autowired
    private EsFileService esFileService;

    @ApiOperation("fileSearch")
    @RequestMapping(
            value = {"/search"},
            method = {RequestMethod.POST},
            produces = {"application/json"}
    )
    public Page search(@RequestBody  FileQueryVO fileQueryVO) throws Exception {
        return esFileService.search(fileQueryVO);
    }
    @ApiOperation("download")
    @RequestMapping(
            value = {"/download/{id}"},
            method = {RequestMethod.GET}
    )
    public void download(@PathVariable String id) throws Exception {
        esFileService.downloadTimesPlusOne(id);
    }
}
