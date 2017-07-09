function replyTo(contentId, parentId) {
    var quote = '在这里输入你的评论';
    var parentType = '1'; // 文章
    var newComment = $('#newComment');
    if (contentId != parentId) {
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
    newComment.find("input[name='contentId']").val(contentId);
    newComment.find("input[name='parentId']").val(parentId);
    newComment.find("input[name='parentType']").val(parentType);
    newComment.find("textarea[name='comment']").attr('placeholder', quote);
}

function applyComment() {
    $('#newComment').find('input, textarea').each(function () {
        $(this).val(jQuery.trim($(this).val()));
    });
    var newComment = $('#newComment');
    var username = newComment.find("input[name='username']").val().trim();
    var email = newComment.find("input[name='email']").val().trim();
    var url = newComment.find("input[name='url']").val().trim();
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
    var newComment = $('#newComment');
    var username = Cookies.get("username");
    var email = Cookies.get("email");
    var url = Cookies.get("url");
    if (undefined != username) {
        newComment.find("input[name='username']").val(username);
    }
    if (undefined != email) {
        newComment.find("input[name='email']").val(email);
    }
    if (undefined != url) {
        newComment.find("input[name='url']").val(url);
    }
});

