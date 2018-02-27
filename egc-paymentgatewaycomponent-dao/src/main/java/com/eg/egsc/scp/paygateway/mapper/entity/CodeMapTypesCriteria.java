package com.eg.egsc.scp.paygateway.mapper.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CodeMapTypesCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CodeMapTypesCriteria() {
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

        public Criteria andPlatformIsNull() {
            addCriterion("platform is null");
            return (Criteria) this;
        }

        public Criteria andPlatformIsNotNull() {
            addCriterion("platform is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformEqualTo(String value) {
            addCriterion("platform =", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotEqualTo(String value) {
            addCriterion("platform <>", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformGreaterThan(String value) {
            addCriterion("platform >", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformGreaterThanOrEqualTo(String value) {
            addCriterion("platform >=", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLessThan(String value) {
            addCriterion("platform <", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLessThanOrEqualTo(String value) {
            addCriterion("platform <=", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLike(String value) {
            addCriterion("platform like", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotLike(String value) {
            addCriterion("platform not like", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformIn(List<String> values) {
            addCriterion("platform in", values, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotIn(List<String> values) {
            addCriterion("platform not in", values, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformBetween(String value1, String value2) {
            addCriterion("platform between", value1, value2, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotBetween(String value1, String value2) {
            addCriterion("platform not between", value1, value2, "platform");
            return (Criteria) this;
        }

        public Criteria andMethodIsNull() {
            addCriterion("\"method\" is null");
            return (Criteria) this;
        }

        public Criteria andMethodIsNotNull() {
            addCriterion("\"method\" is not null");
            return (Criteria) this;
        }

        public Criteria andMethodEqualTo(String value) {
            addCriterion("\"method\" =", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotEqualTo(String value) {
            addCriterion("\"method\" <>", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodGreaterThan(String value) {
            addCriterion("\"method\" >", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodGreaterThanOrEqualTo(String value) {
            addCriterion("\"method\" >=", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLessThan(String value) {
            addCriterion("\"method\" <", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLessThanOrEqualTo(String value) {
            addCriterion("\"method\" <=", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLike(String value) {
            addCriterion("\"method\" like", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotLike(String value) {
            addCriterion("\"method\" not like", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodIn(List<String> values) {
            addCriterion("\"method\" in", values, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotIn(List<String> values) {
            addCriterion("\"method\" not in", values, "method");
            return (Criteria) this;
        }

        public Criteria andMethodBetween(String value1, String value2) {
            addCriterion("\"method\" between", value1, value2, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotBetween(String value1, String value2) {
            addCriterion("\"method\" not between", value1, value2, "method");
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

        public Criteria andExFieldIsNull() {
            addCriterion("ex_field is null");
            return (Criteria) this;
        }

        public Criteria andExFieldIsNotNull() {
            addCriterion("ex_field is not null");
            return (Criteria) this;
        }

        public Criteria andExFieldEqualTo(String value) {
            addCriterion("ex_field =", value, "exField");
            return (Criteria) this;
        }

        public Criteria andExFieldNotEqualTo(String value) {
            addCriterion("ex_field <>", value, "exField");
            return (Criteria) this;
        }

        public Criteria andExFieldGreaterThan(String value) {
            addCriterion("ex_field >", value, "exField");
            return (Criteria) this;
        }

        public Criteria andExFieldGreaterThanOrEqualTo(String value) {
            addCriterion("ex_field >=", value, "exField");
            return (Criteria) this;
        }

        public Criteria andExFieldLessThan(String value) {
            addCriterion("ex_field <", value, "exField");
            return (Criteria) this;
        }

        public Criteria andExFieldLessThanOrEqualTo(String value) {
            addCriterion("ex_field <=", value, "exField");
            return (Criteria) this;
        }

        public Criteria andExFieldLike(String value) {
            addCriterion("ex_field like", value, "exField");
            return (Criteria) this;
        }

        public Criteria andExFieldNotLike(String value) {
            addCriterion("ex_field not like", value, "exField");
            return (Criteria) this;
        }

        public Criteria andExFieldIn(List<String> values) {
            addCriterion("ex_field in", values, "exField");
            return (Criteria) this;
        }

        public Criteria andExFieldNotIn(List<String> values) {
            addCriterion("ex_field not in", values, "exField");
            return (Criteria) this;
        }

        public Criteria andExFieldBetween(String value1, String value2) {
            addCriterion("ex_field between", value1, value2, "exField");
            return (Criteria) this;
        }

        public Criteria andExFieldNotBetween(String value1, String value2) {
            addCriterion("ex_field not between", value1, value2, "exField");
            return (Criteria) this;
        }

        public Criteria andInFieldIsNull() {
            addCriterion("in_field is null");
            return (Criteria) this;
        }

        public Criteria andInFieldIsNotNull() {
            addCriterion("in_field is not null");
            return (Criteria) this;
        }

        public Criteria andInFieldEqualTo(String value) {
            addCriterion("in_field =", value, "inField");
            return (Criteria) this;
        }

        public Criteria andInFieldNotEqualTo(String value) {
            addCriterion("in_field <>", value, "inField");
            return (Criteria) this;
        }

        public Criteria andInFieldGreaterThan(String value) {
            addCriterion("in_field >", value, "inField");
            return (Criteria) this;
        }

        public Criteria andInFieldGreaterThanOrEqualTo(String value) {
            addCriterion("in_field >=", value, "inField");
            return (Criteria) this;
        }

        public Criteria andInFieldLessThan(String value) {
            addCriterion("in_field <", value, "inField");
            return (Criteria) this;
        }

        public Criteria andInFieldLessThanOrEqualTo(String value) {
            addCriterion("in_field <=", value, "inField");
            return (Criteria) this;
        }

        public Criteria andInFieldLike(String value) {
            addCriterion("in_field like", value, "inField");
            return (Criteria) this;
        }

        public Criteria andInFieldNotLike(String value) {
            addCriterion("in_field not like", value, "inField");
            return (Criteria) this;
        }

        public Criteria andInFieldIn(List<String> values) {
            addCriterion("in_field in", values, "inField");
            return (Criteria) this;
        }

        public Criteria andInFieldNotIn(List<String> values) {
            addCriterion("in_field not in", values, "inField");
            return (Criteria) this;
        }

        public Criteria andInFieldBetween(String value1, String value2) {
            addCriterion("in_field between", value1, value2, "inField");
            return (Criteria) this;
        }

        public Criteria andInFieldNotBetween(String value1, String value2) {
            addCriterion("in_field not between", value1, value2, "inField");
            return (Criteria) this;
        }

        public Criteria andMsgOverwriteIsNull() {
            addCriterion("msg_overwrite is null");
            return (Criteria) this;
        }

        public Criteria andMsgOverwriteIsNotNull() {
            addCriterion("msg_overwrite is not null");
            return (Criteria) this;
        }

        public Criteria andMsgOverwriteEqualTo(Short value) {
            addCriterion("msg_overwrite =", value, "msgOverwrite");
            return (Criteria) this;
        }

        public Criteria andMsgOverwriteNotEqualTo(Short value) {
            addCriterion("msg_overwrite <>", value, "msgOverwrite");
            return (Criteria) this;
        }

        public Criteria andMsgOverwriteGreaterThan(Short value) {
            addCriterion("msg_overwrite >", value, "msgOverwrite");
            return (Criteria) this;
        }

        public Criteria andMsgOverwriteGreaterThanOrEqualTo(Short value) {
            addCriterion("msg_overwrite >=", value, "msgOverwrite");
            return (Criteria) this;
        }

        public Criteria andMsgOverwriteLessThan(Short value) {
            addCriterion("msg_overwrite <", value, "msgOverwrite");
            return (Criteria) this;
        }

        public Criteria andMsgOverwriteLessThanOrEqualTo(Short value) {
            addCriterion("msg_overwrite <=", value, "msgOverwrite");
            return (Criteria) this;
        }

        public Criteria andMsgOverwriteIn(List<Short> values) {
            addCriterion("msg_overwrite in", values, "msgOverwrite");
            return (Criteria) this;
        }

        public Criteria andMsgOverwriteNotIn(List<Short> values) {
            addCriterion("msg_overwrite not in", values, "msgOverwrite");
            return (Criteria) this;
        }

        public Criteria andMsgOverwriteBetween(Short value1, Short value2) {
            addCriterion("msg_overwrite between", value1, value2, "msgOverwrite");
            return (Criteria) this;
        }

        public Criteria andMsgOverwriteNotBetween(Short value1, Short value2) {
            addCriterion("msg_overwrite not between", value1, value2, "msgOverwrite");
            return (Criteria) this;
        }

        public Criteria andExMsgFieldIsNull() {
            addCriterion("ex_msg_field is null");
            return (Criteria) this;
        }

        public Criteria andExMsgFieldIsNotNull() {
            addCriterion("ex_msg_field is not null");
            return (Criteria) this;
        }

        public Criteria andExMsgFieldEqualTo(String value) {
            addCriterion("ex_msg_field =", value, "exMsgField");
            return (Criteria) this;
        }

        public Criteria andExMsgFieldNotEqualTo(String value) {
            addCriterion("ex_msg_field <>", value, "exMsgField");
            return (Criteria) this;
        }

        public Criteria andExMsgFieldGreaterThan(String value) {
            addCriterion("ex_msg_field >", value, "exMsgField");
            return (Criteria) this;
        }

        public Criteria andExMsgFieldGreaterThanOrEqualTo(String value) {
            addCriterion("ex_msg_field >=", value, "exMsgField");
            return (Criteria) this;
        }

        public Criteria andExMsgFieldLessThan(String value) {
            addCriterion("ex_msg_field <", value, "exMsgField");
            return (Criteria) this;
        }

        public Criteria andExMsgFieldLessThanOrEqualTo(String value) {
            addCriterion("ex_msg_field <=", value, "exMsgField");
            return (Criteria) this;
        }

        public Criteria andExMsgFieldLike(String value) {
            addCriterion("ex_msg_field like", value, "exMsgField");
            return (Criteria) this;
        }

        public Criteria andExMsgFieldNotLike(String value) {
            addCriterion("ex_msg_field not like", value, "exMsgField");
            return (Criteria) this;
        }

        public Criteria andExMsgFieldIn(List<String> values) {
            addCriterion("ex_msg_field in", values, "exMsgField");
            return (Criteria) this;
        }

        public Criteria andExMsgFieldNotIn(List<String> values) {
            addCriterion("ex_msg_field not in", values, "exMsgField");
            return (Criteria) this;
        }

        public Criteria andExMsgFieldBetween(String value1, String value2) {
            addCriterion("ex_msg_field between", value1, value2, "exMsgField");
            return (Criteria) this;
        }

        public Criteria andExMsgFieldNotBetween(String value1, String value2) {
            addCriterion("ex_msg_field not between", value1, value2, "exMsgField");
            return (Criteria) this;
        }

        public Criteria andInMsgFieldIsNull() {
            addCriterion("in_msg_field is null");
            return (Criteria) this;
        }

        public Criteria andInMsgFieldIsNotNull() {
            addCriterion("in_msg_field is not null");
            return (Criteria) this;
        }

        public Criteria andInMsgFieldEqualTo(String value) {
            addCriterion("in_msg_field =", value, "inMsgField");
            return (Criteria) this;
        }

        public Criteria andInMsgFieldNotEqualTo(String value) {
            addCriterion("in_msg_field <>", value, "inMsgField");
            return (Criteria) this;
        }

        public Criteria andInMsgFieldGreaterThan(String value) {
            addCriterion("in_msg_field >", value, "inMsgField");
            return (Criteria) this;
        }

        public Criteria andInMsgFieldGreaterThanOrEqualTo(String value) {
            addCriterion("in_msg_field >=", value, "inMsgField");
            return (Criteria) this;
        }

        public Criteria andInMsgFieldLessThan(String value) {
            addCriterion("in_msg_field <", value, "inMsgField");
            return (Criteria) this;
        }

        public Criteria andInMsgFieldLessThanOrEqualTo(String value) {
            addCriterion("in_msg_field <=", value, "inMsgField");
            return (Criteria) this;
        }

        public Criteria andInMsgFieldLike(String value) {
            addCriterion("in_msg_field like", value, "inMsgField");
            return (Criteria) this;
        }

        public Criteria andInMsgFieldNotLike(String value) {
            addCriterion("in_msg_field not like", value, "inMsgField");
            return (Criteria) this;
        }

        public Criteria andInMsgFieldIn(List<String> values) {
            addCriterion("in_msg_field in", values, "inMsgField");
            return (Criteria) this;
        }

        public Criteria andInMsgFieldNotIn(List<String> values) {
            addCriterion("in_msg_field not in", values, "inMsgField");
            return (Criteria) this;
        }

        public Criteria andInMsgFieldBetween(String value1, String value2) {
            addCriterion("in_msg_field between", value1, value2, "inMsgField");
            return (Criteria) this;
        }

        public Criteria andInMsgFieldNotBetween(String value1, String value2) {
            addCriterion("in_msg_field not between", value1, value2, "inMsgField");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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