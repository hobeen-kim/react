import {GET, POST, PUT, DELETE, PATCH} from "../auth/fetch-action";

const createTokenHeader = (token) => {
    return {
      headers: {
        'Authorization': 'Bearer ' + token
      }
    }
  }

export const getPostsActionHandler = (page, size) => {
    const URL = '/posts?page=' + page +'&size=' + size + '&sort=id,desc';
    const response = GET(URL, null);
    return response;
}

export const getPostActionHandler = (postId) => {
    const URL = '/posts/' + postId;
    const response = GET(URL, null);
    return response;
}

export const createPostActionHandler = (token, title, content) => {
    const URL = '/posts';
    const postObject = {"title":title, "content":content};
    const response = POST(URL, postObject, createTokenHeader(token));
    return response;
}

export const updatePostActionHandler = (token, postId, title, content) => {
    const URL = '/posts/' + postId;
    const postObject = {"title":title, "content":content};
    const response = PATCH(URL, postObject, createTokenHeader(token));
    return response;
}

export const deletePostActionHandler = (token, postId) => {
    const URL = '/posts/' + postId;
    const response = DELETE(URL, createTokenHeader(token));
    return response;
}

export const getCommentsActionHandler = (postId, page, size) => {
    const URL = '/posts/'+ postId +'comments/?page=' + page +'&size=' + size + '&sort=id,desc';
    const response = GET(URL, null);
    return response;
}

export const createCommentActionHandler = (token, postId, content) => {
    const URL = '/posts' + postId + '/comments';
    const commentObject = {"content":content};
    const response = POST(URL, commentObject, createTokenHeader(token));
    return response;
}

export const updateCommentActionHandler = (token, postId, commentId, content) => {
    const URL = '/posts/' + postId + '/comments/' + commentId;
    const commentObject = {"content":content};
    const response = PATCH(URL, commentObject, createTokenHeader(token));
    return response;
}

export const deleteCommentActionHandler = (token, postId, commentId) => {
    const URL = '/posts/' + postId + '/comments/' + commentId;
    const response = DELETE(URL, createTokenHeader(token));
    return response;
}



