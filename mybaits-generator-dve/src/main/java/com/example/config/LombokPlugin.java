package com.example.config;


import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.util.StringUtility;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 此方法 转化 class 文件放入 mybaits-generator-core 包里下的org.mybatis.generator.plugins目录里面
 */
public class LombokPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        //添加Entity实体类的import
        topLevelClass.addImportedType("lombok.Data");
        topLevelClass.addImportedType("lombok.AllArgsConstructor");
        topLevelClass.addImportedType("lombok.NoArgsConstructor");
        topLevelClass.addImportedType("lombok.ToString");
        topLevelClass.addImportedType("lombok.Builder");
        topLevelClass.addImportedType("io.swagger.annotations.ApiModel");
        topLevelClass.addImportedType("io.swagger.annotations.ApiModelProperty");
        topLevelClass.addImportedType("java.io.Serializable");
        topLevelClass.addSuperInterface(new FullyQualifiedJavaType("java.io.Serializable"));
        //添加Entity 注解
        topLevelClass.addAnnotation("@Data");
        topLevelClass.addAnnotation("@AllArgsConstructor");
        topLevelClass.addAnnotation("@NoArgsConstructor");
        topLevelClass.addAnnotation("@ToString");
        topLevelClass.addAnnotation("@Builder");
        String  remarkTable=introspectedTable.getRemarks();
        topLevelClass.addAnnotation("@ApiModel(description = \""+remarkTable+"\")");
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //添加domain的注解
        topLevelClass.addJavaDocLine("/**\n" +
                "*作者：chengfuse@outlook.com\n" +
                "*联系邮箱:chengfuse@outlook.com\n" +
                "*日期"+dateFormat.format(new Date())+"\n" +
                "*/");
        return true;
    }


    //entity 实体类注释
    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        String  remarks=introspectedColumn.getRemarks();
        if (StringUtility.stringHasValue(remarks)){
            String[] remarkLines=remarks.split(System.getProperty("line.separator"));
            for (String remarkLine:remarkLines) {
                field.addAnnotation("@ApiModelProperty(value=\""+remarkLine+"\")");
            }
        }
        return true;
    }

    /**
     * mapper 接口注释
     * @param interfaze
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        interfaze.addJavaDocLine("/**\n" +
                "*作者：chengfuse@outlook.com\n" +
                "*联系邮箱:chengfuse@outlook.com\n" +
                "*日期"+dateFormat.format(new Date())+"\n" +
                "*/\n" +
                "//"+introspectedTable.getRemarks()+"接口");
        return true;
    }

    /**
     * 不生成getter
     * @param method
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return
     */
    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    /**
     * 不生成setter
     * @param method
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return
     */
    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

}
