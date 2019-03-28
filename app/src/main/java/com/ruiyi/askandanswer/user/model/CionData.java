package com.ruiyi.askandanswer.user.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CionData {


    private List<CostsBean> costs;

    public List<CostsBean> getCosts() {
        return costs;
    }

    public void setCosts(List<CostsBean> costs) {
        this.costs = costs;
    }

    public static class CostsBean {
        /**
         * title : 2019-01-24T00:00:00
         * questionDataGroup : [{"actionNumber":1,"actionType":1,"source":"","private":0,"askQuestionGuid":"5333c8e6c1f942d5959e5c0ff6f67f63","viewNumber":0,"operateTime":"2019-01-24T00:00:00","askData":{"guid":"5333c8e6c1f942d5959e5c0ff6f67f63","operateTime":"2018-09-19T08:47:45","answerTime":"2018-09-20T08:47:45","askUser":{"guid":"a8b1127e-5efb-11e8-8720-3333333333333","name":"小小明","schoolGuid":null,"schoolName":"中山中学","province":"广东","title":null,"avatarUrl":"http://center.lexuewang.cn:5003a8b/112/7e5/a8b1127e-5efb-11e8-8720-3333333333333.jpeg"},"answerUser":{"guid":"a8b1127e-5efb-11e8-8720-382c4a626a68","name":"刘涛","schoolGuid":null,"schoolName":"北京展示中心","province":"北京","title":"特级教师","avatarUrl":"http://center.lexuewang.cn:5003/defalut.jpeg"},"question":"什么是裂项求和？该注意些什么呢？哪种题应该用列项求和？如何使用裂项相消求和？","answer":"1.数列裂项求和是将数列中的每项分解，使得中间项互相抵消，最终简化求和。2.利用裂项相消法求和时，应注意抵消后并不一定只剩下第一项和最后一项，也有可能前面剩两项，后面也剩两项，再就是将通项公式裂项后，有时候需要调整前面的系数，使裂开的两项之差和系数之积与原通项公式相等；","subjectId":"02","subject":"数学"},"askStatus":0}]
         */

        private String title;
        private List<QuestionDataGroupBean> questionDataGroup;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<QuestionDataGroupBean> getQuestionDataGroup() {
            return questionDataGroup;
        }

        public void setQuestionDataGroup(List<QuestionDataGroupBean> questionDataGroup) {
            this.questionDataGroup = questionDataGroup;
        }

        public static class QuestionDataGroupBean {
            /**
             * actionNumber : 1
             * actionType : 1
             * source :
             * private : 0
             * askQuestionGuid : 5333c8e6c1f942d5959e5c0ff6f67f63
             * viewNumber : 0
             * operateTime : 2019-01-24T00:00:00
             * askData : {"guid":"5333c8e6c1f942d5959e5c0ff6f67f63","operateTime":"2018-09-19T08:47:45","answerTime":"2018-09-20T08:47:45","askUser":{"guid":"a8b1127e-5efb-11e8-8720-3333333333333","name":"小小明","schoolGuid":null,"schoolName":"中山中学","province":"广东","title":null,"avatarUrl":"http://center.lexuewang.cn:5003a8b/112/7e5/a8b1127e-5efb-11e8-8720-3333333333333.jpeg"},"answerUser":{"guid":"a8b1127e-5efb-11e8-8720-382c4a626a68","name":"刘涛","schoolGuid":null,"schoolName":"北京展示中心","province":"北京","title":"特级教师","avatarUrl":"http://center.lexuewang.cn:5003/defalut.jpeg"},"question":"什么是裂项求和？该注意些什么呢？哪种题应该用列项求和？如何使用裂项相消求和？","answer":"1.数列裂项求和是将数列中的每项分解，使得中间项互相抵消，最终简化求和。2.利用裂项相消法求和时，应注意抵消后并不一定只剩下第一项和最后一项，也有可能前面剩两项，后面也剩两项，再就是将通项公式裂项后，有时候需要调整前面的系数，使裂开的两项之差和系数之积与原通项公式相等；","subjectId":"02","subject":"数学"}
             * askStatus : 0
             */

            private int actionNumber;
            private int actionType;
            private String source;
            @SerializedName("private")
            private int privateX;
            private String askQuestionGuid;
            private int viewNumber;
            private String operateTime;
            private AskDataBean askData;
            private int askStatus;

            public int getActionNumber() {
                return actionNumber;
            }

            public void setActionNumber(int actionNumber) {
                this.actionNumber = actionNumber;
            }

            public int getActionType() {
                return actionType;
            }

            public void setActionType(int actionType) {
                this.actionType = actionType;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public int getPrivateX() {
                return privateX;
            }

            public void setPrivateX(int privateX) {
                this.privateX = privateX;
            }

            public String getAskQuestionGuid() {
                return askQuestionGuid;
            }

            public void setAskQuestionGuid(String askQuestionGuid) {
                this.askQuestionGuid = askQuestionGuid;
            }

            public int getViewNumber() {
                return viewNumber;
            }

            public void setViewNumber(int viewNumber) {
                this.viewNumber = viewNumber;
            }

            public String getOperateTime() {
                return operateTime;
            }

            public void setOperateTime(String operateTime) {
                this.operateTime = operateTime;
            }

            public AskDataBean getAskData() {
                return askData;
            }

            public void setAskData(AskDataBean askData) {
                this.askData = askData;
            }

            public int getAskStatus() {
                return askStatus;
            }

            public void setAskStatus(int askStatus) {
                this.askStatus = askStatus;
            }

            public static class AskDataBean {
                /**
                 * guid : 5333c8e6c1f942d5959e5c0ff6f67f63
                 * operateTime : 2018-09-19T08:47:45
                 * answerTime : 2018-09-20T08:47:45
                 * askUser : {"guid":"a8b1127e-5efb-11e8-8720-3333333333333","name":"小小明","schoolGuid":null,"schoolName":"中山中学","province":"广东","title":null,"avatarUrl":"http://center.lexuewang.cn:5003a8b/112/7e5/a8b1127e-5efb-11e8-8720-3333333333333.jpeg"}
                 * answerUser : {"guid":"a8b1127e-5efb-11e8-8720-382c4a626a68","name":"刘涛","schoolGuid":null,"schoolName":"北京展示中心","province":"北京","title":"特级教师","avatarUrl":"http://center.lexuewang.cn:5003/defalut.jpeg"}
                 * question : 什么是裂项求和？该注意些什么呢？哪种题应该用列项求和？如何使用裂项相消求和？
                 * answer : 1.数列裂项求和是将数列中的每项分解，使得中间项互相抵消，最终简化求和。2.利用裂项相消法求和时，应注意抵消后并不一定只剩下第一项和最后一项，也有可能前面剩两项，后面也剩两项，再就是将通项公式裂项后，有时候需要调整前面的系数，使裂开的两项之差和系数之积与原通项公式相等；
                 * subjectId : 02
                 * subject : 数学
                 */

                private String guid;
                private String operateTime;
                private String answerTime;
                private AskUserBean askUser;
                private AnswerUserBean answerUser;
                private String question;
                private String answer;
                private String subjectId;
                private String subject;

                public String getGuid() {
                    return guid;
                }

                public void setGuid(String guid) {
                    this.guid = guid;
                }

                public String getOperateTime() {
                    return operateTime;
                }

                public void setOperateTime(String operateTime) {
                    this.operateTime = operateTime;
                }

                public String getAnswerTime() {
                    return answerTime;
                }

                public void setAnswerTime(String answerTime) {
                    this.answerTime = answerTime;
                }

                public AskUserBean getAskUser() {
                    return askUser;
                }

                public void setAskUser(AskUserBean askUser) {
                    this.askUser = askUser;
                }

                public AnswerUserBean getAnswerUser() {
                    return answerUser;
                }

                public void setAnswerUser(AnswerUserBean answerUser) {
                    this.answerUser = answerUser;
                }

                public String getQuestion() {
                    return question;
                }

                public void setQuestion(String question) {
                    this.question = question;
                }

                public String getAnswer() {
                    return answer;
                }

                public void setAnswer(String answer) {
                    this.answer = answer;
                }

                public String getSubjectId() {
                    return subjectId;
                }

                public void setSubjectId(String subjectId) {
                    this.subjectId = subjectId;
                }

                public String getSubject() {
                    return subject;
                }

                public void setSubject(String subject) {
                    this.subject = subject;
                }

                public static class AskUserBean {
                    /**
                     * guid : a8b1127e-5efb-11e8-8720-3333333333333
                     * name : 小小明
                     * schoolGuid : null
                     * schoolName : 中山中学
                     * province : 广东
                     * title : null
                     * avatarUrl : http://center.lexuewang.cn:5003a8b/112/7e5/a8b1127e-5efb-11e8-8720-3333333333333.jpeg
                     */

                    private String guid;
                    private String name;
                    private Object schoolGuid;
                    private String schoolName;
                    private String province;
                    private Object title;
                    private String avatarUrl;

                    public String getGuid() {
                        return guid;
                    }

                    public void setGuid(String guid) {
                        this.guid = guid;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public Object getSchoolGuid() {
                        return schoolGuid;
                    }

                    public void setSchoolGuid(Object schoolGuid) {
                        this.schoolGuid = schoolGuid;
                    }

                    public String getSchoolName() {
                        return schoolName;
                    }

                    public void setSchoolName(String schoolName) {
                        this.schoolName = schoolName;
                    }

                    public String getProvince() {
                        return province;
                    }

                    public void setProvince(String province) {
                        this.province = province;
                    }

                    public Object getTitle() {
                        return title;
                    }

                    public void setTitle(Object title) {
                        this.title = title;
                    }

                    public String getAvatarUrl() {
                        return avatarUrl;
                    }

                    public void setAvatarUrl(String avatarUrl) {
                        this.avatarUrl = avatarUrl;
                    }
                }

                public static class AnswerUserBean {
                    /**
                     * guid : a8b1127e-5efb-11e8-8720-382c4a626a68
                     * name : 刘涛
                     * schoolGuid : null
                     * schoolName : 北京展示中心
                     * province : 北京
                     * title : 特级教师
                     * avatarUrl : http://center.lexuewang.cn:5003/defalut.jpeg
                     */

                    private String guid;
                    private String name;
                    private Object schoolGuid;
                    private String schoolName;
                    private String province;
                    private String title;
                    private String avatarUrl;

                    public String getGuid() {
                        return guid;
                    }

                    public void setGuid(String guid) {
                        this.guid = guid;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public Object getSchoolGuid() {
                        return schoolGuid;
                    }

                    public void setSchoolGuid(Object schoolGuid) {
                        this.schoolGuid = schoolGuid;
                    }

                    public String getSchoolName() {
                        return schoolName;
                    }

                    public void setSchoolName(String schoolName) {
                        this.schoolName = schoolName;
                    }

                    public String getProvince() {
                        return province;
                    }

                    public void setProvince(String province) {
                        this.province = province;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getAvatarUrl() {
                        return avatarUrl;
                    }

                    public void setAvatarUrl(String avatarUrl) {
                        this.avatarUrl = avatarUrl;
                    }
                }
            }
        }
    }
}
