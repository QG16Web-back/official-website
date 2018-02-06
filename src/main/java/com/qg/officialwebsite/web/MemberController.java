package com.qg.officialwebsite.web;

import com.qg.officialwebsite.domain.Group;
import com.qg.officialwebsite.domain.GroupMapper;
import com.qg.officialwebsite.domain.Member;
import com.qg.officialwebsite.domain.Prize;
import com.qg.officialwebsite.dto.Result;
import com.qg.officialwebsite.enums.StateEnum;
import com.qg.officialwebsite.exception.FileFormatErrorException;
import com.qg.officialwebsite.exception.ParamEmptyException;
import com.qg.officialwebsite.exception.ParamLostException;
import com.qg.officialwebsite.exception.QGOfficialWebsiteException;
import com.qg.officialwebsite.service.impl.MemberServiceImpl;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author 小铭
 * Date: 2018/2/1
 * Time: 11:13
 * No struggle, talent how to match the willfulness.
 * Description: QG官网成员展示部分功能的控制器
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/member")
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    private final MemberServiceImpl memberService;
    private final GroupMapper groupMapper;

    @Autowired
    public MemberController(MemberServiceImpl memberService, GroupMapper groupMapper) {
        this.memberService = memberService;
        this.groupMapper = groupMapper;
    }

    /**
     * 添加成员
     * @param request 请求信息
     * @param memberPhoto 照片文件
     * @return Result结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addMember(HttpServletRequest request, @RequestParam("memberPhoto") MultipartFile memberPhoto) {
        Map<String, String[]> map = request.getParameterMap();

        if (!map.containsKey("memberName") || !map.containsKey("memberClass") || !map.containsKey("studentId") ||
                !map.containsKey("memberGrade") || !map.containsKey("groupName") || !map.containsKey("prizeName")
                || !map.containsKey("motto")) {
            logger.warn("参数缺失");
            throw new ParamLostException(StateEnum.PARAM_IS_LOST);
        }

        // 成员姓名
        String memberName = request.getParameter("memberName");
        // 成员班级
        String memberClass = request.getParameter("memberClass");
        // 学号
        String studentId = request.getParameter("studentId");
        // 成员年级
        String memberGrade = request.getParameter("memberGrade");
        // 成员组别
        String groupName = request.getParameter("groupName");
        // 成员所获奖项
        String prizeName = request.getParameter("prizeName");
        // 成员座右铭
        String motto = request.getParameter("motto");

        Group group = groupMapper.selectGroupByGroupName(groupName);
        if (group == null) {
            logger.warn("组别不存在");
            throw new ParamEmptyException(StateEnum.PARAM_IS_EMPTY);
        }

        if (memberName == null || "".equals(memberName) || studentId == null
                || "".equals(studentId) || memberClass == null || "".equals(memberClass)
                || memberGrade == null || "".equals(memberGrade) || motto ==
                null || "".equals(motto)) {
            logger.warn("参数为空");
            throw new ParamEmptyException(StateEnum.PARAM_IS_EMPTY);
        }
        Member member = new Member(memberName, studentId, memberClass, memberGrade, motto, group);

        if (!memberPhoto.isEmpty()) {
            String filename = memberPhoto.getOriginalFilename();
            try {
                if (filename.endsWith(".jpg") || filename.endsWith(".JPG") ||
                        filename.endsWith(".png") || filename.endsWith("PNG")) {
                    // 换成以成员姓名命名
                    filename = memberName + filename.substring(filename.length() - 4);
                    FileUtils.copyInputStreamToFile(memberPhoto.getInputStream(),
                            new File(request.getServletContext().getRealPath("/memberPhotos"), filename));
                    member.setMemberPhotoPath("/memberPhotos/" + filename);
                } else {
                    logger.warn("文件格式错误");
                    // 文件格式错误
                    throw new FileFormatErrorException(StateEnum.FILE_FORMAT_ERROR);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 添加成员奖项
        if (prizeName != null) {
            String[] prizeArray = prizeName.split("#");
            List<Prize> prizes = new ArrayList<>();
            for (String prizeString : prizeArray) {
                Prize prize = new Prize();
                prize.setPrizeName(prizeString);
                prizes.add(prize);
            }
            member.setPrizes(prizes);
        }
        System.out.println(member);
        File file = new File(request.getServletContext().getRealPath("/") +
                member
                .getMemberPhotoPath());
        boolean result = file.delete();
        System.out.println(result);
        return null;
    }
}
