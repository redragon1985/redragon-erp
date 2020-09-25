package com;
import com.framework.ai.AutoGenerateUtil;
import com.framework.ai.ProjectAutoGenerate;

/**
 * @description AutoProject.java
 * @author dongbin
 * @version 
 * @copyright
 */

/**
 * @description
 * @date 2020年6月27日
 * @author dongbin
 * 
 */
public class AutoProject {

    /**
     * @description
     * @date 2020年6月27日
     * @author dongbin
     * @param args
     * @return void
     * 
     */
    public static void main(String[] args) {
        ProjectAutoGenerate p = new ProjectAutoGenerate();
        p.generateProject();
        System.out.println("执行完成，生成程序包路径："+AutoGenerateUtil.projectGenerateDisk);

    }

}
