import React, { createContext, useState } from 'react';
import * as authAction from '../auth/auth-action'; 
import { getPostsActionHandler, createPostActionHandler, updatePostActionHandler, deletePostActionHandler,
     getPostActionHandler, getCommentsActionHandler, createCommentActionHandler, updateCommentActionHandler, 
     deleteCommentActionHandler } from './post-action';

export const PostContext = createContext({
    posts: [],
    comments: [],
    selectedPost: null,
    error: null,
    getPosts: async (page, size) => {},
    getPost: async (postId) => {},
    createPost: async (title, content) => {},
    updatePost: async (postId, title, content) => {},
    deletePost: async (postId) => {},
    getComments: async (postId, page, size) => {},
    createComment: async (postId, content) => {},
    updateComment: async (postId, commentId, content) => {},
    deleteComment: async (postId, commentId) => {}
});

export const PostContextProvider = ({ children }) => {

    const tokenData = authAction.retrieveStoredToken();
    let initialToken;
    if (tokenData) {
      initialToken = tokenData.accessToken;
    }
    const [token, setToken] = useState(initialToken);
    const [posts, setPosts] = useState([]);
    const [comments, setComments] = useState([]);
    const [selectedPost, setSelectedPost] = useState(null);
    const [error, setError] = useState(null);
        
    const getPosts = async (page, size) => {
        setError(null);
        try {
            const response = await getPostsActionHandler(page, size);
            setPosts(response.data);
        } catch (error) {
            setError(error.message || 'Something went wrong!');
        }
    }

    
    const getPost = async (postId) => {
        setError(null);
        try {
            const response = await getPostActionHandler(postId);
            setSelectedPost(response.data);
        } catch (error) {
            setError(error.message || 'Something went wrong!');
        }
    }

    const createPost = async (title, content) => {
        setError(null);
        try {
            const response = await createPostActionHandler(token, title, content);
        } catch (error) {
            setError(error.message || 'Something went wrong!');
        }
    }

    const updatePost = async (postId, title, content) => {
        setError(null);
        try {
            const response = await updatePostActionHandler(token, postId, title, content);
        } catch (error) {
            setError(error.message || 'Something went wrong!');
        }
    }

    const deletePost = async (postId) => {
        setError(null);
        try {
            const response = await deletePostActionHandler(token, postId);
        } catch (error) {
            setError(error.message || 'Something went wrong!');
        }
    }

    const getComments = async (postId, page, size) => {
        setError(null);
        try {
            const response = await getCommentsActionHandler(postId, page, size);
            setComments(response.data);
        } catch (error) {
            setError(error.message || 'Something went wrong!');
        }
    }

    const createComment = async (postId, content) => {
        setError(null);
        try {
            const response = await createCommentActionHandler(token, postId, content);
        } catch (error) {
            setError(error.message || 'Something went wrong!');
        }
    }

    const updateComment = async (postId, title, content) => {
        setError(null);
        try {
            const response = await updateCommentActionHandler(token, postId, title, content);
        } catch (error) {
            setError(error.message || 'Something went wrong!');
        }
    }

    const deleteComment = async (postId, commentId) => {
        setError(null);
        try {
            const response = await deleteCommentActionHandler(token, postId, commentId);
        } catch (error) {
            setError(error.message || 'Something went wrong!');
        }
    }


    const contextValue = {
        posts,
        comments,
        selectedPost,
        error,
        getPosts,
        getPost,
        createPost,
        updatePost,
        deletePost,
        getComments,
        createComment,
        updateComment,
        deleteComment
    }

    return (
        <PostContext.Provider value={contextValue}>
            {children}
        </PostContext.Provider>
    );
}

