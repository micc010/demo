<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${nameSpace}">
    <#--<cache eviction="FIFO" type="com.github.rogerli.framework.cache.RedisMybatisCache" size="1000" readOnly="true"/>-->
    <resultMap id="BaseResultMap" type="${typeName}">
    <#list columnList as col>
        <result column="${col.columnName}" jdbcType="${col.jdbcType}" property="${col.propertyName}"/>
    </#list>
    </resultMap>
    <sql id="Base_Column_List">
    ${colStr}
    </sql>
    <select id="findByKey" parameterType="String" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ${info.tableName}
        where ${info.keyName} = ${'#'}{${info.keyName},jdbcType=VARCHAR}
    </select>
    <delete id="deleteByKey" parameterType="String">
        delete from ${info.tableName}
        where ${info.keyName} = ${'#'}{${info.keyName},jdbcType=VARCHAR}
    </delete>
    <insert id="insert" parameterType="${typeName}">
        <#if "${info.keyName}" == "id">
        <selectKey keyProperty="id" resultType="String" order="BEFORE">
            select replace(uuid(),'-','') from dual
        </selectKey>
        </#if>
        insert into ${info.tableName}(${colStr})
        values
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list columnList as col>
        ${'#'}{${col.propertyName},jdbcType=${col.jdbcType}},
        </#list>
        </trim>
    </insert>
    <insert id="insertSelective" parameterType="${typeName}">
        <#if "${info.keyName}" == "id">
            <selectKey keyProperty="id" resultType="String" order="BEFORE">
                select replace(uuid(),'-','') from dual
            </selectKey>
        </#if>
        insert into ${info.tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list columnList as col>
            <if test="${col.propertyName} != null">
            ${col.columnName},
            </if>
        </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
        <#list columnList as col>
            <if test="${col.propertyName} != null">
            ${'#'}{${col.propertyName},jdbcType=${col.jdbcType}},
            </if>
        </#list>
        </trim>
    </insert>
    <update id="updateByKeySelective" parameterType="${typeName}">
        update ${info.tableName}
        <trim prefix="set" prefixOverrides=",">
        <#list columnList as col>
            <#if "${col.propertyName}" != "id">
                <if test="${col.propertyName} != null">
                    ,${col.columnName} = ${'#'}{${col.propertyName},jdbcType=${col.jdbcType}}
                </if>
            </#if>
        </#list>
        </trim>
        where ${info.keyName} = ${'#'}{${info.keyName},jdbcType=VARCHAR}
    </update>
    <update id="updateByKey" parameterType="${typeName}">
        update ${info.tableName}
        <trim prefix="set" prefixOverrides=",">
        <#list columnList as col>
            <#if "${col.propertyName}" != "id">
                ,${col.columnName} = ${'#'}{${col.propertyName},jdbcType=${col.jdbcType}}
            </#if>
        </#list>
        </trim>
        where ${info.keyName} = ${'#'}{${info.keyName},jdbcType=VARCHAR}
    </update>
    <select id="findList" parameterType="${typeName}" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ${info.tableName}
        <trim prefix="where" prefixOverrides="and|or">
        <#list columnList as col>
            <if test="${col.propertyName} != null">
                and ${col.columnName} = ${'#'}{${col.propertyName},jdbcType=${col.jdbcType}}
            </if>
        </#list>
        </trim>
    </select>
</mapper>