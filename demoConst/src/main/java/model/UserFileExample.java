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

public class UserFileExample {
    /* 单例 */
    private static UserFileExample userFileExample;

    private Map<Long, UserFileDto> exampleMap = new HashMap<>();

    public UserFileDto getUserFileById(Long fileIid) {
        return exampleMap.get(fileIid);
    }

    public static UserFileExample getInstance() {
        if (userFileExample == null) {
            userFileExample = new UserFileExample();
            UserFileDto fileId1 = new UserFileDto();
            fileId1.setUserId(1L);
            fileId1.setFileId(1L);
            UserFileDto fileId2 = new UserFileDto();
            fileId2.setUserId(2L);
            fileId2.setFileId(2L);
            userFileExample.getExampleMap().put(1L, fileId1);
            userFileExample.getExampleMap().put(2L, fileId2);
        }
        return userFileExample;
    }

    public Map<Long, UserFileDto> getExampleMap() {
        return exampleMap;
    }
}
