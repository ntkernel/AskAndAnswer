package com.ruiyi.askandanswer.ask.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AnswerData implements Serializable {


    public int getThumbUpFlag() {
        return thumbUpFlag;
    }

    public void setThumbUpFlag(int thumbUpFlag) {
        this.thumbUpFlag = thumbUpFlag;
    }

    /**
     * questionData : {"private":0,"askQuestionGuid":"bb44ff0f254d4b64ae6f55bf87e88a14","thumupNumber":58,"viewNumber":61,"operateTime":"2018-09-19T08:47:45","askData":{"guid":"bb44ff0f254d4b64ae6f55bf87e88a14","operateTime":"2018-09-19T08:47:45","answerTime":"2018-09-20T08:47:45","askUser":{"guid":"a8b1127e-5efb-11e8-8720-3333333333333","name":"小小明","schoolGuid":null,"schoolName":"中山中学","province":"广东","title":null,"avatarUrl":null},"answerUser":{"guid":"a8b1127e-5efb-11e8-8720-382c4a626a68","name":"刘涛","schoolGuid":null,"schoolName":"北京展示中心","province":"北京","title":"特级教师","avatarUrl":null},"question":"什么是裂项求和？该注意些什么呢？哪种题应该用列项求和？如何使用裂项相消求和？","answer":"<p>1.数列裂项求和是将数列中的每项分解，使得中间项互相抵消，最终简化求和。<\/p><p class=MsoNormal><span>2.<\/span><span>利用裂项相消法求和时，应注意抵消后并不一定只剩下第一项和最后一项，也有可能前面剩两项，后面也剩两项，再就是将通项公式裂项后，有时候需要调整前面的系数，使裂开的两项之差和系数之积与原通项公式相等；<\/span><\/p>","subjectId":"02","subject":"数学","refrenceResource":[{"guid":"1ceefa5cd35e870e6d2841c3ce9dcb6c","resourceType":".docx","title":"课前先学练习备选","type":null,"usageType":null}],"refrenceQuestion":["0a6e1e12ed56499696f799cb829993a5"],"logs":null},"askStatus":1}
     * explanation : []
     * recommend : [{"private":0,"askQuestionGuid":"c99b81ac404645b6b087a927b591eb0b","viewNumber":81,"operateTime":"2018-09-19T08:47:45","askData":{"guid":"c99b81ac404645b6b087a927b591eb0b","operateTime":"2018-09-19T08:47:45","answerTime":"2018-09-20T08:47:45","askUser":{"guid":"a8b1127e-5efb-11e8-8720-3333333333333","name":"小小明","schoolGuid":null,"schoolName":"中山中学","province":"广东","title":null,"avatarUrl":null},"answerUser":{"guid":"a8b1127e-5efb-11e8-8720-382c4a626a68","name":"刘涛","schoolGuid":null,"schoolName":"北京展示中心","province":"北京","title":"特级教师","avatarUrl":null},"question":"什么是裂项求和？该注意些什么呢？哪种题应该用列项求和？如何使用裂项相消求和？","answer":"1.数列裂项求和是将数列中的每项分解，使得中间项互相抵消，最终简化求和。2.利用裂项相消法求和时，应注意抵消后并不一定只剩下第一项和最后一项，也有可能前面剩两项，后面也剩两项，再就是将通项公式裂项后，有时候需要调整前面的系数，使裂开的两项之差和系数之积与原通项公式相等；","subjectId":"02","subject":"数学"},"askStatus":0},{"private":0,"askQuestionGuid":"89150a95888a4ef0b6b4b849b9acd55e","viewNumber":46,"operateTime":"2018-09-19T08:47:45","askData":{"guid":"89150a95888a4ef0b6b4b849b9acd55e","operateTime":"2018-09-19T08:47:45","answerTime":"2018-09-20T08:47:45","askUser":{"guid":"a8b1127e-5efb-11e8-8720-3333333333333","name":"小小明","schoolGuid":null,"schoolName":"中山中学","province":"广东","title":null,"avatarUrl":null},"answerUser":{"guid":"a8b1127e-5efb-11e8-8720-382c4a626a68","name":"刘涛","schoolGuid":null,"schoolName":"北京展示中心","province":"北京","title":"特级教师","avatarUrl":null},"question":"什么是裂项求和？该注意些什么呢？哪种题应该用列项求和？如何使用裂项相消求和？","answer":"1.数列裂项求和是将数列中的每项分解，使得中间项互相抵消，最终简化求和。2.利用裂项相消法求和时，应注意抵消后并不一定只剩下第一项和最后一项，也有可能前面剩两项，后面也剩两项，再就是将通项公式裂项后，有时候需要调整前面的系数，使裂开的两项之差和系数之积与原通项公式相等；","subjectId":"02","subject":"数学"},"askStatus":0}]
     */

    private int thumbUpFlag;
    private QuestionDataBean questionData;
    private List<ExplanationBean> explanation;
    private List<RecommendBean> recommend;

    public QuestionDataBean getQuestionData() {
        return questionData;
    }

    public void setQuestionData(QuestionDataBean questionData) {
        this.questionData = questionData;
    }

    public List<ExplanationBean> getExplanation() {
        return explanation;
    }

    public void setExplanation(List<ExplanationBean> explanation) {
        this.explanation = explanation;
    }

    public List<RecommendBean> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<RecommendBean> recommend) {
        this.recommend = recommend;
    }

    public static class ExplanationBean{
        private String askquestionGuid;
        private String fromUserGuid;
        private String toUserGuid;

        public String getAskquestionGuid() {
            return askquestionGuid;
        }

        public void setAskquestionGuid(String askquestionGuid) {
            this.askquestionGuid = askquestionGuid;
        }

        public String getFromUserGuid() {
            return fromUserGuid;
        }

        public void setFromUserGuid(String fromUserGuid) {
            this.fromUserGuid = fromUserGuid;
        }

        public String getToUserGuid() {
            return toUserGuid;
        }

        public void setToUserGuid(String toUserGuid) {
            this.toUserGuid = toUserGuid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getOperateTime() {
            return operateTime;
        }

        public void setOperateTime(String operateTime) {
            this.operateTime = operateTime;
        }

        private String content;
        private int type;
        private String operateTime;

    }

    public static class QuestionDataBean {
        /**
         * private : 0
         * askQuestionGuid : bb44ff0f254d4b64ae6f55bf87e88a14
         * thumupNumber : 58
         * viewNumber : 61
         * operateTime : 2018-09-19T08:47:45
         * askData : {"guid":"bb44ff0f254d4b64ae6f55bf87e88a14","operateTime":"2018-09-19T08:47:45","answerTime":"2018-09-20T08:47:45","askUser":{"guid":"a8b1127e-5efb-11e8-8720-3333333333333","name":"小小明","schoolGuid":null,"schoolName":"中山中学","province":"广东","title":null,"avatarUrl":null},"answerUser":{"guid":"a8b1127e-5efb-11e8-8720-382c4a626a68","name":"刘涛","schoolGuid":null,"schoolName":"北京展示中心","province":"北京","title":"特级教师","avatarUrl":null},"question":"什么是裂项求和？该注意些什么呢？哪种题应该用列项求和？如何使用裂项相消求和？","answer":"<p>1.数列裂项求和是将数列中的每项分解，使得中间项互相抵消，最终简化求和。<\/p><p class=MsoNormal><span>2.<\/span><span>利用裂项相消法求和时，应注意抵消后并不一定只剩下第一项和最后一项，也有可能前面剩两项，后面也剩两项，再就是将通项公式裂项后，有时候需要调整前面的系数，使裂开的两项之差和系数之积与原通项公式相等；<\/span><\/p>","subjectId":"02","subject":"数学","refrenceResource":[{"guid":"1ceefa5cd35e870e6d2841c3ce9dcb6c","resourceType":".docx","title":"课前先学练习备选","type":null,"usageType":null}],"refrenceQuestion":["0a6e1e12ed56499696f799cb829993a5"],"logs":null}
         * askStatus : 1
         */

        @SerializedName("private")
        private int privateX;
        private String askQuestionGuid;
        private int thumupNumber;
        private int viewNumber;
        private String operateTime;
        private AskDataBean askData;
        private int askStatus;

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

        public int getThumupNumber() {
            return thumupNumber;
        }

        public void setThumupNumber(int thumupNumber) {
            this.thumupNumber = thumupNumber;
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
             * guid : bb44ff0f254d4b64ae6f55bf87e88a14
             * operateTime : 2018-09-19T08:47:45
             * answerTime : 2018-09-20T08:47:45
             * askUser : {"guid":"a8b1127e-5efb-11e8-8720-3333333333333","name":"小小明","schoolGuid":null,"schoolName":"中山中学","province":"广东","title":null,"avatarUrl":null}
             * answerUser : {"guid":"a8b1127e-5efb-11e8-8720-382c4a626a68","name":"刘涛","schoolGuid":null,"schoolName":"北京展示中心","province":"北京","title":"特级教师","avatarUrl":null}
             * question : 什么是裂项求和？该注意些什么呢？哪种题应该用列项求和？如何使用裂项相消求和？
             * answer : <p>1.数列裂项求和是将数列中的每项分解，使得中间项互相抵消，最终简化求和。</p><p class=MsoNormal><span>2.</span><span>利用裂项相消法求和时，应注意抵消后并不一定只剩下第一项和最后一项，也有可能前面剩两项，后面也剩两项，再就是将通项公式裂项后，有时候需要调整前面的系数，使裂开的两项之差和系数之积与原通项公式相等；</span></p>
             * subjectId : 02
             * subject : 数学
             * refrenceResource : [{"guid":"1ceefa5cd35e870e6d2841c3ce9dcb6c","resourceType":".docx","title":"课前先学练习备选","type":null,"usageType":null}]
             * refrenceQuestion : ["0a6e1e12ed56499696f799cb829993a5"]
             * logs : null
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
            private Object logs;
            private List<RefrenceResourceBean> refrenceResource;
            private List<String> refrenceQuestion;

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

            public Object getLogs() {
                return logs;
            }

            public void setLogs(Object logs) {
                this.logs = logs;
            }

            public List<RefrenceResourceBean> getRefrenceResource() {
                return refrenceResource;
            }

            public void setRefrenceResource(List<RefrenceResourceBean> refrenceResource) {
                this.refrenceResource = refrenceResource;
            }

            public List<String> getRefrenceQuestion() {
                return refrenceQuestion;
            }

            public void setRefrenceQuestion(List<String> refrenceQuestion) {
                this.refrenceQuestion = refrenceQuestion;
            }

            public static class AskUserBean {
                /**
                 * guid : a8b1127e-5efb-11e8-8720-3333333333333
                 * name : 小小明
                 * schoolGuid : null
                 * schoolName : 中山中学
                 * province : 广东
                 * title : null
                 * avatarUrl : null
                 */

                private String guid;
                private String name;
                private Object schoolGuid;
                private String schoolName;
                private String province;
                private Object title;
                private Object avatarUrl;

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

                public Object getAvatarUrl() {
                    return avatarUrl;
                }

                public void setAvatarUrl(Object avatarUrl) {
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
                 * avatarUrl : null
                 */

                private String guid;
                private String name;
                private Object schoolGuid;
                private String schoolName;
                private String province;
                private String title;
                private Object avatarUrl;

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

                public Object getAvatarUrl() {
                    return avatarUrl;
                }

                public void setAvatarUrl(Object avatarUrl) {
                    this.avatarUrl = avatarUrl;
                }
            }

            public static class RefrenceResourceBean {
                /**
                 * guid : 1ceefa5cd35e870e6d2841c3ce9dcb6c
                 * resourceType : .docx
                 * title : 课前先学练习备选
                 * type : null
                 * usageType : null
                 */

                private String guid;
                private String resourceType;
                private String title;
                private Object type;
                private Object usageType;

                public String getGuid() {
                    return guid;
                }

                public void setGuid(String guid) {
                    this.guid = guid;
                }

                public String getResourceType() {
                    return resourceType;
                }

                public void setResourceType(String resourceType) {
                    this.resourceType = resourceType;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public Object getType() {
                    return type;
                }

                public void setType(Object type) {
                    this.type = type;
                }

                public Object getUsageType() {
                    return usageType;
                }

                public void setUsageType(Object usageType) {
                    this.usageType = usageType;
                }
            }
        }
    }

    public static class RecommendBean {
        /**
         * private : 0
         * askQuestionGuid : c99b81ac404645b6b087a927b591eb0b
         * viewNumber : 81
         * operateTime : 2018-09-19T08:47:45
         * askData : {"guid":"c99b81ac404645b6b087a927b591eb0b","operateTime":"2018-09-19T08:47:45","answerTime":"2018-09-20T08:47:45","askUser":{"guid":"a8b1127e-5efb-11e8-8720-3333333333333","name":"小小明","schoolGuid":null,"schoolName":"中山中学","province":"广东","title":null,"avatarUrl":null},"answerUser":{"guid":"a8b1127e-5efb-11e8-8720-382c4a626a68","name":"刘涛","schoolGuid":null,"schoolName":"北京展示中心","province":"北京","title":"特级教师","avatarUrl":null},"question":"什么是裂项求和？该注意些什么呢？哪种题应该用列项求和？如何使用裂项相消求和？","answer":"1.数列裂项求和是将数列中的每项分解，使得中间项互相抵消，最终简化求和。2.利用裂项相消法求和时，应注意抵消后并不一定只剩下第一项和最后一项，也有可能前面剩两项，后面也剩两项，再就是将通项公式裂项后，有时候需要调整前面的系数，使裂开的两项之差和系数之积与原通项公式相等；","subjectId":"02","subject":"数学"}
         * askStatus : 0
         */

        @SerializedName("private")
        private int privateX;
        private String askQuestionGuid;
        private int viewNumber;
        private String operateTime;
        private AskDataBeanX askData;
        private int askStatus;

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

        public AskDataBeanX getAskData() {
            return askData;
        }

        public void setAskData(AskDataBeanX askData) {
            this.askData = askData;
        }

        public int getAskStatus() {
            return askStatus;
        }

        public void setAskStatus(int askStatus) {
            this.askStatus = askStatus;
        }

        public static class AskDataBeanX {
            /**
             * guid : c99b81ac404645b6b087a927b591eb0b
             * operateTime : 2018-09-19T08:47:45
             * answerTime : 2018-09-20T08:47:45
             * askUser : {"guid":"a8b1127e-5efb-11e8-8720-3333333333333","name":"小小明","schoolGuid":null,"schoolName":"中山中学","province":"广东","title":null,"avatarUrl":null}
             * answerUser : {"guid":"a8b1127e-5efb-11e8-8720-382c4a626a68","name":"刘涛","schoolGuid":null,"schoolName":"北京展示中心","province":"北京","title":"特级教师","avatarUrl":null}
             * question : 什么是裂项求和？该注意些什么呢？哪种题应该用列项求和？如何使用裂项相消求和？
             * answer : 1.数列裂项求和是将数列中的每项分解，使得中间项互相抵消，最终简化求和。2.利用裂项相消法求和时，应注意抵消后并不一定只剩下第一项和最后一项，也有可能前面剩两项，后面也剩两项，再就是将通项公式裂项后，有时候需要调整前面的系数，使裂开的两项之差和系数之积与原通项公式相等；
             * subjectId : 02
             * subject : 数学
             */

            private String guid;
            private String operateTime;
            private String answerTime;
            private AskUserBeanX askUser;
            private AnswerUserBeanX answerUser;
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

            public AskUserBeanX getAskUser() {
                return askUser;
            }

            public void setAskUser(AskUserBeanX askUser) {
                this.askUser = askUser;
            }

            public AnswerUserBeanX getAnswerUser() {
                return answerUser;
            }

            public void setAnswerUser(AnswerUserBeanX answerUser) {
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

            public static class AskUserBeanX {
                /**
                 * guid : a8b1127e-5efb-11e8-8720-3333333333333
                 * name : 小小明
                 * schoolGuid : null
                 * schoolName : 中山中学
                 * province : 广东
                 * title : null
                 * avatarUrl : null
                 */

                private String guid;
                private String name;
                private Object schoolGuid;
                private String schoolName;
                private String province;
                private Object title;
                private Object avatarUrl;

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

                public Object getAvatarUrl() {
                    return avatarUrl;
                }

                public void setAvatarUrl(Object avatarUrl) {
                    this.avatarUrl = avatarUrl;
                }
            }

            public static class AnswerUserBeanX {
                /**
                 * guid : a8b1127e-5efb-11e8-8720-382c4a626a68
                 * name : 刘涛
                 * schoolGuid : null
                 * schoolName : 北京展示中心
                 * province : 北京
                 * title : 特级教师
                 * avatarUrl : null
                 */

                private String guid;
                private String name;
                private Object schoolGuid;
                private String schoolName;
                private String province;
                private String title;
                private Object avatarUrl;

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

                public Object getAvatarUrl() {
                    return avatarUrl;
                }

                public void setAvatarUrl(Object avatarUrl) {
                    this.avatarUrl = avatarUrl;
                }
            }
        }
    }
}
