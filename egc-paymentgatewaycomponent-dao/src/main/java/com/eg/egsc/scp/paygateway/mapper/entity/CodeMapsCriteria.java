/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.mapper.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CodeMapsCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CodeMapsCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andUuidIsNull() {
            addCriterion("uuid is null");
            return (Criteria) this;
        }

        public Criteria andUuidIsNotNull() {
            addCriterion("uuid is not null");
            return (Criteria) this;
        }

        public Criteria andUuidEqualTo(String value) {
            addCriterion("uuid =", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualTo(String value) {
            addCriterion("uuid <>", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThan(String value) {
            addCriterion("uuid >", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualTo(String value) {
            addCriterion("uuid >=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThan(String value) {
            addCriterion("uuid <", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualTo(String value) {
            addCriterion("uuid <=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLike(String value) {
            addCriterion("uuid like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotLike(String value) {
            addCriterion("uuid not like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidIn(List<String> values) {
            addCriterion("uuid in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotIn(List<String> values) {
            addCriterion("uuid not in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidBetween(String value1, String value2) {
            addCriterion("uuid between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotBetween(String value1, String value2) {
            addCriterion("uuid not between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNull() {
            addCriterion("type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdEqualTo(String value) {
            addCriterion("type_id =", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotEqualTo(String value) {
            addCriterion("type_id <>", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThan(String value) {
            addCriterion("type_id >", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("type_id >=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThan(String value) {
            addCriterion("type_id <", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThanOrEqualTo(String value) {
            addCriterion("type_id <=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLike(String value) {
            addCriterion("type_id like", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotLike(String value) {
            addCriterion("type_id not like", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIn(List<String> values) {
            addCriterion("type_id in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotIn(List<String> values) {
            addCriterion("type_id not in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdBetween(String value1, String value2) {
            addCriterion("type_id between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotBetween(String value1, String value2) {
            addCriterion("type_id not between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andCodeTypeIsNull() {
            addCriterion("code_type is null");
            return (Criteria) this;
        }

        public Criteria andCodeTypeIsNotNull() {
            addCriterion("code_type is not null");
            return (Criteria) this;
        }

        public Criteria andCodeTypeEqualTo(String value) {
            addCriterion("code_type =", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeNotEqualTo(String value) {
            addCriterion("code_type <>", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeGreaterThan(String value) {
            addCriterion("code_type >", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("code_type >=", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeLessThan(String value) {
            addCriterion("code_type <", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeLessThanOrEqualTo(String value) {
            addCriterion("code_type <=", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeLike(String value) {
            addCriterion("code_type like", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeNotLike(String value) {
            addCriterion("code_type not like", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeIn(List<String> values) {
            addCriterion("code_type in", values, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeNotIn(List<String> values) {
            addCriterion("code_type not in", values, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeBetween(String value1, String value2) {
            addCriterion("code_type between", value1, value2, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeNotBetween(String value1, String value2) {
            addCriterion("code_type not between", value1, value2, "codeType");
            return (Criteria) this;
        }

        public Criteria andExCodeIsNull() {
            addCriterion("ex_code is null");
            return (Criteria) this;
        }

        public Criteria andExCodeIsNotNull() {
            addCriterion("ex_code is not null");
            return (Criteria) this;
        }

        public Criteria andExCodeEqualTo(String value) {
            addCriterion("ex_code =", value, "exCode");
            return (Criteria) this;
        }

        public Criteria andExCodeNotEqualTo(String value) {
            addCriterion("ex_code <>", value, "exCode");
            return (Criteria) this;
        }

        public Criteria andExCodeGreaterThan(String value) {
            addCriterion("ex_code >", value, "exCode");
            return (Criteria) this;
        }

        public Criteria andExCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ex_code >=", value, "exCode");
            return (Criteria) this;
        }

        public Criteria andExCodeLessThan(String value) {
            addCriterion("ex_code <", value, "exCode");
            return (Criteria) this;
        }

        public Criteria andExCodeLessThanOrEqualTo(String value) {
            addCriterion("ex_code <=", value, "exCode");
            return (Criteria) this;
        }

        public Criteria andExCodeLike(String value) {
            addCriterion("ex_code like", value, "exCode");
            return (Criteria) this;
        }

        public Criteria andExCodeNotLike(String value) {
            addCriterion("ex_code not like", value, "exCode");
            return (Criteria) this;
        }

        public Criteria andExCodeIn(List<String> values) {
            addCriterion("ex_code in", values, "exCode");
            return (Criteria) this;
        }

        public Criteria andExCodeNotIn(List<String> values) {
            addCriterion("ex_code not in", values, "exCode");
            return (Criteria) this;
        }

        public Criteria andExCodeBetween(String value1, String value2) {
            addCriterion("ex_code between", value1, value2, "exCode");
            return (Criteria) this;
        }

        public Criteria andExCodeNotBetween(String value1, String value2) {
            addCriterion("ex_code not between", value1, value2, "exCode");
            return (Criteria) this;
        }

        public Criteria andInCodeIsNull() {
            addCriterion("in_code is null");
            return (Criteria) this;
        }

        public Criteria andInCodeIsNotNull() {
            addCriterion("in_code is not null");
            return (Criteria) this;
        }

        public Criteria andInCodeEqualTo(String value) {
            addCriterion("in_code =", value, "inCode");
            return (Criteria) this;
        }

        public Criteria andInCodeNotEqualTo(String value) {
            addCriterion("in_code <>", value, "inCode");
            return (Criteria) this;
        }

        public Criteria andInCodeGreaterThan(String value) {
            addCriterion("in_code >", value, "inCode");
            return (Criteria) this;
        }

        public Criteria andInCodeGreaterThanOrEqualTo(String value) {
            addCriterion("in_code >=", value, "inCode");
            return (Criteria) this;
        }

        public Criteria andInCodeLessThan(String value) {
            addCriterion("in_code <", value, "inCode");
            return (Criteria) this;
        }

        public Criteria andInCodeLessThanOrEqualTo(String value) {
            addCriterion("in_code <=", value, "inCode");
            return (Criteria) this;
        }

        public Criteria andInCodeLike(String value) {
            addCriterion("in_code like", value, "inCode");
            return (Criteria) this;
        }

        public Criteria andInCodeNotLike(String value) {
            addCriterion("in_code not like", value, "inCode");
            return (Criteria) this;
        }

        public Criteria andInCodeIn(List<String> values) {
            addCriterion("in_code in", values, "inCode");
            return (Criteria) this;
        }

        public Criteria andInCodeNotIn(List<String> values) {
            addCriterion("in_code not in", values, "inCode");
            return (Criteria) this;
        }

        public Criteria andInCodeBetween(String value1, String value2) {
            addCriterion("in_code between", value1, value2, "inCode");
            return (Criteria) this;
        }

        public Criteria andInCodeNotBetween(String value1, String value2) {
            addCriterion("in_code not between", value1, value2, "inCode");
            return (Criteria) this;
        }

        public Criteria andExMsgIsNull() {
            addCriterion("ex_msg is null");
            return (Criteria) this;
        }

        public Criteria andExMsgIsNotNull() {
            addCriterion("ex_msg is not null");
            return (Criteria) this;
        }

        public Criteria andExMsgEqualTo(String value) {
            addCriterion("ex_msg =", value, "exMsg");
            return (Criteria) this;
        }

        public Criteria andExMsgNotEqualTo(String value) {
            addCriterion("ex_msg <>", value, "exMsg");
            return (Criteria) this;
        }

        public Criteria andExMsgGreaterThan(String value) {
            addCriterion("ex_msg >", value, "exMsg");
            return (Criteria) this;
        }

        public Criteria andExMsgGreaterThanOrEqualTo(String value) {
            addCriterion("ex_msg >=", value, "exMsg");
            return (Criteria) this;
        }

        public Criteria andExMsgLessThan(String value) {
            addCriterion("ex_msg <", value, "exMsg");
            return (Criteria) this;
        }

        public Criteria andExMsgLessThanOrEqualTo(String value) {
            addCriterion("ex_msg <=", value, "exMsg");
            return (Criteria) this;
        }

        public Criteria andExMsgLike(String value) {
            addCriterion("ex_msg like", value, "exMsg");
            return (Criteria) this;
        }

        public Criteria andExMsgNotLike(String value) {
            addCriterion("ex_msg not like", value, "exMsg");
            return (Criteria) this;
        }

        public Criteria andExMsgIn(List<String> values) {
            addCriterion("ex_msg in", values, "exMsg");
            return (Criteria) this;
        }

        public Criteria andExMsgNotIn(List<String> values) {
            addCriterion("ex_msg not in", values, "exMsg");
            return (Criteria) this;
        }

        public Criteria andExMsgBetween(String value1, String value2) {
            addCriterion("ex_msg between", value1, value2, "exMsg");
            return (Criteria) this;
        }

        public Criteria andExMsgNotBetween(String value1, String value2) {
            addCriterion("ex_msg not between", value1, value2, "exMsg");
            return (Criteria) this;
        }

        public Criteria andInMsgIsNull() {
            addCriterion("in_msg is null");
            return (Criteria) this;
        }

        public Criteria andInMsgIsNotNull() {
            addCriterion("in_msg is not null");
            return (Criteria) this;
        }

        public Criteria andInMsgEqualTo(String value) {
            addCriterion("in_msg =", value, "inMsg");
            return (Criteria) this;
        }

        public Criteria andInMsgNotEqualTo(String value) {
            addCriterion("in_msg <>", value, "inMsg");
            return (Criteria) this;
        }

        public Criteria andInMsgGreaterThan(String value) {
            addCriterion("in_msg >", value, "inMsg");
            return (Criteria) this;
        }

        public Criteria andInMsgGreaterThanOrEqualTo(String value) {
            addCriterion("in_msg >=", value, "inMsg");
            return (Criteria) this;
        }

        public Criteria andInMsgLessThan(String value) {
            addCriterion("in_msg <", value, "inMsg");
            return (Criteria) this;
        }

        public Criteria andInMsgLessThanOrEqualTo(String value) {
            addCriterion("in_msg <=", value, "inMsg");
            return (Criteria) this;
        }

        public Criteria andInMsgLike(String value) {
            addCriterion("in_msg like", value, "inMsg");
            return (Criteria) this;
        }

        public Criteria andInMsgNotLike(String value) {
            addCriterion("in_msg not like", value, "inMsg");
            return (Criteria) this;
        }

        public Criteria andInMsgIn(List<String> values) {
            addCriterion("in_msg in", values, "inMsg");
            return (Criteria) this;
        }

        public Criteria andInMsgNotIn(List<String> values) {
            addCriterion("in_msg not in", values, "inMsg");
            return (Criteria) this;
        }

        public Criteria andInMsgBetween(String value1, String value2) {
            addCriterion("in_msg between", value1, value2, "inMsg");
            return (Criteria) this;
        }

        public Criteria andInMsgNotBetween(String value1, String value2) {
            addCriterion("in_msg not between", value1, value2, "inMsg");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNull() {
            addCriterion("delete_flag is null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNotNull() {
            addCriterion("delete_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagEqualTo(Short value) {
            addCriterion("delete_flag =", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotEqualTo(Short value) {
            addCriterion("delete_flag <>", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThan(Short value) {
            addCriterion("delete_flag >", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThanOrEqualTo(Short value) {
            addCriterion("delete_flag >=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThan(Short value) {
            addCriterion("delete_flag <", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThanOrEqualTo(Short value) {
            addCriterion("delete_flag <=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIn(List<Short> values) {
            addCriterion("delete_flag in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotIn(List<Short> values) {
            addCriterion("delete_flag not in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagBetween(Short value1, Short value2) {
            addCriterion("delete_flag between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotBetween(Short value1, Short value2) {
            addCriterion("delete_flag not between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNull() {
            addCriterion("update_user is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("update_user is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(String value) {
            addCriterion("update_user =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(String value) {
            addCriterion("update_user <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(String value) {
            addCriterion("update_user >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("update_user >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(String value) {
            addCriterion("update_user <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("update_user <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLike(String value) {
            addCriterion("update_user like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotLike(String value) {
            addCriterion("update_user not like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<String> values) {
            addCriterion("update_user in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<String> values) {
            addCriterion("update_user not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(String value1, String value2) {
            addCriterion("update_user between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(String value1, String value2) {
            addCriterion("update_user not between", value1, value2, "updateUser");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}