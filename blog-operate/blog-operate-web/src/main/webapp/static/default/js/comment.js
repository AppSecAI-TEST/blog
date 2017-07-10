function replyTo(postId, parentId) {
    var quote = '在这里输入你的评论';
    var parentType = '1'; // 文章
    var commentArea = $('#commentArea');
    if (postId != parentId) {
        var item = $('#' + parentId);
        var author = item.find('.author h6').text();
        quote = item.find("div[class='body']").clone().children().remove().end().text().trim();
        parentType = '2'; // 评论
        parentId = parentId.substring(1);
        if (quote.length > 200) {
            quote = quote.substring(0, 200) + "...";
        }
        quote = author + ' said:\n' + quote.replace(/\s*\n\s*/gi, "\n");
    }
    commentArea.find("input[name='postId']").val(postId);
    commentArea.find("input[name='parentId']").val(parentId);
    commentArea.find("input[name='parentType']").val(parentType);
    commentArea.find("textarea[name='content']").attr('placeholder', quote);
}

function applyComment() {
    $('#commentArea').find('input, textarea').each(function () {
        $(this).val(jQuery.trim($(this).val()));
    });
    var commentArea = $('#commentArea');
    var username = commentArea.find("input[name='username']").val().trim();
    var email = commentArea.find("input[name='email']").val().trim();
    var url = commentArea.find("input[name='url']").val().trim();
    if (username != '') {
        Cookies.set("username", username);
    }
    if (email != '') {
        Cookies.set("email", email);
    }
    if (url != '') {
        Cookies.set("url", url);
    }
    return true;
}


$(document).ready(function () {
    var commentArea = $('#commentArea');
    var username = Cookies.get("username");
    var email = Cookies.get("email");
    var url = Cookies.get("url");
    if (undefined != username) {
        commentArea.find("input[name='username']").val(username);
    }
    if (undefined != email) {
        commentArea.find("input[name='email']").val(email);
    }
    if (undefined != url) {
        commentArea.find("input[name='url']").val(url);
    }
});

