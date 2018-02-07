package com.qg.officialwebsite.web;

import com.qg.officialwebsite.domain.Group;
import com.qg.officialwebsite.domain.GroupMapper;
import com.qg.officialwebsite.domain.Member;
import com.qg.officialwebsite.domain.MemberMapper;
import com.qg.officialwebsite.dto.Result;
import com.qg.officialwebsite.enums.RegexEnum;
import com.qg.officialwebsite.enums.StateEnum;
import com.qg.officialwebsite.exception.FileFormatException;
import com.qg.officialwebsite.exception.GroupException;
import com.qg.officialwebsite.exception.MemberException;
import com.qg.officialwebsite.exception.ParamException;
import com.qg.officialwebsite.exception.StudentIdFormatException;
import com.qg.officialwebsite.service.impl.MemberServiceImpl;
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
    private final MemberMapper memberMapper;

    @Autowired
    public MemberController(MemberServiceImpl memberService, GroupMapper groupMapper, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.groupMapper = groupMapper;
        this.memberMapper = memberMapper;
    }

    /**
     * 添加成员
     *
     * @param request 请求信息
     * @param memberPhoto 照片文件
     * @return Result结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addMember(HttpServletRequest request, @RequestParam("memberPhoto") MultipartFile memberPhoto) {
        Map<String, String[]> map = request.getParameterMap();

        if (!map.containsKey("memberName") || !map.containsKey("memberClass") || !map.containsKey("studentId") || !map.containsKey("groupName")
            || !map.containsKey("prizeName") || !map.containsKey("motto") || !map.containsKey("haveGraduated") || !map.containsKey("afterGraduation")) {
            logger.warn("参数缺失");
            throw new ParamException(StateEnum.PARAM_IS_LOST);
        }

        // 成员姓名
        String memberName = request.getParameter("memberName");
        // 成员班级
        String memberClass = request.getParameter("memberClass");
        // 学号
        String studentId = request.getParameter("studentId");
        // 成员组别
        String groupName = request.getParameter("groupName");
        // 成员所获奖项
        String prizeName = request.getParameter("prizeName");
        // 成员座右铭
        String motto = request.getParameter("motto");
        // 成员是否毕业
        String haveGraduated = request.getParameter("haveGraduated");
        // 成员毕业去向
        String afterGraduation = request.getParameter("afterGraduation");

        if (!studentId.matches(RegexEnum.STUDENT_ID_REGEX.getRegex())) {
            logger.warn("成员学号错误");
            throw new StudentIdFormatException(StateEnum.STUDENT_ID_FORMAT_ERROR);
        }

        if (memberMapper.selectMemberByStudentId(studentId) != null) {
            logger.warn("该成员学号已经存在");
            throw new MemberException(StateEnum.MEMBER_HAS_EXISTED);
        }

        if (!("未毕业".equals(haveGraduated) || "已毕业".equals(haveGraduated))) {
            logger.warn("毕业状态出错");
            throw new MemberException(StateEnum.GRADUATED_STATUS_ERROR);
        }

        Group group = groupMapper.selectGroupByGroupName(groupName);
        if (group == null) {
            logger.warn("组别不存在");
            throw new GroupException(StateEnum.GROUP_NOT_EXIST);
        }

        if (memberName == null || "".equals(memberName) || memberClass == null || "".equals(memberClass) || "".equals(haveGraduated)) {
            logger.warn("参数为空");
            throw new ParamException(StateEnum.PARAM_IS_EMPTY);
        }

        Member member = new Member(memberName, studentId, memberClass, motto, group, haveGraduated, afterGraduation);

        if (!memberPhoto.isEmpty()) {
            String filename = memberPhoto.getOriginalFilename();
            try {
                if (filename.endsWith(".jpg") || filename.endsWith(".JPG") || filename.endsWith(".png") || filename.endsWith("PNG")) {
                    // 换成以成员学号命名
                    filename = studentId + filename.substring(filename.length() - 4);
                    FileUtils.copyInputStreamToFile(memberPhoto.getInputStream(),
                            new File(request.getServletContext().getRealPath("/memberPhotos"), filename));
                    member.setMemberPhotoPath("/memberPhotos/" + filename);
                } else {
                    logger.warn("文件格式错误");
                    // 文件格式错误
                    throw new FileFormatException(StateEnum.FILE_FORMAT_ERROR);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return memberService.addOrUpdateMember(member, prizeName, true);
    }

    /**
     * 展示成员（前端显示）
     *
     * @param map map，含有memberGrade和groupName两个参数
     * @return Result结果
     */
    @RequestMapping(value = "/show", method = RequestMethod.POST, produces = "application/json")
    public Result showMember(@RequestBody Map<String, String> map) {
        System.out.println(map);
        if (!map.containsKey("memberGrade") || !map.containsKey("groupName")) {
            logger.warn("缺失参数");
            throw new ParamException(StateEnum.PARAM_IS_LOST);
        }
        return memberService.showMember(map.get("memberGrade"), map.get("groupName"), true);
    }

    /**
     * 展示成员（后台显示）
     *
     * @param map map，含有memberGrade和groupName两个参数
     * @return Result结果
     */
    @RequestMapping(value = "/backstage/show", method = RequestMethod.POST, produces = "application/json")
    public Result showMemberForBackstage(@RequestBody Map<String, String> map) {
        System.out.println(map);
        if (!map.containsKey("memberGrade") || !map.containsKey("groupName")) {
            logger.warn("缺失参数");
            throw new ParamException(StateEnum.PARAM_IS_LOST);
        }
        return memberService.showMember(map.get("memberGrade"), map.get("groupName"), false);
    }

    /**
     * 修改成员
     *
     * @param request 请求信息
     * @param memberPhoto 照片文件
     * @return Result结果
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateMember(HttpServletRequest request, @RequestParam("memberPhoto") MultipartFile memberPhoto) {
        Map<String, String[]> map = request.getParameterMap();

        if (!map.containsKey("memberName") || !map.containsKey("memberClass") || !map.containsKey("studentId")
                || !map.containsKey("groupName") || !map.containsKey("prizeName") || !map.containsKey("motto")
                || !map.containsKey("haveGraduated") || !map.containsKey("afterGraduation") || !map.containsKey("memberId")) {
            logger.warn("参数缺失");
            throw new ParamException(StateEnum.PARAM_IS_LOST);
        }

        // 成员ID
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        // 成员姓名
        String memberName = request.getParameter("memberName");
        // 成员班级
        String memberClass = request.getParameter("memberClass");
        // 学号
        String studentId = request.getParameter("studentId");
        // 成员组别
        String groupName = request.getParameter("groupName");
        // 成员所获奖项
        String prizeName = request.getParameter("prizeName");
        // 成员座右铭
        String motto = request.getParameter("motto");
        // 成员是否毕业
        String haveGraduated = request.getParameter("haveGraduated");
        // 成员毕业去向
        String afterGraduation = request.getParameter("afterGraduation");

        if (null == memberMapper.selectMemberById(memberId)) {
            logger.warn("成员不存在");
            throw new MemberException(StateEnum.MEMBER_NOT_EXIST);
        }

        if (!studentId.matches(RegexEnum.STUDENT_ID_REGEX.getRegex())) {
            logger.warn("成员学号错误");
            throw new StudentIdFormatException(StateEnum.STUDENT_ID_FORMAT_ERROR);
        }

        if (memberMapper.selectMemberByStudentId(studentId) != null) {
            logger.warn("该成员学号已经存在");
            throw new MemberException(StateEnum.MEMBER_HAS_EXISTED);
        }

        if (!("未毕业".equals(haveGraduated) || "已毕业".equals(haveGraduated))) {
            logger.warn("毕业状态出错");
            throw new MemberException(StateEnum.GRADUATED_STATUS_ERROR);
        }

        Group group = groupMapper.selectGroupByGroupName(groupName);
        if (group == null) {
            logger.warn("组别不存在");
            throw new GroupException(StateEnum.GROUP_NOT_EXIST);
        }

        if (memberName == null || "".equals(memberName) || memberClass == null || "".equals(memberClass) || "".equals(haveGraduated)) {
            logger.warn("参数为空");
            throw new ParamException(StateEnum.PARAM_IS_EMPTY);
        }

        Member member = new Member(memberName, studentId, memberClass, motto, group, haveGraduated, afterGraduation);
        member.setMemberId(memberId);

        if (!memberPhoto.isEmpty()) {
            String filename = memberPhoto.getOriginalFilename();
            try {
                if (filename.endsWith(".jpg") || filename.endsWith(".JPG") || filename.endsWith(".png") || filename.endsWith("PNG")) {
                    // 换成以成员学号命名
                    filename = studentId + filename.substring(filename.length() - 4);
                    FileUtils.copyInputStreamToFile(memberPhoto.getInputStream(),
                            new File(request.getServletContext().getRealPath("/memberPhotos"), filename));
                    member.setMemberPhotoPath("/memberPhotos/" + filename);
                } else {
                    logger.warn("文件格式错误");
                    // 文件格式错误
                    throw new FileFormatException(StateEnum.FILE_FORMAT_ERROR);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return memberService.addOrUpdateMember(member, prizeName, false);
    }

    /**
     * 删除成员
     *
     * @param map map，含有成员ID
     * @return Result结果
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json")
    public Result deleteMember(@RequestBody Map<String, Integer> map) {
        System.out.println(map);
        if (!map.containsKey("memberId")) {
            logger.warn("缺失参数");
            throw new ParamException(StateEnum.PARAM_IS_LOST);
        }
        return memberService.deleteMember(map.get("memberId"));
    }

    @RequestMapping(value = "/select", method = RequestMethod.POST, produces = "application/json")
    public Result selectMember(@RequestBody Map<String, String> map) {
        System.out.println(map);
        if (!map.containsKey("studentId")) {
            logger.warn("缺失参数");
            throw new ParamException(StateEnum.PARAM_IS_LOST);
        }
        return memberService.selectMember(map.get("studentId"));
    }
}
