package com.canbro.coffee;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class GenCodeController {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3304/cb_coffee", "root", "1111")
                .globalConfig(builder -> {
                    builder.author("A-pppp") // 设置作者
                            .outputDir("E://gen//").build(); // 指定输出目录
                })
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                            if (typeCode == Types.SMALLINT) {
                                // 自定义类型转换
                                return DbColumnType.INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        })
                )
                .packageConfig(builder ->
                        builder.parent("com.canbro.coffee") // 设置父包名
//                                .moduleName("system") // 设置父包模块名
                                .pathInfo(Collections.singletonMap(OutputFile.xml, "E://gen//")) // 设置mapperXml生成路径
                )
                .strategyConfig(builder ->
                        builder.addInclude("^t_.*") // 设置需要生成的表名
                                .addTablePrefix("t_", "c_") // 设置过滤表前缀
                                .entityBuilder().enableLombok().build()
                                .controllerBuilder().enableRestStyle().build()

                )
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
