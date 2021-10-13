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

public class FileInfoExample {

    /* 单例 */
    private static FileInfoExample fileInfoExample;
    
    private static long fileIdSeq = 3L;

    private Map<Long, String> exampleMap = new HashMap<>();

    public String getFileNameById(Long fileIid) {
        return exampleMap.get(fileIid);
    }

    public static FileInfoExample getInstance() {
        if (fileInfoExample == null) {
            fileInfoExample = new FileInfoExample();
            fileInfoExample.getExampleMap().put(0L, "error.txt");
            fileInfoExample.getExampleMap().put(1L, "testDownLoad1.txt");
            fileInfoExample.getExampleMap().put(2L, "testDownLoad2.txt");
        }
        return fileInfoExample;
    }

    public Map<Long, String> getExampleMap() {
        return exampleMap;
    }
    
    public static long getFileIdSeq() {
        long nowFileId = fileIdSeq;
        fileIdSeq++;
        return nowFileId;
    }
}
