package com.ruiyi.askandanswer.user.model;

import java.util.List;

public class CollectData {


    /**
     * questionCollect : [{"title":"2019-01-15T00:00:00","questionCollectGroup":[{"questionData":{"guid":"0a6e1e12ed56499696f799cb829993a5","questionType":"单选题","grade":0,"answer":["C"],"analysis":"<p class=\"MsoNormal\">由题可知焦点为<img width=\"35\" height=\"42\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACMAAAAqCAMAAAAOCBKjAAAAAXNSR0IArs4c6QAAAGlQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjqQOma2OpC2OpDbZgAAZgA6ZgBmZpDbZrb/kDoAkDo6kNv/tmYAtpCQttv/tv//25A627Zm2////7Zm/7aQ/9uQ/9u2/9vb//+2///boV9NygAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAABB0lEQVQ4T+1SXROCIBAEU/vSSlOLJEr+/4/suBMDxN56kxlnHFh295ZlbF3/TeDdT/wyaeJaamsP1P4LD7DD+Y47+rrAYg5lhhiZAo3KjzWv5opEoGtz1D2SZrzjA4UhGn2Jioki4lztwBFKGVPDyfHVck43cBfJ2FBy145Ie5WjPbTSIkZdXB1dAwkdsLYAHHIKL8ahhMvAhZjMYny3OMcMw3FZZIAxerMVaFlrHs7xjL80+7QoJTM7vTnOThkGGF3buPCOFy+MGjys3Jh20K6w4QcDkBXqxiEUJexYLtsxGamP7RijrgJTJKixq6QGXzertVvj103lnPshGNLnj6ZHVNetxQQ+x7gR+7gao/AAAAAASUVORK5CYII=\">,∴直线AB的方程为y=-<img width=\"45\" height=\"42\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAC0AAAAqCAMAAAAQwSIQAAAAAXNSR0IArs4c6QAAAHJQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjo6OjqQOma2OpC2OpDbZgAAZgA6ZgBmZma2ZpDbZrb/kDoAkDo6kNv/tmYAtpCQttv/tv//25A627Zm29uQ2////7Zm/7aQ/9uQ/9u2/9vb//+2///bKvAmEQAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAABDklEQVRIS+1TyxKCMAxsFZ/4BkWtUqvw/79ok5SCI029eHDGHHhu0t1NIsQ/ft6BR9lK0INDRI+ZeISZdzIDadXmTH/qfawyoPSI0DphSpvxIpdZW7PO4SUUx8vgQGUVXjsC+lJUJtQScVNgzhIBUdUaddGNTghFtZJI25qBjAsWbXa+TmEJ1TmyElqOhHrvUucTlHXoaquTa1unj1gHbVWsnO9AFQMOpSdMRspAp1XBCCZ009KZSwskEGXnoEkjxMlB6k6RlJ54f3HqjmsV1yKyeIgzW3BTJRR54xnzE5s6qs0WxLZB49nNNgh+00wKYL9p/NBansfydRvvp5AlZiyltfj2yepGbf0DvurAExU+EcrH92ZjAAAAAElFTkSuQmCC\">,与抛物线方程联立得<img width=\"95\" height=\"62\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAF8AAAA+CAMAAAB6Ojc4AAAAAXNSR0IArs4c6QAAAJBQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjo6OjqQOma2OpC2OpDbZgAAZgA6ZgBmZjqQZmZmZma2ZpCQZpDbZrb/kDoAkDo6kDpmkGaQkJC2kNvbkNv/tmYAtpA6tpCQttv/tv/btv//25A625Bm27Zm29uQ2////7Zm/7aQ/9uQ/9u2/9vb//+2///b84/bIwAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAACkElEQVRYR+1Xa1fbMAyNoS2wroHBRsYAw0bG6nlz/v+/myQriZ2HH135wDnxpx7HlqV79bgtimUtCCwIvGcEzOXjMd3/u/etmfI2x7w6iXijz/wDcp1jXn8YuDe+bK5enM08dJqvCVgq12N16r4WC0WtAu7rzcdKANieE3XoxvC5pgpx9fx68ki+104A7u+Y98WQu8GF+raod7Cnz3tMsugNwoO4WDZdTrPsh4M1pUD4gQAHxiz74cP6S4uXRJTskshvU0HVKNHvTjPRVPaEEuuiHtdZv+X4UWN+/nyAm5xWGCWt8Wts39yo1a/O1ylXHPtc74CsuZk46r3W+l+YkrPa+2zdIlD6BNUbogQyQ8X7UItruA48+9ze9PmPe+t+AJ/OL3URpKqLk3KJjprL64TOwvmpt2ECODwqA7bvPjlfxra+IOU6AibPcn259s2naN8dFOa8FwX3TImE2liau7QuSldmV81J7ZY5BkHllbTC/XmLaTjsz1njKzZfKMfd+ZLVnuFueD7qLZLkzcfcB0IIwbfn/SDEvPkL7v35PseU3gix2v/2qUxL/CTyJw+9d/umjE2Vw7HBm9yf/89IqFf4pduW+NHe8/UbFMh0gjeVgKF7wBrpt8kxhl3wMCRJPziLSvyImUyjuNcnVOJz9kH+iZ0UwJgUpy8yqmfQDtnv9IktcWxSY5VCxEhQaBgyTHqVlNgsJeb0SR+KFa2oIDAlTPl5FkgvfOY3qk+4D6J9UtERKd29wenY6xP7ZYQPT8bW/+bbU9ofh27Wh/UJjgkUeIT9mrRkojK2mizSRm04cFJeYAJAte0gFZIKzgq4NH1i+c1cpCAS9ckh9rm8krxS/A8l6fByaEFgQeBNEfgHOFo6N4R+h7wAAAAASUVORK5CYII=\">消去y,得4x<sup>2<\/sup>-12px+p<sup>2<\/sup>=0,设A(x<sub>1<\/sub>,y<sub>1<\/sub>),B(x<sub>2<\/sub>,y<sub>2<\/sub>),则x<sub>1<\/sub>+x<sub>2<\/sub>=3p.∵线段AB的中点的横坐标为3,∴<img width=\"12\" height=\"42\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAwAAAAqCAMAAACwXkr+AAAAAXNSR0IArs4c6QAAAEtQTFRFAAAAAAAAAAA6ADqQAGa2OgAAOjqQOma2OpC2OpDbZgAAZgA6ZpDbZrb/kDoAkDo6kNv/tpCQ25A627Zm2////7Zm/9uQ//+2///bDMHV2QAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAAAcUlEQVQoU82QzQ6AIAyDO/EPFUWE6fs/qaARl3gzHuyx65p9A/4lrojMdRI3CCW4asfTcwbzUtjoIRyxaDgNHsC13ya79hbbmCZrJ2sGwegK+yAmoW/e4Yi0pFD+7g2ZD4iEWUH5+YqlN8idb6561bIDtxQECOnh514AAAAASUVORK5CYII=\">=3,∴p=2,∴抛物线的准线方程为x=-1.<\/p>","difficulty":0,"score":0,"content":"<p class=\"MsoNormal\">已知抛物线y<sup>2<\/sup>=2px(p&gt;0),过其s = \"MsoNormal\">A.x=1&nbsp;&nbsp; B.x=2&nbsp;&nbsp; C.x=-1&nbsp; D.x=-2<\/p>","level":0,"attachments":[{"name":"a1cffc2c023e44dba0594e7cb276b353.files/image001.png","base64":"iVBORw0KGgoAAAANSUhEUgAAACMAAAAqCAMAAAAOCBKjAAAAAXNSR0IArs4c6QAAAGlQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjqQOma2OpC2OpDbZgAAZgA6ZgBmZpDbZrb/kDoAkDo6kNv/tmYAtpCQttv/tv//25A627Zm2////7Zm/7aQ/9uQ/9u2/9vb//+2///boV9NygAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAABB0lEQVQ4T+1SXROCIBAEU/vSSlOLJEr+/4/suBMDxN56kxlnHFh295ZlbF3/TeDdT/wyaeJaamsP1P4LD7DD+Y47+rrAYg5lhhiZAo3KjzWv5opEoGtz1D2SZrzjA4UhGn2Jioki4lztwBFKGVPDyfHVck43cBfJ2FBy145Ie5WjPbTSIkZdXB1dAwkdsLYAHHIKL8ahhMvAhZjMYny3OMcMw3FZZIAxerMVaFlrHs7xjL80+7QoJTM7vTnOThkGGF3buPCOFy+MGjys3Jh20K6w4QcDkBXqxiEUJexYLtsxGamP7RijrgJTJKixq6QGXzertVvj103lnPshGNLnj6ZHVNetxQQ+x7gR+7gao/AAAAAASUVORK5CYII=","contentType":"image/png","encoding":"base64"},{"name":"a1cffc2c023e44dba0594e7cb276b353.files/image002.png","base64":"iVBORw0KGgoAAAANSUhEUgAAAC0AAAAqCAMAAAAQwSIQAAAAAXNSR0IArs4c6QAAAHJQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjo6OjqQOma2OpC2OpDbZgAAZgA6ZgBmZma2ZpDbZrb/kDoAkDo6kNv/tmYAtpCQttv/tv//25A627Zm29uQ2////7Zm/7aQ/9uQ/9u2/9vb//+2///bKvAmEQAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAABDklEQVRIS+1TyxKCMAxsFZ/4BkWtUqvw/79ok5SCI029eHDGHHhu0t1NIsQ/ft6BR9lK0INDRI+ZeISZdzIDadXmTH/qfawyoPSI0DphSpvxIpdZW7PO4SUUx8vgQGUVXjsC+lJUJtQScVNgzhIBUdUaddGNTghFtZJI25qBjAsWbXa+TmEJ1TmyElqOhHrvUucTlHXoaquTa1unj1gHbVWsnO9AFQMOpSdMRspAp1XBCCZ009KZSwskEGXnoEkjxMlB6k6RlJ54f3HqjmsV1yKyeIgzW3BTJRR54xnzE5s6qs0WxLZB49nNNgh+00wKYL9p/NBansfydRvvp5AlZiyltfj2yepGbf0DvurAExU+EcrH92ZjAAAAAElFTkSuQmCC","contentType":"image/png","encoding":"base64"},{"name":"a1cffc2c023e44dba0594e7cb276b353.files/image003.png","base64":"iVBORw0KGgoAAAANSUhEUgAAAF8AAAA+CAMAAAB6Ojc4AAAAAXNSR0IArs4c6QAAAJBQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjo6OjqQOma2OpC2OpDbZgAAZgA6ZgBmZjqQZmZmZma2ZpCQZpDbZrb/kDoAkDo6kDpmkGaQkJC2kNvbkNv/tmYAtpA6tpCQttv/tv/btv//25A625Bm27Zm29uQ2////7Zm/7aQ/9uQ/9u2/9vb//+2///b84/bIwAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAACkElEQVRYR+1Xa1fbMAyNoS2wroHBRsYAw0bG6nlz/v+/myQriZ2HH135wDnxpx7HlqV79bgtimUtCCwIvGcEzOXjMd3/u/etmfI2x7w6iXijz/wDcp1jXn8YuDe+bK5enM08dJqvCVgq12N16r4WC0WtAu7rzcdKANieE3XoxvC5pgpx9fx68ki+104A7u+Y98WQu8GF+raod7Cnz3tMsugNwoO4WDZdTrPsh4M1pUD4gQAHxiz74cP6S4uXRJTskshvU0HVKNHvTjPRVPaEEuuiHtdZv+X4UWN+/nyAm5xWGCWt8Wts39yo1a/O1ylXHPtc74CsuZk46r3W+l+YkrPa+2zdIlD6BNUbogQyQ8X7UItruA48+9ze9PmPe+t+AJ/OL3URpKqLk3KJjprL64TOwvmpt2ECODwqA7bvPjlfxra+IOU6AibPcn259s2naN8dFOa8FwX3TImE2liau7QuSldmV81J7ZY5BkHllbTC/XmLaTjsz1njKzZfKMfd+ZLVnuFueD7qLZLkzcfcB0IIwbfn/SDEvPkL7v35PseU3gix2v/2qUxL/CTyJw+9d/umjE2Vw7HBm9yf/89IqFf4pduW+NHe8/UbFMh0gjeVgKF7wBrpt8kxhl3wMCRJPziLSvyImUyjuNcnVOJz9kH+iZ0UwJgUpy8yqmfQDtnv9IktcWxSY5VCxEhQaBgyTHqVlNgsJeb0SR+KFa2oIDAlTPl5FkgvfOY3qk+4D6J9UtERKd29wenY6xP7ZYQPT8bW/+bbU9ofh27Wh/UJjgkUeIT9mrRkojK2mizSRm04cFJeYAJAte0gFZIKzgq4NH1i+c1cpCAS9ckh9rm8krxS/A8l6fByaEFgQeBNEfgHOFo6N4R+h7wAAAAASUVORK5CYII=","contentType":"image/png","encoding":"base64"},{"name":"a1cffc2c023e44dba0594e7cb276b353.files/image004.png","base64":"iVBORw0KGgoAAAANSUhEUgAAAAwAAAAqCAMAAACwXkr+AAAAAXNSR0IArs4c6QAAAEtQTFRFAAAAAAAAAAA6ADqQAGa2OgAAOjqQOma2OpC2OpDbZgAAZgA6ZpDbZrb/kDoAkDo6kNv/tpCQ25A627Zm2////7Zm/9uQ//+2///bDMHV2QAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAAAcUlEQVQoU82QzQ6AIAyDO/EPFUWE6fs/qaARl3gzHuyx65p9A/4lrojMdRI3CCW4asfTcwbzUtjoIRyxaDgNHsC13ya79hbbmCZrJ2sGwegK+yAmoW/e4Yi0pFD+7g2ZD4iEWUH5+YqlN8idb6561bIDtxQECOnh514AAAAASUVORK5CYII=","contentType":"image/png","encoding":"base64"}],"type":0,"subjectId":null,"options":4},"operateTime":"2019-01-15T15:29:01.062304","questionGuid":"0a6e1e12ed56499696f799cb829993a5","remark":"C","answer":"C"}]}]
     * totalNumber : 1
     * currentPage : 1
     */

    private int totalNumber;
    private int currentPage;
    private List<QuestionCollectBean> questionCollect;

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<QuestionCollectBean> getQuestionCollect() {
        return questionCollect;
    }

    public void setQuestionCollect(List<QuestionCollectBean> questionCollect) {
        this.questionCollect = questionCollect;
    }

    public static class QuestionCollectBean {
        /**
         * title : 2019-01-15T00:00:00
         * questionCollectGroup : [{"questionData":{"guid":"0a6e1e12ed56499696f799cb829993a5","questionType":"单选题","grade":0,"answer":["C"],"analysis":"<p class=\"MsoNormal\">由题可知焦点为<img width=\"35\" height=\"42\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACMAAAAqCAMAAAAOCBKjAAAAAXNSR0IArs4c6QAAAGlQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjqQOma2OpC2OpDbZgAAZgA6ZgBmZpDbZrb/kDoAkDo6kNv/tmYAtpCQttv/tv//25A627Zm2////7Zm/7aQ/9uQ/9u2/9vb//+2///boV9NygAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAABB0lEQVQ4T+1SXROCIBAEU/vSSlOLJEr+/4/suBMDxN56kxlnHFh295ZlbF3/TeDdT/wyaeJaamsP1P4LD7DD+Y47+rrAYg5lhhiZAo3KjzWv5opEoGtz1D2SZrzjA4UhGn2Jioki4lztwBFKGVPDyfHVck43cBfJ2FBy145Ie5WjPbTSIkZdXB1dAwkdsLYAHHIKL8ahhMvAhZjMYny3OMcMw3FZZIAxerMVaFlrHs7xjL80+7QoJTM7vTnOThkGGF3buPCOFy+MGjys3Jh20K6w4QcDkBXqxiEUJexYLtsxGamP7RijrgJTJKixq6QGXzertVvj103lnPshGNLnj6ZHVNetxQQ+x7gR+7gao/AAAAAASUVORK5CYII=\">,∴直线AB的方程为y=-<img width=\"45\" height=\"42\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAC0AAAAqCAMAAAAQwSIQAAAAAXNSR0IArs4c6QAAAHJQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjo6OjqQOma2OpC2OpDbZgAAZgA6ZgBmZma2ZpDbZrb/kDoAkDo6kNv/tmYAtpCQttv/tv//25A627Zm29uQ2////7Zm/7aQ/9uQ/9u2/9vb//+2///bKvAmEQAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAABDklEQVRIS+1TyxKCMAxsFZ/4BkWtUqvw/79ok5SCI029eHDGHHhu0t1NIsQ/ft6BR9lK0INDRI+ZeISZdzIDadXmTH/qfawyoPSI0DphSpvxIpdZW7PO4SUUx8vgQGUVXjsC+lJUJtQScVNgzhIBUdUaddGNTghFtZJI25qBjAsWbXa+TmEJ1TmyElqOhHrvUucTlHXoaquTa1unj1gHbVWsnO9AFQMOpSdMRspAp1XBCCZ009KZSwskEGXnoEkjxMlB6k6RlJ54f3HqjmsV1yKyeIgzW3BTJRR54xnzE5s6qs0WxLZB49nNNgh+00wKYL9p/NBansfydRvvp5AlZiyltfj2yepGbf0DvurAExU+EcrH92ZjAAAAAElFTkSuQmCC\">,与抛物线方程联立得<img width=\"95\" height=\"62\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAF8AAAA+CAMAAAB6Ojc4AAAAAXNSR0IArs4c6QAAAJBQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjo6OjqQOma2OpC2OpDbZgAAZgA6ZgBmZjqQZmZmZma2ZpCQZpDbZrb/kDoAkDo6kDpmkGaQkJC2kNvbkNv/tmYAtpA6tpCQttv/tv/btv//25A625Bm27Zm29uQ2////7Zm/7aQ/9uQ/9u2/9vb//+2///b84/bIwAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAACkElEQVRYR+1Xa1fbMAyNoS2wroHBRsYAw0bG6nlz/v+/myQriZ2HH135wDnxpx7HlqV79bgtimUtCCwIvGcEzOXjMd3/u/etmfI2x7w6iXijz/wDcp1jXn8YuDe+bK5enM08dJqvCVgq12N16r4WC0WtAu7rzcdKANieE3XoxvC5pgpx9fx68ki+104A7u+Y98WQu8GF+raod7Cnz3tMsugNwoO4WDZdTrPsh4M1pUD4gQAHxiz74cP6S4uXRJTskshvU0HVKNHvTjPRVPaEEuuiHtdZv+X4UWN+/nyAm5xWGCWt8Wts39yo1a/O1ylXHPtc74CsuZk46r3W+l+YkrPa+2zdIlD6BNUbogQyQ8X7UItruA48+9ze9PmPe+t+AJ/OL3URpKqLk3KJjprL64TOwvmpt2ECODwqA7bvPjlfxra+IOU6AibPcn259s2naN8dFOa8FwX3TImE2liau7QuSldmV81J7ZY5BkHllbTC/XmLaTjsz1njKzZfKMfd+ZLVnuFueD7qLZLkzcfcB0IIwbfn/SDEvPkL7v35PseU3gix2v/2qUxL/CTyJw+9d/umjE2Vw7HBm9yf/89IqFf4pduW+NHe8/UbFMh0gjeVgKF7wBrpt8kxhl3wMCRJPziLSvyImUyjuNcnVOJz9kH+iZ0UwJgUpy8yqmfQDtnv9IktcWxSY5VCxEhQaBgyTHqVlNgsJeb0SR+KFa2oIDAlTPl5FkgvfOY3qk+4D6J9UtERKd29wenY6xP7ZYQPT8bW/+bbU9ofh27Wh/UJjgkUeIT9mrRkojK2mizSRm04cFJeYAJAte0gFZIKzgq4NH1i+c1cpCAS9ckh9rm8krxS/A8l6fByaEFgQeBNEfgHOFo6N4R+h7wAAAAASUVORK5CYII=\">消去y,得4x<sup>2<\/sup>-12px+p<sup>2<\/sup>=0,设A(x<sub>1<\/sub>,y<sub>1<\/sub>),B(x<sub>2<\/sub>,y<sub>2<\/sub>),则x<sub>1<\/sub>+x<sub>2<\/sub>=3p.∵线段AB的中点的横坐标为3,∴<img width=\"12\" height=\"42\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAwAAAAqCAMAAACwXkr+AAAAAXNSR0IArs4c6QAAAEtQTFRFAAAAAAAAAAA6ADqQAGa2OgAAOjqQOma2OpC2OpDbZgAAZgA6ZpDbZrb/kDoAkDo6kNv/tpCQ25A627Zm2////7Zm/9uQ//+2///bDMHV2QAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAAAcUlEQVQoU82QzQ6AIAyDO/EPFUWE6fs/qaARl3gzHuyx65p9A/4lrojMdRI3CCW4asfTcwbzUtjoIRyxaDgNHsC13ya79hbbmCZrJ2sGwegK+yAmoW/e4Yi0pFD+7g2ZD4iEWUH5+YqlN8idb6561bIDtxQECOnh514AAAAASUVORK5CYII=\">=3,∴p=2,∴抛物线的准线方程为x=-1.<\/p>","difficulty":0,"score":0,"content":"<p class=\"MsoNormal\">已知抛物线y<sup>2<\/sup>=2px(p&gt;0),过其s = \"MsoNormal\">A.x=1&nbsp;&nbsp; B.x=2&nbsp;&nbsp; C.x=-1&nbsp; D.x=-2<\/p>","level":0,"attachments":[{"name":"a1cffc2c023e44dba0594e7cb276b353.files/image001.png","base64":"iVBORw0KGgoAAAANSUhEUgAAACMAAAAqCAMAAAAOCBKjAAAAAXNSR0IArs4c6QAAAGlQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjqQOma2OpC2OpDbZgAAZgA6ZgBmZpDbZrb/kDoAkDo6kNv/tmYAtpCQttv/tv//25A627Zm2////7Zm/7aQ/9uQ/9u2/9vb//+2///boV9NygAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAABB0lEQVQ4T+1SXROCIBAEU/vSSlOLJEr+/4/suBMDxN56kxlnHFh295ZlbF3/TeDdT/wyaeJaamsP1P4LD7DD+Y47+rrAYg5lhhiZAo3KjzWv5opEoGtz1D2SZrzjA4UhGn2Jioki4lztwBFKGVPDyfHVck43cBfJ2FBy145Ie5WjPbTSIkZdXB1dAwkdsLYAHHIKL8ahhMvAhZjMYny3OMcMw3FZZIAxerMVaFlrHs7xjL80+7QoJTM7vTnOThkGGF3buPCOFy+MGjys3Jh20K6w4QcDkBXqxiEUJexYLtsxGamP7RijrgJTJKixq6QGXzertVvj103lnPshGNLnj6ZHVNetxQQ+x7gR+7gao/AAAAAASUVORK5CYII=","contentType":"image/png","encoding":"base64"},{"name":"a1cffc2c023e44dba0594e7cb276b353.files/image002.png","base64":"iVBORw0KGgoAAAANSUhEUgAAAC0AAAAqCAMAAAAQwSIQAAAAAXNSR0IArs4c6QAAAHJQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjo6OjqQOma2OpC2OpDbZgAAZgA6ZgBmZma2ZpDbZrb/kDoAkDo6kNv/tmYAtpCQttv/tv//25A627Zm29uQ2////7Zm/7aQ/9uQ/9u2/9vb//+2///bKvAmEQAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAABDklEQVRIS+1TyxKCMAxsFZ/4BkWtUqvw/79ok5SCI029eHDGHHhu0t1NIsQ/ft6BR9lK0INDRI+ZeISZdzIDadXmTH/qfawyoPSI0DphSpvxIpdZW7PO4SUUx8vgQGUVXjsC+lJUJtQScVNgzhIBUdUaddGNTghFtZJI25qBjAsWbXa+TmEJ1TmyElqOhHrvUucTlHXoaquTa1unj1gHbVWsnO9AFQMOpSdMRspAp1XBCCZ009KZSwskEGXnoEkjxMlB6k6RlJ54f3HqjmsV1yKyeIgzW3BTJRR54xnzE5s6qs0WxLZB49nNNgh+00wKYL9p/NBansfydRvvp5AlZiyltfj2yepGbf0DvurAExU+EcrH92ZjAAAAAElFTkSuQmCC","contentType":"image/png","encoding":"base64"},{"name":"a1cffc2c023e44dba0594e7cb276b353.files/image003.png","base64":"iVBORw0KGgoAAAANSUhEUgAAAF8AAAA+CAMAAAB6Ojc4AAAAAXNSR0IArs4c6QAAAJBQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjo6OjqQOma2OpC2OpDbZgAAZgA6ZgBmZjqQZmZmZma2ZpCQZpDbZrb/kDoAkDo6kDpmkGaQkJC2kNvbkNv/tmYAtpA6tpCQttv/tv/btv//25A625Bm27Zm29uQ2////7Zm/7aQ/9uQ/9u2/9vb//+2///b84/bIwAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAACkElEQVRYR+1Xa1fbMAyNoS2wroHBRsYAw0bG6nlz/v+/myQriZ2HH135wDnxpx7HlqV79bgtimUtCCwIvGcEzOXjMd3/u/etmfI2x7w6iXijz/wDcp1jXn8YuDe+bK5enM08dJqvCVgq12N16r4WC0WtAu7rzcdKANieE3XoxvC5pgpx9fx68ki+104A7u+Y98WQu8GF+raod7Cnz3tMsugNwoO4WDZdTrPsh4M1pUD4gQAHxiz74cP6S4uXRJTskshvU0HVKNHvTjPRVPaEEuuiHtdZv+X4UWN+/nyAm5xWGCWt8Wts39yo1a/O1ylXHPtc74CsuZk46r3W+l+YkrPa+2zdIlD6BNUbogQyQ8X7UItruA48+9ze9PmPe+t+AJ/OL3URpKqLk3KJjprL64TOwvmpt2ECODwqA7bvPjlfxra+IOU6AibPcn259s2naN8dFOa8FwX3TImE2liau7QuSldmV81J7ZY5BkHllbTC/XmLaTjsz1njKzZfKMfd+ZLVnuFueD7qLZLkzcfcB0IIwbfn/SDEvPkL7v35PseU3gix2v/2qUxL/CTyJw+9d/umjE2Vw7HBm9yf/89IqFf4pduW+NHe8/UbFMh0gjeVgKF7wBrpt8kxhl3wMCRJPziLSvyImUyjuNcnVOJz9kH+iZ0UwJgUpy8yqmfQDtnv9IktcWxSY5VCxEhQaBgyTHqVlNgsJeb0SR+KFa2oIDAlTPl5FkgvfOY3qk+4D6J9UtERKd29wenY6xP7ZYQPT8bW/+bbU9ofh27Wh/UJjgkUeIT9mrRkojK2mizSRm04cFJeYAJAte0gFZIKzgq4NH1i+c1cpCAS9ckh9rm8krxS/A8l6fByaEFgQeBNEfgHOFo6N4R+h7wAAAAASUVORK5CYII=","contentType":"image/png","encoding":"base64"},{"name":"a1cffc2c023e44dba0594e7cb276b353.files/image004.png","base64":"iVBORw0KGgoAAAANSUhEUgAAAAwAAAAqCAMAAACwXkr+AAAAAXNSR0IArs4c6QAAAEtQTFRFAAAAAAAAAAA6ADqQAGa2OgAAOjqQOma2OpC2OpDbZgAAZgA6ZpDbZrb/kDoAkDo6kNv/tpCQ25A627Zm2////7Zm/9uQ//+2///bDMHV2QAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAAAcUlEQVQoU82QzQ6AIAyDO/EPFUWE6fs/qaARl3gzHuyx65p9A/4lrojMdRI3CCW4asfTcwbzUtjoIRyxaDgNHsC13ya79hbbmCZrJ2sGwegK+yAmoW/e4Yi0pFD+7g2ZD4iEWUH5+YqlN8idb6561bIDtxQECOnh514AAAAASUVORK5CYII=","contentType":"image/png","encoding":"base64"}],"type":0,"subjectId":null,"options":4},"operateTime":"2019-01-15T15:29:01.062304","questionGuid":"0a6e1e12ed56499696f799cb829993a5","remark":"C","answer":"C"}]
         */

        private String title;
        private List<QuestionCollectGroupBean> questionCollectGroup;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<QuestionCollectGroupBean> getQuestionCollectGroup() {
            return questionCollectGroup;
        }

        public void setQuestionCollectGroup(List<QuestionCollectGroupBean> questionCollectGroup) {
            this.questionCollectGroup = questionCollectGroup;
        }

        public static class QuestionCollectGroupBean {
            /**
             * questionData : {"guid":"0a6e1e12ed56499696f799cb829993a5","questionType":"单选题","grade":0,"answer":["C"],"analysis":"<p class=\"MsoNormal\">由题可知焦点为<img width=\"35\" height=\"42\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACMAAAAqCAMAAAAOCBKjAAAAAXNSR0IArs4c6QAAAGlQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjqQOma2OpC2OpDbZgAAZgA6ZgBmZpDbZrb/kDoAkDo6kNv/tmYAtpCQttv/tv//25A627Zm2////7Zm/7aQ/9uQ/9u2/9vb//+2///boV9NygAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAABB0lEQVQ4T+1SXROCIBAEU/vSSlOLJEr+/4/suBMDxN56kxlnHFh295ZlbF3/TeDdT/wyaeJaamsP1P4LD7DD+Y47+rrAYg5lhhiZAo3KjzWv5opEoGtz1D2SZrzjA4UhGn2Jioki4lztwBFKGVPDyfHVck43cBfJ2FBy145Ie5WjPbTSIkZdXB1dAwkdsLYAHHIKL8ahhMvAhZjMYny3OMcMw3FZZIAxerMVaFlrHs7xjL80+7QoJTM7vTnOThkGGF3buPCOFy+MGjys3Jh20K6w4QcDkBXqxiEUJexYLtsxGamP7RijrgJTJKixq6QGXzertVvj103lnPshGNLnj6ZHVNetxQQ+x7gR+7gao/AAAAAASUVORK5CYII=\">,∴直线AB的方程为y=-<img width=\"45\" height=\"42\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAC0AAAAqCAMAAAAQwSIQAAAAAXNSR0IArs4c6QAAAHJQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjo6OjqQOma2OpC2OpDbZgAAZgA6ZgBmZma2ZpDbZrb/kDoAkDo6kNv/tmYAtpCQttv/tv//25A627Zm29uQ2////7Zm/7aQ/9uQ/9u2/9vb//+2///bKvAmEQAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAABDklEQVRIS+1TyxKCMAxsFZ/4BkWtUqvw/79ok5SCI029eHDGHHhu0t1NIsQ/ft6BR9lK0INDRI+ZeISZdzIDadXmTH/qfawyoPSI0DphSpvxIpdZW7PO4SUUx8vgQGUVXjsC+lJUJtQScVNgzhIBUdUaddGNTghFtZJI25qBjAsWbXa+TmEJ1TmyElqOhHrvUucTlHXoaquTa1unj1gHbVWsnO9AFQMOpSdMRspAp1XBCCZ009KZSwskEGXnoEkjxMlB6k6RlJ54f3HqjmsV1yKyeIgzW3BTJRR54xnzE5s6qs0WxLZB49nNNgh+00wKYL9p/NBansfydRvvp5AlZiyltfj2yepGbf0DvurAExU+EcrH92ZjAAAAAElFTkSuQmCC\">,与抛物线方程联立得<img width=\"95\" height=\"62\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAF8AAAA+CAMAAAB6Ojc4AAAAAXNSR0IArs4c6QAAAJBQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjo6OjqQOma2OpC2OpDbZgAAZgA6ZgBmZjqQZmZmZma2ZpCQZpDbZrb/kDoAkDo6kDpmkGaQkJC2kNvbkNv/tmYAtpA6tpCQttv/tv/btv//25A625Bm27Zm29uQ2////7Zm/7aQ/9uQ/9u2/9vb//+2///b84/bIwAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAACkElEQVRYR+1Xa1fbMAyNoS2wroHBRsYAw0bG6nlz/v+/myQriZ2HH135wDnxpx7HlqV79bgtimUtCCwIvGcEzOXjMd3/u/etmfI2x7w6iXijz/wDcp1jXn8YuDe+bK5enM08dJqvCVgq12N16r4WC0WtAu7rzcdKANieE3XoxvC5pgpx9fx68ki+104A7u+Y98WQu8GF+raod7Cnz3tMsugNwoO4WDZdTrPsh4M1pUD4gQAHxiz74cP6S4uXRJTskshvU0HVKNHvTjPRVPaEEuuiHtdZv+X4UWN+/nyAm5xWGCWt8Wts39yo1a/O1ylXHPtc74CsuZk46r3W+l+YkrPa+2zdIlD6BNUbogQyQ8X7UItruA48+9ze9PmPe+t+AJ/OL3URpKqLk3KJjprL64TOwvmpt2ECODwqA7bvPjlfxra+IOU6AibPcn259s2naN8dFOa8FwX3TImE2liau7QuSldmV81J7ZY5BkHllbTC/XmLaTjsz1njKzZfKMfd+ZLVnuFueD7qLZLkzcfcB0IIwbfn/SDEvPkL7v35PseU3gix2v/2qUxL/CTyJw+9d/umjE2Vw7HBm9yf/89IqFf4pduW+NHe8/UbFMh0gjeVgKF7wBrpt8kxhl3wMCRJPziLSvyImUyjuNcnVOJz9kH+iZ0UwJgUpy8yqmfQDtnv9IktcWxSY5VCxEhQaBgyTHqVlNgsJeb0SR+KFa2oIDAlTPl5FkgvfOY3qk+4D6J9UtERKd29wenY6xP7ZYQPT8bW/+bbU9ofh27Wh/UJjgkUeIT9mrRkojK2mizSRm04cFJeYAJAte0gFZIKzgq4NH1i+c1cpCAS9ckh9rm8krxS/A8l6fByaEFgQeBNEfgHOFo6N4R+h7wAAAAASUVORK5CYII=\">消去y,得4x<sup>2<\/sup>-12px+p<sup>2<\/sup>=0,设A(x<sub>1<\/sub>,y<sub>1<\/sub>),B(x<sub>2<\/sub>,y<sub>2<\/sub>),则x<sub>1<\/sub>+x<sub>2<\/sub>=3p.∵线段AB的中点的横坐标为3,∴<img width=\"12\" height=\"42\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAwAAAAqCAMAAACwXkr+AAAAAXNSR0IArs4c6QAAAEtQTFRFAAAAAAAAAAA6ADqQAGa2OgAAOjqQOma2OpC2OpDbZgAAZgA6ZpDbZrb/kDoAkDo6kNv/tpCQ25A627Zm2////7Zm/9uQ//+2///bDMHV2QAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAAAcUlEQVQoU82QzQ6AIAyDO/EPFUWE6fs/qaARl3gzHuyx65p9A/4lrojMdRI3CCW4asfTcwbzUtjoIRyxaDgNHsC13ya79hbbmCZrJ2sGwegK+yAmoW/e4Yi0pFD+7g2ZD4iEWUH5+YqlN8idb6561bIDtxQECOnh514AAAAASUVORK5CYII=\">=3,∴p=2,∴抛物线的准线方程为x=-1.<\/p>","difficulty":0,"score":0,"content":"<p class=\"MsoNormal\">已知抛物线y<sup>2<\/sup>=2px(p&gt;0),过其s = \"MsoNormal\">A.x=1&nbsp;&nbsp; B.x=2&nbsp;&nbsp; C.x=-1&nbsp; D.x=-2<\/p>","level":0,"attachments":[{"name":"a1cffc2c023e44dba0594e7cb276b353.files/image001.png","base64":"iVBORw0KGgoAAAANSUhEUgAAACMAAAAqCAMAAAAOCBKjAAAAAXNSR0IArs4c6QAAAGlQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjqQOma2OpC2OpDbZgAAZgA6ZgBmZpDbZrb/kDoAkDo6kNv/tmYAtpCQttv/tv//25A627Zm2////7Zm/7aQ/9uQ/9u2/9vb//+2///boV9NygAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAABB0lEQVQ4T+1SXROCIBAEU/vSSlOLJEr+/4/suBMDxN56kxlnHFh295ZlbF3/TeDdT/wyaeJaamsP1P4LD7DD+Y47+rrAYg5lhhiZAo3KjzWv5opEoGtz1D2SZrzjA4UhGn2Jioki4lztwBFKGVPDyfHVck43cBfJ2FBy145Ie5WjPbTSIkZdXB1dAwkdsLYAHHIKL8ahhMvAhZjMYny3OMcMw3FZZIAxerMVaFlrHs7xjL80+7QoJTM7vTnOThkGGF3buPCOFy+MGjys3Jh20K6w4QcDkBXqxiEUJexYLtsxGamP7RijrgJTJKixq6QGXzertVvj103lnPshGNLnj6ZHVNetxQQ+x7gR+7gao/AAAAAASUVORK5CYII=","contentType":"image/png","encoding":"base64"},{"name":"a1cffc2c023e44dba0594e7cb276b353.files/image002.png","base64":"iVBORw0KGgoAAAANSUhEUgAAAC0AAAAqCAMAAAAQwSIQAAAAAXNSR0IArs4c6QAAAHJQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjo6OjqQOma2OpC2OpDbZgAAZgA6ZgBmZma2ZpDbZrb/kDoAkDo6kNv/tmYAtpCQttv/tv//25A627Zm29uQ2////7Zm/7aQ/9uQ/9u2/9vb//+2///bKvAmEQAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAABDklEQVRIS+1TyxKCMAxsFZ/4BkWtUqvw/79ok5SCI029eHDGHHhu0t1NIsQ/ft6BR9lK0INDRI+ZeISZdzIDadXmTH/qfawyoPSI0DphSpvxIpdZW7PO4SUUx8vgQGUVXjsC+lJUJtQScVNgzhIBUdUaddGNTghFtZJI25qBjAsWbXa+TmEJ1TmyElqOhHrvUucTlHXoaquTa1unj1gHbVWsnO9AFQMOpSdMRspAp1XBCCZ009KZSwskEGXnoEkjxMlB6k6RlJ54f3HqjmsV1yKyeIgzW3BTJRR54xnzE5s6qs0WxLZB49nNNgh+00wKYL9p/NBansfydRvvp5AlZiyltfj2yepGbf0DvurAExU+EcrH92ZjAAAAAElFTkSuQmCC","contentType":"image/png","encoding":"base64"},{"name":"a1cffc2c023e44dba0594e7cb276b353.files/image003.png","base64":"iVBORw0KGgoAAAANSUhEUgAAAF8AAAA+CAMAAAB6Ojc4AAAAAXNSR0IArs4c6QAAAJBQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjo6OjqQOma2OpC2OpDbZgAAZgA6ZgBmZjqQZmZmZma2ZpCQZpDbZrb/kDoAkDo6kDpmkGaQkJC2kNvbkNv/tmYAtpA6tpCQttv/tv/btv//25A625Bm27Zm29uQ2////7Zm/7aQ/9uQ/9u2/9vb//+2///b84/bIwAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAACkElEQVRYR+1Xa1fbMAyNoS2wroHBRsYAw0bG6nlz/v+/myQriZ2HH135wDnxpx7HlqV79bgtimUtCCwIvGcEzOXjMd3/u/etmfI2x7w6iXijz/wDcp1jXn8YuDe+bK5enM08dJqvCVgq12N16r4WC0WtAu7rzcdKANieE3XoxvC5pgpx9fx68ki+104A7u+Y98WQu8GF+raod7Cnz3tMsugNwoO4WDZdTrPsh4M1pUD4gQAHxiz74cP6S4uXRJTskshvU0HVKNHvTjPRVPaEEuuiHtdZv+X4UWN+/nyAm5xWGCWt8Wts39yo1a/O1ylXHPtc74CsuZk46r3W+l+YkrPa+2zdIlD6BNUbogQyQ8X7UItruA48+9ze9PmPe+t+AJ/OL3URpKqLk3KJjprL64TOwvmpt2ECODwqA7bvPjlfxra+IOU6AibPcn259s2naN8dFOa8FwX3TImE2liau7QuSldmV81J7ZY5BkHllbTC/XmLaTjsz1njKzZfKMfd+ZLVnuFueD7qLZLkzcfcB0IIwbfn/SDEvPkL7v35PseU3gix2v/2qUxL/CTyJw+9d/umjE2Vw7HBm9yf/89IqFf4pduW+NHe8/UbFMh0gjeVgKF7wBrpt8kxhl3wMCRJPziLSvyImUyjuNcnVOJz9kH+iZ0UwJgUpy8yqmfQDtnv9IktcWxSY5VCxEhQaBgyTHqVlNgsJeb0SR+KFa2oIDAlTPl5FkgvfOY3qk+4D6J9UtERKd29wenY6xP7ZYQPT8bW/+bbU9ofh27Wh/UJjgkUeIT9mrRkojK2mizSRm04cFJeYAJAte0gFZIKzgq4NH1i+c1cpCAS9ckh9rm8krxS/A8l6fByaEFgQeBNEfgHOFo6N4R+h7wAAAAASUVORK5CYII=","contentType":"image/png","encoding":"base64"},{"name":"a1cffc2c023e44dba0594e7cb276b353.files/image004.png","base64":"iVBORw0KGgoAAAANSUhEUgAAAAwAAAAqCAMAAACwXkr+AAAAAXNSR0IArs4c6QAAAEtQTFRFAAAAAAAAAAA6ADqQAGa2OgAAOjqQOma2OpC2OpDbZgAAZgA6ZpDbZrb/kDoAkDo6kNv/tpCQ25A627Zm2////7Zm/9uQ//+2///bDMHV2QAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAAAcUlEQVQoU82QzQ6AIAyDO/EPFUWE6fs/qaARl3gzHuyx65p9A/4lrojMdRI3CCW4asfTcwbzUtjoIRyxaDgNHsC13ya79hbbmCZrJ2sGwegK+yAmoW/e4Yi0pFD+7g2ZD4iEWUH5+YqlN8idb6561bIDtxQECOnh514AAAAASUVORK5CYII=","contentType":"image/png","encoding":"base64"}],"type":0,"subjectId":null,"options":4}
             * operateTime : 2019-01-15T15:29:01.062304
             * questionGuid : 0a6e1e12ed56499696f799cb829993a5
             * remark : C
             * answer : C
             */

            private QuestionDataBean questionData;
            private String operateTime;
            private String questionGuid;
            private String remark;
            private String answer;

            public QuestionDataBean getQuestionData() {
                return questionData;
            }

            public void setQuestionData(QuestionDataBean questionData) {
                this.questionData = questionData;
            }

            public String getOperateTime() {
                return operateTime;
            }

            public void setOperateTime(String operateTime) {
                this.operateTime = operateTime;
            }

            public String getQuestionGuid() {
                return questionGuid;
            }

            public void setQuestionGuid(String questionGuid) {
                this.questionGuid = questionGuid;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }

            public static class QuestionDataBean {
                /**
                 * guid : 0a6e1e12ed56499696f799cb829993a5
                 * questionType : 单选题
                 * grade : 0
                 * answer : ["C"]
                 * analysis : <p class="MsoNormal">由题可知焦点为<img width="35" height="42" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACMAAAAqCAMAAAAOCBKjAAAAAXNSR0IArs4c6QAAAGlQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjqQOma2OpC2OpDbZgAAZgA6ZgBmZpDbZrb/kDoAkDo6kNv/tmYAtpCQttv/tv//25A627Zm2////7Zm/7aQ/9uQ/9u2/9vb//+2///boV9NygAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAABB0lEQVQ4T+1SXROCIBAEU/vSSlOLJEr+/4/suBMDxN56kxlnHFh295ZlbF3/TeDdT/wyaeJaamsP1P4LD7DD+Y47+rrAYg5lhhiZAo3KjzWv5opEoGtz1D2SZrzjA4UhGn2Jioki4lztwBFKGVPDyfHVck43cBfJ2FBy145Ie5WjPbTSIkZdXB1dAwkdsLYAHHIKL8ahhMvAhZjMYny3OMcMw3FZZIAxerMVaFlrHs7xjL80+7QoJTM7vTnOThkGGF3buPCOFy+MGjys3Jh20K6w4QcDkBXqxiEUJexYLtsxGamP7RijrgJTJKixq6QGXzertVvj103lnPshGNLnj6ZHVNetxQQ+x7gR+7gao/AAAAAASUVORK5CYII=">,∴直线AB的方程为y=-<img width="45" height="42" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAC0AAAAqCAMAAAAQwSIQAAAAAXNSR0IArs4c6QAAAHJQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjo6OjqQOma2OpC2OpDbZgAAZgA6ZgBmZma2ZpDbZrb/kDoAkDo6kNv/tmYAtpCQttv/tv//25A627Zm29uQ2////7Zm/7aQ/9uQ/9u2/9vb//+2///bKvAmEQAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAABDklEQVRIS+1TyxKCMAxsFZ/4BkWtUqvw/79ok5SCI029eHDGHHhu0t1NIsQ/ft6BR9lK0INDRI+ZeISZdzIDadXmTH/qfawyoPSI0DphSpvxIpdZW7PO4SUUx8vgQGUVXjsC+lJUJtQScVNgzhIBUdUaddGNTghFtZJI25qBjAsWbXa+TmEJ1TmyElqOhHrvUucTlHXoaquTa1unj1gHbVWsnO9AFQMOpSdMRspAp1XBCCZ009KZSwskEGXnoEkjxMlB6k6RlJ54f3HqjmsV1yKyeIgzW3BTJRR54xnzE5s6qs0WxLZB49nNNgh+00wKYL9p/NBansfydRvvp5AlZiyltfj2yepGbf0DvurAExU+EcrH92ZjAAAAAElFTkSuQmCC">,与抛物线方程联立得<img width="95" height="62" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAF8AAAA+CAMAAAB6Ojc4AAAAAXNSR0IArs4c6QAAAJBQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjo6OjqQOma2OpC2OpDbZgAAZgA6ZgBmZjqQZmZmZma2ZpCQZpDbZrb/kDoAkDo6kDpmkGaQkJC2kNvbkNv/tmYAtpA6tpCQttv/tv/btv//25A625Bm27Zm29uQ2////7Zm/7aQ/9uQ/9u2/9vb//+2///b84/bIwAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAACkElEQVRYR+1Xa1fbMAyNoS2wroHBRsYAw0bG6nlz/v+/myQriZ2HH135wDnxpx7HlqV79bgtimUtCCwIvGcEzOXjMd3/u/etmfI2x7w6iXijz/wDcp1jXn8YuDe+bK5enM08dJqvCVgq12N16r4WC0WtAu7rzcdKANieE3XoxvC5pgpx9fx68ki+104A7u+Y98WQu8GF+raod7Cnz3tMsugNwoO4WDZdTrPsh4M1pUD4gQAHxiz74cP6S4uXRJTskshvU0HVKNHvTjPRVPaEEuuiHtdZv+X4UWN+/nyAm5xWGCWt8Wts39yo1a/O1ylXHPtc74CsuZk46r3W+l+YkrPa+2zdIlD6BNUbogQyQ8X7UItruA48+9ze9PmPe+t+AJ/OL3URpKqLk3KJjprL64TOwvmpt2ECODwqA7bvPjlfxra+IOU6AibPcn259s2naN8dFOa8FwX3TImE2liau7QuSldmV81J7ZY5BkHllbTC/XmLaTjsz1njKzZfKMfd+ZLVnuFueD7qLZLkzcfcB0IIwbfn/SDEvPkL7v35PseU3gix2v/2qUxL/CTyJw+9d/umjE2Vw7HBm9yf/89IqFf4pduW+NHe8/UbFMh0gjeVgKF7wBrpt8kxhl3wMCRJPziLSvyImUyjuNcnVOJz9kH+iZ0UwJgUpy8yqmfQDtnv9IktcWxSY5VCxEhQaBgyTHqVlNgsJeb0SR+KFa2oIDAlTPl5FkgvfOY3qk+4D6J9UtERKd29wenY6xP7ZYQPT8bW/+bbU9ofh27Wh/UJjgkUeIT9mrRkojK2mizSRm04cFJeYAJAte0gFZIKzgq4NH1i+c1cpCAS9ckh9rm8krxS/A8l6fByaEFgQeBNEfgHOFo6N4R+h7wAAAAASUVORK5CYII=">消去y,得4x<sup>2</sup>-12px+p<sup>2</sup>=0,设A(x<sub>1</sub>,y<sub>1</sub>),B(x<sub>2</sub>,y<sub>2</sub>),则x<sub>1</sub>+x<sub>2</sub>=3p.∵线段AB的中点的横坐标为3,∴<img width="12" height="42" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAwAAAAqCAMAAACwXkr+AAAAAXNSR0IArs4c6QAAAEtQTFRFAAAAAAAAAAA6ADqQAGa2OgAAOjqQOma2OpC2OpDbZgAAZgA6ZpDbZrb/kDoAkDo6kNv/tpCQ25A627Zm2////7Zm/9uQ//+2///bDMHV2QAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAAAcUlEQVQoU82QzQ6AIAyDO/EPFUWE6fs/qaARl3gzHuyx65p9A/4lrojMdRI3CCW4asfTcwbzUtjoIRyxaDgNHsC13ya79hbbmCZrJ2sGwegK+yAmoW/e4Yi0pFD+7g2ZD4iEWUH5+YqlN8idb6561bIDtxQECOnh514AAAAASUVORK5CYII=">=3,∴p=2,∴抛物线的准线方程为x=-1.</p>
                 * difficulty : 0
                 * score : 0.0
                 * content : <p class="MsoNormal">已知抛物线y<sup>2</sup>=2px(p&gt;0),过其s = "MsoNormal">A.x=1&nbsp;&nbsp; B.x=2&nbsp;&nbsp; C.x=-1&nbsp; D.x=-2</p>
                 * level : 0
                 * attachments : [{"name":"a1cffc2c023e44dba0594e7cb276b353.files/image001.png","base64":"iVBORw0KGgoAAAANSUhEUgAAACMAAAAqCAMAAAAOCBKjAAAAAXNSR0IArs4c6QAAAGlQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjqQOma2OpC2OpDbZgAAZgA6ZgBmZpDbZrb/kDoAkDo6kNv/tmYAtpCQttv/tv//25A627Zm2////7Zm/7aQ/9uQ/9u2/9vb//+2///boV9NygAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAABB0lEQVQ4T+1SXROCIBAEU/vSSlOLJEr+/4/suBMDxN56kxlnHFh295ZlbF3/TeDdT/wyaeJaamsP1P4LD7DD+Y47+rrAYg5lhhiZAo3KjzWv5opEoGtz1D2SZrzjA4UhGn2Jioki4lztwBFKGVPDyfHVck43cBfJ2FBy145Ie5WjPbTSIkZdXB1dAwkdsLYAHHIKL8ahhMvAhZjMYny3OMcMw3FZZIAxerMVaFlrHs7xjL80+7QoJTM7vTnOThkGGF3buPCOFy+MGjys3Jh20K6w4QcDkBXqxiEUJexYLtsxGamP7RijrgJTJKixq6QGXzertVvj103lnPshGNLnj6ZHVNetxQQ+x7gR+7gao/AAAAAASUVORK5CYII=","contentType":"image/png","encoding":"base64"},{"name":"a1cffc2c023e44dba0594e7cb276b353.files/image002.png","base64":"iVBORw0KGgoAAAANSUhEUgAAAC0AAAAqCAMAAAAQwSIQAAAAAXNSR0IArs4c6QAAAHJQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjo6OjqQOma2OpC2OpDbZgAAZgA6ZgBmZma2ZpDbZrb/kDoAkDo6kNv/tmYAtpCQttv/tv//25A627Zm29uQ2////7Zm/7aQ/9uQ/9u2/9vb//+2///bKvAmEQAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAABDklEQVRIS+1TyxKCMAxsFZ/4BkWtUqvw/79ok5SCI029eHDGHHhu0t1NIsQ/ft6BR9lK0INDRI+ZeISZdzIDadXmTH/qfawyoPSI0DphSpvxIpdZW7PO4SUUx8vgQGUVXjsC+lJUJtQScVNgzhIBUdUaddGNTghFtZJI25qBjAsWbXa+TmEJ1TmyElqOhHrvUucTlHXoaquTa1unj1gHbVWsnO9AFQMOpSdMRspAp1XBCCZ009KZSwskEGXnoEkjxMlB6k6RlJ54f3HqjmsV1yKyeIgzW3BTJRR54xnzE5s6qs0WxLZB49nNNgh+00wKYL9p/NBansfydRvvp5AlZiyltfj2yepGbf0DvurAExU+EcrH92ZjAAAAAElFTkSuQmCC","contentType":"image/png","encoding":"base64"},{"name":"a1cffc2c023e44dba0594e7cb276b353.files/image003.png","base64":"iVBORw0KGgoAAAANSUhEUgAAAF8AAAA+CAMAAAB6Ojc4AAAAAXNSR0IArs4c6QAAAJBQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjo6OjqQOma2OpC2OpDbZgAAZgA6ZgBmZjqQZmZmZma2ZpCQZpDbZrb/kDoAkDo6kDpmkGaQkJC2kNvbkNv/tmYAtpA6tpCQttv/tv/btv//25A625Bm27Zm29uQ2////7Zm/7aQ/9uQ/9u2/9vb//+2///b84/bIwAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAACkElEQVRYR+1Xa1fbMAyNoS2wroHBRsYAw0bG6nlz/v+/myQriZ2HH135wDnxpx7HlqV79bgtimUtCCwIvGcEzOXjMd3/u/etmfI2x7w6iXijz/wDcp1jXn8YuDe+bK5enM08dJqvCVgq12N16r4WC0WtAu7rzcdKANieE3XoxvC5pgpx9fx68ki+104A7u+Y98WQu8GF+raod7Cnz3tMsugNwoO4WDZdTrPsh4M1pUD4gQAHxiz74cP6S4uXRJTskshvU0HVKNHvTjPRVPaEEuuiHtdZv+X4UWN+/nyAm5xWGCWt8Wts39yo1a/O1ylXHPtc74CsuZk46r3W+l+YkrPa+2zdIlD6BNUbogQyQ8X7UItruA48+9ze9PmPe+t+AJ/OL3URpKqLk3KJjprL64TOwvmpt2ECODwqA7bvPjlfxra+IOU6AibPcn259s2naN8dFOa8FwX3TImE2liau7QuSldmV81J7ZY5BkHllbTC/XmLaTjsz1njKzZfKMfd+ZLVnuFueD7qLZLkzcfcB0IIwbfn/SDEvPkL7v35PseU3gix2v/2qUxL/CTyJw+9d/umjE2Vw7HBm9yf/89IqFf4pduW+NHe8/UbFMh0gjeVgKF7wBrpt8kxhl3wMCRJPziLSvyImUyjuNcnVOJz9kH+iZ0UwJgUpy8yqmfQDtnv9IktcWxSY5VCxEhQaBgyTHqVlNgsJeb0SR+KFa2oIDAlTPl5FkgvfOY3qk+4D6J9UtERKd29wenY6xP7ZYQPT8bW/+bbU9ofh27Wh/UJjgkUeIT9mrRkojK2mizSRm04cFJeYAJAte0gFZIKzgq4NH1i+c1cpCAS9ckh9rm8krxS/A8l6fByaEFgQeBNEfgHOFo6N4R+h7wAAAAASUVORK5CYII=","contentType":"image/png","encoding":"base64"},{"name":"a1cffc2c023e44dba0594e7cb276b353.files/image004.png","base64":"iVBORw0KGgoAAAANSUhEUgAAAAwAAAAqCAMAAACwXkr+AAAAAXNSR0IArs4c6QAAAEtQTFRFAAAAAAAAAAA6ADqQAGa2OgAAOjqQOma2OpC2OpDbZgAAZgA6ZpDbZrb/kDoAkDo6kNv/tpCQ25A627Zm2////7Zm/9uQ//+2///bDMHV2QAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAAAcUlEQVQoU82QzQ6AIAyDO/EPFUWE6fs/qaARl3gzHuyx65p9A/4lrojMdRI3CCW4asfTcwbzUtjoIRyxaDgNHsC13ya79hbbmCZrJ2sGwegK+yAmoW/e4Yi0pFD+7g2ZD4iEWUH5+YqlN8idb6561bIDtxQECOnh514AAAAASUVORK5CYII=","contentType":"image/png","encoding":"base64"}]
                 * type : 0
                 * subjectId : null
                 * options : 4
                 */

                private String guid;
                private String questionType;
                private int grade;
                private String analysis;
                private int difficulty;
                private double score;
                private String content;
                private int level;
                private int type;
                private Object subjectId;
                private int options;
                private List<String> answer;
                private List<AttachmentsBean> attachments;

                public String getGuid() {
                    return guid;
                }

                public void setGuid(String guid) {
                    this.guid = guid;
                }

                public String getQuestionType() {
                    return questionType;
                }

                public void setQuestionType(String questionType) {
                    this.questionType = questionType;
                }

                public int getGrade() {
                    return grade;
                }

                public void setGrade(int grade) {
                    this.grade = grade;
                }

                public String getAnalysis() {
                    return analysis;
                }

                public void setAnalysis(String analysis) {
                    this.analysis = analysis;
                }

                public int getDifficulty() {
                    return difficulty;
                }

                public void setDifficulty(int difficulty) {
                    this.difficulty = difficulty;
                }

                public double getScore() {
                    return score;
                }

                public void setScore(double score) {
                    this.score = score;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public Object getSubjectId() {
                    return subjectId;
                }

                public void setSubjectId(Object subjectId) {
                    this.subjectId = subjectId;
                }

                public int getOptions() {
                    return options;
                }

                public void setOptions(int options) {
                    this.options = options;
                }

                public List<String> getAnswer() {
                    return answer;
                }

                public void setAnswer(List<String> answer) {
                    this.answer = answer;
                }

                public List<AttachmentsBean> getAttachments() {
                    return attachments;
                }

                public void setAttachments(List<AttachmentsBean> attachments) {
                    this.attachments = attachments;
                }

                public static class AttachmentsBean {
                    /**
                     * name : a1cffc2c023e44dba0594e7cb276b353.files/image001.png
                     * base64 : iVBORw0KGgoAAAANSUhEUgAAACMAAAAqCAMAAAAOCBKjAAAAAXNSR0IArs4c6QAAAGlQTFRFAAAAAAAAAAA6AABmADqQAGa2OgAAOgA6OgBmOjqQOma2OpC2OpDbZgAAZgA6ZgBmZpDbZrb/kDoAkDo6kNv/tmYAtpCQttv/tv//25A627Zm2////7Zm/7aQ/9uQ/9u2/9vb//+2///boV9NygAAAAF0Uk5TAEDm2GYAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAAZdEVYdFNvZnR3YXJlAE1pY3Jvc29mdCBPZmZpY2V/7TVxAAABB0lEQVQ4T+1SXROCIBAEU/vSSlOLJEr+/4/suBMDxN56kxlnHFh295ZlbF3/TeDdT/wyaeJaamsP1P4LD7DD+Y47+rrAYg5lhhiZAo3KjzWv5opEoGtz1D2SZrzjA4UhGn2Jioki4lztwBFKGVPDyfHVck43cBfJ2FBy145Ie5WjPbTSIkZdXB1dAwkdsLYAHHIKL8ahhMvAhZjMYny3OMcMw3FZZIAxerMVaFlrHs7xjL80+7QoJTM7vTnOThkGGF3buPCOFy+MGjys3Jh20K6w4QcDkBXqxiEUJexYLtsxGamP7RijrgJTJKixq6QGXzertVvj103lnPshGNLnj6ZHVNetxQQ+x7gR+7gao/AAAAAASUVORK5CYII=
                     * contentType : image/png
                     * encoding : base64
                     */

                    private String name;
                    private String base64;
                    private String contentType;
                    private String encoding;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getBase64() {
                        return base64;
                    }

                    public void setBase64(String base64) {
                        this.base64 = base64;
                    }

                    public String getContentType() {
                        return contentType;
                    }

                    public void setContentType(String contentType) {
                        this.contentType = contentType;
                    }

                    public String getEncoding() {
                        return encoding;
                    }

                    public void setEncoding(String encoding) {
                        this.encoding = encoding;
                    }
                }
            }
        }
    }
}
