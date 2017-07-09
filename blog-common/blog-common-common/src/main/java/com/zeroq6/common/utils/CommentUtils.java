package com.zeroq6.common.utils;

import com.zeroq6.blog.common.domain.CommentDomain;
import com.zeroq6.blog.common.enums.field.EmCommentParentType;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yuuki asuna on 2017/5/23.
 */
public class CommentUtils {

    public static String getAvatar(String email) {
        String url = "https://www.gravatar.com/avatar/" ;
        if (StringUtils.isNotBlank(email)) {
            url = url + DigestUtils.md5Hex(email) + "?s=125" ;
        } else {
            url = url + "00000000000000000000000000000000?s=125" ;
        }
        return url;
    }

    public static String transferCommentToHtml(CommentDomain commentDomain) {
        String comment = commentDomain.getContent();
        if (StringUtils.isBlank(comment)) {
            return comment;
        }
        if(commentDomain.getParentType() != EmCommentParentType.PINGLUN.value()){
            return comment.replace("\n", "<br />");
        }
        String html = comment.replace("[quote]", "<div class='quote'><a href='" + "#c" + commentDomain.getParentId() + "'>").replaceFirst(":", ":</a>").replace("[/quote]", "</div>").replace("\n", "<br />");
        return html;
    }

    public static String getDateBeforeNow(Date date) {
        if (null == date) {
            date = new Date(5000L);
        }
        String result = "a moment ago";
        //
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String[] dateNow = sdf.format(new Date()).split("-");
        String[] dateCompareTo = sdf.format(date).split("-");
        String[] des = new String[]{"%s years ago", "%s months ago", "%s days ago", "%s hours ago", "%s minutes ago"};
        for (int i = 0; i < dateNow.length; i++) {
            if (i >= des.length) {
                break;
            }
            int diff = Integer.valueOf(dateNow[i]) - Integer.valueOf(dateCompareTo[i]);
            if (diff != 0) {
                result = String.format(des[i], diff + "");
                break;
            }
        }
        return result;

    }
}
