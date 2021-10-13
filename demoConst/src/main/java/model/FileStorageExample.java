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

import java.util.HashMap;
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

public class FileStorageExample {
    
    /* 单例 */
    private static FileStorageExample fileStorageExample;
    
    private Map<Long, String> exampleMap = new HashMap<>();
    
    public String getFilePathById(Long fileIid) {
        return exampleMap.get(fileIid);
    }
    
    public static FileStorageExample getInstance() {
        if (fileStorageExample == null) {
            fileStorageExample = new FileStorageExample();
            fileStorageExample.getExampleMap().put(1L, "D:\\logs\\file\\Upload\\testDownLoad1.txt");
            fileStorageExample.getExampleMap().put(2L, "D:\\logs\\file\\Upload\\testDownLoad2.txt");
        }
        return fileStorageExample;
    }

    public Map<Long, String> getExampleMap() {
        return exampleMap;
    }
}
