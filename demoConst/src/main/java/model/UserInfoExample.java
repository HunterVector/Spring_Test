/**************************************************************************************** 
 Copyright © 2003-2012 ZTEsoft Corporation. All rights reserved. Reproduction or       <br>
 transmission in whole or in part, in any form or by any means, electronic, mechanical <br>
 or otherwise, is prohibited without the prior written consent of the copyright owner. <br>
 ****************************************************************************************/
package model;/**
 * <Description> <br>
 *
 * @author zheng.yangyang<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate <br>
 * @see <br>
 * @since V8.0<br>
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <Description> <br> 
 *
 * @author zheng.yangyang<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate  <br>
 * @since V8.0<br>
 * @see  <br>
 */

public class UserInfoExample {

    /* 单例 */
    private static UserInfoExample userInfoExample;

    private Map<Long, UserInfoDto> exampleMap = new HashMap<>();

    private Map<String, UserInfoDto> userMap = new HashMap<>();

    public UserInfoDto getUserById(Long userId) {
        return exampleMap.get(userId);
    }

    public UserInfoDto getUserByCode(String userCode) {
        return userMap.get(userCode);
    }

    public static UserInfoExample getInstance() {
        if (userInfoExample == null) {
            userInfoExample = new UserInfoExample();
            UserInfoDto user1 = new UserInfoDto();
            user1.setUserId(1L);
//            user1.setPassword("Admin11");
            user1.setUserCode("admin1");
            UserInfoDto user2 = new UserInfoDto();
            user2.setUserId(2L);
//            user2.setPassword("Admin22");
            user2.setUserCode("admin2");
            userInfoExample.getExampleMap().put(1L, user1);
            userInfoExample.getExampleMap().put(2L, user2);
            userInfoExample.getUserMap().put("admin1", user1);
            userInfoExample.getUserMap().put("admin2", user2);
                
        }
        return userInfoExample;
    }

    public Map<Long, UserInfoDto> getExampleMap() {
        return exampleMap;
    }

    public Map<String, UserInfoDto> getUserMap() {
        return userMap;
    }
}
