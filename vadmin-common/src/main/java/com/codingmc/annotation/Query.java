package com.codingmc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义query注解封装jpa的查询 现可以通过 @Query 注解实现简单的查询与复杂查询
 * 简化类似此代码: ```
 *      new Specification<Blog>() {
 *             @Override
 *             public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
 *                 List<Predicate> predicates =new ArrayList<>();
 *                 if (blog.getTitle() != null && !"".equals(blog.getTitle())){
 *                     predicates.add(criteriaBuilder.like(root.<String>get("title"),"%" + blog.getTitle() + "%"));
 *                 }
 *                 if (blog.getTypeId() != null) {
 *                     predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
 *                 }
 *                 if (blog.isRecomment()) {
 *                     predicates.add(criteriaBuilder.equal(root.<Boolean>get("recomment"), blog.isRecomment()));
 *                 }
 *                 criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
 *                 return null;
 *             }
 *         }, pageable);
 *    ```
 *    现在只需在指定查询实体类(XXXQueryCriteria)的属性(字段)上标注此注解, 代码替换为
 *     (root, criteriaQuery, cb) -> QueryHelp.getPredicate(root, criteria, cb)
 *
 * <h1>参数说明</h1>
 * 字段名称	                   字段描述	                          默认值
 * propName	对象的属性名，如果字段名称与实体字段一致，则可以省略	           ""
 * type	    查询方式，默认为	                                       EQUAL
 * blurry	多字段模糊查询，值为实体字段名称	                           ""
 * joinName	关联实体的名称	                                           ""
 * join	    连接查询方式，左连接或者右连接	                           LEFT
 *
 * <h2>使用方法</h2>
 * public Object queryAll(QueryCriteria criteria, Pageable pageable){
 *     Page<实体> page = repository.findAll(((root, criteriaQuery, cb) -> QueryHelp.getPredicate(root, criteria, cb)),pageable);
 *     return page;
 * }
 *
 * @ClassName Query
 * @Description: TODO
 * @Author zhou
 * @Date 2020/5/28
 * @Version V1.0
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Query {

    // 基本对象属性名
    String propName() default "";
    // 查询方式 默认为EQUAl
    Type type() default Type.EQUAL;

    // 连接查询的属性名, 如User类中的dept
    String joinName() default "";

    /**
     * 适用于简单连接查询 默认左连接
     * @return
     */
    Join join() default Join.LEFT;

    /**
     * 多字段模糊查询, 仅仅支持String类型字段, 多个用逗号隔开, 如@Query(blurry = "email,username")
     * @return
     */
    String blurry() default "";

    enum Type {
        // 相等
        EQUAL
        // 大于等于
        , GREATER_THAN
        // 小于等于
        , LESS_THAN
        // 中模糊查询
        , INNER_LIKE
        // 左模糊查询
        , LEFT_LIKE
        // 右模糊查询
        , RIGHT_LIKE
        // 小于
        , LESS_THAN_NQ
        // 包含
        , IN
        // 不等于
        ,NOT_EQUAL
        // between
        ,BETWEEN
        // 不为空
        ,NOT_NULL
    }
    /**
     * 适用于简单连接查询，复杂的请自定义该注解，或者使用sql查询
     */
    enum Join {
        /**
         * 左右连接
         * */
        LEFT, RIGHT
    }
}
