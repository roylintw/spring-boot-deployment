package com.example.springbootdeployment.controller;

import com.example.springbootdeployment.dao.MemberDao;
import com.example.springbootdeployment.dao.MemberHasRoleDao;
import com.example.springbootdeployment.dao.RoleDao;
import com.example.springbootdeployment.dto.SubscribeRequest;
import com.example.springbootdeployment.dto.UnsubscribeRequest;
import com.example.springbootdeployment.vo.MemberHasRole;
import com.example.springbootdeployment.vo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubscriptionController {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private MemberHasRoleDao memberHasRoleDao;

    private final String ROLE_VIP_MEMBER = "ROLE_VIP_MEMBER";

    @PostMapping("/subscribe")
    public String subscribe(@RequestBody SubscribeRequest subscribeRequest) {

        Integer memberId = subscribeRequest.getMemberId();
        List<Role> roleList = roleDao.getRolesByMemberId(memberId);

        // 檢查訂閱狀態
        boolean isSubscribed = checkSubscribeStatus(roleList);

        // 根據訂閱狀態決定是否執行操作
        if (isSubscribed) {
            return "已訂閱過，不需重複訂閱";

        } else {
            Role vipRole = roleDao.getRoleByRoleName(ROLE_VIP_MEMBER);
            MemberHasRole memberHasRole = new MemberHasRole();
            memberHasRole.setMemberId(memberId);
            if (vipRole != null){
                memberHasRole.setRoleId(vipRole.getRoleId());
            }
            memberHasRoleDao.save(memberHasRole);

            return "訂閱成功！請刪除 Cookie 重新登入";
        }
    }

    @PostMapping("/unsubscribe")
    public String unsubscribe(@RequestBody UnsubscribeRequest unsubscribeRequest) {

        Integer memberId = unsubscribeRequest.getMemberId();
        List<Role> roleList = roleDao.getRolesByMemberId(memberId);

        // 檢查訂閱狀態
        boolean isSubscribed = checkSubscribeStatus(roleList);

        // 根據訂閱狀態決定是否執行操作
        if (isSubscribed) {
            Role vipRole = roleDao.getRoleByRoleName(ROLE_VIP_MEMBER);
            if(vipRole != null){
                memberHasRoleDao.deleteByMemberIdAndRoleId(memberId, vipRole.getRoleId());
            }

            return "取消訂閱成功！請刪除 Cookie 重新登入";

        } else {
            return "尚未訂閱，無法執行取消訂閱操作";
        }
    }

    /**
     * 檢查是否訂閱
     * @param roleList
     * @return boolean
     */
    private boolean checkSubscribeStatus(List<Role> roleList) {
        boolean isSubscribed = false;

        for (Role role : roleList) {
            if (role.getRoleName().equals("ROLE_VIP_MEMBER")) {
                isSubscribed = true;
            }
        }

        return isSubscribed;
    }
}