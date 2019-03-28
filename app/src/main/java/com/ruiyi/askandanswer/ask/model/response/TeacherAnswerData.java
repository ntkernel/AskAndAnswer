package com.ruiyi.askandanswer.ask.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeacherAnswerData {

    /**
     * attentionFlag : 0
     * questionData : [{"private":0,"askQuestionGuid":"0ef35e34ea214add80bbc8bbb4b7919c","viewNumber":0,"operateTime":"2018-09-19T08:47:45","askData":{"guid":"0ef35e34ea214add80bbc8bbb4b7919c","operateTime":"2018-09-19T08:47:45","answerTime":"2018-09-20T08:47:45","askUser":{"guid":"a8b1127e-5efb-11e8-8720-3333333333333","name":"小小明","schoolGuid":null,"schoolName":"中山中学","province":"广东","title":null,"avatarUrl":null},"answerUser":{"guid":"a8b1127e-5efb-11e8-8720-382c4a626a68","name":"刘涛","schoolGuid":null,"schoolName":"北京展示中心","province":"北京","title":"特级教师","avatarUrl":null},"question":"什么是裂项求和？该注意些什么呢？哪种题应该用列项求和？如何使用裂项相消求和？","answer":"<p>1.数列裂项求和是将数列中的每项分解，使得中间项互相抵消，最终简化求和。<\/p><p class=MsoNormal><span>2.<\/span><span>利用裂项相消法求和时，应注意抵消后并不一定只剩下第一项和最后一项，也有可能前面剩两项，后面也剩两项，再就是将通项公式裂项后，有时候需要调整前面的系数，使裂开的两项之差和系数之积与原通项公式相等；<\/span><\/p>","subjectId":"02","subject":"数学"},"askStatus":0},{"private":0,"askQuestionGuid":"b66344d86fbb46b3826a921db0fe4a33","viewNumber":0,"operateTime":"2018-09-19T08:47:45","askData":{"guid":"b66344d86fbb46b3826a921db0fe4a33","operateTime":"2018-09-19T08:47:45","answerTime":"2018-09-20T08:47:45","askUser":{"guid":"a8b1127e-5efb-11e8-8720-3333333333333","name":"小小明","schoolGuid":null,"schoolName":"中山中学","province":"广东","title":null,"avatarUrl":null},"answerUser":{"guid":"a8b1127e-5efb-11e8-8720-382c4a626a68","name":"刘涛","schoolGuid":null,"schoolName":"北京展示中心","province":"北京","title":"特级教师","avatarUrl":null},"question":"什么是裂项求和？该注意些什么呢？哪种题应该用列项求和？如何使用裂项相消求和？","answer":"<p>1.数列裂项求和是将数列中的每项分解，使得中间项互相抵消，最终简化求和。<\/p><p class=MsoNormal><span>2.<\/span><span>利用裂项相消法求和时，应注意抵消后并不一定只剩下第一项和最后一项，也有可能前面剩两项，后面也剩两项，再就是将通项公式裂项后，有时候需要调整前面的系数，使裂开的两项之差和系数之积与原通项公式相等；<\/span><\/p>","subjectId":"02","subject":"数学"},"askStatus":0},{"private":0,"askQuestionGuid":"13af2e82370a4e38ad0151f8e7705982","viewNumber":0,"operateTime":"2018-09-19T08:47:45","askData":{"guid":"13af2e82370a4e38ad0151f8e7705982","operateTime":"2018-09-19T08:47:45","answerTime":"2018-09-20T08:47:45","askUser":{"guid":"a8b1127e-5efb-11e8-8720-3333333333333","name":"小小明","schoolGuid":null,"schoolName":"中山中学","province":"广东","title":null,"avatarUrl":null},"answerUser":{"guid":"a8b1127e-5efb-11e8-8720-382c4a626a68","name":"刘涛","schoolGuid":null,"schoolName":"北京展示中心","province":"北京","title":"特级教师","avatarUrl":null},"question":"什么是裂项求和？该注意些什么呢？哪种题应该用列项求和？如何使用裂项相消求和？","answer":"<p>1.数列裂项求和是将数列中的每项分解，使得中间项互相抵消，最终简化求和。<\/p><p class=MsoNormal><span>2.<\/span><span>利用裂项相消法求和时，应注意抵消后并不一定只剩下第一项和最后一项，也有可能前面剩两项，后面也剩两项，再就是将通项公式裂项后，有时候需要调整前面的系数，使裂开的两项之差和系数之积与原通项公式相等；<\/span><\/p>","subjectId":"02","subject":"数学"},"askStatus":0}]
     * teacherInfor  : {"guid ":"a8b1127e - 5e fb - 11e8 - 8720 - 382 c4a626a68 ","name ":"刘涛 ","schoolName ":"北京展示中心 ","province ":"北京 ","title ":null,"thumbupNumber ":2654,"totalNumber ":48}
     */

    private int attentionFlag;

    public List<QuestionDataBean> getQuestionData() {
        return questionData;
    }

    public void setQuestionData(List<QuestionDataBean> questionData) {
        this.questionData = questionData;
    }

    public TeacherInforBean getTeacherInforBean() {
        return teacherInfor;
    }

    public void setTeacherInforBean(TeacherInforBean teacherInfor) {
        this.teacherInfor = teacherInfor;
    }

    private List<QuestionDataBean> questionData;
    private TeacherInforBean teacherInfor;

    public int getAttentionFlag() {
        return attentionFlag;
    }

    public void setAttentionFlag(int attentionFlag) {
        this.attentionFlag = attentionFlag;
    }

    public static class TeacherInforBean {
        /**
         * guid  : a8b1127e - 5e fb - 11e8 - 8720 - 382 c4a626a68
         * name  : 刘涛
         * schoolName  : 北京展示中心
         * province  : 北京
         * title  : null
         * thumbupNumber  : 2654
         * totalNumber  : 48
         */

        private String Guid;
        private String Name;
        private String SchoolName;
        private String Province;
        private String Title;

        public String getGuid() {
            return Guid;
        }

        public void setGuid(String guid) {
            Guid = guid;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getSchoolName() {
            return SchoolName;
        }

        public void setSchoolName(String schoolName) {
            SchoolName = schoolName;
        }

        public String getProvince() {
            return Province;
        }

        public void setProvince(String province) {
            Province = province;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public int getThumbupNumber() {
            return ThumbupNumber;
        }

        public void setThumbupNumber(int thumbupNumber) {
            ThumbupNumber = thumbupNumber;
        }

        public int getTotalNumber() {
            return TotalNumber;
        }

        public void setTotalNumber(int totalNumber) {
            TotalNumber = totalNumber;
        }

        private int ThumbupNumber;
        private int TotalNumber;

        public String getAvatarUrl() {
            return AvatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            AvatarUrl = avatarUrl;
        }

        private String AvatarUrl;

    }

    public static class QuestionDataBean {
        /**
         * private : 0
         * askQuestionGuid : 0ef35e34ea214add80bbc8bbb4b7919c
         * viewNumber : 0
         * operateTime : 2018-09-19T08:47:45
         * askData : {"guid":"0ef35e34ea214add80bbc8bbb4b7919c","operateTime":"2018-09-19T08:47:45","answerTime":"2018-09-20T08:47:45","askUser":{"guid":"a8b1127e-5efb-11e8-8720-3333333333333","name":"小小明","schoolGuid":null,"schoolName":"中山中学","province":"广东","title":null,"avatarUrl":null},"answerUser":{"guid":"a8b1127e-5efb-11e8-8720-382c4a626a68","name":"刘涛","schoolGuid":null,"schoolName":"北京展示中心","province":"北京","title":"特级教师","avatarUrl":null},"question":"什么是裂项求和？该注意些什么呢？哪种题应该用列项求和？如何使用裂项相消求和？","answer":"<p>1.数列裂项求和是将数列中的每项分解，使得中间项互相抵消，最终简化求和。<\/p><p class=MsoNormal><span>2.<\/span><span>利用裂项相消法求和时，应注意抵消后并不一定只剩下第一项和最后一项，也有可能前面剩两项，后面也剩两项，再就是将通项公式裂项后，有时候需要调整前面的系数，使裂开的两项之差和系数之积与原通项公式相等；<\/span><\/p>","subjectId":"02","subject":"数学"}
         * askStatus : 0
         */

        @SerializedName("private")
        private int privateX;
        private String askQuestionGuid;
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
             * guid : 0ef35e34ea214add80bbc8bbb4b7919c
             * operateTime : 2018-09-19T08:47:45
             * answerTime : 2018-09-20T08:47:45
             * askUser : {"guid":"a8b1127e-5efb-11e8-8720-3333333333333","name":"小小明","schoolGuid":null,"schoolName":"中山中学","province":"广东","title":null,"avatarUrl":null}
             * answerUser : {"guid":"a8b1127e-5efb-11e8-8720-382c4a626a68","name":"刘涛","schoolGuid":null,"schoolName":"北京展示中心","province":"北京","title":"特级教师","avatarUrl":null}
             * question : 什么是裂项求和？该注意些什么呢？哪种题应该用列项求和？如何使用裂项相消求和？
             * answer : <p>1.数列裂项求和是将数列中的每项分解，使得中间项互相抵消，最终简化求和。</p><p class=MsoNormal><span>2.</span><span>利用裂项相消法求和时，应注意抵消后并不一定只剩下第一项和最后一项，也有可能前面剩两项，后面也剩两项，再就是将通项公式裂项后，有时候需要调整前面的系数，使裂开的两项之差和系数之积与原通项公式相等；</span></p>
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
        }
    }
}

