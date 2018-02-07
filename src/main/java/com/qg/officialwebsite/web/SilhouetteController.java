package com.qg.officialwebsite.web;

import com.qg.officialwebsite.domain.Silhouette;
import com.qg.officialwebsite.domain.SilhouetteMapper;
import com.qg.officialwebsite.domain.SilhouetteType;
import com.qg.officialwebsite.domain.SilhouetteTypeMapper;
import com.qg.officialwebsite.dto.Result;
import com.qg.officialwebsite.enums.StateEnum;
import com.qg.officialwebsite.exception.FileFormatException;
import com.qg.officialwebsite.exception.ParamException;
import com.qg.officialwebsite.exception.SilhouetteException;
import com.qg.officialwebsite.service.impl.SilhouetteServiceImpl;
import com.qg.officialwebsite.service.impl.SilhouetteTypeServiceImpl;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @author 小铭
 * Date: 2018/2/7
 * Time: 19:13
 * No struggle, talent how to match the willfulness.
 * Description: 剪影管理控制器
 */
@RestController
@CrossOrigin
@RequestMapping("/silhouette")
public class SilhouetteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SilhouetteController.class);

    private final SilhouetteTypeServiceImpl silhouetteTypeService;

    private final SilhouetteServiceImpl silhouetteService;

    private final SilhouetteTypeMapper silhouetteTypeMapper;

    private final SilhouetteMapper silhouetteMapper;

    @Autowired
    public SilhouetteController(SilhouetteServiceImpl silhouetteService, SilhouetteTypeServiceImpl silhouetteTypeService, SilhouetteTypeMapper
            silhouetteTypeMapper, SilhouetteMapper silhouetteMapper) {
        this.silhouetteService = silhouetteService;
        this.silhouetteTypeService = silhouetteTypeService;
        this.silhouetteTypeMapper = silhouetteTypeMapper;
        this.silhouetteMapper = silhouetteMapper;
    }

    /**
     * 添加剪影
     *
     * @param request 请求信息
     * @param silhouettePhoto 剪影照片
     * @return Result结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addSilhouette(HttpServletRequest request, @RequestParam("silhouettePhoto") MultipartFile silhouettePhoto) {
        Map<String, String[]> map = request.getParameterMap();

        if (!map.containsKey("silhouetteDescription") || !map.containsKey("silhouetteTypeName") || !map.containsKey("silhouetteTime")) {
            LOGGER.warn("参数缺失");
            throw new ParamException(StateEnum.PARAM_IS_LOST);
        }

        // 剪影描述
        String silhouetteDescription = request.getParameter("silhouetteDescription");
        // 剪影类别名称
        String silhouetteTypeName = request.getParameter("silhouetteTypeName");
        // 剪影时间
        String silhouetteTime = request.getParameter("silhouetteTime");

        if (silhouetteDescription == null || "".equals(silhouetteDescription)
                || silhouetteTypeName == null || "".equals(silhouetteTypeName)) {
            LOGGER.warn("参数为空");
            throw new ParamException(StateEnum.PARAM_IS_EMPTY);
        }

        SilhouetteType silhouetteType = silhouetteTypeMapper.selectTypeByName(silhouetteTypeName);
        if (silhouetteType == null) {
            LOGGER.warn("剪影类别不存在");
            throw new SilhouetteException(StateEnum.SILHOUETTE_TYPE_NOT_EXIST);
        }

        Silhouette silhouette = new Silhouette();
        silhouette.setSilhouetteDescription(silhouetteDescription);
        silhouette.setSilhouetteTime(silhouetteTime);
        silhouette.setSilhouetteType(silhouetteType);
        if (!silhouettePhoto.isEmpty()) {
            String filename = silhouettePhoto.getOriginalFilename();
            try {
                if (filename.endsWith(".jpg") || filename.endsWith(".JPG") || filename.endsWith(".png") || filename.endsWith("PNG")) {
                    // 换成以类别加UUID命名
                    filename = silhouetteTypeName + UUID.randomUUID() + filename.substring(filename.length() - 4);
                    FileUtils.copyInputStreamToFile(silhouettePhoto.getInputStream(),
                            new File(request.getServletContext().getRealPath("/silhouettePhotos"), filename));
                    silhouette.setSilhouettePath("/silhouettePhotos/" + filename);
                } else {
                    LOGGER.warn("文件格式错误");
                    throw new FileFormatException(StateEnum.FILE_FORMAT_ERROR);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return silhouetteService.addSilhouette(silhouette);
    }

    /**
     * 删除剪影
     *
     * @param map map，含有剪影ID
     * @return Result结果
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json")
    public Result deleteSilhouette(@RequestBody Map<String, Integer> map) {
        System.out.println(map);
        return silhouetteService.deleteSilhouette(map.get("id"));
    }

    /**
     * 修改剪影
     *
     * @param request 请求信息
     * @param silhouettePhoto 剪影照片
     * @return Result结果
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateSilhouette(HttpServletRequest request, @RequestParam("silhouettePhoto") MultipartFile silhouettePhoto) {
        // 剪影ID
        int silhouetteId = Integer.parseInt(request.getParameter("id"));
        // 剪影描述
        String silhouetteDescription = request.getParameter("silhouetteDescription");
        // 剪影类别名称
        String silhouetteTypeName = request.getParameter("silhouetteTypeName");
        // 剪影时间
        String silhouetteTime = request.getParameter("silhouetteTime");

        if (null == silhouetteMapper.selectSilhouetteById(silhouetteId)) {
            LOGGER.warn("剪影不存在");
            throw new SilhouetteException(StateEnum.SILHOUETTE_NOT_EXIST);
        }

        if (silhouetteDescription == null || "".equals(silhouetteDescription)
                || silhouetteTypeName == null || "".equals(silhouetteTypeName)) {
            LOGGER.warn("参数为空");
            throw new ParamException(StateEnum.PARAM_IS_EMPTY);
        }

        SilhouetteType silhouetteType = silhouetteTypeMapper.selectTypeByName(silhouetteTypeName);
        if (silhouetteType == null) {
            LOGGER.warn("剪影类别不存在");
            throw new SilhouetteException(StateEnum.SILHOUETTE_TYPE_NOT_EXIST);
        }

        Silhouette silhouette = new Silhouette();
        silhouette.setSilhouetteDescription(silhouetteDescription);
        silhouette.setSilhouetteTime(silhouetteTime);
        silhouette.setSilhouetteType(silhouetteType);
        silhouette.setSilhouetteId(silhouetteId);
        if (!silhouettePhoto.isEmpty()) {
            String filename = silhouettePhoto.getOriginalFilename();
            try {
                if (filename.endsWith(".jpg") || filename.endsWith(".JPG") || filename.endsWith(".png") || filename.endsWith("PNG")) {
                    // 换成以类别加UUID命名
                    filename = silhouetteTypeName + UUID.randomUUID() + filename.substring(filename.length() - 4);
                    FileUtils.copyInputStreamToFile(silhouettePhoto.getInputStream(),
                            new File(request.getServletContext().getRealPath("/silhouettePhotos"), filename));
                    silhouette.setSilhouettePath("/silhouettePhotos/" + filename);
                } else {
                    LOGGER.warn("文件格式错误");
                    throw new FileFormatException(StateEnum.FILE_FORMAT_ERROR);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return silhouetteService.updateSilhouette(silhouette);
    }

    /**
     * 显示剪影（前端）
     *
     * @return Result结果
     */
    @RequestMapping(value = "/show", method = RequestMethod.POST, produces = "application/json")
    public Result showSilhouette() {
        return silhouetteService.showSilhouette();
    }

    /**
     * 显示剪影（后台）
     *
     * @param map map，含有剪影类型名称
     * @return Result结果
     */
    @RequestMapping(value = "/backstage/show", method = RequestMethod.POST, produces = "application/json")
    public Result showSilhouetteForBackstage(@RequestBody Map<String, String> map) {
        System.out.println(map);
        if (!map.containsKey("silhouetteTypeName")) {
            LOGGER.warn("缺失参数");
            throw new ParamException(StateEnum.PARAM_IS_LOST);
        }
        return silhouetteService.showSilhouetteForBackstage(map.get("silhouetteTypeName"));
    }

    /**
     * 增加剪影类型
     *
     * @param silhouetteType 剪影类别实体类
     * @return Result结果
     */
    @RequestMapping(value = "/type/add", method = RequestMethod.POST, produces = "application/json")
    public Result addSilhouetteType(@RequestBody SilhouetteType silhouetteType) {
        System.out.println(silhouetteType);
        return silhouetteTypeService.addSilhouetteType(silhouetteType);
    }

    /**
     * 删除剪影类型
     *
     * @param silhouetteType 剪影类别实体类
     * @return Result结果
     */
    @RequestMapping(value = "/type/delete", method = RequestMethod.POST, produces = "application/json")
    public Result deleteSilhouetteType(@RequestBody SilhouetteType silhouetteType) {
        System.out.println(silhouetteType);
        return silhouetteTypeService.deleteSilhouetteType(silhouetteType.getSilhouetteTypeId());
    }

    /**
     * 更新剪影类别
     *
     * @param silhouetteType 剪影类别实体类
     * @return Result结果
     */
    @RequestMapping(value = "/type/update", method = RequestMethod.POST, produces = "application/json")
    public Result updateSilhouetteType(@RequestBody SilhouetteType silhouetteType) {
        System.out.println(silhouetteType);
        return silhouetteTypeService.updateSilhouetteType(silhouetteType);
    }

    /**
     * 显示剪影类别
     *
     * @return Result结果
     */
    @RequestMapping(value = "/type/show", method = RequestMethod.POST, produces = "application/json")
    public Result showSilhouetteType() {
        return silhouetteTypeService.showSilhouetteType();
    }
}
