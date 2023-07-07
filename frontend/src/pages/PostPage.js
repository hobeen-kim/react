import PostList from "../component/post/PostList";
import CreatePost from "../component/post/CreatePost";
import { Fragment } from "react";
import React from "react";
import { Routes, Route } from "react-router-dom";
import {PostContextProvider} from "../store/post/post-context";

const PostPage = () => {
    
    return (
        <Fragment>
            <PostContextProvider>
            <Routes>
            <Route path="/" element={<PostList />} />
            <Route path="/create" element={<CreatePost />} />
          </Routes>
            </PostContextProvider>
        </Fragment>
      );
};


export default PostPage;
